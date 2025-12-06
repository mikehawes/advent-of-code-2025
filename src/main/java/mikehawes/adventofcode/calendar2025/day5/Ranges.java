package mikehawes.adventofcode.calendar2025.day5;

import java.util.*;

public record Ranges(List<Range> list) {

    public static Ranges from(String string) {
        return new Ranges(string.lines().map(Range::from).toList());
    }

    public boolean isFresh(long id) {
        return list.stream().anyMatch(r -> r.contains(id));
    }

    public Ranges removeOverlaps() {
        List<Range> newRanges = new ArrayList<>(list.size());
        Set<Range> unprocessed =  new HashSet<>(list);
        while(!unprocessed.isEmpty()) {
            Range range = unprocessed.iterator().next();
            unprocessed.remove(range);
            Optional<Merge> merge = Merge.from(range, unprocessed);
            while(merge.isPresent()) {
                unprocessed.removeAll(merge.get().from());
                range = merge.get().merged();
                IO.println("Performed merge: " + merge);
                merge = Merge.from(range, unprocessed);
            }
            newRanges.add(range);
        }
        return new Ranges(newRanges);
    }

    private record Merge(Range merged, Set<Range> from) {

        public static Optional<Merge> from(Range range, Set<Range> remaining) {
            Set<Range> mergedFrom = new HashSet<>();
            for (Range other : remaining) {
                Optional<Range> merge = range.merge(other);
                if(merge.isPresent()) {
                    range = merge.get();
                    mergedFrom.add(other);
                }
            }
            if(mergedFrom.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(new Merge(range, mergedFrom));
        }
    }
}
