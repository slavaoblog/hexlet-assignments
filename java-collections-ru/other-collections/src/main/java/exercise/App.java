package exercise;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// BEGIN
class App {
    public static void main(String[] args) {
        Map<String, Object> data1 = new HashMap<>(
                Map.of("one", "eon", "two", "two", "four", true)
        );

        Map<String, Object> data2 = new HashMap<>(
                Map.of("two", "own", "zero", 4, "four", true)
        );
        System.out.println(genDiff(data1, data2));
    }

    public static Map<String, Object> genDiff(Map<String, Object> data1, Map<String, Object> data2) {
        Map<String, Object> data3 = Stream.of(data1, data2)
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1, v2) -> v1 == v2 ? "unchanged" : "changed"));
        Map<String, Object> data4 = data3.entrySet().stream()
                .collect(Collectors.toMap(
                        k -> k.getKey(),
                        v -> {
                            if (!data1.containsKey(v.getKey())) {
                                return "added";
                            } else if (!data2.containsKey(v.getKey())) {
                                return "deleted";
                            } else {
                                return v.getValue();
                            }
                        }));
        Map<String, Object> data5 = data4.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v2, LinkedHashMap::new));
        return data5;
    }
}
//END
