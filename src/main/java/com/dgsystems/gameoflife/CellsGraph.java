/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgsystems.gameoflife;

import java.util.List;

/**
 *
 * @author Domício
 */
public class CellsGraph {

    public Cell[][] nodes;
    private final int size;

    public CellsGraph(int size) {
        this.size = size;
        nodes = new Cell[size][size];
        
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                Cell node = new NotSet(i, j);
                nodes[i][j] = node;
            }
        }
    }

    public void add(Cell cell) {
        var neighboursOffset = List.of(
                new Offset(-1, -1),
                new Offset(-1, 0),
                new Offset(-1, 1),
                new Offset(0, -1),
                new Offset(0, 1),
                new Offset(1, -1),
                new Offset(1, 0),
                new Offset(1, 1)
        );
        
        for(var offset : neighboursOffset) {
            int neighbourRow = cell.row() + offset.row;
            int neighbourCol = cell.col() + offset.col;
            
            if((neighbourRow >= 0 && neighbourRow < size)
                    && neighbourCol >= 0 && neighbourCol < size) {
                cell.children.add(new Position(neighbourRow, neighbourCol));
            }
        }

        nodes[cell.row()][cell.col()] = cell;
    }

    public Cell get(int row, int col) {
        return nodes[row][col];
    }

    record Offset(int row, int col) {

    }
}
