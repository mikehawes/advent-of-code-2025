package mikehawes.adventofcode.calendar2025.day9;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TileTest {

    @Test
    void should_find_top_left_to_bottom_right_area() {
        Tile from = Tile.from("1,1");
        Tile to = Tile.from("2,2");
        assertThat(from.rectangleAreaWith(to))
                .isEqualTo(4);
    }

    @Test
    void should_find_bottom_right_to_top_left_area() {
        Tile from = Tile.from("2,2");
        Tile to = Tile.from("1,1");
        assertThat(from.rectangleAreaWith(to))
                .isEqualTo(4);
    }
}
