/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.pedidosonline.command.receiver;

import java.util.ArrayList;
import java.util.List;

import com.tienda.pedidosonline.model.Producto;
import com.tienda.pedidosonline.component.Component;
import com.tienda.pedidosonline.decorator.concretedecorator.*;
import com.tienda.pedidosonline.model.singleton.DiscountManager;

/**
 * Receptor para los comandos relacionados con el carrito de compras
 * @author Nicolas Cavieres
 */

public class CarritoDeCompras {
    private List<Component> productos = new ArrayList<>();

    public void agregarProducto(Producto producto) {
        // Crear el producto base
        Component productoComponent = new com.tienda.pedidosonline.component.concretecomponent.ProductoBase(producto);

        // Obtener descuentos desde el singleton
        DiscountManager dm = DiscountManager.getInstance();
        double descuentoGeneral = dm.getDescuentoGeneral();
        double descuentoRopa = dm.getDescuentoPorCategoria(producto.getCategoria());
        double descuentoTemporada = dm.getDescuentoTemporada();

        // Aplicar descuentos automáticamente
        // 1. Descuento general
        productoComponent = new DescuentoPorcentaje(productoComponent, descuentoGeneral);
        // 2. Descuento por categoría (si corresponde)
        if (descuentoRopa > 0) {
            productoComponent = new DescuentoPorcentaje(productoComponent, descuentoRopa);
        }
        // 3. Descuento de temporada
        productoComponent = new DescuentoPorcentaje(productoComponent, descuentoTemporada);

        productos.add(productoComponent);
    }
    // ...existing code...

    public void eliminarProducto(Producto producto) {
        productos.removeIf(p -> p.getDescripcion().contains(producto.getNombre()));
    }
    
    // Nuevo método para eliminar producto por índice
    public void eliminarProductoPorIndice(int indice) {
        if (indice >= 0 && indice < productos.size()) {
            productos.remove(indice);
        }
    }

    public void aplicarDescuentoPorcentaje(double porcentaje) {
        for (int i = 0; i < productos.size(); i++) {
            productos.set(i, new DescuentoPorcentaje(productos.get(i), porcentaje));
        }
    }

    public void aplicarDescuentoCategoria(String categoria, double porcentaje) {
        for (int i = 0; i < productos.size(); i++) {
            Component producto = productos.get(i);
            if (producto.getDescripcion().contains(categoria)) {
                productos.set(i, new DescuentoCategoria(producto, categoria, porcentaje));
            }
        }
    }

    public void aplicarDescuentoTemporada(double porcentaje) {
        for (int i = 0; i < productos.size(); i++) {
            productos.set(i, new DescuentoTemporada(productos.get(i), porcentaje));
        }
    }

    public List<Component> getProductos() {
        return productos;
    }

    public double getTotal() {
        return productos.stream().mapToDouble(Component::getPrecio).sum();
    }
}