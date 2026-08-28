package com.blasalda.reservaciones.enums;

import com.blasalda.commons.exceptions.RecursoNoEncontradoException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@AllArgsConstructor
@Getter
public enum EstadoReserva {
    CONFIRMADA(1L, "Reservación confirmada") {
        @Override
        public Set<EstadoReserva> puedeCambiar() {
            return Set.of(EN_CURSO, CANCELADA);
        }
    },
    EN_CURSO(2L, "Reservación en curso") {
        @Override
        public Set<EstadoReserva> puedeCambiar() {
            return Set.of(FINALIZADA);
        }
    },
    FINALIZADA(3L, "Reservación finalizada") {
        @Override
        public Set<EstadoReserva> puedeCambiar() {
            return Set.of();
        }
    },
    CANCELADA(4L, "Reservación cancelada") {
        @Override
        public Set<EstadoReserva> puedeCambiar() {
            return Set.of();
        }
    };

    private final Long codigo;
    private final String descripcion;

    public abstract Set<EstadoReserva> puedeCambiar();

    public boolean puedeCambiarA(EstadoReserva estadoReserva) {
        return puedeCambiar().contains(estadoReserva);
    }

    public static EstadoReserva ObtenerEstadoReservaPorCodigo(Long codigo) {
        for (EstadoReserva estadoReserva : values()) {
            if (codigo.equals(estadoReserva.codigo)) {
                return estadoReserva;
            }
        }
        throw new RecursoNoEncontradoException("Estado de reserva no encontrado");
    }
}
