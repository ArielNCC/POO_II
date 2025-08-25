/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.pedidosonline.utils;

/**
 *
 * @author ariel
 */
public class MenuManager {
	public static void mostrarMenuPrincipal() {
		System.out.println("\n--- Menu Principal ---");
		System.out.println("1. Ver productos");
		System.out.println("2. Ver Carro de compras");
		System.out.println("0. Salir");
		System.out.print("Seleccione una opción: ");
	}

	public static void mostrarMenuProductos() {
		System.out.println("\n--- Productos ---");
		System.out.println("1. Polera");
		System.out.println("2. Pantalon");
		System.out.println("3. Zapatillas");
		System.out.println("0. Volver al menu anterior");
		System.out.print("Seleccione un producto: ");
	}

	public static void mostrarMenuProducto(String nombre, double precio, String categoria) {
		System.out.println("\n--- Información del Producto ---");
		System.out.println("Nombre: " + nombre);
		System.out.println("Precio: $" + precio);
		System.out.println("Categoria: " + categoria);
		System.out.println("1. Agregar al carro de compras");
		System.out.println("0. Volver al menu anterior");
		System.out.print("Seleccione una opción: ");
	}

	public static void mostrarMenuCarrito() {
		System.out.println("\n--- Carro de Compras ---");
		System.out.println("1. Comprar productos del Carro");
		System.out.println("2. Eliminar un Producto");
		System.out.println("3. Agregar mas productos");
		System.out.println("0. Volver al menu anterior");
		System.out.print("Seleccione una opción: ");
	}

	public static void mostrarProductosEnCarrito(java.util.List<com.tienda.pedidosonline.component.Component> carrito) {
		System.out.println("\nProductos en el carrito:");
		if (carrito.isEmpty()) {
			System.out.println("(El carrito está vacío)");
		} else {
			double total = 0;
			for (int i = 0; i < carrito.size(); i++) {
				com.tienda.pedidosonline.component.Component prod = carrito.get(i);
				System.out.println((i+1) + ". " + prod.getDescripcion() + " - $" + prod.getPrecio());
				total += prod.getPrecio();
			}
			System.out.println("Total: $" + total);
		}
	}
    
	public static void mostrarMensaje(String mensaje) {
		System.out.println(mensaje);
	}
}
