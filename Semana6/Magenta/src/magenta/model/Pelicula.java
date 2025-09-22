package magenta.model;

/**
 * Clase modelo que representa una película en el sistema de cartelera.
 * 
 * Esta clase encapsula todos los atributos y comportamientos relacionados
 * con una película, incluyendo información básica como título, director,
 * año de lanzamiento, duración y género.
 * 
 * Proporciona dos constructores: uno para inserción (sin ID) y otro para
 * lectura desde base de datos (con ID).
 * 
 * @author Nicolás Cavieres
 * @author Luis Rebolledo
 * @version 1.0
 * @since 2025
 */
public class Pelicula {
    /** Identificador único de la película (auto-generado por la base de datos) */
    private int id;
    
    /** Título de la película */
    private String titulo;
    
    /** Director de la película */
    private String director;
    
    /** Año de lanzamiento de la película */
    private int anio;
    
    /** Duración de la película en minutos */
    private int duracion;
    
    /** Género cinematográfico de la película */
    private String genero;

    /**
     * Constructor para crear una película nueva (inserción en base de datos).
     * 
     * Este constructor se utiliza cuando se va a insertar una nueva película
     * en la base de datos. El ID se omite ya que es auto-generado.
     * 
     * @param titulo el título de la película
     * @param director el director de la película
     * @param anio el año de lanzamiento
     * @param duracion la duración en minutos
     * @param genero el género cinematográfico
     */
    public Pelicula(String titulo, String director, int anio, int duracion,
        String genero) {
        this.titulo = titulo;
        this.director = director;
        this.anio = anio;
        this.duracion = duracion;
        this.genero = genero;
        }

    /**
     * Constructor completo para crear una película con ID (lectura desde base de datos).
     * 
     * Este constructor se utiliza cuando se recuperan datos desde la base de datos,
     * donde el ID ya existe y es conocido.
     * 
     * @param id el identificador único de la película
     * @param titulo el título de la película
     * @param director el director de la película
     * @param anio el año de lanzamiento
     * @param duracion la duración en minutos
     * @param genero el género cinematográfico
     */
    public Pelicula(int id, String titulo, String director, int anio, int
        duracion, String genero) {
        this.id = id;
        this.titulo = titulo;
        this.director = director;
        this.anio = anio;
        this.duracion = duracion;
        this.genero = genero;
        }

    // Métodos getter y setter
    
    /**
     * Obtiene el ID de la película.
     * @return el identificador único de la película
     */
    public int getId() { return id; }
    
    /**
     * Establece el ID de la película.
     * @param id el identificador único a asignar
     */
    public void setId(int id) { this.id = id; }
    
    /**
     * Obtiene el título de la película.
     * @return el título de la película
     */
    public String getTitulo() { return titulo; }
    
    /**
     * Establece el título de la película.
     * @param titulo el título a asignar
     */
    public void setTitulo(String titulo) { this.titulo = titulo; }
    
    /**
     * Obtiene el director de la película.
     * @return el director de la película
     */
    public String getDirector() { return director; }
    
    /**
     * Establece el director de la película.
     * @param director el director a asignar
     */
    public void setDirector(String director) { this.director = director; }
    
    /**
     * Obtiene el año de lanzamiento de la película.
     * @return el año de lanzamiento
     */
    public int getAnio() { return anio; }
    
    /**
     * Establece el año de lanzamiento de la película.
     * @param anio el año a asignar
     */
    public void setAnio(int anio) { this.anio = anio; }
    
    /**
     * Obtiene la duración de la película en minutos.
     * @return la duración en minutos
     */
    public int getDuracion() { return duracion; }
    
    /**
     * Establece la duración de la película.
     * @param duracion la duración en minutos a asignar
     */
    public void setDuracion(int duracion) { this.duracion = duracion; }
    
    /**
     * Obtiene el género de la película.
     * @return el género cinematográfico
     */
    public String getGenero() { return genero; }
    
    /**
     * Establece el género de la película.
     * @param genero el género a asignar
     */
    public void setGenero(String genero) { this.genero = genero; }

    /**
     * Retorna una representación en cadena de la película.
     * 
     * Incluye todos los atributos de la película en un formato legible
     * para mostrar en consola o logs.
     * 
     * @return cadena con la información completa de la película
     */
    @Override
    public String toString() {
        return "ID: " + id + ", Título: " + titulo + ", Director: " + director 
        + ", Año: " + anio + ", Duración: " + duracion + ", Género: " + genero;
        }
}
