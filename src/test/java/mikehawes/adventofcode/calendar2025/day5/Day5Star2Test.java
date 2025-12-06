package mikehawes.adventofcode.calendar2025.day5;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Day5Star2Test {

    @Test
    void should_count_fresh_ingredients() {
        String input = """
                3-5
                10-14
                16-20
                12-18
                
                irrelevant
                """;
        assertThat(Day5Star2.countFreshIngredients(input))
                .isEqualTo(14);
    }
}
