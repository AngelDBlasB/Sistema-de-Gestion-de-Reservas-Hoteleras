package com.blasalda.commons.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class StringCustomUtils {


    public static void validarNoVacio(String texto, String mensaje) {
        if (texto == null || texto.isBlank())
            throw new IllegalArgumentException(mensaje);
    }

    public static void validarTamanio(String texto, Integer min, Integer max, String mensaje) {
        validarNoVacio(texto, mensaje);

        if (texto.length() < min || texto.length() > max) {
            throw new IllegalArgumentException(mensaje);
        }
    }

    public static String quitarAcentos(String texto) {
        return texto.toLowerCase()
                .replace("á", "a")
                .replace("é", "e")
                .replace("í", "i")
                .replace("ó", "o")
                .replace("ú", "u")
                .replace("ü", "u");

    }

    public static String localDateAString(LocalDate fecha, String patron) {
        final DateTimeFormatter formato = DateTimeFormatter.ofPattern(patron);
        return fecha == null ? null : fecha.format(formato);
    }

    public static String localTimeAString(LocalTime fecha, String patron) {
        final DateTimeFormatter formato = DateTimeFormatter.ofPattern(patron);
        return fecha == null ? null : fecha.format(formato);
    }

    public static String normalizarTexto(String texto) {
        return texto.toLowerCase()
                .replace(" ", "")
                .replace("á", "a")
                .replace("é", "e")
                .replace("í", "i")
                .replace("ó", "o")
                .replace("ú", "u")
                .replace("ü", "u")
                .replace("ñ", "n")
                .replaceAll("[^a-z]", "");
    }

    public static void validarEdad(LocalDate fecha) {
        int edad = LocalDate.now().getYear() - fecha.getYear();
        if (edad < 0 || edad > 150)
            throw new IllegalArgumentException("La edad del huésped debe estar entre 0 y 150 años.");

        if (edad < 18)
            throw new IllegalArgumentException("El huésped debe ser mayor de edad.");
    }

    public static Integer obtenerEdad(LocalDate fecha) {
        return LocalDate.now().getYear() - fecha.getYear() ;
    }


}
