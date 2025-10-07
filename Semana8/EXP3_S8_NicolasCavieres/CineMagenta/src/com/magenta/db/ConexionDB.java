/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.magenta.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;

public class ConexionDB {
    private static String url;
    private static String usuario;
    private static String clave;
    private static boolean cargado = false;

    private static void cargarPropiedades() {
        if (cargado) return;
        try (FileInputStream input = new FileInputStream("db/db.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            url = prop.getProperty("url");
            usuario = prop.getProperty("usuario");
            clave = prop.getProperty("clave");
            cargado = true;
        } catch (Exception e) {
            throw new RuntimeException("No se pudo cargar db.properties: " + e.getMessage(), e);
        }
    }

    public static Connection conectar() throws SQLException {
        cargarPropiedades();
        try {
            Connection conn = DriverManager.getConnection(url, usuario, clave);
            System.out.println("[INFO] Conexión a la base de datos exitosa.");
            return conn;
        } catch (SQLException ex) {
            System.err.println("[ERROR] No se pudo conectar a la base de datos: " + ex.getMessage());
            throw ex;
        }
    }
}
