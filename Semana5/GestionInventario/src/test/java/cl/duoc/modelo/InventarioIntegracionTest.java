/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package cl.duoc.modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ariel
 */
import cl.duoc.demo.modelo.Inventario;
import cl.duoc.demo.modelo.Producto;
import java.util.List;

public class InventarioIntegracionTest {
    @Test
    void testAgregarBuscarEliminarProducto() {
        Inventario inv = new Inventario();

        // Agregar
        Producto p1 = new Producto("A1", "Mouse", "Mouse óptico", 4000, 5);
        assertTrue(inv.agregarProducto(p1));

        // Buscar por código
        assertTrue(inv.buscarPorCodigo("A1").isPresent());
        assertEquals("Mouse", inv.buscarPorCodigo("A1").get().getNombre());

        // Buscar por nombre/descripción
        List<Producto> encontrados = inv.buscarPorNombreODescripcion("mouse");
        assertFalse(encontrados.isEmpty());
        assertEquals("A1", encontrados.get(0).getCodigo());

        // Eliminar
        Producto eliminado = inv.eliminarProducto("A1");
        assertNotNull(eliminado);
        assertFalse(inv.buscarPorCodigo("A1").isPresent());

        // Estado final: Inventario vacío
        assertTrue(inv.listarTodos().isEmpty());
    }
}