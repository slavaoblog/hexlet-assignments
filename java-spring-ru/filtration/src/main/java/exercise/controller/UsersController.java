package exercise.controller;
import exercise.model.User;
import exercise.repository.UserRepository;
import exercise.service.SearchCriteria;
import exercise.service.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.ArrayList;
import java.util.List;


// Зависимости для самостоятельной работы
// import org.springframework.data.querydsl.binding.QuerydslPredicate;
// import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserRepository userRepository;

    // BEGIN
    @GetMapping(path = "")
    public List<User> getUsers(
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName
    ) {
        UserSpecification spec1 = new UserSpecification(new SearchCriteria<>("firstName", firstName));
        UserSpecification spec2 = new UserSpecification(new SearchCriteria<>("lastName", lastName));
        if (firstName != null && lastName != null) {
            return userRepository.findAll(Specification.where(spec1).and(spec2));
        }
        else if (firstName != null || lastName != null) {
            return userRepository.findAll(Specification.where(spec1).or(spec2));
        }
        else {
            return userRepository.findAll();
        }
    }
    // END
}

