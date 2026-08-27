package com.blasalda.habitaciones.enums;

import com.blasalda.commons.exceptions.RecursoNoEncontradoException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
@Getter
public enum TipoHabitacion {
    INDIVIDUAL(1L,"INDIVIDUAL"),
    DOBLE(2L,"DOBLE"),
    SUITE(3L,"SUITE"),
    DELUXE(4L,"DELUXE"),
    PRESIDENCIAL(5L,"PRESIDENCIAL");

    private final Long codigo;
    private final String descripcion;

    public static TipoHabitacion obtenerTipoHabitacionPorCodigo(Long codigo) {

        for (TipoHabitacion t : values()) {
            if (Objects.equals(t.codigo, codigo))
                return t;
        }

        throw new RecursoNoEncontradoException("Codigo de tipo de habitación no valido " + codigo);
    }

}
