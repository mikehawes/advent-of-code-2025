package mikehawes.adventofcode.calendar2025.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day3Star1 {

    static void main() throws IOException {
        String document = Files.readString(Path.of("input/day3.txt"));
        IO.println("Sum of max joltages: " + sumMaxJoltageInBanks(document));
    }

    public static long sumMaxJoltageInBanks(String input) {
        return input.lines()
                .map(Bank::from)
                .mapToLong(Bank::findMaxJoltageFromTwoBatteries)
                .sum();
    }

    public record Bank(List<Long> batteries) {

        public static Bank from(String string) {
            List<Long> batteries = new ArrayList<>(string.length());
            for (int i=0; i<string.length(); i++) {
                batteries.add(Long.parseLong(string.substring(i, i+1)));
            }
            return new Bank(batteries);
        }

        public long findMaxJoltageFromTwoBatteries() {
            BatteryInBank maxBattery = findMaxBatteryWithSpaceAfter();
            long maxSecond = 0;
            for (int i = maxBattery.index() + 1; i < batteries.size(); i++) {
                long joltage = batteries.get(i);
                if (joltage > maxSecond) {
                    maxSecond = joltage;
                }
            }
            return maxBattery.joltage() * 10 + maxSecond;
        }

        private BatteryInBank findMaxBatteryWithSpaceAfter() {
            int foundIndex = 0;
            long maxJoltage = 0;
            int searchEnd = batteries.size() - 1;
            for (int i = 0; i < searchEnd; i++) {
                long joltage = batteries.get(i);
                if (joltage > maxJoltage) {
                    foundIndex = i;
                    maxJoltage = joltage;
                }
            }
            return new BatteryInBank(foundIndex, maxJoltage);
        }
    }

    public record BatteryInBank(int index, long joltage) {
    }
}
