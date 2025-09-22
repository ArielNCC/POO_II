package magenta.dao;

import magenta.model.Pelicula;
import magenta.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO (Data Access Object) para gestionar operaciones CRUD de películas.
 * 
 * Esta clase encapsula todas las operaciones de base de datos relacionadas
 * con la entidad Película, proporcionando una interfaz limpia entre la
 * lógica de negocio y la persistencia de datos.
 * 
 * Implementa operaciones seguras usando PreparedStatement para prevenir
 * inyecciones SQL y garantizar la integridad de los datos.
 * 
 * @author Nicolás Cavieres
 * @author Luis Rebolledo
 * @version 1.0
 * @since 2025
 */
public class PeliculaDAO {

    /**
     * Inserta una nueva película en la base de datos.
     * 
     * Utiliza PreparedStatement para ejecutar una sentencia INSERT de manera
     * segura, evitando inyecciones SQL y manejando los tipos de datos
     * adecuadamente.
     * 
     * @param pelicula objeto Película con los datos a insertar
     * @throws SQLException si ocurre un error durante la inserción
     */
    public void insertarPelicula(Pelicula pelicula) {
        String sql = "INSERT INTO cartelera (titulo, director, anio, duracion, genero) VALUES (?, ?, ?, ?, ?)"; // Sentencia INSERT
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DatabaseConnection.getConnection(); // Obtener la conexión
            pstmt = conn.prepareStatement(sql);

            // Asignar valores a los parámetros de la sentencia SQL
            pstmt.setString(1, pelicula.getTitulo());
            pstmt.setString(2, pelicula.getDirector());
            pstmt.setInt(3, pelicula.getAnio());
            pstmt.setInt(4, pelicula.getDuracion());
            pstmt.setString(5, pelicula.getGenero());

            int filasAfectadas = pstmt.executeUpdate(); // Ejecutar la actualización
            if (filasAfectadas > 0) {
                System.out.println("Película insertada con éxito: " + pelicula.getTitulo());
            } else { 
                System.out.println("No se pudo insertar la película.");
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar película: " +
            e.getMessage());
            e.printStackTrace();
        } finally {
            // Cerrar los recursos
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    // La conexión se gestiona en DatabaseConnection, así que no la cerramos aquí si es una conexión única.
    // Si la conexión se abre y cierra por cada operación, se cerraría aquí.
    // Para este ejemplo, asumimos que getConnection() devuelve una conexión persistente o la cierra el main.
        }
    }

    /**
     * Recupera todas las películas almacenadas en la base de datos.
     * 
     * Ejecuta una consulta SELECT para obtener todos los registros de la
     * tabla cartelera y los convierte en una lista de objetos Película.
     * 
     * @return Lista de objetos Película con todos los registros encontrados.
     *         Retorna una lista vacía si no hay películas registradas.
     */
    public List<Pelicula> obtenerTodasLasPeliculas() {
        List<Pelicula> peliculas = new ArrayList<>();
        String sql = "SELECT id, titulo, director, anio, duracion, genero FROM cartelera"; // Sentencia SELECT
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseConnection.getConnection(); // Obtener la conexión
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery(); // Ejecutar la consulta

            // Procesar los resultados
            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String director = rs.getString("director");
                int anio = rs.getInt("anio");
                int duracion = rs.getInt("duracion");
                String genero = rs.getString("genero");
                Pelicula pelicula = new Pelicula(id, titulo, director, anio, duracion, genero);
                peliculas.add(pelicula);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener películas: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cerrar los recursos en orden inverso de apertura
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return peliculas;
    }

    // ... futuros métodos CRUD ...
}
