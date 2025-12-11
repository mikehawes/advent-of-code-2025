package mikehawes.adventofcode.calendar2025.day10;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.BiFunction;

public record StateMachineNode<T>(T state, List<StateMachineNode<T>> buttonPushes) {

    public static StateMachineNode<IndicatorLights> mapLightsFrom(Machine machine) {
        return mapFrom(machine, IndicatorLights.allOff(machine), Button::press);
    }

    public static <T> StateMachineNode<T> mapFrom(Machine machine, T initialState, BiFunction<Button, T, T> pressButton) {
        IO.println("Machine: " + machine);
        IO.println("Mapping state machine...");
        StateMachineNode<T> start = initState(machine, initialState);
        Map<T, StateMachineNode<T>> stateToNode = new HashMap<>();
        stateToNode.put(start.state(), start);
        start.setButtonPushes(machine, stateToNode, pressButton);
        return start;
    }

    public int fewestButtonPresses(T target) {
        IO.println("Finding fewest button presses...");
        Map<T, Integer> lightsToPresses = new HashMap<>();
        lightsToPresses.put(state, 0);
        PriorityQueue<StateAndPresses<T>> queue = new PriorityQueue<>(Comparator.comparing(StateAndPresses::presses));
        queue.add(new StateAndPresses<>(this, 0));
        while (!queue.isEmpty()) {
            StateAndPresses<T> state = queue.poll();
            int nextPresses = state.presses() + 1;
            for (StateMachineNode<T> pushed : state.buttonPushes()) {
                if (pushed.state().equals(target)) {
                    IO.println("Found " + nextPresses);
                    return nextPresses;
                }
                int lastPresses = lightsToPresses.getOrDefault(pushed.state(), Integer.MAX_VALUE);
                if (nextPresses < lastPresses) {
                    lightsToPresses.put(pushed.state(), nextPresses);
                    queue.add(new StateAndPresses<>(pushed, nextPresses));
                }
            }
        }
        throw new IllegalArgumentException("No route to target found");
    }

    private record StateAndPresses<T>(StateMachineNode<T> state, int presses) {

        List<StateMachineNode<T>> buttonPushes() {
            return state.buttonPushes();
        }
    }

    private static <T> StateMachineNode<T> initState(Machine machine, T state) {
        return new StateMachineNode<>(state, new ArrayList<>(machine.buttons().size()));
    }

    private void setButtonPushes(Machine machine, Map<T, StateMachineNode<T>> stateToNode, BiFunction<Button, T, T> pressButton) {
        List<StateMachineNode<T>> newNodes = new ArrayList<>(machine.buttons().size());
        for (Button button : machine.buttons()) {
            T after = pressButton.apply(button, state);
            StateMachineNode<T> afterNode = stateToNode.computeIfAbsent(after, state -> {
                StateMachineNode<T> newNode = initState(machine, state);
                newNodes.add(newNode);
                return newNode;
            });
            buttonPushes.add(afterNode);
        }
        for (StateMachineNode<T> newNode : newNodes) {
            newNode.setButtonPushes(machine, stateToNode, pressButton);
        }
    }


    @Override
    public String toString() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintWriter out = new PrintWriter(os, false, StandardCharsets.UTF_8);
        printIfVisit(out, new HashSet<>());
        out.flush();
        return os.toString();
    }

    private void printIfVisit(PrintWriter out, Set<T> visitedStates) {
        if (visitedStates.contains(state)) {
            return;
        }
        out.println(state + ":");
        for (StateMachineNode<T> buttonPush : buttonPushes) {
            out.println("  " + buttonPush.state());
        }
        out.println();
        visitedStates.add(state);
        for (StateMachineNode<T> buttonPush : buttonPushes) {
            buttonPush.printIfVisit(out, visitedStates);
        }
    }
}
