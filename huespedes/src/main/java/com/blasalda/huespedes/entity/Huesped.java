package com.blasalda.huespedes.entity;

import com.blasalda.commons.enums.EstadoRegistro;
import com.blasalda.commons.utils.StringCustomUtils;
import com.blasalda.commons.utils.ValoresNumericosUtils;
import com.blasalda.commons.enums.TipoDocumento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "HUESPEDES")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Huesped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_HUESPED")
    private Long id;

    @Column(name = "NOMBRE", nullable = false, length = 50)
    private String nombre;

    @Column(name = "APELLIDO_PATERNO",  nullable = false, length = 50)
    private String apellidoPaterno;

    @Column(name = "APELLIDO_MATERNO",  nullable = false, length = 50)
    private String apellidoMaterno;

    @Column(name = "FECHA_NACIMIENTO", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "EMAIL",  nullable = false, length = 50)
    private String email;

    @Column(name = "TELEFONO",   nullable = false, length = 10)
    private String telefono;

    @Column(name = "TIPO_DOCUMENTO", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;

    @Column(name = "DOCUMENTO", nullable = false, length = 20)
    private String documento;

    @Column(name = "NACIONALIDAD",  nullable = false,  length = 50)
    private String nacionalidad;

    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO_REGISTRO")
    private EstadoRegistro estadoRegistro;

    private static void validarId(Long id, String campo) {
        ValoresNumericosUtils.validarLongPositivo(id, "El id del campo " + campo + " es requerido y debe ser positivo");
    }

    private static void validarDatos(
            String nombre,
            String apellidoPaterno,
            String apellidoMaterno,
            LocalDate fechaNacimiento,
            String email,
            String telefono,
            String documento,
            String nacionalidad
    ) {
        StringCustomUtils.validarEdad(fechaNacimiento);
        StringCustomUtils.validarTamanio(nombre, 2, 50, "El nombre del huésped debe de tener un mínimo de 2 y un máximo de 50 letras.");
        StringCustomUtils.validarTamanio(apellidoPaterno, 2, 50, "El apellido paterno del huésped debe de tener un mínimo de 2 y un máximo de 50 letras.");
        StringCustomUtils.validarTamanio(apellidoMaterno, 2, 50, "El apellido materno del huésped debe de tener un mínimo de 2 y un máximo de 50 letras.");
        StringCustomUtils.validarTamanio(email, 5, 100, "El email del huésped debe de tener un mínimo de 5 y un máximo de 100 letras.");
        StringCustomUtils.validarTamanio(telefono, 10, 10, "El teléfono del huésped debe de tener un mínimo de 10 y un máximo de 10 letras.");
        StringCustomUtils.validarTamanio(documento, 5, 20, "El documento del huésped debe de tener un mínimo de 5 y un máximo de 20 letras.");
        StringCustomUtils.validarTamanio(nacionalidad, 5, 50, "La nacionalidad del huésped debe de tener un mínimo de 5 y un máximo de 50 letras.");
    }

    private void validarNoEliminado() {
        if (this.estadoRegistro == EstadoRegistro.ELIMINADO) {
            throw new IllegalArgumentException("El huésped ya está eliminado");
        }
    }

    public void eliminar() {
        this.estadoRegistro = EstadoRegistro.ELIMINADO;
    }

    public void actualizar(
            String nombre,
            String apellidoPaterno,
            String apellidoMaterno,
            LocalDate fechaNacimiento,
            String email,
            String telefono,
            TipoDocumento tipoDocumento,
            String documento,
            String nacionalidad
    ) {
        validarNoEliminado();

        validarDatos(
                nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, email, telefono, documento, nacionalidad
        );

        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.email = email;
        this.telefono = telefono;
        this.documento = documento;
        this.nacionalidad = nacionalidad;
        this.tipoDocumento = tipoDocumento;
    }

    public static Huesped crear(
            String nombre,
            String apellidoPaterno,
            String apellidoMaterno,
            LocalDate fechaNacimiento,
            String email,
            String telefono,
            String documento,
            String nacionalidad,
            TipoDocumento tipoDocumento
    ) {
        validarDatos(
                nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, email, telefono, documento, nacionalidad
        );

        return Huesped.builder()
                .nombre(nombre)
                .apellidoPaterno(apellidoPaterno)
                .apellidoMaterno(apellidoMaterno)
                .fechaNacimiento(fechaNacimiento)
                .email(email)
                .telefono(telefono)
                .documento(documento)
                .nacionalidad(nacionalidad)
                .tipoDocumento(tipoDocumento)
                .estadoRegistro(EstadoRegistro.ACTIVO)
                .build();
    }
}
