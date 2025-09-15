/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.duoc.demo;


/**
 *
 * @author ariel
 */


import cl.duoc.demo.modelo.Inventario;
import cl.duoc.demo.view.MenuPrincipal;

public class GestionInventario {

    public static void main(String[] args) {
        // Inicializar inventario
        Inventario inventario = new Inventario();

        // Si deseas, puedes agregar productos de ejemplo aquí:
        // inventario.agregarProducto(new Producto("P001", "Mouse", "Mouse óptico", 5000, 10));
        // inventario.agregarProducto(new Producto("P002", "Teclado", "Teclado mecánico", 12000, 5));

        // Lanzar menú principal
        MenuPrincipal menu = new MenuPrincipal(inventario);
        menu.ejecutar();
    }
}