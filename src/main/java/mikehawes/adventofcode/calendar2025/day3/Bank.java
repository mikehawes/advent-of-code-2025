package mikehawes.adventofcode.calendar2025.day3;

import java.util.ArrayList;
import java.util.List;

public record Bank(List<Long> batteries) {

    public static Bank from(String string) {
        List<Long> batteries = new ArrayList<>(string.length());
        for (int i = 0; i < string.length(); i++) {
            batteries.add(Long.parseLong(string.substring(i, i + 1)));
        }
        return new Bank(batteries);
    }

    public long findMaxJoltageFromTwoBatteries() {
        return findMaxJoltageFromNBatteries(2);
    }

    public long findMaxJoltageFromTwelveBatteries() {
        return findMaxJoltageFromNBatteries(12);
    }

    public long findMaxJoltageFromNBatteries(int n) {
        int position = 0;
        long exponent = positionExponent(n);
        long maxJoltage = 0;
        for (int i = 0; i < n; i++) {
            int space = n - i - 1;
            BatteryInBank maxBattery = findMaxBatteryWithStartAndSpaceAfter(position, space);
            maxJoltage += maxBattery.joltage() * exponent;
            exponent /= 10;
            position = maxBattery.index() + 1;
        }
        return maxJoltage;
    }

    private static long positionExponent(int position) {
        long exponent = 1;
        for (int i = 1; i < position; i++) {
            exponent *= 10;
        }
        return exponent;
    }

    private BatteryInBank findMaxBatteryWithStartAndSpaceAfter(int start, int space) {
        int foundIndex = 0;
        long maxJoltage = 0;
        int searchEnd = batteries.size() - space;
        for (int i = start; i < searchEnd; i++) {
            long joltage = batteries.get(i);
            if (joltage > maxJoltage) {
                foundIndex = i;
                maxJoltage = joltage;
            }
        }
        return new BatteryInBank(foundIndex, maxJoltage);
    }

    private record BatteryInBank(int index, long joltage) {
    }
}
