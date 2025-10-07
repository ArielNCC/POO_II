package com.magenta.ui;

import com.magenta.model.Pelicula;
import com.magenta.model.Director;
import com.magenta.model.Genero;
import com.magenta.dao.DirectorDAO;
import com.magenta.dao.GeneroDAO;
import com.magenta.util.DialogUtils;
import com.magenta.util.ValidacionUtils;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class FrmPeliculas extends JFrame {

    // Barra de menú
    private JMenuBar menuBar;
    private JMenu menuArchivo;
    private JMenu menuAyuda;
    private JMenuItem itemSalir;
    private JMenuItem itemAcercaDe;

    private final com.magenta.dao.PeliculaDAO dao = new com.magenta.dao.PeliculaDAO();
    private final DirectorDAO directorDAO = new DirectorDAO();
    private final GeneroDAO generoDAO = new GeneroDAO();

    // ===== Tabla y modelo =====
    private final PeliculaTableModel tableModel = new PeliculaTableModel();
    private final JTable tabla = new JTable(tableModel);

    // ===== Filtros (arriba) =====
    private final JComboBox<Genero> cboGenero = new JComboBox<>();
    private final JTextField txtBusqueda = new JTextField(16);
    // Eliminado filtro de duración
    private final JButton btnListarTodo = new JButton("Listar todo");
    private final JButton btnFiltrar = new JButton("Aplicar filtros");
    private final JButton btnLimpiarFiltros = new JButton("Limpiar filtros");

    // ===== CRUD (abajo) =====
    private final JTextField txtId = new JTextField(6);
    private final JTextField txtTitulo = new JTextField(18);
    private final JComboBox<Director> cboDirector = new JComboBox<>();
    private final JTextField txtAnio = new JTextField(6);
    private final JTextField txtDuracion = new JTextField(6);
    // Genero como combo con objetos Genero
    private final JComboBox<Genero> cboGeneroCrud = new JComboBox<>();

    private final JButton btnGuardar = new JButton("Guardar (Insertar)");
    private final JButton btnModificar = new JButton("Modificar");
    private final JButton btnEliminar = new JButton("Eliminar");
    private final JButton btnLimpiar = new JButton("Limpiar formulario");

    public FrmPeliculas() {
        setTitle("CineMagenta - Gestión de Películas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 650);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));
        setJMenuBar(crearMenuBar());
        add(crearPanelFiltros(), BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(crearPanelCRUD(), BorderLayout.SOUTH);

        // Inicializar combos y tabla
        inicializarCombos();
        listarTodo();

        // Eventos
        btnListarTodo.addActionListener(e -> listarTodo());
        btnFiltrar.addActionListener(e -> aplicarFiltros());
        btnLimpiarFiltros.addActionListener(e -> limpiarFiltros());

        btnGuardar.addActionListener(e -> accionGuardar());
        btnModificar.addActionListener(e -> accionModificar());
        btnEliminar.addActionListener(e -> accionEliminar());
        btnLimpiar.addActionListener(e -> limpiarFormulario());

        // Al seleccionar una fila, cargar al formulario
        tabla.getSelectionModel().addListSelectionListener(this::onSeleccionTabla);
    }

    /**
     * Crea la barra de menú principal
     */
    private JMenuBar crearMenuBar() {
        menuBar = new JMenuBar();
        // Menú Archivo
        menuArchivo = new JMenu("Archivo");
        itemSalir = new JMenuItem("Salir");
        itemSalir.addActionListener(e -> System.exit(0));
        menuArchivo.add(itemSalir);
        menuBar.add(menuArchivo);

        // Menú Ayuda
        menuAyuda = new JMenu("Ayuda");
        itemAcercaDe = new JMenuItem("Acerca de...");
        itemAcercaDe.addActionListener(e -> DialogUtils.showInfo(this, "CineMagenta\nAutor: Nicolas Cavieres\n2025"));
        menuAyuda.add(itemAcercaDe);
        menuBar.add(menuAyuda);

        return menuBar;
    }

    // -------------------- UI: Filtros --------------------
    private JPanel crearPanelFiltros() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBorder(new TitledBorder("Búsqueda y filtros"));

        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(5,5,5,5);
        gc.gridy = 0;

        // Búsqueda por título
        gc.gridx = 0; p.add(new JLabel("Busqueda por Titulo:"), gc);
        gc.gridx = 1; txtBusqueda.setPreferredSize(new Dimension(150, 26)); p.add(txtBusqueda, gc);

        // Género
        gc.gridx = 2; p.add(new JLabel("Género:"), gc);
        gc.gridx = 3; cboGenero.setPreferredSize(new Dimension(120, 26)); p.add(cboGenero, gc);

        // Botones
        gc.gridx = 4; p.add(btnFiltrar, gc);
        gc.gridx = 5; p.add(btnListarTodo, gc);
        gc.gridx = 6; p.add(btnLimpiarFiltros, gc);

        return p;
    }

    // Acción para limpiar filtros
    private void limpiarFiltros() {
        txtBusqueda.setText("");
        cboGenero.setSelectedIndex(0);
        listarTodo();
    }

    // -------------------- UI: CRUD --------------------
    private JPanel crearPanelCRUD() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBorder(new TitledBorder("Formulario CRUD"));

        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(5,5,5,5);
        gc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;

        // ID y Título
        gc.gridy = row; gc.gridx = 0; p.add(new JLabel("ID:"), gc);
        txtId.setEditable(false);
        gc.gridx = 1; p.add(txtId, gc);

        gc.gridy = row; gc.gridx = 2; p.add(new JLabel("Título:"), gc);
        gc.gridx = 3; gc.gridwidth = 2; p.add(txtTitulo, gc);

        // Director y Año
        row++;
        gc.gridwidth = 1;
        gc.gridy = row; gc.gridx = 0; p.add(new JLabel("Director:"), gc);
        gc.gridx = 1; gc.gridwidth = 2; p.add(cboDirector, gc);

        gc.gridwidth = 1;
        gc.gridy = row; gc.gridx = 3; p.add(new JLabel("Año:"), gc);
        gc.gridx = 4; p.add(txtAnio, gc);

        // Duración y Género
        row++;
        gc.gridy = row; gc.gridx = 0; p.add(new JLabel("Duración (min):"), gc);
        gc.gridx = 1; p.add(txtDuracion, gc);

        gc.gridy = row; gc.gridx = 2; p.add(new JLabel("Género:"), gc);
        gc.gridx = 3; gc.gridwidth = 2; p.add(cboGeneroCrud, gc);

        // Botones
        row++;
        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        acciones.add(btnGuardar);
        acciones.add(btnModificar);
        acciones.add(btnEliminar);
        acciones.add(btnLimpiar);

        gc.gridy = row; gc.gridx = 0; gc.gridwidth = 5;
        p.add(acciones, gc);

        return p;
    }

    // -------------------- Inicialización --------------------
    private void inicializarCombos() {
        try {
            // Cargar géneros
            List<Genero> generos = generoDAO.listarTodos();
            
            // Combo de filtros - agregar opción "Todos"
            cboGenero.addItem(new Genero(null, "-- Todos --"));
            for (Genero genero : generos) {
                cboGenero.addItem(genero);
            }
            
            // Combo de CRUD
            for (Genero genero : generos) {
                cboGeneroCrud.addItem(genero);
            }
            
            // Cargar directores
            List<Director> directores = directorDAO.listarTodos();
            for (Director director : directores) {
                cboDirector.addItem(director);
            }
            
        } catch (SQLException ex) {
            mostrarError("Error al cargar combos: " + ex.getMessage());
        }
    }

    // -------------------- Filtros / listado --------------------
    private void listarTodo() {
        try {
            List<Pelicula> lista = dao.listarTodas();
            tableModel.setData(lista);
        } catch (SQLException ex) {
            mostrarError("Error al listar: " + ex.getMessage());
        }
    }

    private void aplicarFiltros() {
        try {
            String busqueda = txtBusqueda.getText().trim();
            Genero generoSeleccionado = (Genero) cboGenero.getSelectedItem();
            String genero = (generoSeleccionado != null && generoSeleccionado.getId() != null) 
                          ? generoSeleccionado.getNombre() : null;
            List<Pelicula> lista = dao.buscarPeliculas(busqueda, genero);
            tableModel.setData(lista);
        } catch (SQLException ex) {
            mostrarError("Error al aplicar filtros: " + ex.getMessage());
        }
    }

    // -------------------- CRUD acciones --------------------
    private void accionGuardar() {
        Pelicula p = construirPeliculaDesdeFormulario(false);
        if (p == null) return; // hubo errores y ya se mostró mensaje

        try {
            dao.insertar(p);
            JOptionPane.showMessageDialog(this, "Película guardada con éxito.");
            listarTodo();
            limpiarFormulario();
        } catch (SQLException ex) {
            mostrarError("Error al guardar: " + ex.getMessage());
        }
    }

    private void accionModificar() {
        Pelicula p = construirPeliculaDesdeFormulario(true);
        if (p == null) return;

        try {
            dao.actualizar(p);
            JOptionPane.showMessageDialog(this, "Película modificada con éxito.");
            listarTodo();
            limpiarFormulario();
        } catch (SQLException ex) {
            mostrarError("Error al modificar: " + ex.getMessage());
        }
    }

    private void accionEliminar() {
        String idStr = txtId.getText();
        if (idStr == null || idStr.isBlank()) {
            mostrarError("Seleccione una película de la tabla o cargue un ID para eliminar.");
            return;
        }
        int id = Integer.parseInt(idStr);
        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Eliminar película ID " + id + "?", "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        try {
            dao.eliminar(id);
            JOptionPane.showMessageDialog(this, "Película eliminada.");
            listarTodo();
            limpiarFormulario();
        } catch (SQLException ex) {
            mostrarError("Error al eliminar: " + ex.getMessage());
        }
    }

    // -------------------- Helpers --------------------
    private void onSeleccionTabla(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) return;
        int row = tabla.getSelectedRow();
        if (row < 0) return;

        Pelicula peli = tableModel.getAt(row);
        if (peli == null) return;

                // Set the JMenuBar after creating it
                {
                    setJMenuBar(crearMenuBar());
                }
        txtId.setText(String.valueOf(peli.getId()));
        txtTitulo.setText(peli.getTitulo());
        cboDirector.setSelectedItem(peli.getDirector());
        txtAnio.setText(peli.getAnio() != null ? peli.getAnio().toString() : "");
        txtDuracion.setText(peli.getDuracion() != null ? peli.getDuracion().toString() : "");
        cboGeneroCrud.setSelectedItem(peli.getGenero());
    }

    private Pelicula construirPeliculaDesdeFormulario(boolean requiereId) {
        try {
            Integer id = null;
            if (requiereId) {
                if (txtId.getText() == null || txtId.getText().isBlank())
                    throw new IllegalArgumentException("Debe seleccionar un registro (ID) para modificar.");
                id = Integer.parseInt(txtId.getText().trim());
            }

            String titulo = validarNoVacio(txtTitulo.getText(), "Título");
            Director director = (Director) cboDirector.getSelectedItem();
            if (director == null) {
                throw new IllegalArgumentException("Debe seleccionar un director.");
            }
            
            Integer anio = parseIntegerObligatorio(txtAnio.getText(), "Año");
            Integer duracion = parseIntegerObligatorio(txtDuracion.getText(), "Duración");
            Genero genero = (Genero) cboGeneroCrud.getSelectedItem();
            
            if (genero == null) {
                throw new IllegalArgumentException("Debe seleccionar un género.");
            }

            // Validaciones de negocio
            if (anio < 1888 || anio > 2100) {
                throw new IllegalArgumentException("El año debe estar entre 1888 y 2100.");
            }
            if (duracion <= 0) {
                throw new IllegalArgumentException("La duración debe ser mayor a 0.");
            }

            return new Pelicula(id, titulo, director, anio, duracion, genero);
        } catch (IllegalArgumentException ex) {
            mostrarError(ex.getMessage());
            return null;
        }
    }


    // Métodos de validación centralizados usando ValidationUtils
    private String validarNoVacio(String val, String nombreCampo) {
        return ValidacionUtils.requireNotEmpty(val, nombreCampo);
    }

    private Integer parseIntegerObligatorio(String text, String nombreCampo) {
        return ValidacionUtils.parseInteger(text, nombreCampo, true);
    }

    private void limpiarFormulario() {
        txtId.setText("");
        txtTitulo.setText("");
        if (cboDirector.getItemCount() > 0) cboDirector.setSelectedIndex(0);
        txtAnio.setText("");
        txtDuracion.setText("");
        if (cboGeneroCrud.getItemCount() > 0) cboGeneroCrud.setSelectedIndex(0);
        tabla.clearSelection();
    }

    private void mostrarError(String msg) {
        DialogUtils.showError(this, msg);
    }

    // Punto de entrada
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FrmPeliculas().setVisible(true));
    }
}