/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package singleton.exp1_s1_grupo21;

/**
 *
 * @author ariel
 */
public class Producto {
    private final String nombre;
    private final double precio;
    
    public Producto(String nombre, double precio){
        this.nombre = nombre;
        this.precio = precio;
    
    }
    public String getNombre(){ return nombre;}
    public double getPrecio(){ return precio;}

    @Override
    public String toString() {
        return nombre + " - $" + precio;
    }    
}