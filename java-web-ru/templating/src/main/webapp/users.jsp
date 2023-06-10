<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<!DOCTYPE html>
                <html lang=\"ru\">
                    <head>
                        <meta charset=\"UTF-8\">
                        <title>Users</title>
                                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
                                    rel="stylesheet"
                                    integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
                                    crossorigin="anonymous">
                    </head>
    <body>
    <table width="70%" border="2" cellpadding="2">
    <tr bgcolor="#ffcc00" align="left" valign="top">
    <th>id</th>
    <th>Full name</th>
    </tr>
        <c:forEach var="user" items="${users}">
            <tr>
            <td>${user.get("id")}</td>
            <td><a href='/users/show?id=${user.get("id")}'>${user.get("firstName")} ${user.get("lastName")}</a></td>
            </tr>
        </c:forEach>
        </table>
    </body>
</html>

<!-- END -->
