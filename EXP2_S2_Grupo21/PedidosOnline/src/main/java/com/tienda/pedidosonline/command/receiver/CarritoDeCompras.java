/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.pedidosonline.command.receiver;

/**
 *
 * @author ariel
 */
// CarritoDeCompras.java (Receptor)
public class CarritoDeCompras {
    private String nombreUsuario;
    public CarritoDeCompras(String nombreUsuario) {
    this.nombreUsuario = nombreUsuario;
    }
    public void agregarProducto(String producto) {
    System.out.println(nombreUsuario + ": '"+ producto + "' agregado al carrito.");
    // Lógica real para añadir producto a una lista, base de datos, etc.
    }
    public void eliminarProducto(String producto) {
    System.out.println(nombreUsuario + ": '" + producto + "' eliminado del carrito.");
    // Lógica real para eliminar producto
    }
}