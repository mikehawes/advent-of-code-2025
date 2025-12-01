package mikehawes.adventofcode.calendar2025.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day1Star2 {

    static void main() throws IOException {
        String document = Files.readString(Path.of("input/day1.txt"));
        IO.println("Password: " + findPassword(document));
    }

    public static String findPassword(String document) {
        State state = new State(50, 0);
        for(long move : Day1Star1.parseMoves(document)) {
            Update update = state.update(move);
            state = update.after();
            IO.println(update);
        }
        return ""+state.zeroPasses();
    }

    public record State(long position, long zeroPasses) {

        public Update update(long move) {
            long zeroPasses = zeroPasses(move);
            State after = new State(newPosition(move), this.zeroPasses + zeroPasses);
            return new Update(this, after, move, zeroPasses);
        }

        private long newPosition(long move) {
            long acc = position + move;
            long newPosition = acc % 100;
            if(newPosition < 0) {
                newPosition += 100;
            }
            return newPosition;
        }

        private long zeroPasses(long move) {
            if(move > 0) {
                return (move + position) / 100;
            }
            long adjPosition = (100-position) % 100;
            long adjMove = -move;
            return (adjMove + adjPosition) / 100;
        }
    }

    public record Update(State before, State after, long move, long zeroPasses) {
    }
}
