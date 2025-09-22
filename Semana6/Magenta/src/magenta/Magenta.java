package magenta;

import magenta.dao.PeliculaDAO;
import magenta.model.Pelicula;
import magenta.util.DatabaseConnection;
import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal del sistema de gestión de cartelera de películas Magenta.
 * 
 * Esta aplicación permite administrar una base de datos de películas mediante
 * un menú de consola interactivo que ofrece funcionalidades para agregar
 * nuevas películas y listar todas las películas existentes.
 * 
 * Proyecto desarrollado como tarea para la asignatura Programación Orientada 
 * a Objetos II de DuocUC.
 * 
 * @author Nicolás Cavieres
 * @author Luis Rebolledo
 * @version 1.0
 * @since 2025
 */
public class Magenta {
    
    /** Scanner para leer entrada del usuario desde la consola */
    private static final Scanner scanner = new Scanner(System.in);
    
    /** DAO para gestionar operaciones de base de datos relacionadas con películas */
    private static final PeliculaDAO peliculaDAO = new PeliculaDAO();
    
    /**
     * Método principal que inicia la aplicación.
     * 
     * Configura la codificación UTF-8, establece conexión con la base de datos
     * y muestra el menú principal si la conexión es exitosa.
     * 
     * @param args argumentos de línea de comandos (no utilizados)
     * @throws Exception si ocurre un error durante la ejecución
     */
    public static void main(String[] args) throws Exception {
        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        
        // Verificar conexión a la base de datos
        Connection conn = DatabaseConnection.getConnection();
        if (conn != null) {
            System.out.println("La aplicación está conectada a la base de datos.");
            mostrarMenu();
        } else {
            System.out.println("No se pudo establecer la conexión con la base de datos.");
            return;
        }
        
        // Cerrar conexión al finalizar
        DatabaseConnection.closeConnection();
    }
    
    /**
     * Muestra el menú principal de la aplicación y maneja la navegación.
     * 
     * Presenta un bucle que muestra las opciones disponibles y ejecuta
     * la funcionalidad correspondiente según la selección del usuario.
     * Incluye validación de entrada y manejo de errores.
     */
    private static void mostrarMenu() {
        int opcion;
        
        do {
            System.out.println("\n=== MAGENTA DB - CARTELERA ===");
            System.out.println("1. Agregar Película");
            System.out.println("2. Listar todas las Películas");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            
            try {
                opcion = Integer.parseInt(scanner.nextLine().trim());
                
                switch (opcion) {
                    case 1 -> agregarPelicula();
                    case 2 -> listarPeliculas();
                    case 3 -> System.out.println("¡Gracias por usar Magenta DB!");
                    default -> System.out.println("Opción inválida. Por favor seleccione una opción del 1 al 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
                opcion = 0; // Para continuar el loop
            }
        } while (opcion != 3);
    }
    
    /**
     * Gestiona el proceso de agregar una nueva película a la base de datos.
     * 
     * Solicita al usuario todos los datos necesarios (título, director, año,
     * duración y género) con validaciones correspondientes, y luego crea
     * un objeto Película que se inserta en la base de datos.
     * 
     * Incluye validaciones para:
     * - Campos obligatorios no vacíos
     * - Año dentro de rango válido (1900-2030)
     * - Duración mayor a 0
     * - Género seleccionado de lista predefinida
     */
    private static void agregarPelicula() {
        System.out.println("\n--- AGREGAR NUEVA PELÍCULA ---");
        
        try {
            // Título
            String titulo;
            do {
                System.out.print("Título: ");
                titulo = scanner.nextLine().trim();
                if (titulo.isEmpty()) {
                    System.out.println("El título no puede estar vacío. Intente nuevamente.");
                }
            } while (titulo.isEmpty());
            
            // Director
            String director;
            do {
                System.out.print("Director: ");
                director = scanner.nextLine().trim();
                if (director.isEmpty()) {
                    System.out.println("El director no puede estar vacío. Intente nuevamente.");
                }
            } while (director.isEmpty());
            
            // Año
            int anio = 0;
            boolean anioValido = false;
            do {
                System.out.print("Año (ej: 2023): ");
                try {
                    anio = Integer.parseInt(scanner.nextLine().trim());
                    if (anio < 1900 || anio > 2030) {
                        System.out.println("El año debe estar entre 1900 y 2030. Intente nuevamente.");
                    } else {
                        anioValido = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Debe ingresar un año válido. Intente nuevamente.");
                }
            } while (!anioValido);
            
            // Duración
            int duracion = 0;
            boolean duracionValida = false;
            do {
                System.out.print("Duración en minutos (ej: 120): ");
                try {
                    duracion = Integer.parseInt(scanner.nextLine().trim());
                    if (duracion <= 0) {
                        System.out.println("La duración debe ser mayor a 0. Intente nuevamente.");
                    } else {
                        duracionValida = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Debe ingresar una duración válida. Intente nuevamente.");
                }
            } while (!duracionValida);
            
            // Género
            System.out.println("Géneros disponibles:");
            String[] generos = {"Comedia", "Drama", "Accion", "Terror", "Romance", "Documental"};
            for (int i = 0; i < generos.length; i++) {
                System.out.println((i + 1) + ". " + generos[i]);
            }
            
            int opcionGenero = 0;
            boolean generoValido = false;
            String generoSeleccionado = "";
            do {
                System.out.print("Seleccione el género (1-6): ");
                try {
                    opcionGenero = Integer.parseInt(scanner.nextLine().trim());
                    if (opcionGenero >= 1 && opcionGenero <= 6) {
                        generoSeleccionado = generos[opcionGenero - 1];
                        generoValido = true;
                    } else {
                        System.out.println("Debe seleccionar una opción del 1 al 6. Intente nuevamente.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Debe ingresar un número válido. Intente nuevamente.");
                }
            } while (!generoValido);
            
            // Crear y guardar la película
            Pelicula nuevaPelicula = new Pelicula(titulo, director, anio, duracion, generoSeleccionado);
            peliculaDAO.insertarPelicula(nuevaPelicula);
            System.out.println("¡Película agregada exitosamente!");
            
        } catch (Exception e) {
            System.out.println("Error al agregar la película: " + e.getMessage());
        }
    }
    
    /**
     * Obtiene y muestra todas las películas almacenadas en la base de datos.
     * 
     * Recupera la lista completa de películas usando PeliculaDAO y las
     * presenta en formato legible. Si no hay películas registradas,
     * muestra un mensaje informativo.
     * 
     * Incluye manejo de errores para problemas de conexión o consulta
     * a la base de datos.
     */
    private static void listarPeliculas() {
        System.out.println("\n--- LISTADO DE PELÍCULAS ---");
        
        try {
            List<Pelicula> peliculas = peliculaDAO.obtenerTodasLasPeliculas();
            
            if (peliculas.isEmpty()) {
                System.out.println("No hay películas registradas en la base de datos.");
            } else {
                System.out.println("Total de películas: " + peliculas.size() + "\n");
                for (Pelicula pelicula : peliculas) {
                    System.out.println(pelicula.toString());
                    System.out.println("----------------------------------------");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al obtener las películas: " + e.getMessage());
        }
    }
}
