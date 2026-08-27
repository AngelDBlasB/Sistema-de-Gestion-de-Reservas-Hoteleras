package com.blasalda.habitaciones.service;

import com.blasalda.commons.dto.habitacion.HabitacionRequest;
import com.blasalda.commons.dto.habitacion.HabitacionResponse;
import com.blasalda.commons.enums.EstadoHabitacion;
import com.blasalda.commons.enums.EstadoRegistro;
import com.blasalda.habitaciones.enums.TipoHabitacion;
import com.blasalda.commons.exceptions.RecursoNoEncontradoException;
import com.blasalda.habitaciones.entity.Habitacion;
import com.blasalda.habitaciones.mapper.HabitacionMapper;
import com.blasalda.habitaciones.repository.HabitacionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class HabitacionServiceImpl implements HabitacionService {

    private final HabitacionRepository habitacionRepository;
    private final HabitacionMapper habitacionMapper;

    @Override
    public List<HabitacionResponse> listar() {

        log.info("Listando todos las habitaciones activas");

        return habitacionRepository.findByEstadoRegistro(EstadoRegistro.ACTIVO).stream()
                .map(habitacionMapper :: entidadAResponse).toList();
    }

    @Override
    public HabitacionResponse obtenerPorId(Long id) {
        return habitacionMapper.entidadAResponse(obtenerHabitacionActivaOExcepciom(id));
    }

    @Override
    public HabitacionResponse obtenerHabitacionPorIdSinEstado(Long id) {
        log.info("Buscando habitacion sin estado con id{}",id);

        return habitacionMapper.entidadAResponse(habitacionRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Habitacion sin estado no encontrado con id: " + id)));
    }

    @Override
    public HabitacionResponse registrar(HabitacionRequest request) {

        log.info("Registrando nueva habitación: {}", request.numeroHabitacion());

        validarNumeroHabitacion(request);

        Habitacion habitacion = habitacionMapper.requestAEntidad(request);

        habitacion.actualizarTipoHabitacion(
                TipoHabitacion.obtenerTipoHabitacionPorCodigo(request.tipo()));

        habitacionRepository.save(habitacion);

        log.info("Nueva habitación registrada: {}", habitacion.getNumeroHabitacion());

        return habitacionMapper.entidadAResponse(habitacion);
    }

    @Override
    public HabitacionResponse actualizar(HabitacionRequest request, Long id) {
        Habitacion habitacion = obtenerHabitacionActivaOExcepciom(id);

        log.info("Actualizando medico con id: {}", id);

        //validarSinCitasConfirmadasOEnCurso(id);

        validarCambioNumeroHabitacion(request,id);

        habitacion.actualizar(
                request.numeroHabitacion(),
                TipoHabitacion.obtenerTipoHabitacionPorCodigo(request.tipo()),
                request.precio(),
                request.capacidad());


        log.info("Habitacion actualizada correctamente");

        return habitacionMapper.entidadAResponse(habitacion);
    }

    @Override
    public void eliminar(Long id) {

        Habitacion habitacion = obtenerHabitacionActivaOExcepciom(id);

        log.info("Eliminando habitacion con id: {}", id);

        validarEstadoHabitacionOcupada(id);

        habitacion.eliminar();

        log.info("Habitacion eliminado correctamente");

    }

    @Override
    public void actualizarEstadoHabitacion(Long idHabitacion, Long idEstadoHabitacion) {

        Habitacion habitacion = obtenerHabitacionActivaOExcepciom(idHabitacion);

        log.info("Actualizando estado de la habitacion con id: {}", idHabitacion);

        EstadoHabitacion nuevoEstado = EstadoHabitacion.
                obtenerEstadoPorCodigo(idEstadoHabitacion);

        habitacion.actualizarEstado(nuevoEstado);

        log.info("Estado de la habitacion actualizada correctamente a: {}", nuevoEstado);


    }

    private Habitacion obtenerHabitacionActivaOExcepciom(Long id){
        log.info("Buscando habitacion activa con id {} ", id);

        return habitacionRepository.findByIdAndEstadoRegistro(id, EstadoRegistro.ACTIVO)
                .orElseThrow(()-> new RecursoNoEncontradoException(
                        "Medico activo no encontrado con id: " + id
                ));
    }

    private void validarNumeroHabitacion (HabitacionRequest request){

        log.info("Validando el número de la habitacion");

        if (habitacionRepository.existsByNumeroHabitacionAndEstadoRegistro(
                request.numeroHabitacion(),EstadoRegistro.ACTIVO))
            throw new IllegalArgumentException("Ya existe una habitacion activa con ese número asignado");

    }

    private void validarCambioNumeroHabitacion(HabitacionRequest request, Long id){

        log.info("Validando el cambio del número de la habitación");

        if (habitacionRepository.existsByNumeroHabitacionAndEstadoRegistroAndIdNot(
                request.numeroHabitacion(),EstadoRegistro.ACTIVO,id))
            throw new IllegalArgumentException("Ya existe una habitación activa con el número: "
                    + request.numeroHabitacion());

    }

    private void validarEstadoHabitacionOcupada(Long id){

        if(habitacionRepository.existsByIdAndEstadoHabitacion(id, EstadoHabitacion.OCUPADA))
            throw new IllegalArgumentException("No se puede eliminar la habitacion en estado: "
            + EstadoHabitacion.OCUPADA.name());

    }
}
