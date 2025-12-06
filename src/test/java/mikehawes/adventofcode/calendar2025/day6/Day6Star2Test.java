package mikehawes.adventofcode.calendar2025.day6;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day6Star2Test {

    @Test
    void should_read_problem_from_string_list() {
        List<String> parts = List.of(
                "64",
                "23",
                "314");
        assertThat(Problem.from(parts, Operation.Add))
                .isEqualTo(new Problem(List.of(4L, 431L, 623L), Operation.Add));
    }
}
