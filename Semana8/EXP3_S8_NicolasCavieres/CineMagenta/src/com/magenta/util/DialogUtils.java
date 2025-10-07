package com.magenta.util;

import javax.swing.JOptionPane;
import java.awt.Component;

/**
 * Utilidades para mostrar diálogos de usuario.
 * Centraliza la gestión de mensajes e interacciones con el usuario.
 * 
 * @author Nicolas Cavieres
 */
public class DialogUtils {
    /**
     * Muestra un diálogo de información al usuario
     * @param parent Componente padre del diálogo
     * @param message Mensaje a mostrar
     */
    public static void showInfo(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Información", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Muestra un diálogo de advertencia al usuario
     * @param parent Componente padre del diálogo
     * @param message Mensaje de advertencia a mostrar
     */
    public static void showWarning(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Advertencia", JOptionPane.WARNING_MESSAGE);
    }
    
    /**
     * Muestra un diálogo de error al usuario
     * @param parent Componente padre del diálogo
     * @param message Mensaje de error a mostrar
     */
    public static void showError(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Muestra un diálogo de confirmación (Sí/No)
     * @param parent Componente padre del diálogo
     * @param message Mensaje de confirmación
     * @return true si el usuario selecciona "Sí", false en caso contrario
     */
    public static boolean confirm(Component parent, String message) {
        int res = JOptionPane.showConfirmDialog(parent, message, "Confirmar", JOptionPane.YES_NO_OPTION);
        return res == JOptionPane.YES_OPTION;
    }
}
