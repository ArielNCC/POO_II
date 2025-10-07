package com.magenta.util;

/**
 * Utilidades para validación de datos de entrada.
 * Centraliza las validaciones comunes para campos de formularios.
 * 
 * @author Nicolas Cavieres
 */
public class ValidacionUtils {
    /**
     * Valida que un campo de texto no esté vacío
     * @param value Valor a validar
     * @param fieldName Nombre del campo para mensajes de error
     * @return El valor limpio (sin espacios al inicio/final)
     * @throws IllegalArgumentException si el valor está vacío o es null
     */
    public static String requireNotEmpty(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo '" + fieldName + "' es obligatorio.");
        }
        return value.trim();
    }

    /**
     * Parsea un string a Integer con validación
     * @param value Valor string a convertir
     * @param fieldName Nombre del campo para mensajes de error
     * @param required Si true, el campo es obligatorio
     * @return Integer parseado o null si no es obligatorio y está vacío
     * @throws IllegalArgumentException si no se puede parsear o es obligatorio y está vacío
     */
    public static Integer parseInteger(String value, String fieldName, boolean required) {
        if (value == null || value.trim().isEmpty()) {
            if (required) throw new IllegalArgumentException("El campo '" + fieldName + "' es obligatorio.");
            return null;
        }
        try {
            return Integer.valueOf(value.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El campo '" + fieldName + "' debe ser un número entero.");
        }
    }
}
