package mikehawes.adventofcode.calendar2025.day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;

public class Day8Star2 {

    static void main() throws IOException {
        String input = Files.readString(Path.of("input/day8.txt"));
        List<Point> boxes = Point.listFrom(input);
        List<Connection> connections = Connection.streamAll(boxes)
                .sorted(Comparator.comparing(Connection::distance))
                .toList();
        IO.println("Found "+connections.size()+" connections, distance min "+connections.getFirst().distance()+", max "+connections.getLast().distance());
    }

}
