/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.pedidosonline.command.invoker;

import com.tienda.pedidosonline.command.Command;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ariel
 */
public class GestorDePedidos {
    private Command command;
    private List<Command> historialCommand = new ArrayList<>();
    
    // Metodo para configurar el coomando a ejecutar
    public void setCommand (Command command){
        this.command = command;
    }
    
    // Metodo para ejecutar el comando actual y guardarlo en el historial
    public void realizarOperacion (){
            if (command !=null){
                command.ejecutar();
                historialCommand.add(command); // Guarda para posibles funcionalidades futuras (deshacer, reiintentar)
            } else {
                System.out.println("No se ha configurado ningun comando para ejecutar.");
            }
    }
    
    public void mostrarHistorial(){
        System.out.println("\n--- Historial de Comandos ---");
        for (int i=0; i<historialCommand.size(); i++){
            System.out.println( (i+1) + "."
                    + historialCommand.get(i).getClass().getSimpleName());
        } System.out.println("-----------------------------");
    }
    
}
