package com.dgsystems.gameoflife;

import java.util.List;

public class Program {
    public static void main(String[] args) throws InterruptedException {

        var gameOfLife = new GameOfLife();

        List<Cell> seed = List.of(
                new Live(0, 0),
                new Dead(0, 1),
                new Live(0, 2),
                new Dead(0, 3),
                new Dead(0, 4),
                new Dead(1, 0),
                new Live(1, 1),
                new Live(1, 2),
                new Dead(1, 3),
                new Dead(1, 4),
                new Dead(2, 0),
                new Live(2, 1),
                new Dead(2, 2),
                new Dead(2, 3),
                new Dead(2, 4),
                new Dead(3, 0),
                new Dead(3, 1),
                new Dead(3, 2),
                new Live(3, 3),
                new Live(3, 4),
                new Dead(4, 0),
                new Dead(4, 1),
                new Dead(4, 2),
                new Live(4, 3),
                new Live(4, 4)
        );

        gameOfLife.applySeed(seed);

        for (int i = 0; i < 30; i++) {
            gameOfLife.printCellsGrid(gameOfLife.getCells());
            Thread.sleep(1000);
            gameOfLife.tick();
        }

        System.out.print("\033[17B");
    }
}
