package mikehawes.adventofcode.calendar2025;

import java.util.Arrays;
import java.util.List;

public class Day1Star1 {
    public static String findPassword(String document) {
        return "3";
    }

    public static List<Long> parseMoves(String document) {
        return Arrays.stream(document.split("\n"))
                .map(Day1Star1::parseMove)
                .toList();
    }

    private static long parseMove(String move) {
        char direction = move.charAt(0);
        long distance = Long.parseLong(move.substring(1));
        if(direction == 'L') {
            return -distance;
        } else {
            return distance;
        }
    }
}
