/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.tienda.pedidosonline.command.concretecommand;

import com.tienda.pedidosonline.command.Command;
import com.tienda.pedidosonline.command.receiver.CarritoDeCompras;
import com.tienda.pedidosonline.model.Producto;

/**
 * Comando para eliminar un producto del carrito
 * Soporta eliminación por producto específico o por índice mediante sobrecarga de constructores
 * @author Nicolas Cavieres
 */
public class EliminarProductoCarro implements Command {
    private CarritoDeCompras carrito;
    private Producto producto;
    private int indice;
    private boolean eliminarPorIndice;

    /**
     * Constructor para eliminar por producto específico
     * @param carrito Carrito del cual eliminar
     * @param producto Producto a eliminar
     */
    public EliminarProductoCarro(CarritoDeCompras carrito, Producto producto) {
        this.carrito = carrito;
        this.producto = producto;
        this.eliminarPorIndice = false;
    }

    /**
     * Constructor para eliminar por índice
     * @param carrito Carrito del cual eliminar
     * @param indice Índice del producto a eliminar
     */
    public EliminarProductoCarro(CarritoDeCompras carrito, int indice) {
        this.carrito = carrito;
        this.indice = indice;
        this.eliminarPorIndice = true;
    }

    @Override
    public void ejecutar() {
        if (eliminarPorIndice) {
            carrito.eliminarProductoPorIndice(indice);
        } else {
            carrito.eliminarProducto(producto);
        }
    }
}