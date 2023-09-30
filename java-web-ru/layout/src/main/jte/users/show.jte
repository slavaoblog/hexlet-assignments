<!-- BEGIN -->
@import exercise.dto.users.UserPage;

@param UserPage page

@template.layout.page(
content = @`
<div class="mx-auto p-4 py-md-5">
    <main>
        <h2>${page.getUser().getFirstName()} ${page.getUser().getLastName()}</h2>
        <div>${page.getUser().getEmail()}</div>
    </main>
</div>
`
    )
<!-- END -->
