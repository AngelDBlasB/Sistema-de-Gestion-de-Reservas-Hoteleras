package com.blasalda.reservaciones.enums;

import com.blasalda.commons.exceptions.RecursoNoEncontradoException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EstadoReserva {
    CONFIRMADA(1L, "Reservación confirmada"),
    EN_CURSO(2L, "Reservación en curso"),
    FINALIZADA(3L, "Reservación finalizada"),
    CANCELADA(4L, "Reservación cancelada");

    private final Long codigo;
    private final String descripcion;

    public static EstadoReserva ObtenerEstadoReservaPorCodigo(Long codigo) {
        for (EstadoReserva estadoReserva : values()) {
            if (codigo.equals(estadoReserva.codigo)) {
                return estadoReserva;
            }
        }
        throw new RecursoNoEncontradoException("Estado de reserva no encontrado");
    }
}
