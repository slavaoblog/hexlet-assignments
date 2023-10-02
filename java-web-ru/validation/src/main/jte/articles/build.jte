<!-- BEGIN -->
@import exercise.dto.articles.NewArticlePage
@param NewArticlePage page

@template.layout.page(
    content = @`
    <form action="/articles" method="post">
        <div>
            <label>
                Название статьи
                <input type="text" required name="title" value="${page.getTitle()}" />
            </label>
                @if(page.getErrors() != null && page.getErrors().get("title") != null)
            <ul>
                @for(var error : page.getErrors().get("title"))
                <li>${error.getMessage()}</li>
                @endfor
            </ul>
            @endif
        </div>
        <div>
            <label for="content">Содержание</label>
            <textarea required id="content" name="content" rows="4" cols="50">
                ${page.getContent()}
            </textarea>
                @if(page.getErrors() != null && page.getErrors().get("content") != null)
            <ul>
                @for(var error : page.getErrors().get("content"))
                <li>${error.getMessage()}</li>
                @endfor
            </ul>
            @endif
        </div>
        <input type="submit" value="Сохранить" />
    </form>
`
)
<!-- END -->
