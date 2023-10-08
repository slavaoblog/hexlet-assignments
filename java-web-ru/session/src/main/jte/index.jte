@import exercise.dto.MainPage
@import exercise.util.NamedRoutes
@param MainPage page

@template.layout.page(
    content = @`
    <main>
        <h1>Привет, Хекслет!!!</h1>
        @if(page.getName() != null)
            Добро пожаловать, ${page.getName().toString()}.
            <form action="${NamedRoutes.logoutPath()}" method="post">
                <input type="submit" class="btn brn-primary" value="Выйти">
            </form>

        @endif
        @if(page.getName() == null)
            <a href="${NamedRoutes.buildSessionPath()}">Войти</a>
        @endif
    </main>
    `
    )
