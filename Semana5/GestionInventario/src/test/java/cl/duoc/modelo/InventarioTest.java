/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package cl.duoc.modelo;

/**
 *
 * @author ariel
 */

import cl.duoc.demo.modelo.Inventario;
import cl.duoc.demo.modelo.Producto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class InventarioTest {

    @Test
    void testAgregarProducto() {
        Inventario inv = new Inventario();
        Producto p = new Producto("C1", "Cable", "Cable USB-C", 2000, 25);
        assertTrue(inv.agregarProducto(p));
        // Producto duplicado
        assertFalse(inv.agregarProducto(p));
        // Producto nulo
        assertThrows(IllegalArgumentException.class, () -> inv.agregarProducto(null));
    }

    @Test
    void testEliminarProducto() {
        Inventario inv = new Inventario();
        Producto p = new Producto("C2", "Disco Duro", "SSD", 40000, 3);
        inv.agregarProducto(p);
        assertNotNull(inv.eliminarProducto("C2"));
        // Eliminar inexistente
        assertNull(inv.eliminarProducto("ZZZ"));
    }

    @Test
    void testBuscarPorNombreODescripcion() {
        Inventario inv = new Inventario();
        inv.agregarProducto(new Producto("C3", "Tablet", "Tablet gráfica", 55000, 8));
        inv.agregarProducto(new Producto("C4", "Tablet", "No gráfica", 35000, 2));
        // Buscar por nombre
        List<Producto> res = inv.buscarPorNombreODescripcion("tablet");
        assertEquals(2, res.size());
        // Buscar inexistente
        assertTrue(inv.buscarPorNombreODescripcion("notebook").isEmpty());
    }

    @Test
    void testListarTodos() {
        Inventario inv = new Inventario();
        Producto p1 = new Producto("C5", "Parlante", "", 8000, 9);
        Producto p2 = new Producto("C6", "Pendrive", "", 3000, 15);
        inv.agregarProducto(p1);
        inv.agregarProducto(p2);
        List<Producto> lista = inv.listarTodos();
        assertEquals(2, lista.size());
        assertEquals("C5", lista.get(0).getCodigo());
        assertEquals("C6", lista.get(1).getCodigo());
    }
}