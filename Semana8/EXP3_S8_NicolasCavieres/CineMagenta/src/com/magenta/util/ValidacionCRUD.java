package com.magenta.util;

/**
 * Utilidades para logging y validación de operaciones CRUD.
 * Centraliza los mensajes de consola para operaciones de base de datos.
 * 
 * @author Nicolas Cavieres
 */
public class ValidacionCRUD {

    public static void conexionExitosa() {
        System.out.println("[INFO] Conexión a la base de datos exitosa.");
    }
    
    public static void conexionFallida(String msg) {
        System.err.println("[ERROR] No se pudo conectar a la base de datos: " + msg);
    }

    public static void insertOK() {
        System.out.println("[OK] Película insertada correctamente en la base de datos.");
    }
    
    public static void insertWarn() {
        System.out.println("[WARN] No se insertó la película en la base de datos.");
    }
    public static void updateOK() {
        System.out.println("[OK] Película actualizada correctamente en la base de datos.");
    }
    public static void updateWarn() {
        System.out.println("[WARN] No se actualizó ninguna película (ID no encontrado).");
    }
    public static void deleteOK() {
        System.out.println("[OK] Película eliminada correctamente de la base de datos.");
    }
    public static void deleteWarn() {
        System.out.println("[WARN] No se eliminó ninguna película (ID no encontrado).");
    }
    public static void consultaTodas(int total) {
        System.out.println("[INFO] Consulta de todas las películas realizada. Total: " + total);
    }
    public static void consultaFiltrada(int total) {
        System.out.println("[INFO] Consulta filtrada realizada. Total: " + total);
    }
    public static void encontradoPorId(int id) {
        System.out.println("[INFO] Película encontrada con ID: " + id);
    }
    public static void noEncontradoPorId(int id) {
        System.out.println("[WARN] No se encontró película con ID: " + id);
    }
}
