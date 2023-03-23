/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.dgsystems.gameoflife;

import java.util.List;
import java.util.Random;

/**
 *
 * @author Domício
 */
public class GameOfLife {

    private final CellsGraph cells;

    public char[][] getCells() {
        char[][] array = new char[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                Cell data = cells.nodes[i][j];
                if(data != null)
                    array[i][j] = data.state();
                else
                    array[i][j] = Cell.NotSet;
            }
        }

        return array;
    }

    public GameOfLife() {
        cells = new CellsGraph(SIZE);
    }
    private static final int SIZE = 5;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread.sleep(500);

            char[] chars = {'#', '@', '?'};
            Random rand = new Random();
            char randomChar = chars[rand.nextInt(chars.length)];

            char[][] cells = {
                {randomChar, randomChar, randomChar, randomChar, randomChar},
                {randomChar, randomChar, randomChar, randomChar, randomChar},
                {randomChar, randomChar, randomChar, randomChar, randomChar},
                {randomChar, randomChar, randomChar, randomChar, randomChar},
                {randomChar, randomChar, randomChar, randomChar, randomChar}
            };

            printCellsGrid(cells);
        }

        System.out.print("\033[17B");
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

    public void applySeed(List<Cell> seed) {
        for (var cell : seed) {
            cells.add(cell);
        }
    }

    public GameOfLife tick() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
