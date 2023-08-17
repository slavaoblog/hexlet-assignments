package exercise.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import exercise.model.City;
import exercise.repository.CityRepository;
import exercise.service.WeatherService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
public class CityController {


    private final WeatherService weatherService;

    // BEGIN
    @GetMapping(path = "/cities/{id}", produces="application/json")
    public String getById(@PathVariable("id") Long id) {
        return weatherService.showWeatherById(id);
    }

    @GetMapping(path = "/search", produces="application/json")
    public List<Map<String, String>> searchByPrefix(@RequestParam(name = "name", required = false) String prefix) throws JsonProcessingException {
        if (prefix == null) {
            return weatherService.getAllSorted();
        }
        return weatherService.getAllByPrefix(prefix.toUpperCase());
    }
    // END
}

