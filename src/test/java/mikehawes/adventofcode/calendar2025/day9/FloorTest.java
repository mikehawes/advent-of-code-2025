package mikehawes.adventofcode.calendar2025.day9;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FloorTest {

    @Test
    void should_create_from_tiles() {
        String input = """
                7,1
                11,1
                11,7
                9,7
                9,5
                2,5
                2,3
                7,3
                """;
        List<Tile> tiles = Tile.listFrom(input);
        Floor floor = Floor.from(tiles);
        assertThat(floor.print())
                .isEqualTo("""
                        .#.#
                        ##..
                        #.#.
                        ..##""");
    }
}
