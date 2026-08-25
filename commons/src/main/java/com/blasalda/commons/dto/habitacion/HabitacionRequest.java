package com.blasalda.commons.dto.habitacion;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record HabitacionRequest(

        @NotNull(message = "El número de habitación es requerido")
        @Positive(message = "El número de habitación debe ser mayor a 0")
        Integer numeroHabitacion,

        @NotNull(message = "El tipo de habitación es requerido")
        Long tipo,

        @NotNull(message = "El precio es requerido")
        @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
        BigDecimal precio,

        @NotNull(message = "La capacidad es requerida")
        @Min(value = 1, message = "La capacidad mínima es de 1 persona")
        Short capacidad

) {
}
