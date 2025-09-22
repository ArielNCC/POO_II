/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.tienda.pedidosonline.controller;

import com.tienda.pedidosonline.view.VistaDescuentos;

import java.util.Scanner;

/**
 * Controlador para gestionar la lógica de aplicación de descuentos
 * Implementa la lógica que antes estaba en VistaDescuentos
 * @author Nicolas
 */
public class DescuentoController implements VistaDescuentos {

    @Override
    public int mostrarOpcionesDescuento(Scanner sc) {
        int opcion = -1;
        int intentos = 0;
        final int MAX_INTENTOS = 5;

        while (intentos < MAX_INTENTOS) {
            try {
                System.out.println("\n--- APLICAR DESCUENTO ---");
                System.out.println("1. Descuento Porcentaje");
                System.out.println("2. Descuento Categoría");
                System.out.println("3. Descuento Temporada");
                System.out.println("0. Volver al menú anterior");
                System.out.print("Seleccione una opción: ");

                String entrada = sc.nextLine();
                opcion = Integer.parseInt(entrada);

                if (opcion >= 0 && opcion <= 3) {
                    return opcion;
                } else {
                    intentos++;
                    System.out.println("[X] Numero fuera de rango. Intento " + intentos + "/" + MAX_INTENTOS);
                }
            } catch (NumberFormatException e) {
                intentos++;
                System.out.println("[X] Entrada invalida. Intento " + intentos + "/" + MAX_INTENTOS);
            }
        }
        return -1;
    }

    @Override
    public double solicitarPorcentaje(Scanner sc) {
        int intentos = 0;
        final int MAX_INTENTOS = 5;
        while (intentos < MAX_INTENTOS) {
            try {
                System.out.print("Ingrese el porcentaje de descuento (1-100): ");
                double porcentaje = Double.parseDouble(sc.nextLine());
                if (porcentaje >= 1 && porcentaje <= 100) return porcentaje;
                intentos++;
                System.out.println("[X] El porcentaje debe estar entre 1 y 100. Intento " + intentos + "/" + MAX_INTENTOS);
            } catch (Exception e) {
                intentos++;
                System.out.println("[X] Entrada invalida. Intento " + intentos + "/" + MAX_INTENTOS);
            }
        }
        return -1;
    }

    @Override
    public String solicitarCategoria(Scanner sc) {
        int intentos = 0;
        final int MAX_INTENTOS = 5;
        while (intentos < MAX_INTENTOS) {
            System.out.print("Ingrese la categoría a la que desea aplicar el descuento: ");
            String categoria = sc.nextLine().trim();
            if (!categoria.isEmpty()) return categoria;
            intentos++;
            System.out.println("[X] La categoria no puede estar vacia. Intento " + intentos + "/" + MAX_INTENTOS);
        }
        return "";
    }

    @Override
    public void mostrarConfirmacion(String tipo, double porcentaje, String categoria) {
        String msj = "\n[OK] Descuento aplicado correctamente: ";
        switch (tipo) {
            case "porcentaje":
                msj += porcentaje + "% a todos los productos.";
                break;
            case "categoria":
                msj += porcentaje + "% a la categoría '" + categoria + "'.";
                break;
            case "temporada":
                msj += porcentaje + "% por promoción de temporada.";
                break;
            default:
                msj += "¡Acción completada!";
        }
        System.out.println(msj);
    }
}
