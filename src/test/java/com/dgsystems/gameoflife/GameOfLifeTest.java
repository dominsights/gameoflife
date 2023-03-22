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
        var seed = List.of(
                new Cell(2, 2, Cell.Live),
                new Cell(1, 2, Cell.Live),
                new Cell(2, 3, Cell.Live)
        );

        gameOfLife = gameOfLife.applySeed(seed);

        char[][] expected = {
            {Cell.NotSet, Cell.NotSet, Cell.NotSet, Cell.NotSet, Cell.NotSet},
            {Cell.NotSet, Cell.NotSet, Cell.Live, Cell.NotSet, Cell.NotSet},
            {Cell.NotSet, Cell.NotSet, Cell.Live, Cell.Live, Cell.NotSet},
            {Cell.NotSet, Cell.NotSet, Cell.NotSet, Cell.NotSet, Cell.NotSet},
            {Cell.NotSet, Cell.NotSet, Cell.NotSet, Cell.NotSet, Cell.NotSet}
        };
        
        assertThat(gameOfLife.getCells()).isEqualTo(expected);
    }
}
