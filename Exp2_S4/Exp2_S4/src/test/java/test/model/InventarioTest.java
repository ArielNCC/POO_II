/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test.model;

import com.mycompany.exp2_s4.modelo.Inventario;
import com.mycompany.exp2_s4.modelo.Producto;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author luisr
 */
public class InventarioTest {

    @Test
    void agregarProducto_ok() {
        Inventario inv = new Inventario();
        Producto p = new Producto("P1", "Lapiz", "HB", 200, 100);
        assertTrue(inv.agregarProducto(p));
        assertTrue(inv.buscarPorCodigo("P1").isPresent());
    }

    @Test
    void agregarProducto_nulo_debeFallar() {
        Inventario inv = new Inventario();
        assertThrows(IllegalArgumentException.class, () -> inv.agregarProducto(null));
    }

    @Test
    void eliminarProducto_existente() {
        Inventario inv = new Inventario();
        Producto p = new Producto("P2", "Borrador", "Goma", 300, 50);
        inv.agregarProducto(p);
        Producto eliminado = inv.eliminarProducto("P2");
        assertNotNull(eliminado);
        assertEquals("P2", eliminado.getCodigo());
        assertEquals(Optional.empty(), inv.buscarPorCodigo("P2"));
    }

    @Test
    void eliminarProducto_inexistente() {
        Inventario inv = new Inventario();
        assertNull(inv.eliminarProducto("ZZZ"));
    }

    @Test
    void buscarPorNombre_coincidencias() {
        Inventario inv = new Inventario();
        inv.agregarProducto(new Producto("A", "Teclado", "Mecánico", 1000, 1));
        inv.agregarProducto(new Producto("B", "Mouse", "Óptico", 500, 2));
        inv.agregarProducto(new Producto("C", "Monitor", "24\"", 2000, 3));

        List<Producto> r = inv.buscarPorNombreODescripcion("mo");
        // Mouse y Monitor contienen "mo" (case-insensitive)
        assertEquals(2, r.size());
    }

    @Test
    void buscarPorNombre_sinResultados() {
        Inventario inv = new Inventario();
        inv.agregarProducto(new Producto("A", "Teclado", "Mecánico", 1000, 1));
        List<Producto> r = inv.buscarPorNombreODescripcion("impresora");
        assertTrue(r.isEmpty());
    }

    @Test
    void listarTodos_contieneAgregados() {
        Inventario inv = new Inventario();
        inv.agregarProducto(new Producto("1", "A", "a", 1, 1));
        inv.agregarProducto(new Producto("2", "B", "b", 2, 2));
        inv.agregarProducto(new Producto("3", "C", "c", 3, 3));
        List<Producto> lista = inv.listarTodos();
        assertEquals(3, lista.size());
    }

    @Test
    void actualizarPrecioYStock_porCodigo() {
        Inventario inv = new Inventario();
        inv.agregarProducto(new Producto("X", "Item", "Desc", 10, 5));
        assertTrue(inv.actualizarPrecio("X", 20));
        assertTrue(inv.actualizarStock("X", 7));
        Producto p = inv.buscarPorCodigo("X").get();
        assertEquals(20, p.getPrecio(), 0.0001);
        assertEquals(7, p.getStock());
    }
}
