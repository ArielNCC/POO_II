/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.tienda.pedidosonline.command.concretecommand;

import com.tienda.pedidosonline.command.Command;
import com.tienda.pedidosonline.command.receiver.CarritoDeCompras;

/**
 * Comando para aplicar descuento porcentual
 * @author Nicolas Cavieres
 */

public class AplicarDescuentoPorcentaje implements Command {
    private CarritoDeCompras carrito;
    private double porcentaje;

    public AplicarDescuentoPorcentaje(CarritoDeCompras carrito, double porcentaje) {
        this.carrito = carrito;
        this.porcentaje = porcentaje;
    }

    @Override
    public void ejecutar() {
        carrito.aplicarDescuentoPorcentaje(porcentaje);
    }
}
