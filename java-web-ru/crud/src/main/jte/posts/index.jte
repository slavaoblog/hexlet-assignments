<!-- BEGIN -->
@import exercise.dto.posts.PostsPage
@param PostsPage page

@template.layout.page(
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
            <td>
                <a href="/posts/${post.getId()}">${post.getName()}</a>
            </td>
            <td>
                ${post.getBody()}
            </td>
        </tr>
        @endfor
        </tbody>
    </table>
    <div>
        <a href="/posts?page=${page.getPageNumber() - 1}" class="btn btn-primary" role="button" data-bs-toggle="button">Previous</a>
        <a href="/posts?page=${page.getPageNumber() + 1}" class="btn btn-primary" role="button" data-bs-toggle="button">Next</a>
    </div>
`
    )
<!-- END -->
