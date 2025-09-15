/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package cl.duoc;

/**
 *
 * @author ariel
 */
import cl.duoc.modelo.ProductoTest;
import cl.duoc.modelo.InventarioTest;
import cl.duoc.modelo.InventarioIntegracionTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
    ProductoTest.class,
    InventarioTest.class,
    InventarioIntegracionTest.class
})
public class SuiteTest {
    // Vacío, solo anotaciones
}