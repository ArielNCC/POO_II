/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.tienda.pedidosonline.decorator.concretedecorator;

import com.tienda.pedidosonline.component.Component;
import com.tienda.pedidosonline.decorator.DescuentoDecorator;

/**
 * Decorador concreto que aplica un descuento por categoría
 * @author Nicolas Cavieres
 */

public class DescuentoCategoria extends DescuentoDecorator {
    private String categoria;
    private double porcentaje;

    public DescuentoCategoria(Component componente, String categoria, double porcentaje) {
        super(componente);
        this.categoria = categoria;
        this.porcentaje = porcentaje;
    }

    @Override
    public String getDescripcion() {
        return componente.getDescripcion() + " [Descuento Categoría " + categoria + "]";
    }

    @Override
    public double getPrecio() {
        // Verificar si el producto pertenece a la categoría
        if (componente.getDescripcion().contains(categoria)) {
            return componente.getPrecio() * (1 - porcentaje/100.0);
        } else {
            return componente.getPrecio();
        }
    }
}