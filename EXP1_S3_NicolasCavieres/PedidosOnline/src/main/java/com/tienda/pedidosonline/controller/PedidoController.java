/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.tienda.pedidosonline.controller;

import com.tienda.pedidosonline.command.receiver.CarritoDeCompras;
import com.tienda.pedidosonline.component.Component;
import com.tienda.pedidosonline.model.Pedido;
import com.tienda.pedidosonline.model.Producto;
import com.tienda.pedidosonline.view.VistaCarrito;
import com.tienda.pedidosonline.model.singleton.DiscountManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Controlador para gestionar los pedidos y la visualización del carrito
 * @author Nicolas
 */
public class PedidoController implements VistaCarrito {

    private Pedido pedido;

    public PedidoController(Pedido pedido) {
        this.pedido = pedido;
    }

    public void agregarProducto(String nombre, double precio, String categoria) {
        pedido.agregarProducto(new Producto(nombre, precio, categoria));
    }

    @Override
    public void mostrarCarrito(CarritoDeCompras carrito) {
        DiscountManager dm = DiscountManager.getInstance();
        System.out.println("\n--- CARRITO DE COMPRAS ---");
        System.out.println("(Descuentos aplicados: " +
                dm.getDescuentoGeneral() + "% general + " +
                dm.getDescuentoRopa() + "% para Ropa + " +
                dm.getDescuentoTemporada() + "% temporada)");

        List<Component> productos = carrito.getProductos();
        if (productos.isEmpty()) {
            System.out.println("(El carrito está vacío)");
        } else {
            double totalSinDescuento = 0;
            double totalConDescuento = 0;

            List<Double> preciosOriginales = new ArrayList<>();
            for (Component prod : productos) {
                double precioOriginal = estimarPrecioOriginal(prod);
                preciosOriginales.add(precioOriginal);
                totalSinDescuento += precioOriginal;
                totalConDescuento += prod.getPrecio();
            }

            double totalDescuentos = totalSinDescuento - totalConDescuento;

            for (int i = 0; i < productos.size(); i++) {
                Component prod = productos.get(i);
                String descripcionBase = obtenerDescripcionBase(prod.getDescripcion());
                double precioOriginal = preciosOriginales.get(i);
                double precioConDescuento = prod.getPrecio();
                double descuento = precioOriginal - precioConDescuento;

                String nombreProducto = descripcionBase.split(" ")[0];

                System.out.printf("%-40s $%d\n", descripcionBase + ":", (int) Math.round(precioOriginal));

                if (descuento > 0) {
                    System.out.printf("→ %s con descuento:%s $%d (Ahorro: $%d)\n",
                            nombreProducto,
                            " ".repeat(22 - nombreProducto.length()),
                            (int) Math.round(precioConDescuento),
                            (int) Math.round(descuento));
                }
            }

            System.out.println("-----------------------------------------------------------");
            System.out.printf("%-40s $%d\n", "Total sin descuento", (int) Math.round(totalSinDescuento));
            System.out.printf("%-40s -$%d\n", "Total descuentos aplicados", (int) Math.round(totalDescuentos));
            System.out.printf("%-40s $%d\n", "TOTAL A PAGAR", (int) Math.round(totalConDescuento));
        }
    }

    @Override
    public void mostrarProductosNumerados(List<Component> productos) {
        System.out.println("\n--- PRODUCTOS EN EL CARRITO ---");
        if (productos.isEmpty()) {
            System.out.println("(El carrito está vacío)");
            return;
        }
        for (int i = 0; i < productos.size(); i++) {
            Component producto = productos.get(i);
            String descripcionBase = obtenerDescripcionBase(producto.getDescripcion());
            String nombreProducto = descripcionBase.split(" \\(")[0];
            System.out.printf("%d. %s\n", i + 1, nombreProducto);
        }
    }

    @Override
    public int solicitarProductoEliminar(int max, Scanner sc) {
        int seleccion = -1;
        int intentos = 0;
        final int MAX_INTENTOS = 5;

        while (intentos < MAX_INTENTOS) {
            try {
                System.out.print("Ingrese el número del producto que desea eliminar (0 para cancelar): ");
                String entrada = sc.nextLine();
                seleccion = Integer.parseInt(entrada);

                if (seleccion == 0 || (seleccion >= 1 && seleccion <= max)) {
                    return seleccion;
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
    public void mostrarConfirmacionEliminado(String nombre) {
        System.out.println("[OK] Producto '" + nombre + "' eliminado correctamente del carrito.");
    }

    // ---------------- MÉTODOS AUXILIARES ----------------

    private double estimarPrecioOriginal(Component producto) {
        String descripcion = producto.getDescripcion();
        String descripcionBase = obtenerDescripcionBase(descripcion);

        if (descripcionBase.contains("Polera")) return 15000.0;
        if (descripcionBase.contains("Pantalon")) return 25000.0;
        if (descripcionBase.contains("Zapatillas")) return 40000.0;

        double precioActual = producto.getPrecio();
        boolean esRopa = descripcionBase.contains("Ropa");

        DiscountManager dm = DiscountManager.getInstance();
        double descuentoGeneral = dm.getDescuentoGeneral();
        double descuentoRopa = dm.getDescuentoRopa();
        double descuentoTemporada = dm.getDescuentoTemporada();

        double precioOriginal = precioActual;
        precioOriginal = precioOriginal / (1 - descuentoTemporada / 100.0);
        if (esRopa) precioOriginal = precioOriginal / (1 - descuentoRopa / 100.0);
        precioOriginal = precioOriginal / (1 - descuentoGeneral / 100.0);

        return precioOriginal;
    }

    private String obtenerDescripcionBase(String descripcionCompleta) {
        int indiceDescuento = descripcionCompleta.indexOf("[Descuento");
        if (indiceDescuento != -1) {
            return descripcionCompleta.substring(0, indiceDescuento).trim();
        }
        return descripcionCompleta;
    }
}