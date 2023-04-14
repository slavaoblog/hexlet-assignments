package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void testTake() {
        // BEGIN
        List<Integer> actual = List.of(1, 2, 3, 4);
        int count = 3;
        assertThat(App.take(actual, count))
                .isNotEmpty()
                .hasSize(count)
                .startsWith(1)
                .endsWith(3);
        // END
    }
}
