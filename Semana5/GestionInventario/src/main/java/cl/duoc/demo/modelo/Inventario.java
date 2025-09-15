/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.duoc.demo.modelo;

/**
 *
 * @author ariel
 */

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Inventario {
    private final Map<String, Producto> productos = new HashMap<>();

    // agrega si el producto es válido y el código no existe
    public boolean agregarProducto(Producto p) {
        if (p == null) throw new IllegalArgumentException("No se puede agregar un producto nulo.");
        String codigo = p.getCodigo();
        if (productos.containsKey(codigo)) return false;
        productos.put(codigo, p);
        return true;
    }

    // elimina y retorna el producto, o null si no existía
    public Producto eliminarProducto(String codigo) {
        if (codigo == null) return null;
        return productos.remove(codigo);
    }

    public Optional<Producto> buscarPorCodigo(String codigo) {
        if (codigo == null) return Optional.empty();
        return Optional.ofNullable(productos.get(codigo));
    }

    // busca por nombre o descripción 
    public List<Producto> buscarPorNombreODescripcion(String query) {
        if (query == null || query.trim().isEmpty()) return Collections.emptyList();
        String q = query.toLowerCase();
        return productos.values().stream()
                .filter(p -> p.getNombre().toLowerCase().contains(q) ||
                             p.getDescripcion().toLowerCase().contains(q))
                .sorted(Comparator.comparing(Producto::getCodigo))
                .collect(Collectors.toList());
    }

    // lista todos productos ordenados por código
    public List<Producto> listarTodos() {
        return productos.values().stream()
                .sorted(Comparator.comparing(Producto::getCodigo))
                .collect(Collectors.toList());
    }

    // reemplaza un producto existente por código
    public boolean actualizarProducto(Producto p) {
        if (p == null) throw new IllegalArgumentException("Producto nulo.");
        String codigo = p.getCodigo();
        if (!productos.containsKey(codigo)) return false;
        productos.put(codigo, p);
        return true;
    }

    public boolean actualizarPrecio(String codigo, double nuevoPrecio) {
        Optional<Producto> op = buscarPorCodigo(codigo);
        if (!op.isPresent()) return false;
        op.get().actualizarPrecio(nuevoPrecio);
        return true;
    }

    public boolean actualizarStock(String codigo, int nuevoStock) {
        Optional<Producto> op = buscarPorCodigo(codigo);
        if (!op.isPresent()) return false;
        op.get().actualizarStock(nuevoStock);
        return true;
    }

    // informe tabulado simple
    public String generarInforme() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-10s %-20s %-10s %-8s %-6s%n", "CODIGO", "NOMBRE", "PRECIO", "STOCK", "DESC?"));
        sb.append("------------------------------------------------------------------\n");
        for (Producto p : listarTodos()) {
            sb.append(String.format("%-10s %-20s %-10.2f %-8d %-6s%n",
                    p.getCodigo(), p.getNombre(), p.getPrecio(), p.getStock(),
                    (p.getDescripcion() != null && !p.getDescripcion().isEmpty()) ? "si" : "no"));
        }
        return sb.toString();
    }
}