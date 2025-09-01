/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.tienda.pedidosonline.view;

import com.tienda.pedidosonline.command.receiver.CarritoDeCompras;
import com.tienda.pedidosonline.component.Component;

import java.util.List;
import java.util.Scanner;

/**
 * Interfaz para la vista del carrito de compras
 * @author Nicolas
 */
public interface VistaCarrito {

    void mostrarCarrito(CarritoDeCompras carrito);

    void mostrarProductosNumerados(List<Component> productos);

    int solicitarProductoEliminar(int max, Scanner sc);

    void mostrarConfirmacionEliminado(String nombre);
}