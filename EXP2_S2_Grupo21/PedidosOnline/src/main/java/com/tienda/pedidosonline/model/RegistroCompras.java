/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.pedidosonline.model;

/**
 *
 * @author ariel
 */
public class RegistroCompras {
	// Guarda el registro de la compra en un archivo de texto
	public static void guardarRegistroCompra(String resumen) {
		try (java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter("registro_de_compras.txt", true))) {
			writer.write("----- COMPRA -----\n");
			writer.write(resumen);
			writer.write("------------------\n\n");
		} catch (Exception e) {
			System.out.println("Error al guardar el registro de compra: " + e.getMessage());
		}
	}

	// Lee el registro de compras desde el archivo de texto
	public static String leerRegistroCompras() {
		StringBuilder sb = new StringBuilder();
		try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader("registro_de_compras.txt"))) {
			String linea;
			while ((linea = reader.readLine()) != null) {
				sb.append(linea).append("\n");
			}
		} catch (Exception e) {
			System.out.println("Error al leer el registro de compras: " + e.getMessage());
		}
		return sb.toString();
	}
}
