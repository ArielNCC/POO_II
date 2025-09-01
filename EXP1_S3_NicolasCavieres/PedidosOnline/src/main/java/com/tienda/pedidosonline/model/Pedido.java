/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.tienda.pedidosonline.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Modelo que representa un Pedido en el sistema
 * @author Nicolas Cavieres
 */

 public class Pedido {
    private List<Producto> productos = new ArrayList<>();

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public List<Producto> getProductos() {
        return productos;
    }
}