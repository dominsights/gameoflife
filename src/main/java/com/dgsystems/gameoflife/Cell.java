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
        children = new ArrayList<>();
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
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.row;
        hash = 29 * hash + this.col;
        hash = 29 * hash + this.state;
        hash = 29 * hash + Objects.hashCode(this.children);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cell other = (Cell) obj;
        if (this.row != other.row) {
            return false;
        }
        if (this.col != other.col) {
            return false;
        }
        if (this.state != other.state) {
            return false;
        }
        return Objects.equals(this.children, other.children);
    }

    List<Position> children;

    /**
     * @return the children
     */
    public List<Position> getChildren() {
        return children;
    }

    /**
     * @param children the children to set
     */
    public void setChildren(List<Position> children) {
        this.children = children;
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
