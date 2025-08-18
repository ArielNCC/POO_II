/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package singleton.exp1_s1_grupo21;

/**
 *
 * @author ariel
 */
public class Singleton {
    private static final Singleton instancia = new Singleton();

    //constructor privado:
    private Singleton(){}
    
    public static Singleton getInstance(){
        if (instancia == null) {
            throw new IllegalArgumentException("La instancia Singleton no puede ser null");
        }
        return instancia;
    }
    
    //descuento inventado: si la compra supera los $50.000 tiene 10% de descto
    public double aplicarDescuento(double subtotal){
        if (subtotal > 50000) {
            return subtotal*0.10;
        }
        return 0;
    }
}
