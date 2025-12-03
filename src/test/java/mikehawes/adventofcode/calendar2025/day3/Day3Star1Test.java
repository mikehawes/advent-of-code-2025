package mikehawes.adventofcode.calendar2025.day3;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Day3Star1Test {

    @Test
    void should_find_largest_joltage_at_start() {
        String bank = "987654321111111";
        assertThat(Day3Star1.sumMaxJoltageInBanks(bank))
                .isEqualTo(98);
    }

    @Test
    void should_find_largest_joltage_at_start_and_end() {
        String bank = "811111111111119";
        assertThat(Day3Star1.sumMaxJoltageInBanks(bank))
                .isEqualTo(89);
    }

    @Test
    void should_find_largest_joltage_at_end() {
        String bank = "234234234234278";
        assertThat(Day3Star1.sumMaxJoltageInBanks(bank))
                .isEqualTo(78);
    }

    @Test
    void should_find_largest_joltage_in_middle() {
        String bank = "818181911112111";
        assertThat(Day3Star1.sumMaxJoltageInBanks(bank))
                .isEqualTo(92);
    }

    @Test
    void should_sum_joltage_from_banks() {
        String banks = """
                987654321111111
                811111111111119
                234234234234278
                818181911112111
                """;
        assertThat(Day3Star1.sumMaxJoltageInBanks(banks))
                .isEqualTo(357);
    }
}
