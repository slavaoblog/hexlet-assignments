<!-- BEGIN -->
@import exercise.dto.users.UsersPage

@param UsersPage page

@template.layout.page(
content = @`
<table class="table table-striped table-hover">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Name</th>
    </tr>
    </thead>
    <tbody  class="table-group-divider">
    @for(var user : page.getUsers())
        <tr>
            <td>
                ${user.getId()}
            </td>
            <td>
                <a href="/users/${user.getId()}">${user.getFirstName()} ${user.getLastName()}</a>
            </td>
        </tr>
    @endfor
    </tbody>
</table>
`
)
<!-- END -->
