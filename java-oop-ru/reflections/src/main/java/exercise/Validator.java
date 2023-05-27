package exercise;

import java.io.FilterOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

// BEGIN
class Validator {
    public static List<String> validate(Address cl) {
        List<String> result = new ArrayList<>();
        String str = null;
        for (Field field : cl.getClass().getDeclaredFields()) {
            field.setAccessible(true);
                try {
                    if (field.isAnnotationPresent(NotNull.class) && field.get(cl) == null) {
                        result.add(field.getName());
                }
            } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
        }
        return result;
    }
}
// END
