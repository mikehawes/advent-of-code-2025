package mikehawes.adventofcode.calendar2025.day7;

import mikehawes.adventofcode.calendar2025.grid.Grid;
import mikehawes.adventofcode.calendar2025.grid.Position;

public class Tachyon {

    public static Position findStart(Grid grid) {
        return grid.positions()
                .filter(position -> "S".equals(grid.cell(position)))
                .findFirst().orElseThrow();
    }
}
