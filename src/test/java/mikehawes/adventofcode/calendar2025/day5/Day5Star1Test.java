package mikehawes.adventofcode.calendar2025.day5;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Day5Star1Test {

    @Test
    void should_count_fresh_ingredients() {
        String input = """
                3-5
                10-14
                16-20
                12-18
                
                1
                5
                8
                11
                17
                32
                """;
        assertThat(Day5Star1.countFreshIngredients(input))
                .isEqualTo(3);
    }
}
