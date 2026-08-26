package com.blasalda.reservaciones.service;

import com.blasalda.commons.clients.HabitacionClient;
import com.blasalda.commons.clients.HuespedClient;
import com.blasalda.commons.dto.habitacion.HabitacionResponse;
import com.blasalda.commons.dto.huespedes.HuespedResponse;
import com.blasalda.commons.enums.EstadoRegistro;
import com.blasalda.commons.exceptions.RecursoNoEncontradoException;
import com.blasalda.reservaciones.dto.ReservacionRequest;
import com.blasalda.reservaciones.dto.ReservacionResponse;
import com.blasalda.reservaciones.entity.Reservacion;
import com.blasalda.reservaciones.enums.EstadoReserva;
import com.blasalda.reservaciones.mapper.ReservacionMapper;
import com.blasalda.reservaciones.repository.ReservacionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ReservacionServiceImpl implements ReservacionService {

    private final ReservacionRepository reservacionRepository;

    private final ReservacionMapper reservacionMapper;

    private final HuespedClient huespedClient;

    private final HabitacionClient habitacionClient;

    @Override
    public List<ReservacionResponse> listar() {
        return reservacionRepository.findByEstadoRegistro(EstadoRegistro.ACTIVO)
                .stream().map(reservacion -> reservacionMapper.entidadAResponse(
                        reservacion,
                        obtenerHuespedSinEstado(reservacion.getIdHuesped()),
                        obtenerHabitacionSinEstado(reservacion.getIdHabitacion())
                ))
                .toList();
    }

    @Override
    public ReservacionResponse obtenerReservacionSinEstado(Long id) {
        Reservacion reservacion = obtenerReservacionOExcepcion(id);

        return reservacionMapper.entidadAResponse(
                reservacion,
                obtenerHuespedSinEstado(reservacion.getIdHuesped()),
                obtenerHabitacionSinEstado(reservacion.getIdHabitacion())
        );
    }

    @Override
    public ReservacionResponse obtenerPorId(Long id) {
        Reservacion reservacion = obtenerReservacionActivaOExcepcion(id);

        return reservacionMapper.entidadAResponse(
                reservacion,
                obtenerHuespedSinEstado(reservacion.getIdHuesped()),
                obtenerHabitacionSinEstado(reservacion.getIdHabitacion())
        );
    }

    @Override
    public ReservacionResponse registrar(ReservacionRequest request) {

        log.info("Registrando nueva reservación");

        HuespedResponse huespedResponse = huespedClient.obtenerHuespedActivoPorId(request.idHuesped());

        HabitacionResponse habitacionResponse = habitacionClient.obtenerHabitacionActivaPorId(request.idHabitacion());

        Reservacion reservacion = reservacionMapper.requestAEntidad(request);

        reservacionRepository.save(reservacion);

        return reservacionMapper.entidadAResponse(reservacion, huespedResponse, habitacionResponse);
    }

    @Override
    public ReservacionResponse actualizar(ReservacionRequest request, Long id) {

        Reservacion reservacion = obtenerReservacionActivaOExcepcion(id);

        HuespedResponse huesped = huespedClient.obtenerHuespedActivoPorId(request.idHuesped());

        HabitacionResponse habitacion = habitacionClient.obtenerHabitacionActivaPorId(request.idHabitacion());

        reservacion.actualizar(
                huesped.id(),
                habitacion.id(),
                request.fechaEntrada(),
                request.fechaSalida()
        );

        reservacionRepository.save(reservacion);
        return reservacionMapper.entidadAResponse(reservacion, huesped, habitacion);
    }

    @Override
    public void cambiarEstadoReservacion(Long id, Long idEstadoReserva) {

        log.info("Cambiando estado Reservación");

        Reservacion reservacion = obtenerReservacionActivaOExcepcion(id);

        reservacion.cambiarEstado(EstadoReserva.ObtenerEstadoReservaPorCodigo(idEstadoReserva));

        reservacionRepository.save(reservacion);

    }

    @Override
    public void eliminar(Long id) {

        Reservacion reservacion = obtenerReservacionActivaOExcepcion(id);

        reservacion.eliminar();

        reservacionRepository.save(reservacion);

    }

    private Reservacion obtenerReservacionOExcepcion(Long id) {
        log.info("Obteniendo Reservación por ID: {}", id);

        return reservacionRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Paciente no encontrado"));
    }

    private Reservacion obtenerReservacionActivaOExcepcion(Long id) {
        log.info("Obteniendo Reservación activa con ID: {}", id);

        return reservacionRepository.findByIdAndEstadoRegistro(id, EstadoRegistro.ACTIVO)
                .orElseThrow(() -> new RecursoNoEncontradoException("Reservación no encontrada"));
    }

    private HuespedResponse obtenerHuespedSinEstado(Long id) {
        log.info("Obteniendo Huésped por ID: {}", id);

        return huespedClient.obtenerHuespedPorId(id);
    }

    private HabitacionResponse obtenerHabitacionSinEstado(Long id) {
        log.info("Obteniendo Habitación por ID: {}", id);

        return habitacionClient.obtenerHabitacionPorId(id);
    }
}
