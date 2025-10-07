package com.magenta.app;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import com.magenta.ui.FrmPeliculas;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Aplicación principal CineMagenta
 * Sistema de gestión de cartelera de películas
 * 
 * @author Nicolas Cavieres
 */
public class MagentaApp {

    /**
     * Punto de entrada principal de la aplicación CineMagenta
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        // Forzar salida estándar y error a UTF-8
        try {
            System.setOut(new java.io.PrintStream(System.out, true, java.nio.charset.StandardCharsets.UTF_8));
            System.setErr(new java.io.PrintStream(System.err, true, java.nio.charset.StandardCharsets.UTF_8));
        } catch (Exception e) {
            // Si falla, sigue con la salida estándar
        }
        // Configurar Look and Feel del sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            // Si no se puede establecer, usar el por defecto
            System.out.println("No se pudo establecer el Look and Feel del sistema: " + e.getMessage());
        }
        // Ejecutar la interfaz gráfica en el Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            System.out.println("=== INICIANDO CINEMAGENTA ===");
            System.out.println("Sistema de Gestión de Cartelera de Películas");
            System.out.println("Autor: Nicolas Cavieres");
            System.out.println("==================================");
            new FrmPeliculas().setVisible(true);
        });
    }
}
