package com.dgsystems.gameoflife;

import java.util.*;
import java.util.stream.Collectors;

public class CellsGraph implements Cloneable {
    public static final List<Offset> NEIGHBOURS_OFFSET = List.of(
            new Offset(-1, -1),
            new Offset(-1, 0),
            new Offset(-1, 1),
            new Offset(0, -1),
            new Offset(0, 1),
            new Offset(1, -1),
            new Offset(1, 0),
            new Offset(1, 1)
    );

    private Map<Position, List<Position>> positionGraph;
    private List<Cell> elements;

    private CellsGraph() {
        positionGraph = new HashMap<>();
        elements = new ArrayList<>();
    }

    private CellsGraph(HashMap<Position, List<Position>> cellListHashMap, List<Cell> elements) {
        positionGraph = cellListHashMap;
        this.elements = elements;
    }

    public void addCell(Cell cell) {
        positionGraph.put(new Position(cell.row, cell.col), new ArrayList<>());
        elements.add(cell);
    }

    public void updateCell(Cell cell) {
        elements.set(elements.indexOf(cell), cell);
    }

    public void addEdge(Cell source, Cell destination) {
        positionGraph.get(new Position(source.row, source.col)).add(new Position(destination.row, destination.col));
    }

    public Cell get(int row, int col) {
        var position = positionGraph.keySet().stream().filter(c -> c.x() == row && c.y() == col).findFirst().orElseThrow();

        return elements.stream().filter(c -> c.row == position.x() && c.col == position.y()).findFirst().orElseThrow();
    }

    public List<Cell> getNeighbours(Cell cell) {
        return positionGraph.get(new Position(cell.row, cell.col))
                .stream()
                .map(p -> elements
                        .stream()
                        .filter(c -> c.row == p.x() && c.col() == p.y())
                        .findFirst()
                        .orElseThrow())
                .collect(Collectors.toList());
    }

    public List<Cell> getCells() {
        return Collections.unmodifiableList(elements);
    }

    @Override
    protected CellsGraph clone() {
        return new CellsGraph(new HashMap<>(positionGraph), new ArrayList<>(elements));
    }

    public static CellsGraph newGraph(int size) {
        var graph = new CellsGraph();

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                Cell node = new Dead(i, j);
                graph.addCell(node);
            }
        }

        for(var cell : graph.getCells()) {
            for (var offset : NEIGHBOURS_OFFSET) {
                int neighbourRow = cell.row() + offset.row;
                int neighbourCol = cell.col() + offset.col;

                if ((neighbourRow >= 0 && neighbourRow < size) && neighbourCol >= 0 && neighbourCol < size) {
                    graph.addEdge(cell, graph.get(neighbourRow, neighbourCol));
                }
            }
        }

        return graph;
    }

    record Offset(int row, int col) {

    }
}
