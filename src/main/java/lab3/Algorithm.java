package lab3;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Algorithm {

    public static AtomicInteger result = new AtomicInteger();
    public static AtomicInteger pairIdx = new AtomicInteger();

    public static int algorithm(Set<Integer>[] pairs) {
        var tribes = getTribes(pairs);
        var dividedByGender = divideTribesByGender(tribes);
        return createPairs(dividedByGender);
    }

    private static Set<Set<Integer>> getTribes(Set<Integer>[] pairs) {
        return Arrays.stream(pairs).map(currentPair -> {
            if (pairIdx.get() != pairs.length - 1) {
                var first = (Integer) pairs[pairIdx.get() + 1].toArray()[0];
                var second = (Integer) pairs[pairIdx.get() + 1].toArray()[1];
                if (currentPair.contains(first) || currentPair.contains(second)) {
                    Set<Integer> interSection = Stream.of(currentPair, pairs[pairIdx.get() + 1]).flatMap(Collection::stream)
                            .collect(Collectors.toSet());
                    pairs[pairIdx.get() + 1] = Set.of(0);
                    pairIdx.getAndIncrement();
                    return interSection;
                }
            }
            pairIdx.getAndIncrement();
            return currentPair;
        }).filter(it -> !it.contains(0))
                .collect(Collectors.toSet());
    }

    private static int createPairs(List<Map<String, List<Integer>>> dividedByGender) {
        dividedByGender.forEach(map -> {
            if (pairIdx.get() != dividedByGender.size() - 1) {

                int nextTribeManCount, nextTribeWomanCount, tribeWomanCount, tribeManCount;

                if (map.get("woman") != null && dividedByGender.get(pairIdx.get() + 1).get("man") != null) {
                    tribeWomanCount = map.get("woman").size();
                    nextTribeManCount = dividedByGender.get(pairIdx.get() + 1).get("man").size();
                    result.addAndGet(tribeWomanCount * nextTribeManCount);
                }

                if (map.get("man") != null && dividedByGender.get(pairIdx.get() + 1).get("woman") != null) {
                    tribeManCount = map.get("man").size();
                    nextTribeWomanCount = dividedByGender.get(pairIdx.get() + 1).size();
                    result.addAndGet(tribeManCount * nextTribeWomanCount);
                }
            }
        });
        return result.get();
    }

    private static List<Map<String, List<Integer>>> divideTribesByGender(Set<Set<Integer>> tribes) {
        pairIdx.set(0);
        return tribes.stream()
                .map(tribe -> tribe.stream().collect(Collectors.groupingBy(person -> {
                    if (person % 2 == 0) {
                        return "woman";
                    } else {
                        return "man";
                    }
                })))
                .collect(Collectors.toList());
    }
}

