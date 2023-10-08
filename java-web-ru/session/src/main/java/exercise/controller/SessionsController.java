package exercise.controller;

import java.util.Collections;
import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.repository.UsersRepository;
import static exercise.util.Security.encrypt;

import exercise.util.Security;
import io.javalin.http.Context;

public class SessionsController {

    // BEGIN
    public static void build(Context ctx) {
        ctx.render("build.jte");
    }

    public static void create(Context ctx) {
        var name = ctx.formParam("name");
        var pass = ctx.formParam("password");
        var encryptedPass = Security.encrypt(pass);
        if (UsersRepository.existsByName(name)) {
            var user = UsersRepository.findByName(name);
            if (user.getPassword().equals(encryptedPass)) {
                ctx.sessionAttribute("currentUser", name);
                var page = new MainPage(name);
                ctx.status(302);
                ctx.render("index.jte", Collections.singletonMap("page", page));
                return;
            }
        }
        var message = "Wrong username or password";
        var page = new LoginPage(name, message);
        ctx.render("build.jte", Collections.singletonMap("page", page));
    }

    public static void destroy(Context ctx) {
        ctx.sessionAttribute("currentUser", null);
        ctx.redirect("/");
    }

    public static void root(Context ctx) {
        var page = new MainPage(ctx.sessionAttribute("currentUser"));
        ctx.render("index.jte", Collections.singletonMap("page", page));
    }
    // END
}
