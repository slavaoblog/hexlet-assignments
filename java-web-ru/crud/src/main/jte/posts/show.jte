<!-- BEGIN -->
@import exercise.dto.posts.PostPage
@param PostPage page

@template.layout.page(
    content = @`
    <div class="mx-auto p-4 py-md-5">
        <main>
            <h2>${page.getPost().getName()}</h2>
            <div>${page.getPost().getBody()}</div>
        </main>
    </div>
    <div>
        <a href="/posts" class="btn btn-primary" role="button" data-bs-toggle="button">Back to List</a>
    </div>
`
    )
<!-- END -->
