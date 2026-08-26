package com.blasalda.reservaciones.mapper;

import com.blasalda.commons.dto.habitacion.DatosHabitacion;
import com.blasalda.commons.dto.habitacion.HabitacionResponse;
import com.blasalda.commons.dto.huespedes.DatosHuesped;
import com.blasalda.commons.dto.huespedes.HuespedResponse;
import com.blasalda.commons.mapper.CommonMapper;
import com.blasalda.reservaciones.dto.ReservacionRequest;
import com.blasalda.reservaciones.dto.ReservacionResponse;
import com.blasalda.reservaciones.entity.Reservacion;
import com.blasalda.reservaciones.enums.EstadoReserva;
import org.springframework.stereotype.Component;

@Component
public class ReservacionMapper implements CommonMapper<ReservacionRequest, ReservacionResponse, Reservacion> {


    @Override
    public Reservacion requestAEntidad(ReservacionRequest request) {
        if (request == null) return null;
        return Reservacion.crear(
                request.idHuesped(),
                request.idHabitacion(),
                EstadoReserva.CONFIRMADA.getCodigo(),
                request.fechaEntrada(),
                request.fechaSalida()
        );
    }

    @Override
    public ReservacionResponse entidadAResponse(Reservacion entidad) {
        if (entidad == null) return null;
        return new ReservacionResponse(
                entidad.getId(),
                null,
                null,
                entidad.getFechaEntrada(),
                entidad.getFechaSalida(),
                entidad.getEstadoReserva().getDescripcion()
        );
    }

    public ReservacionResponse entidadAResponse(Reservacion entidad, HuespedResponse datosHuesped, HabitacionResponse datosHabitacion) {
        if (entidad == null) return null;

        return new ReservacionResponse(
                entidad.getId(),
                huespedResponseADatosHuesped(datosHuesped),
                habitacionResponseADatosHabitacion(datosHabitacion),
                entidad.getFechaEntrada(),
                entidad.getFechaSalida(),
                entidad.getEstadoReserva().getDescripcion()
        );
    }

    private DatosHuesped huespedResponseADatosHuesped(HuespedResponse datosHuesped) {
        if (datosHuesped == null) return null;
        return new DatosHuesped(
                datosHuesped.nombre(),
                datosHuesped.edad(),
                datosHuesped.telefono(),
                datosHuesped.email(),
                datosHuesped.tipoDocumento(),
                datosHuesped.nacionalidad()
        );
    }

    private DatosHabitacion habitacionResponseADatosHabitacion(HabitacionResponse datosHabitacion) {
        if (datosHabitacion == null) return null;
        return new DatosHabitacion(
                datosHabitacion.numeroHabitacion(),
                datosHabitacion.precio(),
                datosHabitacion.capacidad(),
                datosHabitacion.estadoHabitacion()
        );
    }


}
