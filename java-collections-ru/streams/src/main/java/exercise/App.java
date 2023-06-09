package exercise;

import java.util.List;
import java.util.stream.Collectors;

// BEGIN
class App {
    public static void main(String[] args) {
        List<String> emails = List.of(
                "info@gmail.com",
                "info@yandex.ru",
                "mk@host.com",
                "support@hexlet.io",
                "info@hotmail.com",
                "support.yandex.ru@host.com"
                );
        System.out.println(getCountOfFreeEmails(emails));
    }
    public static long getCountOfFreeEmails(List<String> list) {
        List<String> listOfFreeDomains = List.of(
                "gmail.com",
                "yandex.ru",
                "hotmail.com"
        );
        long count = list.stream()
                .filter(domain -> listOfFreeDomains.contains(domain.split("@")[1]))
                .count();
        return count;
    }
}
// END
