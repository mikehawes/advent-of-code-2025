package mikehawes.adventofcode.calendar2025.day6;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Day6Star1Test {

    @Test
    void should_add_example_problem_results() {
        String input = """
                123 328  51 64\s
                 45 64  387 23\s
                  6 98  215 314
                *   +   *   + \s
                """;

        assertThat(Day6Star1.sumResults(input))
                .isEqualTo(4277556);
    }
}
