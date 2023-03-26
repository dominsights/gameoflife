/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgsystems.gameoflife;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author domic
 */
public abstract class Cell {

    protected final int row;
    protected final int col;
    protected char state;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int row() {
        return row;
    }

    public int col() {
        return col;
    }

    public char state() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Cell)) return false;
        Cell cell = (Cell) o;
        return row == cell.row && col == cell.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    static char Live = '@';
    static char Dead = ' ';
}

record Position(int x, int y) { }

class Live extends Cell {

    public Live(int row, int col) {
        super(row, col);
        state = '@';
    }

}

class Dead extends Cell {

    public Dead(int row, int col) {
        super(row, col);
        state = ' ';
    }
}
