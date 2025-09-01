/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.tienda.pedidosonline.component.concretecomponent;

import com.tienda.pedidosonline.component.Component;
import com.tienda.pedidosonline.model.Producto;

/**
 *
 * @author ariel
 */

public class ProductoBase extends Component {
    private Producto producto;

    public ProductoBase(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String getDescripcion() {
        return producto.getNombre() + " (" + producto.getCategoria() + ")";
    }

    @Override
    public double getPrecio() {
        return producto.getPrecio();
    }
}