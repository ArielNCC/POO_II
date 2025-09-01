/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.tienda.pedidosonline.command.concretecommand;

import com.tienda.pedidosonline.command.Command;
import com.tienda.pedidosonline.command.receiver.CarritoDeCompras;
import com.tienda.pedidosonline.model.Producto;

/**
 *
 * @author ariel
 */

public class AgregarProductoCarro implements Command {
    private CarritoDeCompras carrito;
    private Producto producto;

    public AgregarProductoCarro(CarritoDeCompras carrito, Producto producto) {
        this.carrito = carrito;
        this.producto = producto;
    }
    
    @Override
    public void ejecutar() {
        carrito.agregarProducto(producto);
    }
}
