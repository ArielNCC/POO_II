/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.duoc.demo.modelo;

/**
 *
 * @author ariel
 */

import java.util.Objects;

public class Producto {
    private String codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;

    public Producto(String codigo, String nombre, String descripcion, double precio, int stock) {
        setCodigo(codigo);
        setNombre(nombre);
        setDescripcion(descripcion);
        setPrecio(precio);
        setStock(stock);
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código no puede estar vacío.");
        }
        this.codigo = codigo.trim();
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre.trim();
    }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) {
        this.descripcion = (descripcion == null) ? "" : descripcion.trim();
    }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) {
        if (precio < 0) throw new IllegalArgumentException("El precio no puede ser negativo.");
        this.precio = precio;
    }

    public int getStock() { return stock; }
    public void setStock(int stock) {
        if (stock < 0) throw new IllegalArgumentException("El stock no puede ser negativo.");
        this.stock = stock;
    }

    // actualiza el precio validando no-negatividad
    public void actualizarPrecio(double nuevoPrecio) {
        setPrecio(nuevoPrecio);
    }

    // Fija el stock a un valor válido (>=0)
    public void actualizarStock(int nuevoStock) {
        setStock(nuevoStock);
    }

    // ajusta en delta (puede ser negativo) y valida que no quede < 0
    public void ajustarStock(int delta) {
        int nuevo = this.stock + delta;
        if (nuevo < 0) throw new IllegalArgumentException("El ajuste dejaría stock negativo.");
        this.stock = nuevo;
    }

    // utilidad para búsqueda por nombre/descripcion
    public boolean coincideCon(String query) {
        if (query == null || query.trim().isEmpty()) return false;
        String q = query.toLowerCase();
        return nombre.toLowerCase().contains(q) || descripcion.toLowerCase().contains(q);
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto)) return false;
        Producto that = (Producto) o;
        return Objects.equals(codigo, that.codigo);
    }

    @Override public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override public String toString() {
        return String.format("[%s] %s | %s | $%.2f | stock: %d", codigo, nombre, descripcion, precio, stock);
    }
}