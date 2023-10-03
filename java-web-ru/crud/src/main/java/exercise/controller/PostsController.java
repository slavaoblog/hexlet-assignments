package exercise.controller;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;

import io.javalin.http.Context;

public class PostsController {

    // BEGIN
    public static void show(Context ctx) {
        try {
            Long id = ctx.pathParamAsClass("id", Long.class).get();
            Post post = PostRepository.find(id).orElseThrow();
            var page = new PostPage(post);
            ctx.render("posts/show.jte", Collections.singletonMap("page", page));
        } catch (NoSuchElementException e) {
            ctx.status(404);
            ctx.result("Page not found");
        }
    }

    public static void index(Context ctx) {
        List<Post> posts = PostRepository.getEntities();
        int pageNumber = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
        int perPage = 5;
        int totalPages = posts.size() % perPage == 0 ? posts.size() / perPage : posts.size() / perPage + 1;
        pageNumber = Math.max(pageNumber, 1);
        pageNumber = Math.min(pageNumber, totalPages);
        int start = perPage * (pageNumber - 1);
        int end = perPage * pageNumber;
        var postsPerPage = posts.subList(start, end);
        var page = new PostsPage(postsPerPage, pageNumber);
        ctx.render("posts/index.jte", Collections.singletonMap("page", page));
    }
    // END
}
