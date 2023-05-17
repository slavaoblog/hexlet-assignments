package exercise;

import java.util.*;
import java.util.Map.Entry;

// BEGIN
public class App {
    public static void swapKeyValue(KeyValueStorage database) {
        Map<String, String> storage = new HashMap<>(database.toMap());
        Set<String> keys = storage.keySet();
        for (String key : keys) {
            database.set(storage.get(key), key);
            database.unset(key);
        }
        System.out.println(database.toMap());
    }
}
// END
