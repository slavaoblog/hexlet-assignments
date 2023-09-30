<!-- BEGIN -->
@import exercise.dto.users.UsersPage

@param UsersPage page

@template.layout.page(
    content = @`
    <form action="/users" method="get">
        <input type="search" name="term" value="${page.getTerm()}">
        <input type="submit" value="Искать">
    </form>
<table class="table table-striped table-hover">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Name</th>
    </tr>
    </thead>
    <tbody class="table-group-divider">
    @for(var user : page.getUsers())
    <tr>
        <td>
            ${user.getId()}
        </td>
        <td>
            ${user.getFirstName()} ${user.getLastName()}
        </td>
    </tr>
    @endfor
    </tbody>
</table>
`
    )
<!-- END -->
