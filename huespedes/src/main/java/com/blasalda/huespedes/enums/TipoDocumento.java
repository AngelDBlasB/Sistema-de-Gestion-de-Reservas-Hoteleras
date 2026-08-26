package com.blasalda.huespedes.enums;

import com.blasalda.commons.exceptions.RecursoNoEncontradoException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TipoDocumento {
    INE(1L, "INE"),
    DNI(2L, "DNI"),
    PASAPORTE(3L, "PASAPORTE"),
    CEDULA_PROFESIONAL(4L, "CEDULA PROFESIONAL"),
    LICENCIA_CONDUCIR(5L, "LICENCIA DE CONDUCIR");

    private final Long codigo;

    public final String descripcion;

    public static TipoDocumento getTipoDocumento(Long codigo) {
        for (TipoDocumento tipoDocumento : TipoDocumento.values()) {
            if (tipoDocumento.codigo.equals(codigo)) {
                return tipoDocumento;
            }
        }
        throw new RecursoNoEncontradoException("Tipo de documento no encontrado");
    }
}
