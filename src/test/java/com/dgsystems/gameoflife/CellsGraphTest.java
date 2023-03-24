/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgsystems.gameoflife;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Domício
 */
public class CellsGraphTest {
    @Test
    public void test_insert_node() {
        var matrixGraph = new CellsGraph(5);
        var cell = new Dead(2, 2);
        matrixGraph.add(cell);
        var retrieved = matrixGraph.get(2, 2);
        
        var expected = new Dead(2, 2);
        expected.setChildren(List.of(
            new Position(1,1),
            new Position(1,2),
            new Position(1,3),
            new Position(2,1),
            new Position(2,3),
            new Position(3,1),
            new Position(3,2),
            new Position(3,3)
            ));
        
        assertThat(retrieved).isEqualTo(expected);
    }
}
