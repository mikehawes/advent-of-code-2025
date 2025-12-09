package mikehawes.adventofcode.calendar2025.day9;

import mikehawes.adventofcode.calendar2025.grid.Grid;

public record FloorGrid(Floor floor, Grid grid) {

    public String print() {
        return grid.print();
    }
}
