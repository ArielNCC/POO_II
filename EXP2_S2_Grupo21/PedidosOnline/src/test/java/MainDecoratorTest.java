
import com.tienda.pedidosonline.component.Component;
import com.tienda.pedidosonline.component.concretecomponent.ProductoBase;
import com.tienda.pedidosonline.decorator.concretedecorator.DescuentoCategoria;
import com.tienda.pedidosonline.decorator.concretedecorator.DescuentoPorcentaje;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ariel
 */
// MainDecoratorTest.java
public class MainDecoratorTest {
    public static void main(String[] args) {
    // 1. Crear un producto base
        Component camiseta = new ProductoBase("Camiseta", 25.0, "Ropa");
    
    // 2. Imprimir valores originales
        System.out.println("Producto Original: " 
                    + camiseta.getDescripcion() 
                    + ", Precio: $" 
                    + String.format("%.2f", camiseta.getPrecio()));
    
    // 2. Aplicar un descuento porcentual (10%)
        Component camisetaConDescuento10PorCiento = new DescuentoPorcentaje(camiseta, 10);
        System.out.println("Después de 10% Descuento: " 
                + camisetaConDescuento10PorCiento.getDescripcion() 
                + ", Precio: $" 
                + String.format("%.2f", camisetaConDescuento10PorCiento.getPrecio()));

        // 3. Aplicar un descuento fijo ($5) SOBRE el producto ya con descuento del 10%
        Component camisetaConDescuentoDoble = new DescuentoCategoria(camisetaConDescuento10PorCiento, 5.0);
        System.out.println("Después de Descuento Fijo ($5): " 
                + camisetaConDescuentoDoble.getDescripcion() 
                + ", Precio: $" 
                + String.format("%.2f", camisetaConDescuentoDoble.getPrecio()));

        // Otro ejemplo: un pantalón con un 20% de descuento
        Component pantalon = new ProductoBase("Pantalón", 60.0, "Ropa");
        Component pantalonConDescuento20PorCiento = new DescuentoPorcentaje(pantalon, 20);
        System.out.println("Producto Original: " 
                + pantalon.getDescripcion() 
                + ", Precio: $" 
                + String.format("%.2f", pantalon.getPrecio()));
        System.out.println("Después de 20% Descuento: " 
                + pantalonConDescuento20PorCiento.getDescripcion() 
                + ", Precio: $" 
                + String.format("%.2f", pantalonConDescuento20PorCiento.getPrecio()));
    }
}