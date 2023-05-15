package exercise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingDouble;

// BEGIN
class App {
    public static List<String> buildApartmentsList(List<Home> list, int n) {
        List<String> result = new ArrayList<>();
        if (list.isEmpty()) {
            return result;
        }
        List<Home> sortedList = list.stream()
                .sorted(comparingDouble(Home::getArea))
                .collect(Collectors.toList());
        for (var i = 0; i < n; i++) {
            result.add(sortedList.get(i).toString());
        }
        return result;
    }
}
// END
