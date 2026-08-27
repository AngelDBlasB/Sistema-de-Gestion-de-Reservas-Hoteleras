package com.blasalda.commons.dto.huespedes;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
public record HuespedRequest(
        @NotBlank(message = "El nombre del huésped es requerido")
        @Size(min = 2, max = 50, message = "El nombre debe tener entre 5 y 50 caracteres")
        String nombre,

        @NotBlank(message = "El apellido paterno del huésped es requerido")
        @Size(min = 2, max = 50, message = "El apellido paterno debe tener entre 5 y 50 caracteres")
        String apellidoPaterno,

        @NotBlank(message = "El apellido materno del huésped es requerido")
        @Size(min = 2, max = 50, message = "El apellido materno debe tener entre 5 y 50 caracteres")
        String apellidoMaterno,

        @NotNull(message = "La fecha de nacimiento  es requerida")
        @PastOrPresent(message = "La fecha debe ser pasada")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate fechaNacimiento,

        @NotBlank(message = "El email del huésped es requerido")
        @Size(min = 5, max = 50, message = "El email debe tener entre 5 y 50 caracteres")
        String email,

        @NotBlank(message = "El teléfono del huésped es requerido")
        @Size(min = 10, max = 10, message = "El teléfono debe tener entre 10 y 10 caracteres")
        String telefono,

        @NotNull(message = "Se requiere seleccionar un tipo de documento")
        @Positive(message = "El tipo del documento debe ser válido")
        Long tipoDocumento,

        @NotBlank(message = "El identificador del documento es requerido")
        @Size(min = 3, max = 20, message = "El identificador del documento debe tener entre 3 y 20 caracteres")
        String documento,

        @NotBlank(message = "La nacionalidad del huésped es requerida")
        @Size(min = 2, max = 50, message = "La nacionalidad debe tener entre 2 y 50 caracteres")
        String nacionalidad
) {
}
