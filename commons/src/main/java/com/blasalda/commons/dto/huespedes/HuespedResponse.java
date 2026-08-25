package com.blasalda.commons.dto.huespedes;

public record HuespedResponse(
        Long id,
        String nombre,
        String edad,
        String email,
        String telefono,
        String tipoDocumento,
        String documento,
        String nacionalidad
) {
}
