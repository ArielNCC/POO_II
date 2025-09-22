/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.tienda.pedidosonline.command.concretecommand;

import com.tienda.pedidosonline.command.Command;
import com.tienda.pedidosonline.command.receiver.CarritoDeCompras;

/**
 * Comando para aplicar descuento por categoría
 * @author Nicolas Cavieres
 */

public class AplicarDescuentoCategoria implements Command {
    private CarritoDeCompras carrito;
    private String categoria;
    private double porcentaje;

    public AplicarDescuentoCategoria(CarritoDeCompras carrito, String categoria, double porcentaje) {
        this.carrito = carrito;
        this.categoria = categoria;
        this.porcentaje = porcentaje;
    }

    @Override
    public void ejecutar() {
        carrito.aplicarDescuentoCategoria(categoria, porcentaje);
    }
}