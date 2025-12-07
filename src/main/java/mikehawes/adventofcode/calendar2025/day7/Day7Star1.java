package mikehawes.adventofcode.calendar2025.day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day7Star1 {

    static void main() throws IOException {
        String input = Files.readString(Path.of("input/day7.txt"));
        IO.println("Number of beam splits: " + countBeamSplits(input));
    }

    public static long countBeamSplits(String input) {
        Grid grid = Grid.from(input);
        Position start = grid.findStart();
        State state = new State(Set.of(start), 0);
        while (!state.positions().isEmpty()) {
            state = state.move(grid);
        }
        return state.splits();
    }

    public record State(Set<Position> positions, long splits) {

        public State move(Grid grid) {
            long splits = 0;
            Set<Position> newPositions = new HashSet<>();
            for(Position position : positions) {
                List<Position> moved = movePosition(position, grid);
                if(moved.size() > 1) {
                    splits++;
                }
                newPositions.addAll(moved);
            }
            return new State(newPositions, this.splits + splits);
        }
    }

    private static List<Position> movePosition(Position position, Grid grid) {
        Position below = position.below();
        if(!grid.contains(below)) {
            return List.of();
        }
        String cell = grid.cell(below);
        if("^".equals(cell)) {
            return below.sides().stream().filter(grid::contains).toList();
        }
        return List.of(below);
    }
}
