package com.blasalda.commons.dto.habitacion;

import java.math.BigDecimal;

public record HabitacionResponse(

        Long id,
        Long idEstadoHabitacion,
        String estadoHabitacion,
        Integer numeroHabitacion,
        String tipo,
        Long idTipoHabitacion,
        BigDecimal precio,
        Short capacidad

) {
}
