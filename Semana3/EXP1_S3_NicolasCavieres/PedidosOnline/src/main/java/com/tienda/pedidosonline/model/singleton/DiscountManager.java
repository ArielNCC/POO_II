/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.tienda.pedidosonline.model.singleton;

/**
 * Singleton para gestionar los descuentos disponibles en la aplicación
 * @author Nicolas Cavieres
 */
public class DiscountManager {
    private static DiscountManager instance;

    private double descuentoGeneral = 10.0; // %
    private double descuentoRopa = 15.0;    // %
    private double descuentoTemporada = 5.0;// %

    private DiscountManager() {
        if (instance != null) {
            throw new RuntimeException("Instancia única, use getInstance() para acceder.");
        }
    }

    public static DiscountManager getInstance() {
        if (instance == null) {
            synchronized (DiscountManager.class) {
                if (instance == null) {
                    instance = new DiscountManager();
                }
            }
        }
        return instance;
    }

    public double getDescuentoGeneral() {
        return descuentoGeneral;
    }

    public double getDescuentoRopa() {
        return descuentoRopa;
    }

    public double getDescuentoTemporada() {
        return descuentoTemporada;
    }

    public double getDescuentoPorCategoria(String categoria) {
        if ("Ropa".equalsIgnoreCase(categoria)) {
            return descuentoRopa;
        }
        return 0.0;
    }
}