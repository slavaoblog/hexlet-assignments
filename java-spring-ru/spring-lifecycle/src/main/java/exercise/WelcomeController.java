package exercise;

import exercise.daytimes.Daytime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@RestController
public class WelcomeController {
//    private final Meal meal;
//    private final Daytime daytime;
//    @Autowired
//    public WelcomeController(Meal meal, Daytime daytime) {
//        this.meal = meal;
//        this.daytime = daytime;
//    }

    @Autowired
    Meal meal;

    @Autowired
    Daytime daytime;

    @GetMapping("/daytime")
    public String greetings() {
        String dayTimeName = daytime.getName();
        String result = "It is " + dayTimeName + " now. Enjoy your " + meal.getMealForDaytime(dayTimeName);
        return result;
    }
}
// END
