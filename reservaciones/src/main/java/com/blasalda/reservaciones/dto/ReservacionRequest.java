package com.blasalda.reservaciones.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record ReservacionRequest(
        @NotNull(message = "El id del huésped es requerido")
        @Positive(message = "El id del huésped debe ser positiva")
        Long idHuesped,
        @NotNull(message = "El id de la habitación es requerido")
        @Positive(message = "El id de la habitación debe ser positiva")
        Long idHabitacion,
        @NotNull(message = "La fecha de entrada de la reservación es requerida")
        @FutureOrPresent(message = "La fecha entrada debe ser futura")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate fechaEntrada,
        @NotNull(message = "La fecha de salida de la reservación es requerida")
        @FutureOrPresent(message = "La fecha de salida debe ser futura")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate fechaSalida
) {
}
