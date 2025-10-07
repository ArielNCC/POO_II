package com.magenta.model;

/**
 * Clase modelo que representa un director de películas.
 * Contiene la información básica de un director incluyendo ID y nombre.
 * 
 * @author Nicolas Cavieres
 */
public class Director {
    /** Identificador único del director */
    private Integer id;
    /** Nombre completo del director */
    private String nombre;

    /**
     * Constructor por defecto
     */
    public Director() {}
    
    /**
     * Constructor completo con ID y nombre
     * @param id Identificador único del director
     * @param nombre Nombre completo del director
     */
    public Director(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    /**
     * Constructor para nuevo director sin ID (será asignado por la BD)
     * @param nombre Nombre completo del director
     */
    public Director(String nombre) {
        this(null, nombre);
    }
    /**
     * Obtiene el ID del director
     * @return ID del director
     */
    public Integer getId() { return id; }
    
    /**
     * Establece el ID del director
     * @param id ID del director
     */
    public void setId(Integer id) { this.id = id; }
    
    /**
     * Obtiene el nombre del director
     * @return Nombre completo del director
     */
    public String getNombre() { return nombre; }
    
    /**
     * Establece el nombre del director
     * @param nombre Nombre completo del director
     */
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    /**
     * Representación en cadena del director (solo el nombre)
     * @return Nombre del director
     */
    @Override
    public String toString() { return nombre; }
}
