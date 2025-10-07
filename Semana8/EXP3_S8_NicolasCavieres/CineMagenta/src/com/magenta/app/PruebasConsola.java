/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.magenta.app;

import javax.swing.SwingUtilities;
import com.magenta.ui.FrmPeliculas;

public class PruebasConsola {

    public static void main(String[] args) {
        // Forzar salida estándar y error a UTF-8
        try {
            System.setOut(new java.io.PrintStream(System.out, true, java.nio.charset.StandardCharsets.UTF_8));
            System.setErr(new java.io.PrintStream(System.err, true, java.nio.charset.StandardCharsets.UTF_8));
        } catch (Exception e) {
            // Si falla, sigue con la salida estándar
        }
        // Ahora se ejecuta desde MagentaApp.java - aplicación principal CineMagenta
        SwingUtilities.invokeLater(() -> new FrmPeliculas().setVisible(true));
    }

    
    //EJECUTAMOS DESDE MAGENTAAPP O DESDE ACÁ. 
    
    //SI DESCOMENTAMOS ESTO VEMOS POR CONSOLA - PRUEBAS CINEMAGENTA
    
    /*public static void main(String[] args) {
        PeliculaDAO dao = new PeliculaDAO();

        try {
            // 1) Listar todas
            System.out.println("== LISTAR TODAS ==");
            imprimir(dao.listarTodas());

            // 2) Insertar
            System.out.println("\n== INSERTAR ==");
            Pelicula nueva = new Pelicula(
                    "Avatar", "James Cameron", 2009, 162, "Accion"
            );
            dao.insertar(nueva);
            imprimir(dao.listarTodas());

            // 3) Tomar el último ID (para demo)
            List<Pelicula> todas = dao.listarTodas();
            int ultimoId = todas.get(todas.size() - 1).getId();

            // 4) Actualizar
            System.out.println("\n== ACTUALIZAR ==");
            Pelicula mod = new Pelicula(
                    ultimoId, "Avatar: El Camino del Agua", "James Cameron", 2022, 192, "Accion"
            );
            dao.actualizar(mod);
            imprimir(dao.listarTodas());

            // 5) Filtros: genero y/o rango duración
            System.out.println("\n== FILTRO: genero=Drama ==");
            imprimir(dao.listarFiltrado("Drama", null, null));

            System.out.println("\n== FILTRO: duración 120 a 180 minutos ==");
            imprimir(dao.listarFiltrado(null, 120, 180));

            System.out.println("\n== FILTRO: genero=Comedia y duración 90 a 150 minutos ==");
            imprimir(dao.listarFiltrado("Comedia", 90, 150));

            // 6) Obtener por ID
            System.out.println("\n== OBTENER POR ID (id=1) ==");
            Pelicula pelicula = dao.obtenerPorId(1);
            if (pelicula != null) {
                System.out.println("Película encontrada: " + pelicula);
            } else {
                System.out.println("No se encontró película con ID 1");
            }

            // 7) Eliminar (dejamos la BD limpia de la prueba)
            System.out.println("\n== ELIMINAR (ultimoId) ==");
            dao.eliminar(ultimoId);
            imprimir(dao.listarTodas());

            System.out.println("\nOK - Pruebas completadas.");
        } catch (Exception ex) {
            System.err.println("ERROR: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private static void imprimir(List<Pelicula> lista) {
        for (Pelicula p : lista) {
            System.out.println(p);
        }
    }*/
        
}

