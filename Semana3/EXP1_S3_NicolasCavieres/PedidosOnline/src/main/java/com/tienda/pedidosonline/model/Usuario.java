/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.tienda.pedidosonline.model;

/**
 * Modelo que representa a un Usuario en el sistema
 * @author Nicolas Cavieres
 */

public class Usuario {
    private String nombre;

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() { return nombre; }
}