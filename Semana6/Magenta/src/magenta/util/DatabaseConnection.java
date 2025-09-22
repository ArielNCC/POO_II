package magenta.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase utilitaria para gestionar la conexión a la base de datos MySQL.
 * 
 * Implementa el patrón Singleton para mantener una única instancia de
 * conexión durante toda la ejecución de la aplicación. Proporciona
 * métodos para establecer y cerrar la conexión de forma segura.
 * 
 * Configuración de conexión:
 * - Host: localhost
 * - Puerto: 3306
 * - Base de datos: magenta_db
 * - Usuario: root
 * - Contraseña: admin
 * 
 * @author Nicolás Cavieres
 * @author Luis Rebolledo
 * @version 1.0
 * @since 2025
 */
public class DatabaseConnection {
    
    /** URL de conexión a la base de datos MySQL */
    private static final String URL = "jdbc:mysql://localhost:3306/magenta_db?useSSL=false&serverTimezone=UTC";
    
    /** Usuario para la conexión a la base de datos */
    private static final String USER = "root"; // cámbialo si usas otro usuario
    
    /** Contraseña para la conexión a la base de datos */
    private static final String PASSWORD = "admin";
    
    /** Instancia única de conexión (patrón Singleton) */
    private static Connection connection = null;

    /**
     * Establece una conexión a la base de datos MySQL usando el patrón Singleton.
     * 
     * Si no existe una conexión activa, carga el driver JDBC de MySQL y
     * establece una nueva conexión. Si ya existe una conexión, la reutiliza.
     * 
     * @return Un objeto Connection si la conexión es exitosa, null en caso contrario
     */
    public static Connection getConnection() {
        if (connection == null) {
        try {
            // Paso 1: Cargar el controlador JDBC de MySQL
            // El Class.forName() registra el controlador con el DriverManager
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Controlador JDBC cargado correctamente."); // Mensaje de confirmación
            // Paso 2: Establecer la conexión utilizando DriverManager
            connection = DriverManager.getConnection(URL, USER,PASSWORD);
            System.out.println("Conexión a la base de datos Cine_DB establecida con éxito."); // Mensaje de confirmación
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró la clase del controlador JDBC de MySQL.");
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos:" + e.getMessage()); // Manejo de errores de acceso a la base de datos
            }
        }
        return connection;
    }
    
    /**
     * Cierra la conexión activa a la base de datos de forma segura.
     * 
     * Si existe una conexión activa, la cierra y resetea la variable
     * de instancia a null para permitir nuevas conexiones futuras.
     * Maneja las excepciones SQL que puedan ocurrir durante el cierre.
     */
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null; // Resetear la conexión
                System.out.println("Conexión a la base de datos cerrada.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
 
    /**
     * Método principal para pruebas de conexión a la base de datos.
     * 
     * Ejecuta una prueba básica de conexión, mostrando mensajes
     * informativos sobre el estado de la conexión.
     * 
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        // Prueba de la conexión
        Connection conn = DatabaseConnection.getConnection();
        if (conn != null) {
            System.out.println("La aplicación está conectada a la base de datos.");
            DatabaseConnection.closeConnection();
            } else {
                System.out.println("La aplicación NO pudo conectarse a la base de datos.");
            }
        }
    }