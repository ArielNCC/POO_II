/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.pedidosonline.decorator;

import com.tienda.pedidosonline.component.Component;

/**
 *
 * @author ariel
 */
// DescuentoDecorator.java
public abstract class DescuentoDecorator implements Component {
    protected Component componenteDecorado; // Referencia al objeto a decorar
    public DescuentoDecorator(Component componenteDecorado) {
    this.componenteDecorado = componenteDecorado;
    }
    // Los métodos abstractos serán implementados por los decoradores concretos.
    // Por simplicidad, aquí los implementamos para delegar al componente original
    // y permitir que los decoradores concretos los sobrescriban para añadir funcionalidad.
    @Override
    public double getPrecio() {
    return componenteDecorado.getPrecio();
    }
    @Override
    public String getDescripcion() {
    return componenteDecorado.getDescripcion();
    }
   }

