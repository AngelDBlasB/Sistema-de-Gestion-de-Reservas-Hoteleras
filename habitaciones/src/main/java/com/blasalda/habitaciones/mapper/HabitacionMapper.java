package com.blasalda.habitaciones.mapper;

import com.blasalda.commons.dto.habitacion.HabitacionRequest;
import com.blasalda.commons.dto.habitacion.HabitacionResponse;
import com.blasalda.commons.enums.EstadoHabitacion;
import com.blasalda.commons.enums.EstadoRegistro;
import com.blasalda.commons.mapper.CommonMapper;
import com.blasalda.habitaciones.entity.Habitacion;
import org.springframework.stereotype.Component;

@Component
public class HabitacionMapper implements CommonMapper<HabitacionRequest, HabitacionResponse, Habitacion> {

    @Override
    public Habitacion requestAEntidad(HabitacionRequest request) {
        if (request == null) return null;

        return Habitacion.builder()
                .numeroHabitacion(request.numeroHabitacion())
                .precio(request.precio())
                .capacidad(request.capacidad())
                .estadoHabitacion(EstadoHabitacion.DISPONIBLE)
                .estadoRegistro(EstadoRegistro.ACTIVO)
                .build();
    }

    @Override
    public HabitacionResponse entidadAResponse(Habitacion entidad) {
        if (entidad == null) return null;

        return new HabitacionResponse(
                entidad.getId(),
                entidad.getEstadoHabitacion().getCodigo(),
                entidad.getEstadoHabitacion().getDescripcion(),
                entidad.getNumeroHabitacion(),
                entidad.getTipo().getDescripcion(),
                entidad.getTipo().getCodigo(),
                entidad.getPrecio(),
                entidad.getCapacidad()
        );
    }
}
