package mikehawes.adventofcode.calendar2025.day7;

public record Splitter(Position position, Splitter left, Splitter right) {

    public static Splitter read(Grid grid) {
        IO.println("Reading splitters...");
        return read(grid, grid.findStart());
    }

    private static Splitter read(Grid grid, Position position) {
        while (!"^".equals(grid.cell(position))) {
            position = position.below();
            if (!grid.contains(position)) {
                return null;
            }
        }
        return new Splitter(position, read(grid, position.left()), read(grid, position.right()));
    }

    public long countPaths() {
        IO.println("Counting paths...");
        return paths(left) + paths(right);
    }

    private static long paths(Splitter splitter) {
        if(splitter == null) {
            return 1;
        } else {
            return splitter.countPaths();
        }
    }
}
