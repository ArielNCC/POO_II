/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.tienda.pedidosonline.view;

import java.util.Scanner;

/**
 * Interfaz para la vista de descuentos
 * Define los contratos que debe cumplir cualquier implementación
 * @author Nicolas
 */
public interface VistaDescuentos {

    int mostrarOpcionesDescuento(Scanner sc);

    double solicitarPorcentaje(Scanner sc);

    String solicitarCategoria(Scanner sc);

    void mostrarConfirmacion(String tipo, double porcentaje, String categoria);
}