package mikehawes.adventofcode.calendar2025.day7;

import mikehawes.adventofcode.calendar2025.grid.Grid;
import mikehawes.adventofcode.calendar2025.grid.Position;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SplitterTest {

    @Test
    void should_read_single_splitter() {
        String input = """
                .S.
                .^.
                """;
        assertThat(read(input)).isEqualTo(
                end(new Position(1, 1)));
    }

    @Test
    void should_read_left_splitter() {
        String input = """
                ..S.
                ..^.
                .^..
                """;
        assertThat(read(input)).isEqualTo(
                leftOnly(new Position(2, 1),
                        end(new Position(1, 2))));
    }

    @Test
    void should_read_split_splitter() {
        String input = """
                ..S..
                ..^..
                .^.^.
                """;
        assertThat(read(input)).isEqualTo(
                leftAndRight(
                        new Position(2, 1),
                        end(new Position(1, 2)),
                        end(new Position(3, 2))));
    }

    @Test
    void should_read_joined_splitter() {
        String input = """
                ..S..
                ..^..
                .^.^.
                ..^..
                """;
        Splitter found = read(input);
        assertThat(found.left().right())
                .isSameAs(found.right().left());
    }

    private static Splitter read(String input) {
        return Splitter.read(Grid.from(input));
    }

    private static Splitter leftOnly(Position position, Splitter left) {
        return new Splitter(position, left, null, left.paths() + 1);
    }

    private static Splitter leftAndRight(Position position, Splitter left, Splitter right) {
        return new Splitter(position, left, right, left.paths() + right.paths());
    }

    private static Splitter end(Position position) {
        return new Splitter(position, null, null, 2);
    }
}
