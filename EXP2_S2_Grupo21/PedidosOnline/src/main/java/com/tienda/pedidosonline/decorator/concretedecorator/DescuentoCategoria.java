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

// DescuentoCategoria.java
public class DescuentoCategoria extends DescuentoDecorator {
    private double cantidadDescuento;
    
    public DescuentoCategoria(Component componenteDecorado, double cantidadDescuento) {
        super(componenteDecorado);
        this.cantidadDescuento = cantidadDescuento;
    }
    
    @Override
    public double getPrecio() {
    // Aplica un descuento fijo al precio del componente decorado
        double precioFinal = componenteDecorado.getPrecio() - cantidadDescuento;
        return (precioFinal < 0) ? 0 : precioFinal; // Asegura que el precio no sea negativo
    }
    
    @Override
    public String getDescripcion() {
        return componenteDecorado.getDescripcion() + " (Descuento Fijo $" + cantidadDescuento + ")";
    }
}