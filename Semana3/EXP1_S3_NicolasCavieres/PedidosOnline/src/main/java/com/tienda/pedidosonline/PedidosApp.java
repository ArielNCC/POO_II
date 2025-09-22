/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.tienda.pedidosonline;

import com.tienda.pedidosonline.controller.ProductoController;
import com.tienda.pedidosonline.model.Producto;
import com.tienda.pedidosonline.model.Pedido;
import com.tienda.pedidosonline.controller.PedidoController;
import com.tienda.pedidosonline.command.receiver.CarritoDeCompras;
import com.tienda.pedidosonline.utils.MenuManager;
import com.tienda.pedidosonline.component.Component;
import com.tienda.pedidosonline.command.concretecommand.AgregarProductoCarro;
import com.tienda.pedidosonline.command.concretecommand.EliminarProductoCarro;
import com.tienda.pedidosonline.view.VistaProductos;
import com.tienda.pedidosonline.utils.RegistroCompras;

import java.util.Scanner;

/**
 * Clase principal de la aplicación PedidosOnline.
 * 
 * Aplica el patrón MVC: esta clase es el punto de entrada y coordina la interacción entre las vistas (VistaProductos, VistaCarrito),
 * los controladores (ProductoController) y los modelos (Producto, Pedido, CarritoDeCompras).
 * Utiliza el patrón Command para ejecutar acciones como agregar y eliminar productos del carrito.
 * Utiliza el patrón Decorator para aplicar descuentos a los productos.
 * @author Nicolas
 */
public class PedidosApp {
    /**
     * Método principal. Inicia la aplicación y gestiona el ciclo de vida principal.
     * Aplica el patrón MVC coordinando vistas, controladores y modelos.
     * Maneja la interacción principal del usuario.
     */
    public static void main(String[] args) {
        try {
            System.setProperty("file.encoding", "UTF-8");
            Scanner sc = new Scanner(System.in);

            // Productos predefinidos
            ProductoController productoController = new ProductoController();
            productoController.agregarProducto(new Producto("Polera", 15000.0, "Ropa"));
            productoController.agregarProducto(new Producto("Pantalon", 25000.0, "Ropa"));
            productoController.agregarProducto(new Producto("Zapatillas", 40000.0, "Calzado"));


            CarritoDeCompras carrito = new CarritoDeCompras();
            Pedido pedido = new Pedido();
            PedidoController pedidoController = new PedidoController(pedido);

            int opcionPrincipal;
            do {
                try {
                    MenuManager.mostrarMenuPrincipal();
                    opcionPrincipal = getInt(sc);

                    switch (opcionPrincipal) {
                        case 1 -> menuProductos(sc, productoController, carrito);
                        case 2 -> menuCarrito(sc, carrito, productoController, pedidoController);
                        case 0 -> MenuManager.mostrarMensaje("¡Gracias por usar PedidosOnline!");
                        default -> MenuManager.mostrarMensaje("Opción inválida.");
                    }
                } catch (Exception e) {
                    mostrarError("Error inesperado: " + e.getMessage());
                    opcionPrincipal = -1; // continuar
                }
            } while (opcionPrincipal != 0);

            sc.close();
        } catch (Exception e) {
            mostrarError("Error fatal: " + e.getMessage() + "\nEl programa se cerrará.");
        }
    }

    // ----------------- MENÚS -----------------

    /**
     * Menú para mostrar el catálogo de productos y permitir al usuario seleccionar uno para ver detalles o agregar al carrito.
     * Vista: VistaProductos. Controlador: ProductoController. Modelo: Producto.
     * Aplica el patrón MVC.
     * @param sc Scanner para entrada del usuario
     * @param productoController Controlador de productos
     * @param carrito Carrito de compras
     */
    private static void menuProductos(Scanner sc, ProductoController productoController, CarritoDeCompras carrito) {
        int opcionProd;
        VistaProductos vistaProductos = productoController;

        do {
            try {
                opcionProd = vistaProductos.mostrarProductos(productoController, sc);

                if (opcionProd >= 1 && opcionProd <= productoController.getProductos().size()) {
                    Producto prod = productoController.getProductos().get(opcionProd - 1);
                    menuProducto(sc, prod, carrito);
                } else if (opcionProd != 0) {
                    MenuManager.mostrarMensaje("Opción inválida.");
                }
            } catch (IndexOutOfBoundsException e) {
                mostrarError("Índice de producto inválido.");
                opcionProd = -1;
            } catch (Exception e) {
                mostrarError("Error en el menú de productos: " + e.getMessage());
                opcionProd = 0; // salir
            }
        } while (opcionProd != 0);
    }

    /**
     * Menú para mostrar la información de un producto y permitir agregarlo al carrito.
     * Utiliza el patrón Command para agregar productos.
     * Vista: VistaProductos. Modelo: Producto. Controlador: ProductoController.
     * @param sc Scanner para entrada del usuario
     * @param prod Producto seleccionado
     * @param carrito Carrito de compras
     */
    private static void menuProducto(Scanner sc, Producto prod, CarritoDeCompras carrito) {
        int opcion;
        // Usar ProductoController como VistaProductos
        ProductoController vistaProductos = new ProductoController();

        do {
            try {
                System.out.println("\n--- Información del Producto ---");
                System.out.println("Nombre: " + prod.getNombre());
                System.out.println("Precio: $" + (int)Math.round(prod.getPrecio()));
                System.out.println("Categoría: " + prod.getCategoria());

                System.out.println("1. Agregar al carro de compras");
                System.out.println("0. Volver al menú anterior");
                System.out.print("Seleccione una opción: ");

                opcion = getInt(sc);

                if (opcion == 1) {
                    new AgregarProductoCarro(carrito, prod).ejecutar();

                    String descuentoInfo = prod.getCategoria().equals("Ropa")
                            ? " (con descuentos del 10% general + 15% por Ropa + 5% temporada)"
                            : " (con descuentos del 10% general + 5% temporada)";

                    vistaProductos.mostrarConfirmacionAgregar(prod.getNombre() + descuentoInfo);
                    opcion = 0; // volver atrás
                } else if (opcion != 0) {
                    MenuManager.mostrarMensaje("Opción inválida.");
                }
            } catch (Exception e) {
                mostrarError("Error en el menú de producto: " + e.getMessage());
                opcion = 0; // salir
            }
        } while (opcion != 0);
    }

    /**
     * Menú para mostrar y gestionar el carrito de compras.
     * Permite comprar, eliminar productos y agregar más productos.
     * Utiliza el patrón Command para ejecutar acciones sobre el carrito.
     * Vista: VistaCarrito. Modelo: CarritoDeCompras. Controlador: ProductoController.
     * @param sc Scanner para entrada del usuario
     * @param carrito Carrito de compras
     * @param productoController Controlador de productos
     */
    /**
     * Menú para mostrar y gestionar el carrito de compras.
     * Utiliza PedidoController (implementa IVistaCarrito) para mostrar y gestionar la vista del carrito.
     */
    private static void menuCarrito(Scanner sc, CarritoDeCompras carrito, ProductoController productoController, PedidoController pedidoController) {
        int opcion;
        do {
            try {
                pedidoController.mostrarCarrito(carrito);

                System.out.println("\n--- Carro de Compras ---");
                System.out.println("(Descuentos disponibles: 10% general | 15% en Ropa | 5% temporada)");
                System.out.println("1. Comprar productos del Carro");
                System.out.println("2. Eliminar un Producto");
                System.out.println("3. Agregar más productos");
                System.out.println("0. Volver al menú anterior");
                System.out.print("Seleccione una opción: ");

                opcion = getInt(sc);

                switch (opcion) {
                    case 1 -> procesarCompra(carrito);
                    case 2 -> eliminarProductoDelCarro(sc, carrito, pedidoController);
                    case 3 -> menuProductos(sc, productoController, carrito);
                    case 0 -> {} // salir
                    default -> MenuManager.mostrarMensaje("Opción inválida.");
                }
            } catch (Exception e) {
                mostrarError("Error en el menú de carrito: " + e.getMessage());
                opcion = 0; // salir
            }
        } while (opcion != 0);
    }

    // ----------------- ACCIONES -----------------

    /**
     * Procesa la compra de los productos en el carrito.
     * Registra la compra y aplica el patrón Decorator para calcular descuentos.
     * Modelo: Pedido, CarritoDeCompras. Utilidad: RegistroCompras.
     * @param carrito Carrito de compras
     */
    private static void procesarCompra(CarritoDeCompras carrito) {
        if (carrito.getProductos().isEmpty()) {
            MenuManager.mostrarMensaje("El carro está vacío.");
            return;
        }
        try {
            Pedido pedido = new Pedido();
            double totalSinDescuento = carrito.getProductos().stream()
                    .mapToDouble(PedidosApp::estimarPrecioOriginal)
                    .sum();

            RegistroCompras registro = new RegistroCompras();
            registro.registrarCompra(pedido, carrito.getProductos(),
                                     totalSinDescuento, carrito.getTotal());

            MenuManager.mostrarMensaje("¡Compra realizada con éxito! Total pagado: $" +
                                       (int)Math.round(carrito.getTotal()));
            carrito.getProductos().clear();
        } catch (Exception e) {
            mostrarError("Error al procesar la compra: " + e.getMessage());
        }
    }

    /**
     * Permite al usuario eliminar un producto del carrito seleccionando por índice.
     * Utiliza el patrón Command para ejecutar la eliminación.
     * Vista: VistaCarrito. Modelo: CarritoDeCompras.
     * @param sc Scanner para entrada del usuario
     * @param carrito Carrito de compras
     * @param vistaCarrito Vista del carrito
     */
    /**
     * Elimina un producto del carrito usando PedidoController como vista.
     */
    private static void eliminarProductoDelCarro(Scanner sc, CarritoDeCompras carrito, PedidoController pedidoController) {
        if (carrito.getProductos().isEmpty()) {
            MenuManager.mostrarMensaje("El carro está vacío.");
            return;
        }
        try {
            pedidoController.mostrarProductosNumerados(carrito.getProductos());
            int numProducto = pedidoController.solicitarProductoEliminar(carrito.getProductos().size(), sc);

            if (numProducto > 0) {
                Component comp = carrito.getProductos().get(numProducto - 1);
                String nombreProducto = obtenerNombreProducto(comp.getDescripcion());

                new EliminarProductoCarro(carrito, numProducto - 1).ejecutar();
                pedidoController.mostrarConfirmacionEliminado(nombreProducto);
            }
        } catch (IndexOutOfBoundsException e) {
            mostrarError("Índice de producto inválido.");
        } catch (Exception e) {
            mostrarError("Error al eliminar el producto: " + e.getMessage());
        }
    }

    // ----------------- UTILIDADES -----------------

    /**
     * Muestra un mensaje de error en consola.
     * @param mensaje Mensaje a mostrar
     */
    private static void mostrarError(String mensaje) {
        System.out.println("\n[ERROR] " + mensaje);
    }

    /**
     * Solicita un número entero al usuario con validación y límite de intentos.
     * Evita caídas por entradas inválidas.
     * @param sc Scanner para entrada del usuario
     * @return Número ingresado por el usuario
     */
    private static int getInt(Scanner sc) {
        int intentos = 0;
        final int MAX_INTENTOS = 5;

        while (intentos < MAX_INTENTOS) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                intentos++;
                if (intentos < MAX_INTENTOS) {
                    System.out.println("[X] Entrada invalida. Intento " + intentos + "/" + MAX_INTENTOS);
                } else {
                    System.out.println("[X] Has superado el numero de intentos. Cerrando programa.");
                    System.exit(0);
                }
            } catch (Exception e) {
                intentos++;
                if (intentos < MAX_INTENTOS) {
                    System.out.println("[X] Error inesperado. Intento " + intentos + "/" + MAX_INTENTOS);
                } else {
                    System.out.println("[X] Has superado el numero de intentos. Cerrando programa.");
                    System.exit(0);
                }
            }
        }
        return -1;
    }

    /**
     * Extrae el nombre base del producto desde su descripción (sin categoría ni decoradores).
     * @param descripcion Descripción completa del producto
     * @return Nombre base del producto
     */
    private static String obtenerNombreProducto(String descripcion) {
        if (descripcion.contains("(")) {
            return descripcion.split(" \\(")[0];
        }
        return descripcion;
    }

    /**
     * Estima el precio original de un producto aplicando el patrón Decorator para revertir descuentos.
     * @param producto Producto decorado
     * @return Precio original estimado
     */
    private static double estimarPrecioOriginal(Component producto) {
        String descripcion = producto.getDescripcion();
        String descripcionBase = obtenerDescripcionBase(descripcion);

        return switch (descripcionBase) {
            case String s when s.contains("Polera") -> 15000.0;
            case String s when s.contains("Pantalon") -> 25000.0;
            case String s when s.contains("Zapatillas") -> 40000.0;
            default -> {
                double precioOriginal = producto.getPrecio();
                precioOriginal /= 0.95; // revertir 5% temporada
                if (descripcionBase.contains("Ropa")) precioOriginal /= 0.85; // revertir 15% ropa
                precioOriginal /= 0.9; // revertir 10% general
                yield precioOriginal;
            }
        };
    }

    /**
     * Obtiene la descripción base del producto sin decoradores.
     * @param descripcionCompleta Descripción completa del producto
     * @return Descripción base sin decoradores
     */
    private static String obtenerDescripcionBase(String descripcionCompleta) {
        int indiceDescuento = descripcionCompleta.indexOf("[Descuento");
        return (indiceDescuento != -1) ? descripcionCompleta.substring(0, indiceDescuento).trim()
                                       : descripcionCompleta;
    }
}
