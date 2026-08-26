package com.blasalda.commons.dto.habitacion;

import java.math.BigDecimal;

public record DatosHabitacion(
        Integer numeroHabitacion,
        BigDecimal precio,
        Short capacidad,
        String estadoHabitacion
) {
}
