/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.tienda.pedidosonline.controller;

import com.tienda.pedidosonline.model.Producto;
import com.tienda.pedidosonline.view.VistaProductos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Controlador para gestionar los productos de la tienda
 * Implementa la lógica que antes estaba en VistaProductos
 * @author Nicolas
 */
public class ProductoController implements VistaProductos {

    private List<Producto> productos = new ArrayList<>();

    // ---------------- MÉTODOS DE NEGOCIO ----------------
    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public List<Producto> getProductos() {
        return productos;
    }

    // ---------------- IMPLEMENTACIÓN DE VistaProductos ----------------
    @Override
    public int mostrarProductos(ProductoController pc, Scanner sc) {
        List<Producto> productos = pc.getProductos();
        int seleccion = -1;
        int intentos = 0;
        final int MAX_INTENTOS = 5;

        while (intentos < MAX_INTENTOS) {
            try {
                // Mostrar catálogo
                System.out.println("\n--- PRODUCTOS DISPONIBLES ---");
                for (int i = 0; i < productos.size(); i++) {
                    Producto p = productos.get(i);
                    System.out.printf("%d. %-15s $%-10d [%s]\n",
                            i + 1, p.getNombre(),
                            (int) Math.round(p.getPrecio()),
                            p.getCategoria());
                }
                System.out.println("0. Volver");
                System.out.print("Seleccione el número de producto: ");

                String entrada = sc.nextLine();
                seleccion = Integer.parseInt(entrada);

                if (seleccion >= 0 && seleccion <= productos.size()) {
                    return seleccion;
                } else {
                    intentos++;
                    System.out.println("[X] Numero fuera de rango. Intento " + intentos + "/" + MAX_INTENTOS);
                }
            } catch (NumberFormatException e) {
                intentos++;
                System.out.println("[X] Entrada invalida. Intento " + intentos + "/" + MAX_INTENTOS);
            } catch (Exception e) {
                intentos++;
                System.out.println("[X] Error inesperado. Intento " + intentos + "/" + MAX_INTENTOS);
            }
        }
        return -1; // Nunca debería llegar aquí
    }

    @Override
    public void mostrarConfirmacionAgregar(String nombre) {
        System.out.println("[OK] Producto '" + nombre + "' agregado correctamente al carrito.");
    }
}
