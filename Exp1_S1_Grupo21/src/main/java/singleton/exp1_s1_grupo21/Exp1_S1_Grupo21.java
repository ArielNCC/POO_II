/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package singleton.exp1_s1_grupo21;

import java.util.Scanner;

/**
 *
 * @author ariel
 */
public class Exp1_S1_Grupo21 {

    public static void main(String[] args) {
        //productos inventados para la solucion:
        
        Producto p1 = new Producto("Polera", 12000);
        Producto p2 = new Producto("Pantalón", 25000);
        Producto p3 = new Producto("Chaleco", 20000);
        Producto p4 = new Producto("Chaqueta", 50000);
        
        Producto[] catalogo = {p1,p2,p3,p4};
        
        System.out.println("-Tienda Singleton-");
        System.out.println("Catálogo:");
        for (int i = 0; i < catalogo.length; i++) {
            System.out.println((i+1)+". "+ catalogo[i]);
        }
        
        Carrito carrito = new Carrito();
        Scanner sc = new Scanner(System.in);
        
        
        while (true) {
            System.out.println("Ingrese número de producto a agregar (0 para terminar): ");
            int opcion = -1;
            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor ingrese un número entre 0 y " + catalogo.length);
                continue;
            }
            if(opcion == 0) break;
            if (opcion < 1 || opcion > catalogo.length) {
                System.out.println("Opción Inválida. Por favor ingrese un número entre 0 y " + catalogo.length);
                continue;
            }
            carrito.agregarProducto(catalogo[opcion-1]);
            System.out.println(catalogo[opcion-1].getNombre()+ " Agregado al carrito");
        }
        
        System.out.println("Resumen de Compra: ");
        carrito.getProductos().forEach(p -> System.out.println("- " + p));
        double subtotal = carrito.calcularSubtotal();
        double descuento = Singleton.getInstance().aplicarDescuento(subtotal);
        double total = carrito.calcularTotal();
        
        System.out.println("Subtotal        : $"+ subtotal);
        System.out.println("Descto aplicado : $"+ descuento);
        System.out.println("TOTAL A PAGAR   : $"+ total);
        
        sc.close();
        System.out.println("Gracias por su compra");
    }
}