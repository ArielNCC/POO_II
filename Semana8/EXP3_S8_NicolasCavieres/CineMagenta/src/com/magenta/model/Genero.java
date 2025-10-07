package com.magenta.model;

/**
 * Clase modelo que representa un género cinematográfico.
 * Define los diferentes tipos de géneros disponibles para las películas.
 * 
 * @author Nicolas Cavieres
 */
public class Genero {
    /** Identificador único del género */
    private Integer id;
    /** Nombre del género cinematográfico */
    private String nombre;

    /**
     * Constructor por defecto
     */
    public Genero() {}
    
    /**
     * Constructor completo con ID y nombre
     * @param id Identificador único del género
     * @param nombre Nombre del género cinematográfico
     */
    public Genero(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    /**
     * Constructor para nuevo género sin ID (será asignado por la BD)
     * @param nombre Nombre del género cinematográfico
     */
    public Genero(String nombre) {
        this(null, nombre);
    }
    /**
     * Obtiene el ID del género
     * @return ID del género
     */
    public Integer getId() { return id; }
    
    /**
     * Establece el ID del género
     * @param id ID del género
     */
    public void setId(Integer id) { this.id = id; }
    
    /**
     * Obtiene el nombre del género
     * @return Nombre del género cinematográfico
     */
    public String getNombre() { return nombre; }
    
    /**
     * Establece el nombre del género
     * @param nombre Nombre del género cinematográfico
     */
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    /**
     * Representación en cadena del género (solo el nombre)
     * @return Nombre del género
     */
    @Override
    public String toString() { return nombre; }
}
