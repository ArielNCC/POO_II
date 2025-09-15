/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.duoc.demo.view;

/**
 *
 * @author ariel
 */

import cl.duoc.demo.modelo.Inventario;
import cl.duoc.demo.modelo.Producto;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MenuPrincipal {
    private final Inventario inventario;
    private final Scanner scanner;

    public MenuPrincipal(Inventario inventario) {
        this.inventario = inventario;
        this.scanner = new Scanner(System.in);
    }

    public void ejecutar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");
            switch (opcion) {
                case 1 -> agregarProducto();
                case 2 -> actualizarProducto();
                case 3 -> eliminarProducto();
                case 4 -> buscarProducto();
                case 5 -> listarProductos();
                case 6 -> mostrarInforme();
                case 0 -> System.out.println("¡Hasta pronto!");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println("\n--- MENÚ GESTIÓN DE INVENTARIO ---");
        System.out.println("1. Agregar producto");
        System.out.println("2. Actualizar producto");
        System.out.println("3. Eliminar producto");
        System.out.println("4. Buscar producto");
        System.out.println("5. Listar todos los productos");
        System.out.println("6. Informe de inventario");
        System.out.println("0. Salir");
    }

    private int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ex) {
                System.out.println("Ingrese un número válido.");
            }
        }
    }

    private double leerDouble(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException ex) {
                System.out.println("Ingrese un número válido.");
            }
        }
    }

    private void agregarProducto() {
        try {
            System.out.print("Código: ");
            String codigo = scanner.nextLine();
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Descripción: ");
            String descripcion = scanner.nextLine();
            double precio = leerDouble("Precio: ");
            int stock = leerEntero("Stock: ");
            Producto p = new Producto(codigo, nombre, descripcion, precio, stock);
            if (inventario.agregarProducto(p)) {
                System.out.println("Producto agregado correctamente.");
            } else {
                System.out.println("Ya existe un producto con ese código.");
            }
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private void actualizarProducto() {
        System.out.print("Código del producto a actualizar: ");
        String codigo = scanner.nextLine();
        Optional<Producto> op = inventario.buscarPorCodigo(codigo);
        if (op.isEmpty()) {
            System.out.println("No existe producto con ese código.");
            return;
        }
        Producto p = op.get();
        System.out.println("Producto actual: " + p);
        System.out.println("1. Cambiar precio");
        System.out.println("2. Cambiar stock");
        System.out.println("3. Cambiar nombre y descripción");
        System.out.print("Seleccione campo a actualizar: ");
        String opcion = scanner.nextLine();
        switch (opcion) {
            case "1" -> {
                double nuevoPrecio = leerDouble("Nuevo precio: ");
                if (inventario.actualizarPrecio(codigo, nuevoPrecio))
                    System.out.println("Precio actualizado.");
                else
                    System.out.println("Error al actualizar precio.");
            }
            case "2" -> {
                int nuevoStock = leerEntero("Nuevo stock: ");
                if (inventario.actualizarStock(codigo, nuevoStock))
                    System.out.println("Stock actualizado.");
                else
                    System.out.println("Error al actualizar stock.");
            }
            case "3" -> {
                System.out.print("Nuevo nombre: ");
                String nuevoNombre = scanner.nextLine();
                System.out.print("Nueva descripción: ");
                String nuevaDesc = scanner.nextLine();
                Producto nuevo = new Producto(codigo, nuevoNombre, nuevaDesc, p.getPrecio(), p.getStock());
                if (inventario.actualizarProducto(nuevo))
                    System.out.println("Nombre y descripción actualizados.");
                else
                    System.out.println("Error al actualizar.");
            }
            default -> System.out.println("Opción inválida.");
        }
    }

    private void eliminarProducto() {
        System.out.print("Código del producto a eliminar: ");
        String codigo = scanner.nextLine();
        Producto eliminado = inventario.eliminarProducto(codigo);
        if (eliminado != null) {
            System.out.println("Producto eliminado: " + eliminado);
        } else {
            System.out.println("No existe producto con ese código.");
        }
    }

    private void buscarProducto() {
        System.out.print("Ingrese nombre o descripción a buscar: ");
        String query = scanner.nextLine();
        List<Producto> resultados = inventario.buscarPorNombreODescripcion(query);
        if (resultados.isEmpty()) {
            System.out.println("No se encontraron productos.");
        } else {
            resultados.forEach(System.out::println);
        }
    }

    private void listarProductos() {
        List<Producto> productos = inventario.listarTodos();
        if (productos.isEmpty()) {
            System.out.println("Inventario vacío.");
        } else {
            productos.forEach(System.out::println);
        }
    }

    private void mostrarInforme() {
        System.out.println("\nINFORME DE INVENTARIO");
        System.out.println(inventario.generarInforme());
    }
}