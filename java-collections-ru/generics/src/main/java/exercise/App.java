package exercise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Map.Entry;

// BEGIN
class App {
    public static void main(String[] args) {
        List<Map<String, String>> BOOKS = List.of(
                Map.of("title", "Cymbeline", "author", "Shakespeare", "year", "1611"),
                Map.of("title", "Book of Fooos", "author", "FooBar", "year", "1111"),
                Map.of("title", "The Tempest", "author", "Shakespeare", "year", "1611"),
                Map.of("title", "Book of Foos Barrrs", "author", "FooBar", "year", "2222"),
                Map.of("title", "Still foooing", "author", "FooBar", "year", "3333"),
                Map.of("title", "Happy Foo", "author", "FooBar", "year", "4444")
        );

        Map<String, String> where = Map.of("author", "Shakespeare", "year", "1611");
        findWhere(BOOKS, where);
    }

    public static List findWhere(List<Map<String, String>> listOfBooks, Map<String, String> map) {
        List<Map<String, String>> result = new ArrayList<>();
        for (Map book : listOfBooks) {
            result.add(book);
        }
        for (Map book : listOfBooks) {
            for (String key : map.keySet()) {
                if (!book.containsValue(map.get(key))) {
                    result.remove(book);
                }
            }
        }
        System.out.println(result);
        return result;
    }
}
//END
