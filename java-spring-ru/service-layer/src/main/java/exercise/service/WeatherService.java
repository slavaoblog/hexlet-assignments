package exercise.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import exercise.HttpClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import exercise.CityNotFoundException;
import exercise.repository.CityRepository;
import exercise.model.City;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class WeatherService {

    @Autowired
    CityRepository cityRepository;

    // Клиент
    HttpClient client;

    // При создании класса сервиса клиент передаётся снаружи
    // В теории это позволит заменить клиент без изменения самого сервиса
    WeatherService(HttpClient client) {
        this.client = client;
    }

    // BEGIN
    public String showWeatherById(Long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException("city not found"));
        String cityName = city.getName();
        return client.get("http://weather/api/v2/cities/" + cityName);
    }

    public List<Map<String, String>> getAllSorted() throws JsonProcessingException {
        List<City> citiesList = cityRepository.findAllByOrderByNameAsc();
        return getWeatherForList(citiesList);
    }

    public List<Map<String, String>> getAllByPrefix(String prefix) throws JsonProcessingException {
        List<City> citiesList = cityRepository.findByNameStartingWith(prefix);
        return getWeatherForList(citiesList);
    }

    public List<Map<String, String>> getWeatherForList(List<City> list) throws JsonProcessingException {
        List<City> citiesList = list;
        List<Map<String, String>> sortedList = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        for (City city : citiesList) {
            String cityName = city.getName();
            String response = client.get("http://weather/api/v2/cities/" + cityName);
            HashMap<String, String> responseMap = mapper.readValue(response, new TypeReference<HashMap<String, String>>() {
            });
            Map<String, String> filteredMap = responseMap.entrySet().stream()
                    .filter(entry -> entry.getKey().equals("name") || entry.getKey().equals("temperature"))
                    .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
            sortedList.add(filteredMap);
        }
        return sortedList;
    }
    // END
}
