package mikehawes.adventofcode.calendar2025.day7;

import java.util.List;

public record Position(int x, int y) {

    public Position below() {
        return relative(0, 1);
    }

    public List<Position> sides() {
        return List.of(relative(-1, 0), relative(1, 0));
    }

    public Position relative(int x, int y) {
        return new Position(this.x + x, this.y + y);
    }
}
