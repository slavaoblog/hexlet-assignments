@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes
@param PostsPage page

@template.layout.page(
page = page,
content = @`
    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Body</th>
        </tr>
        </thead>
        <tbody class="table-group-divider">
            @for(var post : page.getPosts())
                <tr>
                    <td>${post.getName()}</td>
                    <td>${post.getBody()}</td>
                </tr>
            @endfor
        </tbody>
    </table>
`
)