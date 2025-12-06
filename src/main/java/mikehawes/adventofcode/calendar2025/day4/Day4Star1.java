package mikehawes.adventofcode.calendar2025.day4;

import java.util.List;

public class Day4Star1 {
    public static long countReachableRolls(String input) {
        List<List<String>> grid = splitGrid(input);
        long reachableRolls = 0;
        for (int y=0; y<grid.size(); y++) {
            List<String> row = grid.get(y);
            for (int x = 0; x < row.size(); x++) {
                String cell = row.get(x);
                if ("@".equals(cell)) {
                    List<String> adjacent = findAdjacentCells(x, y, grid);
                    long numWithRolls = adjacent.stream().filter("@"::equals).count();
                    if (numWithRolls < 4) {
                        reachableRolls++;
                    }
                }
            }
        }
        return reachableRolls;
    }

    private static List<List<String>> splitGrid(String input) {
        return input.lines()
                .map(line -> List.of(line.split("")))
                .toList();
    }

    private static List<String> findAdjacentCells(int x, int y, List<List<String>> grid) {
        return List.of();
    }
}
