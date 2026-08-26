package com.blasalda.reservaciones.entity;

import com.blasalda.commons.enums.EstadoRegistro;
import com.blasalda.commons.utils.ValoresNumericosUtils;
import com.blasalda.reservaciones.enums.EstadoReserva;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "RESERVACIONES")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class Reservacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RESERVA")
    private Long id;

    @Column(name = "ID_HUESPED", nullable = false)
    private Long idHuesped;

    @Column(name = "ID_HABITACION", nullable = false)
    private Long idHabitacion;

    @Column(name = "FECHA_ENTRADA", nullable = false)
    private LocalDate fechaEntrada;

    @Column(name = "FECHA_SALIDA", nullable = false)
    private LocalDate fechaSalida;

    @Column(name = "ESTADO_RESERVA")
    @Enumerated(EnumType.STRING)
    private EstadoReserva estadoReserva;

    @Column(name = "ESTADO_REGISTRO")
    @Enumerated(EnumType.STRING)
    private EstadoRegistro estadoRegistro;

    private static void validarId(Long id, String campo) {
        ValoresNumericosUtils.validarLongPositivo(id, "El id del campo " + campo + " debe ser positivo.");
    }

    private static void validarFechas(LocalDate fechaEntrada, LocalDate fechaSalida) {
        if (fechaEntrada == null || !fechaEntrada.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de entrada de la reservación es requerida y debe ser futura");
        }
        if (fechaSalida == null || !fechaSalida.isAfter(fechaEntrada)) {
            throw new IllegalArgumentException("La fecha de salida de la reservación es requerida y debe ser futura");
        }

    }

    private static void validarDatos(
            Long idHuesped,
            Long idHabitacion,
            Long idEstadoReserva,
            LocalDate fechaEntrada,
            LocalDate fechaSalida
    ) {
        validarId(idHuesped, "huésped");
        validarId(idHabitacion, "habitación");
        validarId(idEstadoReserva, "estado de la reserva");
        validarFechas(fechaEntrada, fechaSalida);
    }

    private void validarNoEliminado() {
        if (this.estadoRegistro == EstadoRegistro.ELIMINADO) {
            throw new IllegalArgumentException("La reservación ya se encuentra eliminada");
        }
    }

    public void actualizar(
            Long idHuesped,
            Long idHabitacion,
            LocalDate fechaEntrada,
            LocalDate fechaSalida
    ) {
        validarNoEliminado();
        validarDatos(idHuesped, idHabitacion, this.getEstadoReserva().getCodigo(), fechaEntrada, fechaSalida);
        this.idHuesped = idHuesped;
        this.idHabitacion = idHabitacion;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
    }

    public void cambiarEstado(EstadoReserva estadoReserva) {
        this.estadoReserva = estadoReserva;
    }

    public static Reservacion crear(
            Long idHuesped,
            Long idHabitacion,
            Long idEstadoReserva,
            LocalDate fechaEntrada,
            LocalDate fechaSalida
    ) {
        validarDatos(idHuesped, idHabitacion, idEstadoReserva, fechaEntrada, fechaSalida);
        return Reservacion.builder()
                .idHuesped(idHuesped)
                .idHabitacion(idHabitacion)
                .estadoReserva(EstadoReserva.ObtenerEstadoReservaPorCodigo(idEstadoReserva))
                .fechaEntrada(fechaEntrada)
                .fechaSalida(fechaSalida)
                .estadoRegistro(EstadoRegistro.ACTIVO)
                .build();
    }

    public void eliminar() {
        validarNoEliminado();

        this.estadoRegistro = EstadoRegistro.ELIMINADO;
    }


}
