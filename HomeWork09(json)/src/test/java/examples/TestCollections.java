package examples;

import java.util.*;

public class TestCollections {
    Set<Integer> set = new TreeSet<>(java.util.Arrays.asList(47,47,47,47,47,47,47));
    List<String> list = new ArrayList<>(java.util.Arrays.asList("one", "two", "tree", "four", "five"));
    transient Random random = new Random();
    Map<Long, String> map = new HashMap<>();

    public TestCollections() {
        map.put(random.nextLong(), "map" + random.nextInt(100));
        map.put(random.nextLong(), "map" + random.nextInt(100));
        map.put(random.nextLong(), "map" + random.nextInt(100));
        map.put(random.nextLong(), "map" + random.nextInt(100));
        map.put(random.nextLong(), "map" + random.nextInt(100));
        map.put(random.nextLong(), "map" + random.nextInt(100));
    }
}
