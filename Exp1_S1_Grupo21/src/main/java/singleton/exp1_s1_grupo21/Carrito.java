/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package singleton.exp1_s1_grupo21;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ariel
 */
public class Carrito {
    private final List<Producto> productos = new ArrayList<>();
    
    public void agregarProducto( Producto p){
        productos.add(p);
    
    }
    public List<Producto> getProductos(){
        return productos;
    }
    
    public double calcularSubtotal(){
        return productos.stream().mapToDouble(Producto::getPrecio).sum();
    }
    
    public double calcularTotal(){
        double subtotal = calcularSubtotal();
        double descuento = Singleton.getInstance().aplicarDescuento(subtotal);
        return subtotal - descuento;
    }
}
