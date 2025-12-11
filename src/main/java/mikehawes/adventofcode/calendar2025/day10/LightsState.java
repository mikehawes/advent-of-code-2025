package mikehawes.adventofcode.calendar2025.day10;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public record LightsState(IndicatorLights lights, List<LightsState> buttonPushes) {

    @Override
    public String toString() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintWriter out = new PrintWriter(os, false, StandardCharsets.UTF_8);
        printIfVisit(out, new HashSet<>());
        out.flush();
        return os.toString();
    }

    private void printIfVisit(PrintWriter out, Set<IndicatorLights> visitedStates) {
        if (visitedStates.contains(lights)) {
            return;
        }
        out.println(lights + ":");
        for (LightsState buttonPush : buttonPushes) {
            out.println("  " + buttonPush.lights());
        }
        out.println();
        visitedStates.add(lights);
        for (LightsState buttonPush : buttonPushes) {
            buttonPush.printIfVisit(out, visitedStates);
        }
    }
}
