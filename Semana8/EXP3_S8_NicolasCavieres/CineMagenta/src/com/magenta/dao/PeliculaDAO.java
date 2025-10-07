
package com.magenta.dao;

import java.sql.*;
import java.util.*;
import com.magenta.db.ConexionDB;
import com.magenta.model.Pelicula;
import com.magenta.model.Director;
import com.magenta.model.Genero;
import com.magenta.util.ValidacionCRUD;
import com.magenta.exception.PeliculaNoEncontradaException;

public class PeliculaDAO {

    /**
     * Obtiene una película por su ID.Lanza PeliculaNoEncontradaException si no existe.
     * @param id
     * @return 
     * @throws java.sql.SQLException
     * @throws com.magenta.exception.PeliculaNoEncontradaException
     */
    public Pelicula obtenerPorId(int id) throws SQLException, PeliculaNoEncontradaException {
        String sql = "SELECT c.id, c.titulo, c.anio, c.duracion, " +
                "d.id as director_id, d.nombre as director_nombre, " +
                "g.id as genero_id, g.nombre as genero_nombre " +
                "FROM cartelera c " +
                "JOIN director d ON c.director_id = d.id " +
                "JOIN genero g ON c.genero_id = g.id " +
                "WHERE c.id = ?";
        try (Connection cn = ConexionDB.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ValidacionCRUD.encontradoPorId(id);
                    return mapRow(rs);
                } else {
                    ValidacionCRUD.noEncontradoPorId(id);
                    throw new PeliculaNoEncontradaException("No se encontró película con ID: " + id);
                }
            }
        }
    }

    // BÚSQUEDA AVANZADA: por título (case-insensitive), género y duración
    public List<Pelicula> buscarPeliculas(String titulo, String generoNombre) throws SQLException {
        StringBuilder sql = new StringBuilder(
            "SELECT c.id, c.titulo, c.anio, c.duracion, " +
            "d.id as director_id, d.nombre as director_nombre, " +
            "g.id as genero_id, g.nombre as genero_nombre " +
            "FROM cartelera c " +
            "JOIN director d ON c.director_id = d.id " +
            "JOIN genero g ON c.genero_id = g.id WHERE 1=1");
        List<Object> parametros = new ArrayList<>();
        if (titulo != null && !titulo.isBlank()) {
            sql.append(" AND LOWER(c.titulo) LIKE ?");
            parametros.add("%" + titulo.toLowerCase() + "%");
        }
        if (generoNombre != null && !generoNombre.trim().isEmpty()) {
            sql.append(" AND g.nombre = ?");
            parametros.add(generoNombre);
        }
        sql.append(" ORDER BY c.id ASC");
        List<Pelicula> lista = new ArrayList<>();
        try (Connection cn = ConexionDB.conectar();
             PreparedStatement ps = cn.prepareStatement(sql.toString())) {
            for (int i = 0; i < parametros.size(); i++) {
                ps.setObject(i + 1, parametros.get(i));
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapRow(rs));
                }
            }
            ValidacionCRUD.consultaFiltrada(lista.size());
        }
        return lista;
    }

    // CRUD y utilidades
    public void insertar(Pelicula p) throws SQLException {
        if (p == null) throw new IllegalArgumentException("La película no puede ser null");
        if (p.getDirector() == null || p.getDirector().getId() == null) {
            throw new IllegalArgumentException("El director es obligatorio y debe tener un ID válido");
        }
        if (p.getGenero() == null || p.getGenero().getId() == null) {
            throw new IllegalArgumentException("El género es obligatorio y debe tener un ID válido");
        }
        
        String sql = "INSERT INTO cartelera (titulo, director_id, anio, duracion, genero_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection cn = ConexionDB.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, p.getTitulo());
            ps.setInt(2, p.getDirector().getId());
            ps.setInt(3, p.getAnio());
            ps.setInt(4, p.getDuracion());
            ps.setInt(5, p.getGenero().getId());
            int filas = ps.executeUpdate();
            if (filas > 0) {
                ValidacionCRUD.insertOK();
            } else {
                ValidacionCRUD.insertWarn();
            }
        }
    }

    public void actualizar(Pelicula p) throws SQLException {
        if (p == null) throw new IllegalArgumentException("La película no puede ser null");
        if (p.getId() == null) throw new IllegalArgumentException("ID es obligatorio para actualizar");
        if (p.getDirector() == null || p.getDirector().getId() == null) {
            throw new IllegalArgumentException("El director es obligatorio y debe tener un ID válido");
        }
        if (p.getGenero() == null || p.getGenero().getId() == null) {
            throw new IllegalArgumentException("El género es obligatorio y debe tener un ID válido");
        }
        
        String sql = "UPDATE cartelera SET titulo = ?, director_id = ?, anio = ?, duracion = ?, genero_id = ? WHERE id = ?";
        try (Connection cn = ConexionDB.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, p.getTitulo());
            ps.setInt(2, p.getDirector().getId());
            ps.setInt(3, p.getAnio());
            ps.setInt(4, p.getDuracion());
            ps.setInt(5, p.getGenero().getId());
            ps.setInt(6, p.getId());
            int filas = ps.executeUpdate();
            if (filas > 0) {
                ValidacionCRUD.updateOK();
            } else {
                ValidacionCRUD.updateWarn();
            }
        }
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM cartelera WHERE id = ?";
        try (Connection cn = ConexionDB.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                ValidacionCRUD.deleteOK();
            } else {
                ValidacionCRUD.deleteWarn();
            }
        }
    }

    public List<Pelicula> listarTodas() throws SQLException {
        String sql = "SELECT c.id, c.titulo, c.anio, c.duracion, " +
                "d.id as director_id, d.nombre as director_nombre, " +
                "g.id as genero_id, g.nombre as genero_nombre " +
                "FROM cartelera c " +
                "JOIN director d ON c.director_id = d.id " +
                "JOIN genero g ON c.genero_id = g.id " +
                "ORDER BY c.id ASC";
        List<Pelicula> lista = new ArrayList<>();
        try (Connection cn = ConexionDB.conectar();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapRow(rs));
            }
            ValidacionCRUD.consultaTodas(lista.size());
        }
        return lista;
    }

    // Mapeo de fila -> Pelicula
    private Pelicula mapRow(ResultSet rs) throws SQLException {
        Director director = new Director(rs.getInt("director_id"), rs.getString("director_nombre"));
        Genero genero = new Genero(rs.getInt("genero_id"), rs.getString("genero_nombre"));
        return new Pelicula(
                rs.getInt("id"),
                rs.getString("titulo"),
                director,
                rs.getInt("anio"),
                rs.getInt("duracion"),
                genero
        );
    }
}

