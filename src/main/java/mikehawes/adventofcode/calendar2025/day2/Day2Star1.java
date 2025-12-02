package mikehawes.adventofcode.calendar2025.day2;

public class Day2Star1 {

    public static long countInvalidIdsInRange(String rangeString) {
        return Range.from(rangeString).countInvalidIds();
    }

    public record Range(long start, long end, String startString, String endString) {

        public static Range from(String rangeString) {
            String[] parts = rangeString.split("-");
            return new Range(Long.parseLong(parts[0]), Long.parseLong(parts[1]), parts[0], parts[1]);
        }

        public long countInvalidIds() {
            int count = 0;
            for(long i = start; i <= end; i++) {
                String id = Long.toString(i);
                int middle = id.length() / 2;
                if(id.substring(0, middle).equals(id.substring(middle))) {
                    count++;
                }
            }
            return count;
        }
    }
}
