package magenta.ui;

import javax.swing.*;

/**
 * Ventana principal de la aplicación con interfaz gráfica Swing.
 * 
 * Esta clase representa la ventana principal del sistema de gestión de
 * cartelera, proporcionando una interfaz gráfica alternativa a la consola.
 * Incluye un menú que permite acceder a la funcionalidad de agregar películas.
 * 
 * @author Nicolás Cavieres
 * @author Luis Rebolledo
 * @version 1.0
 * @since 2025
 */
public class MainFrame extends JFrame {
    
    /**
     * Constructor que inicializa la ventana principal.
     * 
     * Configura el título, tamaño, comportamiento de cierre y posición
     * de la ventana. También crea y configura la barra de menú con
     * las opciones disponibles.
     */
    public MainFrame() {
        setTitle("Magenta_db - cartelera");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Opciones");
        JMenuItem agregar = new JMenuItem("Agregar Película");

        agregar.addActionListener(e -> new FormAgregar(this));

        menu.add(agregar);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    /**
     * Método principal para ejecutar la interfaz gráfica.
     * 
     * Utiliza SwingUtilities.invokeLater() para asegurar que la creación
     * y visualización de la interfaz gráfica se ejecute en el Event Dispatch Thread.
     * 
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
