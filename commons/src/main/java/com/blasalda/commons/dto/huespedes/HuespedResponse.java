package com.blasalda.commons.dto.huespedes;

import com.blasalda.commons.enums.TipoDocumento;

import java.time.LocalDate;

public record HuespedResponse(
        Long id,
        String nombre,
        String apellidoPaterno,
        String apellidoMaterno,
        LocalDate edad,
        String email,
        String telefono,
        TipoDocumento tipoDocumento,
        String documento,
        String nacionalidad
) {
}
