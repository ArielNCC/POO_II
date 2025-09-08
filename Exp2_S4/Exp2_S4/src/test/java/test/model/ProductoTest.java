/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test.model;

import com.mycompany.exp2_s4.modelo.Producto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author luisr
 */
public class ProductoTest {

    @Test
    void crearProducto_conAtributosEsperados() {
        Producto p = new Producto("X1", "Item", "Desc", 1000.0, 5);
        assertEquals("X1", p.getCodigo());
        assertEquals("Item", p.getNombre());
        assertEquals("Desc", p.getDescripcion());
        assertEquals(1000.0, p.getPrecio(), 0.0001);
        assertEquals(5, p.getStock());
    }

    @Test
    void actualizarAtributos_conSetters() {
        Producto p = new Producto("X2", "A", "B", 10.0, 1);
        p.setNombre("Nuevo");
        p.setDescripcion("Detalle");
        p.setPrecio(20.0);
        p.setStock(9);
        assertAll(
            () -> assertEquals("Nuevo", p.getNombre()),
            () -> assertEquals("Detalle", p.getDescripcion()),
            () -> assertEquals(20.0, p.getPrecio(), 0.0001),
            () -> assertEquals(9, p.getStock())
        );
    }

    @Test
    void cambiarYRecuperarPrecio() {
        Producto p = new Producto("X3", "A", "B", 10.0, 1);
        p.actualizarPrecio(99.9);
        assertEquals(99.9, p.getPrecio(), 0.0001);
    }

    @Test
    void cambiarYVerificarStock() {
        Producto p = new Producto("X4", "A", "B", 10.0, 1);
        p.actualizarStock(33);
        assertEquals(33, p.getStock());
    }

    @Test
    void validacionesPrecioYStock() {
        assertThrows(IllegalArgumentException.class, () -> new Producto("C", "N", "D", -1, 0));
        assertThrows(IllegalArgumentException.class, () -> new Producto("C2", "N", "D", 0, -5));
    }
}
