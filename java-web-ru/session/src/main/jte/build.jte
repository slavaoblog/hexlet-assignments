@import exercise.dto.LoginPage
@import exercise.util.NamedRoutes
@param LoginPage page

@template.layout.page(
    content = @`
    @if(page != null)
        <div>
            ${page.getError()}
        </div>
        @endif
    <div>
    <form method="post" action="${NamedRoutes.loginPath()}">
        <label>
            Name
            <input type="text" name="name">
        </label>
        <label>
            Password
            <input type="password" name="password">
        </label>
        <button type="submit" name="authorize">Authorize</button>
    </form>
</div>
`
)