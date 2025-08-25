/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.tienda.pedidosonline;

/**
 *
 * @author ariel
 */
public class PedidosApp {

    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        java.util.List<com.tienda.pedidosonline.component.Component> carrito = new java.util.ArrayList<>();

        // Productos base
        com.tienda.pedidosonline.component.concretecomponent.ProductoBase camiseta = new com.tienda.pedidosonline.component.concretecomponent.ProductoBase("Polera", 25000.0, "Ropa");
        com.tienda.pedidosonline.component.concretecomponent.ProductoBase pantalon = new com.tienda.pedidosonline.component.concretecomponent.ProductoBase("Pantalón", 60000.0, "Ropa");
        com.tienda.pedidosonline.component.concretecomponent.ProductoBase zapatillas = new com.tienda.pedidosonline.component.concretecomponent.ProductoBase("Zapatillas", 40000.0, "Calzado");

        boolean salir = false;
        while (!salir) {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Ver productos");
            System.out.println("2. Ver Carro de compras");
            System.out.println("3. Ver historial de compras");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            int opcionPrincipal = leerOpcion(scanner);
            switch (opcionPrincipal) {
                case 1: // Ver productos
                    boolean volverProductos = false;
                    while (!volverProductos) {
                        com.tienda.pedidosonline.utils.MenuManager.mostrarMenuProductos();
                        int opcionProducto = leerOpcion(scanner);
                        switch (opcionProducto) {
                            case 1: // Polera
                                mostrarInfoYAgregar(scanner, carrito, camiseta);
                                break;
                            case 2: // Pantalon
                                mostrarInfoYAgregar(scanner, carrito, pantalon);
                                break;
                            case 3: // Zapatillas
                                mostrarInfoYAgregar(scanner, carrito, zapatillas);
                                break;
                            case 0:
                                volverProductos = true;
                                break;
                            default:
                                com.tienda.pedidosonline.utils.MenuManager.mostrarMensaje("Opción inválida.");
                        }
                    }
                    break;
                case 2: // Ver Carro de compras
                    boolean volverCarrito = false;
                    while (!volverCarrito) {
                        mostrarCarritoConDescuentos(carrito);
                        com.tienda.pedidosonline.utils.MenuManager.mostrarMenuCarrito();
                        int opcionCarrito = leerOpcion(scanner);
                        switch (opcionCarrito) {
                            case 1: // Comprar productos del Carro
                                if (carrito.isEmpty()) {
                                    com.tienda.pedidosonline.utils.MenuManager.mostrarMensaje("El carrito está vacío. No se puede comprar.");
                                } else {
                                    String resumen = obtenerResumenCompra(carrito);
                                    System.out.println("\nResumen de la compra:");
                                    System.out.println(resumen);
                                    System.out.println("¿Desea realizar la compra de los productos?");
                                    System.out.println("1. Sí");
                                    System.out.println("2. No");
                                    System.out.println("3. Volver al menú principal");
                                    int confirmacion = leerOpcion(scanner);
                                    if (confirmacion == 1) {
                                        com.tienda.pedidosonline.model.RegistroCompras.guardarRegistroCompra(resumen);
                                        com.tienda.pedidosonline.utils.MenuManager.mostrarMensaje("¡Compra realizada y registrada!");
                                        carrito.clear();
                                    } else if (confirmacion == 2) {
                                        carrito.clear();
                                        com.tienda.pedidosonline.utils.MenuManager.mostrarMensaje("Carrito borrado. No se realizó la compra.");
                                    } else {
                                        com.tienda.pedidosonline.utils.MenuManager.mostrarMensaje("Volviendo al menú principal...");
                                        volverCarrito = true;
                                    }
                                }
                                break;
                            case 2: // Eliminar un Producto
                                if (carrito.isEmpty()) {
                                    com.tienda.pedidosonline.utils.MenuManager.mostrarMensaje("El carrito está vacío.");
                                } else {
                                    com.tienda.pedidosonline.utils.MenuManager.mostrarMensaje("Ingrese el número del producto a eliminar:");
                                    int numEliminar = leerOpcion(scanner);
                                    if (numEliminar > 0 && numEliminar <= carrito.size()) {
                                        carrito.remove(numEliminar - 1);
                                        com.tienda.pedidosonline.utils.MenuManager.mostrarMensaje("Producto eliminado.");
                                    } else {
                                        com.tienda.pedidosonline.utils.MenuManager.mostrarMensaje("Número inválido.");
                                    }
                                }
                                break;
                            case 3: // Agregar más productos
                                boolean volverProductosMenu = false;
                                while (!volverProductosMenu) {
                                    com.tienda.pedidosonline.utils.MenuManager.mostrarMenuProductos();
                                    int opcionProducto = leerOpcion(scanner);
                                    switch (opcionProducto) {
                                        case 1: // Polera
                                            mostrarInfoYAgregar(scanner, carrito, camiseta);
                                            break;
                                        case 2: // Pantalon
                                            mostrarInfoYAgregar(scanner, carrito, pantalon);
                                            break;
                                        case 3: // Zapatillas
                                            mostrarInfoYAgregar(scanner, carrito, zapatillas);
                                            break;
                                        case 0:
                                            volverProductosMenu = true;
                                            break;
                                        default:
                                            com.tienda.pedidosonline.utils.MenuManager.mostrarMensaje("Opción inválida.");
                                    }
                                }
                                break;
                            case 0:
                                volverCarrito = true;
                                break;
                            default:
                                com.tienda.pedidosonline.utils.MenuManager.mostrarMensaje("Opción inválida.");
                        }
                    }
                    break;
                case 3: // Ver historial de compras
                    System.out.println("\n--- Historial de compras ---");
                    String historial = com.tienda.pedidosonline.model.RegistroCompras.leerRegistroCompras();
                    System.out.println(historial.isEmpty() ? "No hay compras registradas." : historial);
                    break;
                case 0:
                    salir = true;
                    com.tienda.pedidosonline.utils.MenuManager.mostrarMensaje("¡Hasta luego!");
                    break;
                default:
                    com.tienda.pedidosonline.utils.MenuManager.mostrarMensaje("Opción inválida.");
            }
        }
        scanner.close();
    }

    // Genera el resumen detallado de la compra
    private static String obtenerResumenCompra(java.util.List<com.tienda.pedidosonline.component.Component> carrito) {
        StringBuilder sb = new StringBuilder();
        sb.append("\nResumen del Carrito:\n");
        sb.append("# | Producto | Categoria | Precio: $ | con descuento: $\n");

        double totalSinDescuento = 0;
        double totalConDescuentoRopa = 0;
        double totalOtros = 0;
        double descuentoRopa = 0;
        double descuentoTotal = 0;

        int idx = 1;
        for (com.tienda.pedidosonline.component.Component prod : carrito) {
            String descripcion = prod.getDescripcion();
            String[] partes = descripcion.split("\\|");
            String nombre = partes[0].trim();
            String categoria = "";
            if (partes.length > 1) {
                String[] catPart = partes[1].split(":");
                if (catPart.length > 1) {
                    categoria = catPart[1].trim();
                }
            }
            double precioOriginal = prod.getPrecio();
            double precioConDescuento = prod.getPrecio();
            // Si es ropa, aplicar DescuentoCategoria (ejemplo: $5000)
            if (categoria.equalsIgnoreCase("Ropa")) {
                com.tienda.pedidosonline.decorator.DescuentoDecorator descFijo = new com.tienda.pedidosonline.decorator.concretedecorator.DescuentoCategoria(prod, 5000);
                precioConDescuento = descFijo.getPrecio();
                sb.append(String.format("%d | %s | %s | $%.0f | $%.0f\n", idx, nombre, categoria, precioOriginal, precioConDescuento));
                totalSinDescuento += precioOriginal;
                totalConDescuentoRopa += precioConDescuento;
                descuentoRopa += (precioOriginal - precioConDescuento);
            } else {
                sb.append(String.format("%d | %s | %s | $%.0f | $%.0f\n", idx, nombre, categoria, precioOriginal, precioConDescuento));
                totalSinDescuento += precioOriginal;
                totalOtros += precioConDescuento;
            }
            idx++;
        }
        double totalConDescuento = totalConDescuentoRopa + totalOtros;
        // DescuentoPorcentaje se aplica al total si corresponde
        if (totalSinDescuento > 30000) {
            double porcentaje = 10;
            descuentoTotal = totalConDescuento * porcentaje / 100;
            totalConDescuento -= descuentoTotal;
        }

        sb.append("\nResumen de descuentos:\n");
        sb.append("Total sin descuentos: $").append(String.format("%.0f", totalSinDescuento)).append("\n");
        sb.append("Descuento aplicado en productos de Ropa: $").append(String.format("%.0f", descuentoRopa)).append("\n");
        sb.append("Descuento aplicado en el total de la compra: $").append(String.format("%.0f", descuentoTotal)).append("\n");
        sb.append("TOTAL A PAGAR: $").append(String.format("%.0f", totalConDescuento)).append("\n");
        return sb.toString();
    }

    private static void mostrarCarritoConDescuentos(java.util.List<com.tienda.pedidosonline.component.Component> carrito) {
        System.out.println("\nProductos en el carrito:");
        if (carrito.isEmpty()) {
            System.out.println("(El carrito está vacío)");
        } else {
            System.out.println(obtenerResumenCompra(carrito));
        }
    }

    private static int leerOpcion(java.util.Scanner scanner) {
        int opcion = -1; // Valor por defecto para opción inválida
        boolean valido = false; // Controlamos la validez de la entrada
        int intentos = 0; // Contador de intentos
        while (!valido && intentos < 5) { // Limitar a 5 intentos
            String input = scanner.nextLine();
            try {
                opcion = Integer.parseInt(input.trim());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
            intentos++;
        }
        if (!valido) {
            System.out.println("Demasiados intentos inválidos. Regresando al menú anterior.");
            return -1;
        }
        return opcion;
    }

    private static void mostrarInfoYAgregar(java.util.Scanner scanner, java.util.List<com.tienda.pedidosonline.component.Component> carrito, com.tienda.pedidosonline.component.Component producto) {
        String nombre = producto.getDescripcion().split("\\|")[0].trim();
        double precio = producto.getPrecio();
        String categoria = "";
        String[] partes = producto.getDescripcion().split(":");
        if (partes.length > 1) {
            categoria = partes[1].trim();
        }
        com.tienda.pedidosonline.utils.MenuManager.mostrarMenuProducto(nombre, precio, categoria);
        try {
            int opcion = leerOpcion(scanner);
            if (opcion == 1) {
                carrito.add(producto);
                com.tienda.pedidosonline.utils.MenuManager.mostrarMensaje("Producto agregado al carrito.");
            }
        } catch (Exception e) {
            com.tienda.pedidosonline.utils.MenuManager.mostrarMensaje("Error: " + e.getMessage());
        }
    }
}
