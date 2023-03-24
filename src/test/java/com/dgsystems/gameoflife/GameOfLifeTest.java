/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgsystems.gameoflife;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author Domício
 */
public class GameOfLifeTest {

    public GameOfLifeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void should_apply_seed() {
        var gameOfLife = new GameOfLife();
        List<Cell> seed = List.of(
                new Live(2, 2),
                new Live(1, 2),
                new Live(2, 3)
        );

        gameOfLife.applySeed(seed);

        char[][] expected = {
            {Cell.Dead, Cell.Dead, Cell.Dead, Cell.Dead, Cell.Dead},
            {Cell.Dead, Cell.Dead, Cell.Live, Cell.Dead, Cell.Dead},
            {Cell.Dead, Cell.Dead, Cell.Live, Cell.Live, Cell.Dead},
            {Cell.Dead, Cell.Dead, Cell.Dead, Cell.Dead, Cell.Dead},
            {Cell.Dead, Cell.Dead, Cell.Dead, Cell.Dead, Cell.Dead}
        };

        assertThat(gameOfLife.getCells()).isEqualTo(expected);
    }

    @Test
    public void dead_cell_with_three_live_neighbours_become_live_after_first_generation() {
        var gameOfLife = new GameOfLife();
        List<Cell> seed = List.of(
                new Live(2, 2),
                new Live(1, 2),
                new Live(2, 3),
                new Dead(1, 3)
        );

        gameOfLife.applySeed(seed);

        gameOfLife.tick();

        char[][] expected = {
            {Cell.Dead, Cell.Dead, Cell.Dead, Cell.Dead, Cell.Dead},
            {Cell.Dead, Cell.Dead, Cell.Live, Cell.Live, Cell.Dead},
            {Cell.Dead, Cell.Dead, Cell.Live, Cell.Live, Cell.Dead},
            {Cell.Dead, Cell.Dead, Cell.Dead, Cell.Dead, Cell.Dead},
            {Cell.Dead, Cell.Dead, Cell.Dead, Cell.Dead, Cell.Dead}
        };
        
        assertThat(gameOfLife.getCells()).isEqualTo(expected);
    }

    @Test
    public void test_any_live_cell_with_less_than_two_live_neighbours_dies() {
        var gameOfLife = new GameOfLife();
        List<Cell> seed = List.of(
                new Live(2, 2),
                new Live(1, 2),
                new Dead(2, 3),
                new Dead(1, 3)
        );

        gameOfLife.applySeed(seed);
        gameOfLife.tick();

        char[][] expected = {
                {Cell.Dead, Cell.Dead, Cell.Dead, Cell.Dead, Cell.Dead},
                {Cell.Dead, Cell.Dead, Cell.Dead, Cell.Dead, Cell.Dead},
                {Cell.Dead, Cell.Dead, Cell.Dead, Cell.Dead, Cell.Dead},
                {Cell.Dead, Cell.Dead, Cell.Dead, Cell.Dead, Cell.Dead},
                {Cell.Dead, Cell.Dead, Cell.Dead, Cell.Dead, Cell.Dead}
        };

        assertThat(gameOfLife.getCells()).isEqualTo(expected);
    }

    @Test
    public void test_cell_with_four_live_neighbours_dies() {
        var gameOfLife = new GameOfLife();

        List<Cell> seed = List.of(
                new Dead(0, 0),
                new Dead(0, 1),
                new Dead(0, 2),
                new Dead(0, 3),
                new Dead(0, 4),
                new Dead(1, 0),
                new Dead(1, 1),
                new Dead(1, 2),
                new Dead(1, 3),
                new Dead(1, 4),
                new Dead(2, 0),
                new Live(2, 1),
                new Live(2, 2),
                new Live(2, 3),
                new Dead(2, 4),
                new Dead(3, 0),
                new Live(3, 1),
                new Live(3, 2),
                new Dead(3, 3),
                new Dead(3, 4),
                new Dead(4, 0),
                new Dead(4, 1),
                new Dead(4, 2),
                new Dead(4, 3),
                new Dead(4, 4)
        );

        gameOfLife.applySeed(seed);
        gameOfLife.tick();

        char[][] expected = {
                {Cell.Dead, Cell.Dead, Cell.Dead, Cell.Dead, Cell.Dead},
                {Cell.Dead, Cell.Dead, Cell.Live, Cell.Dead, Cell.Dead},
                {Cell.Dead, Cell.Live, Cell.Dead, Cell.Live, Cell.Dead},
                {Cell.Dead, Cell.Live, Cell.Dead, Cell.Live, Cell.Dead},
                {Cell.Dead, Cell.Dead, Cell.Dead, Cell.Dead, Cell.Dead}
        };

        assertThat(gameOfLife.getCells()).isEqualTo(expected);
    }

    @Test
    public void test_two_generations() {
        var gameOfLife = new GameOfLife();

        List<Cell> seed = List.of(
                new Dead(0, 0),
                new Dead(0, 1),
                new Dead(0, 2),
                new Dead(0, 3),
                new Dead(0, 4),
                new Dead(1, 0),
                new Dead(1, 1),
                new Dead(1, 2),
                new Dead(1, 3),
                new Dead(1, 4),
                new Dead(2, 0),
                new Live(2, 1),
                new Live(2, 2),
                new Live(2, 3),
                new Dead(2, 4),
                new Dead(3, 0),
                new Dead(3, 1),
                new Live(3, 2),
                new Dead(3, 3),
                new Dead(3, 4),
                new Dead(4, 0),
                new Dead(4, 1),
                new Dead(4, 2),
                new Dead(4, 3),
                new Dead(4, 4)
        );

        gameOfLife.applySeed(seed);
        gameOfLife.tick();
        gameOfLife.tick();

        char[][] expected = {
                {Cell.Dead, Cell.Dead, Cell.Dead, Cell.Dead, Cell.Dead},
                {Cell.Dead, Cell.Live, Cell.Live, Cell.Live, Cell.Dead},
                {Cell.Dead, Cell.Dead, Cell.Dead, Cell.Dead, Cell.Dead},
                {Cell.Dead, Cell.Live, Cell.Dead, Cell.Live, Cell.Dead},
                {Cell.Dead, Cell.Dead, Cell.Live, Cell.Dead, Cell.Dead}
        };

        assertThat(gameOfLife.getCells()).isEqualTo(expected);
    }

    @Test
    public void test_glider_for_one_generation() {
        List<Cell> glider = List.of(
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
                new Dead(3, 3),
                new Dead(3, 4),
                new Dead(4, 0),
                new Dead(4, 1),
                new Dead(4, 2),
                new Dead(4, 3),
                new Dead(4, 4)
        );

        var gameOfLife = new GameOfLife();
        gameOfLife.applySeed(glider);
        gameOfLife.tick();

        char[][] expected = {
                {Cell.Dead, Cell.Dead, Cell.Live, Cell.Dead, Cell.Dead},
                {Cell.Live, Cell.Dead, Cell.Live, Cell.Dead, Cell.Dead},
                {Cell.Dead, Cell.Live, Cell.Live, Cell.Dead, Cell.Dead},
                {Cell.Dead, Cell.Dead, Cell.Dead, Cell.Dead, Cell.Dead},
                {Cell.Dead, Cell.Dead, Cell.Dead, Cell.Dead, Cell.Dead}
        };

        assertThat(gameOfLife.getCells()).isEqualTo(expected);

    }
}
