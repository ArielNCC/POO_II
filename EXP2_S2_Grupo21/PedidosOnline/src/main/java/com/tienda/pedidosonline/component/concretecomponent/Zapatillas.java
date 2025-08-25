/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.pedidosonline.component.concretecomponent;

import com.tienda.pedidosonline.component.Component;

/**
 *
 * @author ariel
 */
// ProductoBase.java
public class Zapatillas implements Component {
    private String nombre;
    private double precio;
    private String categoria;
    
    public Zapatillas (String nombre, double precio, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }
    @Override
    public double getPrecio() {
        return this.precio;
    }
    @Override
    public String getDescripcion() {
        return this.nombre + " | Categoria: " + this.categoria;
    }
}