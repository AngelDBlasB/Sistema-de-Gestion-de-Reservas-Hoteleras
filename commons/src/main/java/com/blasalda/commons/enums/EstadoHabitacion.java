package com.blasalda.commons.enums;

import com.blasalda.commons.exceptions.RecursoNoEncontradoException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
@Getter
public enum EstadoHabitacion {
    DISPONIBLE(1L,"Disponible para usar"),
    OCUPADA(2L,"Ocupada sin disponibilidad"),
    LIMPIEZA(3L,"En estado de limpieza"),
    MANTENIMIENTO(4L, "En estado de mantenimiento");

    private final Long codigo;
    private final String descripcion;

    public static EstadoHabitacion obtenerEstadoPorCodigo(Long codigo) {

        for (EstadoHabitacion e : values()) {
            if (Objects.equals(e.codigo, codigo))
                return e;
        }

        throw new RecursoNoEncontradoException("Codigo de estado no valido " + codigo);
    }

}
