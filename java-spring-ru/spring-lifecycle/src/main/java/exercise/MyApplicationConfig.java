package exercise;

import java.time.LocalDateTime;

import exercise.daytimes.Daytime;
import exercise.daytimes.Morning;
import exercise.daytimes.Day;
import exercise.daytimes.Evening;
import exercise.daytimes.Night;

// BEGIN
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyApplicationConfig {

    @Bean
    public Daytime getDayTime() {
        LocalDateTime lt = LocalDateTime.now();
        int hourOfDay = lt.getHour();
        if (hourOfDay >= 6 && hourOfDay < 12) {
            return new Morning();
        }
        else if (hourOfDay >= 12 && hourOfDay < 18) {
            return new Day();
        }
        else if (hourOfDay >= 18 && hourOfDay < 23) {
            return new Evening();
        }
        else {
            return new Night();
        }
    }
}
// END
