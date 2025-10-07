/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.magenta.ui;

import com.magenta.model.Pelicula;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class PeliculaTableModel extends AbstractTableModel {
    private final String[] cols = {"ID", "Título", "Director", "Año", "Duración (min)", "Género"};
    private final List<Pelicula> data = new ArrayList<>();

    public void setData(List<Pelicula> peliculas) {
        data.clear();
        if (peliculas != null) data.addAll(peliculas);
        fireTableDataChanged();
    }

    public Pelicula getAt(int row) {
        if (row < 0 || row >= data.size()) return null;
        return data.get(row);
    }

    @Override public int getRowCount() { return data.size(); }
    @Override public int getColumnCount() { return cols.length; }
    @Override public String getColumnName(int c) { return cols[c]; }

    @Override
    public Object getValueAt(int row, int col) {
        Pelicula p = data.get(row);
        return switch (col) {
            case 0 -> p.getId();
            case 1 -> p.getTitulo();
            case 2 -> p.getDirector() != null ? p.getDirector().getNombre() : null;
            case 3 -> p.getAnio();
            case 4 -> p.getDuracion();
            case 5 -> p.getGenero() != null ? p.getGenero().getNombre() : null;
            default -> null;
        };
    }

    @Override public Class<?> getColumnClass(int c) {
        return switch (c) {
            case 0, 3, 4 -> Integer.class; // ID, Año, Duración
            default -> String.class;
        };
    }
}
