/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package cl.duoc.modelo;

/**
 *
 * @author ariel
 */
import cl.duoc.demo.modelo.Producto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductoTest {

    @Test
    void testCreacionProducto() {
        Producto p = new Producto("B2", "Teclado", "Teclado mecánico", 9900, 10);
        assertEquals("B2", p.getCodigo());
        assertEquals("Teclado", p.getNombre());
        assertEquals("Teclado mecánico", p.getDescripcion());
        assertEquals(9900, p.getPrecio());
        assertEquals(10, p.getStock());
    }

    @Test
    void testActualizarAtributos() {
        Producto p = new Producto("B3", "Monitor", "", 25000, 4);
        p.actualizarPrecio(30000);
        assertEquals(30000, p.getPrecio());
        p.actualizarStock(7);
        assertEquals(7, p.getStock());
    }
}