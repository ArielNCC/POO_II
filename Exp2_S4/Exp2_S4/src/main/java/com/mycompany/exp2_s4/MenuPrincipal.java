/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.exp2_s4;

import com.mycompany.exp2_s4.modelo.Inventario;
import com.mycompany.exp2_s4.modelo.Producto;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 *
 * @author luisr
 */
public class MenuPrincipal {
    
    private final Inventario inventario;
    private final Scanner scanner;
    
    public MenuPrincipal() {
        this.inventario = new Inventario();
        this.scanner = new Scanner(System.in);
        precargarEjemplo(); 
    }

    private void precargarEjemplo() {
        inventario.agregarProducto(new Producto("A001", "Teclado", "Teclado mecánico", 29990, 15));
        inventario.agregarProducto(new Producto("A002", "Mouse", "Mouse óptico", 9990, 40));
        inventario.agregarProducto(new Producto("A003", "Monitor", "24 pulgadas", 129990, 8));
    }

    public void menu() {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Selecciona una opción: ");
            try {
                switch (opcion) {
                    case 1: agregarProducto(); break;
                    case 2: actualizarProducto(); break;
                    case 3: eliminarProducto(); break;
                    case 4: buscarProductos(); break;
                    case 5: listarProductos(); break;
                    case 6: mostrarInforme(); break;
                    case 0: System.out.println("Saliendo... Gracias!"); break;
                    default: System.out.println("Opción no válida");
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
            System.out.println();
        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println("*** GESTIÓN DE INVENTARIO ***");
        System.out.println("1. Agregar producto");
        System.out.println("2. Actualizar producto");
        System.out.println("3. Eliminar producto");
        System.out.println("4. Buscar porductos (nombre/descripcion)");
        System.out.println("5. Listar productos");
        System.out.println("6. Mostrar informe");
        System.out.println("0. Salir");
    }

    private void agregarProducto() {
        System.out.println(">> Agregar producto");
        String codigo = leerTexto("Código: ");
        String nombre = leerTexto("Nombre: ");
        String descripcion = leerTexto("Descripción: ");
        double precio = leerDouble("Precio: ");
        int stock = leerEntero("Stock: ");
        boolean ok = inventario.agregarProducto(new Producto(codigo, nombre, descripcion, precio, stock));
        System.out.println(ok ? "Producto agregado." : "Ya existe un producto con ese código.");
    }

    private void actualizarProducto() {
        System.out.println(">> Actualizar producto por código");
        String codigo = leerTexto("Código: ");
        Optional<Producto> op = inventario.buscarPorCodigo(codigo);
        if (!op.isPresent()) {
            System.out.println("No existe producto con ese código.");
            return;
        }
        Producto p = op.get();
        System.out.println("Actualizando: " + p);

        String nombre = leerTextoOpcional("Nuevo nombre (ENTER para mantener): ");
        if (!nombre.isEmpty()) p.setNombre(nombre);

        String descripcion = leerTextoOpcional("Nueva descripción (ENTER para mantener): ");
        if (!descripcion.isEmpty()) p.setDescripcion(descripcion);

        String precioStr = leerTextoOpcional("Nuevo precio (ENTER para mantener): ");
        if (!precioStr.isEmpty()) p.setPrecio(Double.parseDouble(precioStr));

        String stockStr = leerTextoOpcional("Nuevo stock (ENTER para mantener): ");
        if (!stockStr.isEmpty()) p.setStock(Integer.parseInt(stockStr));

        boolean ok = inventario.actualizarProducto(p);
        System.out.println(ok ? "Producto actualizado." : "No se pudo actualizar.");
    }

    private void eliminarProducto() {
        System.out.println(">> Eliminar producto");
        String codigo = leerTexto("Código: ");
        Producto eliminado = inventario.eliminarProducto(codigo);
        System.out.println(eliminado != null ? "Eliminado: " + eliminado : "No existía ese código.");
    }

    private void buscarProductos() {
        System.out.println(">> Búsqueda");
        String query = leerTexto("Texto a buscar (nombre/descripcion): ");
        List<Producto> resultados = inventario.buscarPorNombreODescripcion(query);
        if (resultados.isEmpty()) {
            System.out.println("Sin resultados.");
        } else {
            resultados.forEach(System.out::println);
        }
    }

    private void listarProductos() {
        System.out.println(">> Listado completo");
        List<Producto> lista = inventario.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("Inventario vacío.");
        } else {
            lista.forEach(System.out::println);
        }
    }

    private void mostrarInforme() {
        System.out.println(">> Informe de Inventario");
        System.out.println(inventario.generarInforme());
    }

    private String leerTexto(String prompt) {
        System.out.print(prompt);
        String s = scanner.nextLine();
        while (s == null || s.trim().isEmpty()) {
            System.out.print("Valor requerido. " + prompt);
            s = scanner.nextLine();
        }
        return s.trim();
    }

    private String leerTextoOpcional(String prompt) {
        System.out.print(prompt);
        String s = scanner.nextLine();
        return (s == null) ? "" : s.trim();
    }

    private int leerEntero(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine();
            try { return Integer.parseInt(s.trim()); }
            catch (Exception e) { System.out.println("Debe ser un número entero"); }
        }
    }

    private double leerDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine();
            try { return Double.parseDouble(s.trim()); }
            catch (Exception e) { System.out.println("Debe ser un número (use punto decimal)"); }
        }
    }

    public static void main(String[] args) {
        new MenuPrincipal().menu();
    }
}
