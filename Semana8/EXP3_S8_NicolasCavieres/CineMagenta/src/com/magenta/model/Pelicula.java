package com.magenta.model;

/**
 * Clase modelo que representa una película en la cartelera del cine.
 * Contiene toda la información necesaria de una película incluyendo
 * referencias a director y género.
 * 
 * @author Nicolas Cavieres
 */
public class Pelicula {
    /** Identificador único de la película */
    private Integer id;
    /** Título de la película */
    private String titulo;
    /** Director de la película */
    private Director director;
    /** Año de estreno */
    private Integer anio;
    /** Duración en minutos */
    private Integer duracion;
    /** Género cinematográfico */
    private Genero genero;

    /**
     * Constructor por defecto
     */
    public Pelicula() {}

    /**
     * Constructor completo con todos los campos
     * @param id Identificador único de la película
     * @param titulo Título de la película
     * @param director Director de la película
     * @param anio Año de estreno
     * @param duracion Duración en minutos
     * @param genero Género cinematográfico
     */
    public Pelicula(Integer id, String titulo, Director director, Integer anio, Integer duracion, Genero genero) {
        this.id = id;
        this.titulo = titulo;
        this.director = director;
        this.anio = anio;
        this.duracion = duracion;
        this.genero = genero;
    }

    /**
     * Constructor para nueva película sin ID (será asignado por la BD)
     * @param titulo Título de la película
     * @param director Director de la película
     * @param anio Año de estreno
     * @param duracion Duración en minutos
     * @param genero Género cinematográfico
     */
    public Pelicula(String titulo, Director director, Integer anio, Integer duracion, Genero genero) {
        this(null, titulo, director, anio, duracion, genero);
    }

    /**
     * Obtiene el ID de la película
     * @return ID de la película
     */
    public Integer getId() { return id; }
    
    /**
     * Establece el ID de la película
     * @param id ID de la película
     */
    public void setId(Integer id) { this.id = id; }

    /**
     * Obtiene el título de la película
     * @return Título de la película
     */
    public String getTitulo() { return titulo; }
    
    /**
     * Establece el título de la película
     * @param titulo Título de la película
     */
    public void setTitulo(String titulo) { this.titulo = titulo; }

    /**
     * Obtiene el director de la película
     * @return Objeto Director
     */
    public Director getDirector() { return director; }
    
    /**
     * Establece el director de la película
     * @param director Objeto Director
     */
    public void setDirector(Director director) { this.director = director; }

    /**
     * Obtiene el año de estreno
     * @return Año de estreno
     */
    public Integer getAnio() { return anio; }
    
    /**
     * Establece el año de estreno
     * @param anio Año de estreno
     */
    public void setAnio(Integer anio) { this.anio = anio; }

    /**
     * Obtiene la duración en minutos
     * @return Duración en minutos
     */
    public Integer getDuracion() { return duracion; }
    
    /**
     * Establece la duración en minutos
     * @param duracion Duración en minutos
     */
    public void setDuracion(Integer duracion) { this.duracion = duracion; }

    /**
     * Obtiene el género cinematográfico
     * @return Objeto Genero
     */
    public Genero getGenero() { return genero; }
    
    /**
     * Establece el género cinematográfico
     * @param genero Objeto Genero
     */
    public void setGenero(Genero genero) { this.genero = genero; }

    /**
     * Representación en cadena de la película con todos sus datos
     * @return Cadena con la información completa de la película
     */
    @Override
    public String toString() {
        return "Pelicula{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", director=" + (director != null ? director.getNombre() : null) +
                ", anio=" + anio +
                ", duracion=" + duracion +
                ", genero=" + (genero != null ? genero.getNombre() : null) +
                '}';
    }
}

