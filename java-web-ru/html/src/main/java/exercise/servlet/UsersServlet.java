package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.ArrayUtils;

public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        String result = new String(Files.readAllBytes(Paths.get("src/main/resources/users.json")));
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, String>> data = mapper.readValue(result, new TypeReference<>() {
        });
        return data;
        // END
    }

    private void showUsers(HttpServletRequest request,
                           HttpServletResponse response)
            throws IOException {

        // BEGIN
        List<Map<String, String>> listUsers = getUsers();
        StringBuilder body = new StringBuilder();
        body.append("""
                <!DOCTYPE html>
                <html lang=\"ru\">
                    <head>
                        <meta charset=\"UTF-8\">
                        <title>Example application | Users</title>
                        <link rel=\"stylesheet\" href=\"mysite.css\">
                    </head>
                    <body>
                    <table>
                """);

        for (Map<String, String> hashMap : listUsers) {
            body
                    .append("<tr><td>" + hashMap.get("id") + "</td>")
                    .append("<td><a href=\"/users/" + hashMap.get("id") + "\">")
                    .append(hashMap.get("firstName") + " " + hashMap.get("lastName") + "</a></td></tr>");
        }
        body.append("""
                      </table>
                    </body>
                </html>
                """);
        // Устанавливаем тип содержимого ответа и кодировку
        response.setContentType("text/html;charset=UTF-8");
        // При помощи метода setStatus() можно установить код ответа
        // response.setStatus(HttpServletResponseSC_OK)

        PrintWriter out = response.getWriter();
        out.println(body.toString());

        // Гипотетический пример, показывающий структуру
//        users = [
//        {
//            "id": "4",
//                "firstName": "John",
//                "lastName": "Doe",
//                "email": "johndoe@gmail.com",
//        },
        // другие пользователи
//  ]
        // END
    }

    private void showUser(HttpServletRequest request,
                          HttpServletResponse response,
                          String id)
            throws IOException {

        // BEGIN
        PrintWriter out = response.getWriter();
        if (id.isEmpty()) {
            out.println("Not found");
            response.sendError(404);
        }
        if (!new String(Files.readAllBytes(Paths.get("src/main/resources/users.json"))).contains("id\" : \"" + id)) {
            out.println("Not found");
            response.sendError(404);
        }
        List<Map<String, String>> listUsers = getUsers();
        StringBuilder body = new StringBuilder();
        body.append("""
                <!DOCTYPE html>
                <html lang=\"ru\">
                    <head>
                        <meta charset=\"UTF-8\">
                        <title>Example application | Users</title>
                        <link rel=\"stylesheet\" href=\"mysite.css\">
                    </head>
                    <body>
                    <table>
                """);

        for (Map<String, String> hashMap : listUsers) {
            if (hashMap.get("id").equals(id)) {
                body
                        .append("<tr><td>\"id\": \"" + hashMap.get("id") + "\"," + "</td></tr>")
                        .append("<tr><td>\"firstName\": \"" + hashMap.get("firstName") + "\"," + "</td></tr>")
                        .append("<tr><td>\"lastName\": \"" + hashMap.get("lastName") + "\"," + "</td></tr>")
                        .append("<tr><td>\"email\": \"" + hashMap.get("email") + "\"," + "</td></tr>");
            }
        }
            body.append("""
                          </table>
                        </body>
                    </html>
                    """);
            // Устанавливаем тип содержимого ответа и кодировку
            response.setContentType("text/html;charset=UTF-8");
            // При помощи метода setStatus() можно установить код ответа
            // response.setStatus(HttpServletResponseSC_OK)
            out.println(body.toString());
            // END
        }
}
