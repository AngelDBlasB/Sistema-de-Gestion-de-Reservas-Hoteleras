package com.blasalda.reservaciones.dto;

import com.blasalda.commons.dto.habitacion.DatosHabitacion;
import com.blasalda.commons.dto.huespedes.DatosHuesped;

import java.time.LocalDate;

public record ReservacionResponse(
        Long id,
        DatosHuesped datosHuesped,
        DatosHabitacion datosHabitacion,
        LocalDate fechaEntrada,
        LocalDate fechaSalida,
        String estadoReserva
) {
}
