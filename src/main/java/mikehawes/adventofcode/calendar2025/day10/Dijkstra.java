package mikehawes.adventofcode.calendar2025.day10;

import java.util.*;
import java.util.function.Function;

public class Dijkstra {

    public static <N, T> int fewestSteps(N start, T target, Function<N, T> getState, Function<N, List<N>> getLinks) {

        Map<T, Integer> stateToPresses = new HashMap<>();
        stateToPresses.put(getState.apply(start), 0);
        PriorityQueue<State<N, T>> queue = new PriorityQueue<>(Comparator.comparing(State::distance));
        queue.add(new State<>(null, 0, start, getState.apply(start), 0));
        while (!queue.isEmpty()) {
            State<N, T> state = queue.poll();
            int newDistance = state.distance() + 1;
            List<N> links = getLinks.apply(state.node());
            for (int i = 0; i < links.size(); i++) {
                N pushed = links.get(i);
                State<N, T> next = new State<>(state, i, pushed, getState.apply(pushed), newDistance);
                if (next.state().equals(target)) {
                    IO.println("Found distance " + newDistance + ": " + next);
                    return newDistance;
                }
                int lastPresses = stateToPresses.getOrDefault(next.state(), Integer.MAX_VALUE);
                if (newDistance < lastPresses) {
                    stateToPresses.put(next.state(), newDistance);
                    queue.add(next);
                }
            }
        }
        throw new IllegalArgumentException("No route to target found");
    }

    private record State<T, S>(State<T, S> prev, int linkIndex, T node, S state, int distance) {

        @Override
        public String toString() {
            StringBuilder out = new StringBuilder();
            if (prev != null) {
                out.append(prev).append(" chose ").append(linkIndex).append(", ");
            }
            out.append(state);
            return out.toString();
        }
    }
}
