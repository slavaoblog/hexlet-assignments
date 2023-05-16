package exercise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingDouble;

// BEGIN
class App {
    public static void main(String[] args) {
        CharSequence text = new ReversedSequence("abcdef");
        System.out.println(text);
        System.out.println(text.charAt(1));
        System.out.println(text.length());
        System.out.println(text.subSequence(1, 4));
    }
    public static List<String> buildApartmentsList(List<Home> list, int n) {
        return list.stream()
                .sorted(Home::compareTo)
                .limit(n)
                .map(x -> x.toString())
                .collect(Collectors.toList());
    }
//        List<String> result = new ArrayList<>();
//        if (list.isEmpty()) {
//            return result;
//        }
//        List<Home> sortedList = list.stream()
//                .sorted(comparingDouble(Home::getArea))
//                .collect(Collectors.toList());
//        for (var i = 0; i < n; i++) {
//            result.add(sortedList.get(i).toString());
//        }
//        return result;
//    }
}
// END
