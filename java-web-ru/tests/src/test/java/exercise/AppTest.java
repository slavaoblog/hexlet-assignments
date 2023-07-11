package exercise;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;
import io.ebean.DB;

import exercise.domain.User;
import exercise.domain.query.QUser;
import io.ebean.Database;

class AppTest {

    private static Javalin app;
    private static String baseUrl;

    // BEGIN
    @BeforeAll
    public static void beforeAll() {
        app = App.getApp();
        app.start(0);
        int port = app.port();
        baseUrl = "http://localhost:" + port;
    }
    // END

    // Между тестами база данных очищается
    // Благодаря этому тесты не влияют друг на друга
    @BeforeEach
    void beforeEach() {
        Database db = DB.getDefault();
        db.truncate("users");
        User existingUser = new User("Wendell", "Legros", "a@a.com", "123456");
        existingUser.save();
    }

    @Test
    void testUsers() {

        // Выполняем GET запрос на адрес http://localhost:port/users
        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users")
            .asString();
        // Получаем тело ответа
        String content = response.getBody();

        // Проверяем код ответа
        assertThat(response.getStatus()).isEqualTo(200);
        // Проверяем, что страница содержит определенный текст
        assertThat(response.getBody()).contains("Wendell Legros");
    }

    @Test
    void testNewUser() {

        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users/new")
            .asString();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    // BEGIN
    @Test
    void createUser_valid() {
        String actualName = "Viacheslav";
        String actualSurname = "Oblog";
        String actualEmail = "slavaoblog@ya.ru";
        String actualPassword = "12341234";

        HttpResponse<String> response = Unirest
                .post(baseUrl + "/users")
                .field("firstName", actualName)
                .field("lastName", actualSurname)
                .field("email", actualEmail)
                .field("password", actualPassword)
                .asEmpty();

        assertThat(response.getStatus()).isEqualTo(302);
        assertThat(response.getHeaders().getFirst("Location")).isEqualTo("/users");

        User actualUser = new QUser()
                .firstName.equalTo(actualName)
                .findOne();

        assertThat(actualUser).isNotNull();
        assertThat(actualUser.getFirstName()).isEqualTo(actualName);
        assertThat(actualUser.getLastName()).isEqualTo(actualSurname);
        assertThat(actualUser.getEmail()).isEqualTo(actualEmail);
        assertThat(actualUser.getPassword()).isEqualTo(actualPassword);
    }

    @Test
    void createUser_invalid() {
        String actualName = "Viacheslav";
        String actualSurname = "";
        String actualEmail = "slavaoblog@";
        String actualPassword = "123";

        HttpResponse<String> response = Unirest
                .post(baseUrl + "/users")
                .field("firstName", actualName)
                .field("lastName", actualSurname)
                .field("email", actualEmail)
                .field("password", actualPassword)
                .asString();

        assertThat(response.getStatus()).isEqualTo(422);

        User actualUser = new QUser()
                .firstName.equalTo(actualName)
                .findOne();

        String content = response.getBody();

        assertThat(content.contains("Фамилия не должна быть пустой"));
        assertThat(content.contains(actualName));

        assertThat(actualUser).isNull();
    }

    @AfterAll
    public static void afterAll() {
        app.stop();
    }
    // END
}
