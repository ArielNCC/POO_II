/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.tienda.pedidosonline.decorator;

import com.tienda.pedidosonline.component.Component;

/**
 *
 * @author ariel
 */
// DescuentoDecorator.java

public abstract class DescuentoDecorator extends Component {
    protected Component componente;

    public DescuentoDecorator(Component componente) {
        this.componente = componente;
    }
}