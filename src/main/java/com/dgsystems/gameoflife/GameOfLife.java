/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.dgsystems.gameoflife;

import java.util.List;

import static com.dgsystems.gameoflife.CellsGraph.copy2dArray;

/**
 * @author Domício
 */
public class GameOfLife {
    private static final int SIZE = 5;
    private final CellsGraph cells;

    public GameOfLife() {
        cells = new CellsGraph(SIZE);
    }

    public char[][] getCells() {
        char[][] array = new char[SIZE][SIZE];

        for (Cell[] outer : cells.nodes) {
            for (Cell data : outer) {
                array[data.row][data.col] = data.state();
            }
        }

        return array;
    }

    public void applySeed(List<Cell> seed) {
        for (var cell : seed) {
            cells.add(cell);
        }
    }

    public void tick() {
        Cell[][] copiedArray = copy2dArray(cells.nodes);

        for (Cell[] outer : cells.nodes) {
            for (Cell cell : outer) {
                verifyRulesForDeadCells(copiedArray, cell);
                verifyRulesForLiveCells(copiedArray, cell);
            }
        }

        cells.nodes = copiedArray;
    }

    public static void printCellsGrid(char[][] cells) {
        System.out.println(" _____ _____ _____ _____ _____");
        System.out.println("|     |     |     |     |     |");
        System.out.println(String.format("|  %s  |  %s  |  %s  |  %s  |  %s  |", cells[0][0], cells[0][1], cells[0][2], cells[0][3], cells[0][4]));
        System.out.println("|_____|_____|_____|_____|_____|");
        System.out.println("|     |     |     |     |     |");
        System.out.println(String.format("|  %s  |  %s  |  %s  |  %s  |  %s  |", cells[1][0], cells[1][1], cells[1][2], cells[1][3], cells[1][4]));
        System.out.println("|_____|_____|_____|_____|_____|");
        System.out.println("|     |     |     |     |     |");
        System.out.println(String.format("|  %s  |  %s  |  %s  |  %s  |  %s  |", cells[2][0], cells[2][1], cells[2][2], cells[2][3], cells[2][4]));
        System.out.println("|_____|_____|_____|_____|_____|");
        System.out.println("|     |     |     |     |     |");
        System.out.println(String.format("|  %s  |  %s  |  %s  |  %s  |  %s  |", cells[3][0], cells[3][1], cells[3][2], cells[3][3], cells[3][4]));
        System.out.println("|_____|_____|_____|_____|_____|");
        System.out.println("|     |     |     |     |     |");
        System.out.println(String.format("|  %s  |  %s  |  %s  |  %s  |  %s  |", cells[4][0], cells[4][1], cells[4][2], cells[4][3], cells[4][4]));
        System.out.println("|_____|_____|_____|_____|_____|");

        System.out.print("\033[?25l");
        System.out.print("\033[16F");
        System.out.flush();
    }

    private void verifyRulesForLiveCells(Cell[][] copiedArray, Cell cell) {
        if (cell instanceof Live live) {
            if (verifyBecomeDeadLessThanTwoLiveNeighbours(live) || verifyBecomeDeadMoreThanThreeLiveNeighbours(live)) {
                updateDeadCell(copiedArray, cell);
            }
        }
    }

    private void verifyRulesForDeadCells(Cell[][] copiedArray, Cell cell) {
        if (cell instanceof Dead dead && verifyBecomeAliveThreeLiveNeighbours(dead)) {
            updateLiveCell(copiedArray, cell);
        }
    }

    private static void updateLiveCell(Cell[][] copiedArray, Cell cell) {
        var updatedCell = new Live(cell.row, cell.col);
        updatedCell.setChildren(cell.children);
        copiedArray[cell.row][cell.col] = updatedCell;
    }

    private static void updateDeadCell(Cell[][] copiedArray, Cell cell) {
        var updatedCell = new Dead(cell.row, cell.col);
        updatedCell.setChildren(cell.children);
        copiedArray[cell.row][cell.col] = updatedCell;
    }

    private boolean verifyBecomeDeadMoreThanThreeLiveNeighbours(Live cell) {
        return countLiveNeighbours(cell) > 3;
    }

    private boolean verifyBecomeDeadLessThanTwoLiveNeighbours(Live cell) {
        return countLiveNeighbours(cell) < 2;
    }

    private boolean verifyBecomeAliveThreeLiveNeighbours(Cell cell) {
        return countLiveNeighbours(cell) == 3;
    }

    private long countLiveNeighbours(Cell cell) {
        return cell.children
                .stream()
                .map(c -> cells.nodes[c.x()][c.y()])
                .filter(c -> c instanceof Live)
                .count();
    }
}
