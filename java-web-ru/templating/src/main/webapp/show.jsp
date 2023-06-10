<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<!DOCTYPE html>
                <html lang=\"ru\">
                    <head>
                        <meta charset=\"UTF-8\">
                        <title>User</title>
                                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
                                    rel="stylesheet"
                                    integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
                                    crossorigin="anonymous">
                    </head>
    <body>
    <p>
        <c:forEach var="key" items="${user.keySet()}">
            ${key}: ${user.get(key)}<br>
        </c:forEach>
        <br>
        <a href='/users/delete?id=${user.get("id")}'>Delete this user</a>
        <br><br>
                <a href='/users'>Back to the list</a>
        </p>
    </body>
</html>

<!-- END -->