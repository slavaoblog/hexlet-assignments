package exercise.controller;

import exercise.model.Course;
import exercise.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseRepository courseRepository;

    @GetMapping(path = "")
    public Iterable<Course> getCourses() {
        return courseRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Course getCourse(@PathVariable long id) {
        return courseRepository.findById(id);
    }

    // BEGIN
    @GetMapping(path = "/{id}/previous")
    public List<Course> getPreviousCourses(@PathVariable long id) {
        List<Long> ids = getPreviousCoursesIds(id);

        List<Course> prevCourses = new ArrayList<>();
        if (ids != null && !ids.isEmpty()) {
            for (long previousId : ids) {
                prevCourses.add(courseRepository.findById(previousId));
            }
        }
        return prevCourses;
    }

    private List<Long> getPreviousCoursesIds(long id) {
        Course course = courseRepository.findById(id);
        String path = course.getPath();
        if (path != null && !path.isEmpty()) {
            return Arrays.stream(path.split("\\."))
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
        }
        return null;
    }
    // END

}
