package com.blasalda.commons.dto.habitacion;

import com.blasalda.commons.enums.EstadoHabitacion;
import com.blasalda.commons.enums.TipoHabitacion;

import java.math.BigDecimal;

public record HabitacionResponse(

        Long id,
        String estadoHabitacion,
        Integer numeroHabitacion,
        String tipo,
        Long idTipoHabitacion,
        BigDecimal precio,
        Short capacidad

) {
}
