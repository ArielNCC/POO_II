package com.magenta.dao;

import com.magenta.db.ConexionDB;
import com.magenta.model.Director;
import java.sql.*;
import java.util.*;

/**
 * Data Access Object para la entidad Director.
 * Maneja todas las operaciones de base de datos relacionadas con directores.
 * 
 * @author Nicolas Cavieres
 */
public class DirectorDAO {
    /**
     * Obtiene todos los directores ordenados alfabéticamente por nombre
     * @return Lista de todos los directores
     * @throws SQLException si ocurre un error en la base de datos
     */
    public List<Director> listarTodos() throws SQLException {
        List<Director> lista = new ArrayList<>();
        String sql = "SELECT id, nombre FROM director ORDER BY nombre";
        try (Connection cn = ConexionDB.conectar();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Director(rs.getInt("id"), rs.getString("nombre")));
            }
        }
        return lista;
    }
    /**
     * Busca un director por su ID
     * @param id ID del director a buscar
     * @return Objeto Director si se encuentra, null si no existe
     * @throws SQLException si ocurre un error en la base de datos
     */
    public Director obtenerPorId(int id) throws SQLException {
        String sql = "SELECT id, nombre FROM director WHERE id = ?";
        try (Connection cn = ConexionDB.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Director(rs.getInt("id"), rs.getString("nombre"));
                }
            }
        }
        return null;
    }
    /**
     * Busca un director por su nombre exacto
     * @param nombre Nombre del director a buscar
     * @return Objeto Director si se encuentra, null si no existe
     * @throws SQLException si ocurre un error en la base de datos
     */
    public Director obtenerPorNombre(String nombre) throws SQLException {
        String sql = "SELECT id, nombre FROM director WHERE nombre = ?";
        try (Connection cn = ConexionDB.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Director(rs.getInt("id"), rs.getString("nombre"));
                }
            }
        }
        return null;
    }
}
