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
        assertThat(Connection.findShortest(3, points)).containsExactly(
                new Connection(new Point(0, 0, 1), new Point(0, 0, 10), 9),
                new Connection(new Point(0, 0, 10), new Point(0, 0, 100), 90),
                new Connection(new Point(0, 0, 1), new Point(0, 0, 100), 99));
    }

    @Test
    void should_find_more_connections() {
        List<Point> points = Point.listFrom("""
                0,0,1
                0,0,10
                0,0,50
                0,0,100
                """);
        assertThat(Connection.findShortest(10, points)).containsExactly(
                new Connection(new Point(0, 0, 1), new Point(0, 0, 10), 9),
                new Connection(new Point(0, 0, 10), new Point(0, 0, 50), 40),
                new Connection(new Point(0, 0, 1), new Point(0, 0, 50), 49),
                new Connection(new Point(0, 0, 50), new Point(0, 0, 100), 50),
                new Connection(new Point(0, 0, 10), new Point(0, 0, 100), 90),
                new Connection(new Point(0, 0, 1), new Point(0, 0, 100), 99));
    }

    @Test
    void should_limit_shortest() {
        List<Point> points = Point.listFrom("""
                0,0,1
                0,0,10
                0,0,50
                0,0,100
                """);
        assertThat(Connection.findShortest(2, points)).containsExactly(
                new Connection(new Point(0, 0, 1), new Point(0, 0, 10), 9),
                new Connection(new Point(0, 0, 10), new Point(0, 0, 50), 40));
    }
}
