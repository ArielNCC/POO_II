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
// AgregarProductoComando.java
public class AgregarProductoCommand implements Command {
    private CarritoDeCompras carrito; // Receptor
    private String producto;
    public AgregarProductoCommand(CarritoDeCompras carrito, String
   producto) {
    this.carrito = carrito;
    this.producto = producto;
    }
    
    @Override
    public void ejecutar() {
        // Delega la acción real al recepto   
        carrito.agregarProducto(producto);
    }

}
