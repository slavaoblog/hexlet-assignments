<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<!DOCTYPE html>
                <html lang=\"ru\">
                    <head>
                        <meta charset=\"UTF-8\">
                        <title>Delete</title>
                                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
                                    rel="stylesheet"
                                    integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
                                    crossorigin="anonymous">
                    </head>
    <body>
            Are you sure you want to delete user "${user.get("firstName")} ${user.get("lastName")}"?<br>
            You will not be able to cancel this operation<br>
            <form action="/users/show?id=${user.get("id")}" method="get">
            <a class="btn btn-primary" href="/users/show?id=${user.get("id")}" role="button">Cancel</a>
                        </form>
            <form action="/users/delete?id=${user.get("id")}" method="post">
                <button type="submit" class="btn btn-danger">Delete</button>
            </form>
    </body>
</html>

<!-- END -->
