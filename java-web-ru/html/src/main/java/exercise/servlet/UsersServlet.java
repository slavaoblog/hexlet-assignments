package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

import java.nio.file.Paths;
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
                    <div class="container">
                                            <a href="/">Главная</a>
                    <table>
                """);

        for (Map<String, String> user : listUsers) {
            String id = user.get("id");
            String fullName = user.get("firstName") + " " + user.get("lastName");
            body
                    .append("<tr>")
                    .append("<td>" + id + "</td>")
                    .append("<td><a href=\"/users/" + id + "\">" + fullName + "</a></td>")
                    .append("</tr>");
        }
        body.append("""
                      </table>
                      </div>
                    </body>
                </html>
                """);
        // Устанавливаем тип содержимого ответа и кодировку
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        out.println(body);
        // END
    }

    private void showUser(HttpServletRequest request,
                          HttpServletResponse response,
                          String id)
            throws IOException {

        // BEGIN
        response.setContentType("text/html;charset=UTF-8");
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
                    <div class="container">
                                            <a href="/users">Список пользователей</a>
                    <table>
                """);

        for (Map<String, String> user : listUsers) {
            if (user.get("id").equals(id)) {
                body
                        .append("<tr><td>\"id\": \"" + user.get("id") + "\",</td></tr>")
                        .append("<tr><td>\"firstName\": \"" + user.get("firstName") + "\",</td></tr>")
                        .append("<tr><td>\"lastName\": \"" + user.get("lastName") + "\",</td></tr>")
                        .append("<tr><td>\"email\": \"" + user.get("email") + "\",</td></tr>");
            }
        }
            body.append("""
                          </table>
                          </div>
                        </body>
                    </html>
                    """);
            out.println(body);
            // END
        }
}
