<!-- BEGIN -->
@import exercise.dto.users.UserPage;
@param UserPage page

<html>
<head>
    <title>Пользователь</title>
</head>
<body>
<main>
    <h1>${page.getUser().getFirstName()} ${page.getUser().getLastName()}</h1>
    <h2>${page.getUser().getEmail()}</h2>
</main>
</body>
</html>
<!-- END -->
