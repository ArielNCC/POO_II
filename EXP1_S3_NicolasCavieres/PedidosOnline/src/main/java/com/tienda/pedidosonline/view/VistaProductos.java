/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.tienda.pedidosonline.view;

import com.tienda.pedidosonline.controller.ProductoController;
import java.util.Scanner;

/**
 * Interfaz para la vista de productos
 * Define los contratos que debe cumplir cualquier implementación
 * @author Nicolas
 */
public interface VistaProductos {

    int mostrarProductos(ProductoController pc, Scanner sc);

    void mostrarConfirmacionAgregar(String nombre);
}