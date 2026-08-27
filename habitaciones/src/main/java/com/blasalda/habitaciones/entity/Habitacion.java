package com.blasalda.habitaciones.entity;

import com.blasalda.commons.enums.EstadoHabitacion;
import com.blasalda.commons.enums.EstadoRegistro;
import com.blasalda.habitaciones.enums.TipoHabitacion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "HABITACIONES")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_HABITACION")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO_HABITACION", nullable = false, length = 20)
    private EstadoHabitacion estadoHabitacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO_REGISTRO", nullable = false, length = 20)
    private EstadoRegistro estadoRegistro;

    @Column(name = "NUMERO_HABITACION", nullable = false)
    private Integer numeroHabitacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO", nullable = false, length = 30)
    private TipoHabitacion tipo;

    @Column(name = "PRECIO", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(name = "CAPACIDAD", nullable = false)
    private Short capacidad;

    private void validarDatos(Integer numeroHabitacion, TipoHabitacion tipo,
                              BigDecimal precio, Short capacidad) {

        if (numeroHabitacion == null || numeroHabitacion <= 0) {
            throw new IllegalArgumentException("El número de habitación es requerido y debe ser mayor a 0");
        }

        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de habitación es requerido");
        }

        if (precio == null || precio.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio es requerido y debe ser mayor a 0");
        }

        if (capacidad == null || capacidad < 1) {
            throw new IllegalArgumentException("La capacidad es requerida y debe ser al menos de 1 persona");
        }
    }

    public void actualizarTipoHabitacion(TipoHabitacion tipo) {
        validarNoEliminado();

        if (tipo == null)
            throw new IllegalArgumentException("El tipo es requerida");

        this.tipo = tipo;
    }

    public void actualizar(Integer numeroHabitacion, TipoHabitacion tipo,
                           BigDecimal precio, Short capacidad) {

        validarNoEliminado();

        validarDatos(numeroHabitacion, tipo, precio, capacidad);

        this.numeroHabitacion = numeroHabitacion;
        this.tipo = tipo;
        this.precio = precio;
        this.capacidad = capacidad;
    }

    private void validarNoEliminado() {
        if (this.estadoRegistro == EstadoRegistro.ELIMINADO) {
            throw new IllegalStateException("No se puede modificar una habitación que ha sido eliminada");
        }
    }

    public void eliminar(){
        validarNoEliminado();
        this.estadoRegistro = EstadoRegistro.ELIMINADO;
    }

    public void actualizarEstado(EstadoHabitacion estadoHabitacion) {
        validarNoEliminado();

        if (estadoHabitacion == null)
            throw new IllegalArgumentException("El estado de la habitación es requerido");

        this.estadoHabitacion = estadoHabitacion;
    }
}
