/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.pedidosonline.decorator.concretedecorator;

import com.tienda.pedidosonline.component.Component;
import com.tienda.pedidosonline.decorator.DescuentoDecorator;

/**
 *
 * @author ariel
 */

// DescuentoPorcentaje.java
public class DescuentoPorcentaje extends DescuentoDecorator {
    private double porcentaje;
    public DescuentoPorcentaje(Component componenteDecorado, double
   porcentaje) {
    super(componenteDecorado);
    this.porcentaje = porcentaje;
    }
    @Override
    public double getPrecio() {
    // Aplica el descuento porcentual al precio del componente decorado
    return componenteDecorado.getPrecio() * (1 - porcentaje / 100);
    }
    @Override
    public String getDescripcion() {
    return componenteDecorado.getDescripcion() + " (Descuento " +
   porcentaje + "%)";
    }
}
