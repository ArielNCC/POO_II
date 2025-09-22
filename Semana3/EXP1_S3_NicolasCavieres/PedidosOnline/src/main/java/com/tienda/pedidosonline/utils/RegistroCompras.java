/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.tienda.pedidosonline.utils;

import com.tienda.pedidosonline.model.Pedido;
import com.tienda.pedidosonline.component.Component;
import com.tienda.pedidosonline.model.singleton.DiscountManager;

import java.util.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase para registrar y mantener el historial de compras
 * @author Nicolas Cavieres
 */
public class RegistroCompras {
    private List<Pedido> historial = new ArrayList<>();
    private static final String ARCHIVO = "registro_de_compras.txt";
    private final Random random = new Random();

    public void registrarCompra(Pedido pedido, List<Component> productos, double totalSinDescuento, double totalConDescuento) {
        historial.add(pedido);
        guardarCompraDetalladaEnArchivo(productos, totalSinDescuento, totalConDescuento);
    }

    private String generarIdCompra() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    private void guardarCompraDetalladaEnArchivo(List<Component> productos, double totalSinDescuento, double totalConDescuento) {
        try (FileWriter fw = new FileWriter(ARCHIVO, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            LocalDateTime ahora = LocalDateTime.now();
            DateTimeFormatter formatterFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");

            String idCompra = generarIdCompra();

            bw.write("----- COMPRA -----\n");
            bw.write("ID_COMPRA: " + idCompra + "\n");
            bw.write("Fecha: " + ahora.format(formatterFecha) + "\n");
            bw.write("Hora: " + ahora.format(formatterHora) + "\n");
            bw.write("\n");

            bw.write("Resumen del Carrito:\n");
            bw.write("# | Producto | Categoria | Precio: $ | con descuento: $\n");

            for (int i = 0; i < productos.size(); i++) {
                Component producto = productos.get(i);
                String descripcion = producto.getDescripcion();

                String nombreProducto = "Desconocido";
                String categoria = "N/A";
                if (descripcion.contains("(") && descripcion.contains(")")) {
                    String[] partes = descripcion.split("\\(|\\)");
                    nombreProducto = partes[0].trim();
                    categoria = partes[1].trim();
                }

                double precioConDescuento = producto.getPrecio();
                double precioOriginal = estimarPrecioOriginal(producto);

                bw.write(String.format("%d | %s | %s | %.0f | %.0f\n",
                        i + 1,
                        nombreProducto,
                        categoria,
                        precioOriginal,
                        precioConDescuento));
            }

            bw.write("\n");

            double totalDescuentos = totalSinDescuento - totalConDescuento;
            bw.write("Resumen de descuentos:\n");
            bw.write(String.format("Total sin descuentos: $%.0f\n", totalSinDescuento));
            bw.write(String.format("Total descuentos aplicados: $%.0f\n", totalDescuentos));
            bw.write(String.format("TOTAL A PAGAR: $%.0f\n", totalConDescuento));
            bw.write("-------- FIN ----------\n\n");

        } catch (IOException e) {
            System.out.println("Error al guardar la compra: " + e.getMessage());
        }
    }

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

    public List<Pedido> getHistorial() {
        return historial;
    }
}