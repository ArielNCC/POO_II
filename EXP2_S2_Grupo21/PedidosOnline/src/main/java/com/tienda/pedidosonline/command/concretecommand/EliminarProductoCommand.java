/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.pedidosonline.command.concretecommand;

import com.tienda.pedidosonline.command.Command;
import com.tienda.pedidosonline.command.receiver.CarritoDeCompras;

/**
 *
 * @author ariel
 */
// EliminarProductoComando.java
public class EliminarProductoCommand implements Command {
    private CarritoDeCompras carrito; // Receptor
    private String producto;
    public EliminarProductoCommand(CarritoDeCompras carrito, String
   producto) {
    this.carrito = carrito;
    this.producto = producto;
    }
    @Override
    public void ejecutar() {
    // Delega la acción real al receptor
    carrito.eliminarProducto(producto);
    }
}