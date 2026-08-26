package com.blasalda.commons.dto.huespedes;

public record DatosHuesped(
        String nombre,
        String edad,
        String telefono,
        String email,
        String tipoDocumento,
        String nacionalidad
) {
}
