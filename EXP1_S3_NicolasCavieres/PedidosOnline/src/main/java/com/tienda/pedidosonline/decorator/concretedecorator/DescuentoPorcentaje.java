/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.tienda.pedidosonline.decorator.concretedecorator;

import com.tienda.pedidosonline.component.Component;
import com.tienda.pedidosonline.decorator.DescuentoDecorator;

/**
 * Decorador concreto que aplica un descuento porcentual
 * @author Nicolas Cavieres
 */

public class DescuentoPorcentaje extends DescuentoDecorator {
    private double porcentaje;

    public DescuentoPorcentaje(Component componente, double porcentaje) {
        super(componente);
        this.porcentaje = porcentaje;
    }

    @Override
    public String getDescripcion() {
        return componente.getDescripcion() + " [Descuento " + porcentaje + "%]";
    }

    @Override
    public double getPrecio() {
        return componente.getPrecio() * (1 - porcentaje/100.0);
    }
}