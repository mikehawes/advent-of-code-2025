package mikehawes.adventofcode.calendar2025.day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day7Star2 {

    static void main() throws IOException {
        String input = Files.readString(Path.of("input/day7.txt"));
        IO.println("Number of tachyon paths: " + countTachyonPaths(input));
    }

    public static long countTachyonPaths(String input) {
        Grid grid = Grid.from(input);
        Position start = grid.findStart();
        State state = new State(List.of(start), 0);
        while (!state.positions().isEmpty()) {
            state = state.move(grid);
        }
        return state.terminatedPaths();
    }

    private record State(List<Position> positions, long terminatedPaths) {

        State move(Grid grid) {
            List<Position> newPositions = new ArrayList<>();
            long terminatedPaths = terminatedPaths();
            for(Position position : positions) {
                State moved = movePosition(position, grid);
                newPositions.addAll(moved.positions);
                terminatedPaths += moved.terminatedPaths;
            }
            return new State(newPositions, terminatedPaths);
        }
    }

    private static State movePosition(Position position, Grid grid) {
        Position below = position.below();
        if(!grid.contains(below)) {
            return new State(List.of(), 1);
        }
        String cell = grid.cell(below);
        if("^".equals(cell)) {
            List<Position> sides = below.sides();
            List<Position> newPositions = new ArrayList<>(sides.size());
            long terminatedPaths = 0;
            for(Position side : sides) {
                if(grid.contains(side)) {
                    newPositions.add(side);
                } else {
                    terminatedPaths++;
                }
            }
            return new State(newPositions, terminatedPaths);
        }
        return new State(List.of(below), 0);
    }
}
