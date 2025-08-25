
import com.tienda.pedidosonline.command.Command;
import com.tienda.pedidosonline.command.concretecommand.AgregarProductoCommand;
import com.tienda.pedidosonline.command.concretecommand.EliminarProductoCommand;
import com.tienda.pedidosonline.command.invoker.GestorDePedidos;
import com.tienda.pedidosonline.command.receiver.CarritoDeCompras;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



/**
 *
 * @author ariel
 */
// MainCommandTest.java
public class MainCommandTest {
    public static void main(String[] args) {
    // 1. Crear el receptor (quien sabe hacer las acciones)
    CarritoDeCompras miCarrito = new CarritoDeCompras("UsuarioA");
    // 2. Crear el invocador (quien puede ejecutar comandos)
    GestorDePedidos gestor = new GestorDePedidos();
    // 3. Crear comandos concretos, pasándoles el receptor y los detalles de la operación
    Command agregarCamiseta = new AgregarProductoCommand(miCarrito, "Camiseta");
    Command agregarPantalon = new AgregarProductoCommand(miCarrito, "Pantalón");
    Command eliminarCamiseta = new EliminarProductoCommand(miCarrito, "Camiseta");
    Command agregarZapatos = new EliminarProductoCommand(miCarrito, "Zapatillas");
    // 4. Configurar y ejecutar comandos a través del invocador
    System.out.println("--- Ejecutando Comandos ---");
    gestor.setCommand(agregarCamiseta);
    gestor.realizarOperacion();
    gestor.setCommand(agregarPantalon);
    gestor.realizarOperacion();
    gestor.setCommand(eliminarCamiseta);
    gestor.realizarOperacion();
    gestor.setCommand(agregarZapatos);
    gestor.realizarOperacion();
    // Mostrar el historial de comandos ejecutados
    gestor.mostrarHistorial();
    }
}