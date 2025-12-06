package mikehawes.adventofcode.calendar2025.day6;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day6Star2Test {

    @Test
    void should_read_problem_from_columns() {
        List<String> parts = List.of(
                "623",
                "431",
                "  4");
        assertThat(Problem.fromColumns(parts, Operation.Add))
                .isEqualTo(new Problem(List.of(4L, 431L, 623L), Operation.Add));
    }

    @Test
    void should_read_problem_from_columns_2() {
        List<String> parts = List.of(
                "1  ",
                "24 ",
                "356");
        assertThat(Problem.fromColumns(parts, Operation.Add))
                .isEqualTo(new Problem(List.of(356L, 24L, 1L), Operation.Add));
    }

    @Test
    void should_add_example_problem_results() {
        String input = """
                123 328  51 64\s
                 45 64  387 23\s
                  6 98  215 314
                *   +   *   + \s
                """;

        assertThat(Day6Star2.sumResults(input))
                .isEqualTo(3263827);
    }
}
