/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.tienda.pedidosonline.controller;

import com.tienda.pedidosonline.model.Usuario;

/**
 * Controlador para gestionar los usuarios en el sistema
 * @author Nicolas Cavieres
 */

 public class UsuarioController {
    private Usuario usuario;

    public UsuarioController(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNombreUsuario() {
        return usuario.getNombre();
    }
}