/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test.model;

import com.mycompany.exp2_s4.modelo.Inventario;
import com.mycompany.exp2_s4.modelo.Producto;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author luisr
 */
public class IntegracionProductoInventarioTest {
    
   @Test
    void flujoBasico_agregar_buscar_eliminar_y_coherencia() {
        Inventario inv = new Inventario();

        // Agregar
        Producto p = new Producto("K1", "Cargador", "USB-C", 7990, 10);
        assertTrue(inv.agregarProducto(p));

        // Buscar
        Optional<Producto> buscado = inv.buscarPorCodigo("K1");
        assertTrue(buscado.isPresent());
        assertEquals("Cargador", buscado.get().getNombre());

        // Actualizar
        assertTrue(inv.actualizarPrecio("K1", 8990));
        assertTrue(inv.actualizarStock("K1", 12));
        Producto actualizado = inv.buscarPorCodigo("K1").get();
        assertEquals(8990, actualizado.getPrecio(), 0.0001);
        assertEquals(12, actualizado.getStock());

        // Eliminar
        Producto eliminado = inv.eliminarProducto("K1");
        assertNotNull(eliminado);

        // Coherencia del estado
        assertFalse(inv.buscarPorCodigo("K1").isPresent());
    }
}