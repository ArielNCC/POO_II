package magenta.ui;

import magenta.dao.PeliculaDAO;
import magenta.model.Pelicula;
import javax.swing.*;
import java.awt.*;

/**
 * Formulario modal para agregar nuevas películas a la base de datos.
 * 
 * Esta clase extiende JDialog y proporciona una interfaz gráfica
 * para capturar los datos de una nueva película. Incluye validaciones
 * básicas y utiliza PeliculaDAO para persistir la información.
 * 
 * El formulario incluye campos para:
 * - Título de la película
 * - Director
 * - Año de lanzamiento
 * - Duración en minutos
 * - Género (selección mediante ComboBox)
 * 
 * @author Nicolás Cavieres
 * @author Luis Rebolledo
 * @version 1.0
 * @since 2025
 */
public class FormAgregar extends JDialog {
    /** Campo de texto para el título de la película */
    private final JTextField txtTitulo;
    
    /** Campo de texto para el director de la película */
    private final JTextField txtDirector;
    
    /** Campo de texto para el año de lanzamiento */
    private final JTextField txtAnio;
    
    /** Campo de texto para la duración en minutos */
    private final JTextField txtDuracion;
    
    /** ComboBox para seleccionar el género de la película */
    private final JComboBox<String> cbGenero;

    /**
     * Constructor que inicializa el formulario de agregar película.
     * 
     * Configura la ventana modal, inicializa todos los componentes
     * (campos de texto, botones, etc.) y establece el layout del formulario.
     * 
     * @param parent la ventana padre de este diálogo modal
     */
    public FormAgregar(JFrame parent) {
        super(parent, "Agregar Película", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);

        txtTitulo = new JTextField(20);
        txtDirector = new JTextField(20);
        txtAnio = new JTextField(4);
        txtDuracion = new JTextField(4);
        cbGenero = new JComboBox<>(new String[]{"Comedia","Drama","Accion","Terror","Romance","Documental"});

        JButton btnGuardar = new JButton("Guardar");
        JButton btnLimpiar = new JButton("Limpiar");

        btnGuardar.addActionListener(e -> guardarPelicula());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        setLayout(new GridLayout(6,2));
        add(new JLabel("Título:")); add(txtTitulo);
        add(new JLabel("Director:")); add(txtDirector);
        add(new JLabel("Año:")); add(txtAnio);
        add(new JLabel("Duración (min):")); add(txtDuracion);
        add(new JLabel("Género:")); add(cbGenero);
        add(btnGuardar); add(btnLimpiar);

        setVisible(true);
    }

    /**
     * Método privado que maneja el proceso de guardar una nueva película.
     * 
     * Valida los datos ingresados, crea un objeto Película y lo guarda
     * en la base de datos usando PeliculaDAO. Muestra mensajes de confirmación
     * o error según corresponda.
     * 
     * Validaciones incluidas:
     * - Campos obligatorios no vacíos (título y director)
     * - Formato numérico válido para año y duración
     */
    private void guardarPelicula() {
        try {
            String titulo = txtTitulo.getText().trim();
            String director = txtDirector.getText().trim();
            int anio = Integer.parseInt(txtAnio.getText().trim());
            int duracion = Integer.parseInt(txtDuracion.getText().trim());
            String genero = (String) cbGenero.getSelectedItem();

            if (titulo.isEmpty() || director.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos obligatorios.");
                return;
            }

            Pelicula p = new Pelicula(titulo, director, anio, duracion, genero);
            new PeliculaDAO().insertarPelicula(p);
            JOptionPane.showMessageDialog(this, "Película guardada correctamente.");
            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en los datos ingresados.");
        }
    }

    /**
     * Método privado que limpia todos los campos del formulario.
     * 
     * Resetea todos los campos de texto a valores vacíos y el ComboBox
     * de género a la primera opción, permitiendo al usuario ingresar
     * una nueva película desde cero.
     */
    private void limpiarCampos() {
        txtTitulo.setText("");
        txtDirector.setText("");
        txtAnio.setText("");
        txtDuracion.setText("");
        cbGenero.setSelectedIndex(0);
    }
}
