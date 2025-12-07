package mikehawes.adventofcode.calendar2025.grid;

import java.util.List;

public record Position(int x, int y) {

    public List<Position> sides() {
        return List.of(left(), right());
    }

    public List<Position> adjacentWithDiagonals() {
        return List.of(
                aboveLeft(), above(), aboveRight(),
                right(), belowRight(), below(),
                belowLeft(), left());
    }

    public Position left() {
        return relative(-1, 0);
    }

    public Position right() {
        return relative(1, 0);
    }

    public Position below() {
        return relative(0, 1);
    }

    public Position above() {
        return relative(0, -1);
    }

    public Position aboveLeft() {
        return relative(-1, -1);
    }

    public Position aboveRight() {
        return relative(1, -1);
    }

    public Position belowLeft() {
        return relative(-1, 1);
    }

    public Position belowRight() {
        return relative(1, 1);
    }

    public Position relative(int x, int y) {
        return new Position(this.x + x, this.y + y);
    }
}
