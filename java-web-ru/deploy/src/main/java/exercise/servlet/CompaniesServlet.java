package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.stream.Collectors;
import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {

        // BEGIN
        PrintWriter out = response.getWriter();
        List<String> list = getCompanies();
        if (request.getQueryString() == null || request.getParameter("search") == null) {
            out.println(list.stream().collect(Collectors.joining("\n")));
        } else {
            String search = request.getParameter("search");
            String filteredList = list.stream()
                    .filter(company -> company.contains(search))
                    .collect(Collectors.joining("\n"));
            if (filteredList.isEmpty()) {
                out.println("Companies not found");
            } else {
                out.println(filteredList);
            }
        }
        // END
    }
}
