package mikehawes.adventofcode.calendar2025.day8;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ConnectionTest {

    @Test
    void should_find_shortest_connections() {
        List<Point> points = Point.listFrom("""
                0,0,1
                0,0,10
                0,0,100
                """);
        assertThat(Connection.findShortest(points)).containsExactly(
                new Connection(new Point(0, 0, 1), new Point(0, 0, 10), 9),
                new Connection(new Point(0, 0, 10), new Point(0, 0, 100), 90),
                new Connection(new Point(0, 0, 1), new Point(0, 0, 100), 99));
    }
}
