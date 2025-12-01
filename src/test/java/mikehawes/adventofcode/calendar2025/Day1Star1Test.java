package mikehawes.adventofcode.calendar2025;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Day1Star1Test {
    @Test
    void should_find_password_from_example_document() {
        String document = """
                L68
                L30
                R48
                L5
                R60
                L55
                L1
                L99
                R14
                L82
                """;

        String password = Day1Star1.findPassword(document);

        assertThat(password).isEqualTo("3");
    }
}
