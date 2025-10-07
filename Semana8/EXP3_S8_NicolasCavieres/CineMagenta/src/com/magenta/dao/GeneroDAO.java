package com.magenta.dao;

import com.magenta.db.ConexionDB;
import com.magenta.model.Genero;
import java.sql.*;
import java.util.*;

/**
 * Data Access Object para la entidad Genero.
 * Maneja todas las operaciones de base de datos relacionadas con géneros cinematográficos.
 * 
 * @author Nicolas Cavieres
 */
public class GeneroDAO {
    /**
     * Obtiene todos los géneros ordenados alfabéticamente por nombre
     * @return Lista de todos los géneros cinematográficos
     * @throws SQLException si ocurre un error en la base de datos
     */
    public List<Genero> listarTodos() throws SQLException {
        List<Genero> lista = new ArrayList<>();
        String sql = "SELECT id, nombre FROM genero ORDER BY nombre";
        try (Connection cn = ConexionDB.conectar();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Genero(rs.getInt("id"), rs.getString("nombre")));
            }
        }
        return lista;
    }
    /**
     * Busca un género por su ID
     * @param id ID del género a buscar
     * @return Objeto Genero si se encuentra, null si no existe
     * @throws SQLException si ocurre un error en la base de datos
     */
    public Genero obtenerPorId(int id) throws SQLException {
        String sql = "SELECT id, nombre FROM genero WHERE id = ?";
        try (Connection cn = ConexionDB.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Genero(rs.getInt("id"), rs.getString("nombre"));
                }
            }
        }
        return null;
    }
    /**
     * Busca un género por su nombre exacto
     * @param nombre Nombre del género a buscar
     * @return Objeto Genero si se encuentra, null si no existe
     * @throws SQLException si ocurre un error en la base de datos
     */
    public Genero obtenerPorNombre(String nombre) throws SQLException {
        String sql = "SELECT id, nombre FROM genero WHERE nombre = ?";
        try (Connection cn = ConexionDB.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Genero(rs.getInt("id"), rs.getString("nombre"));
                }
            }
        }
        return null;
    }
}
