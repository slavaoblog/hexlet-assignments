package exercise.controller;

import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;
import exercise.model.User;
import exercise.util.NamedRoutes;
import java.util.Collections;
import exercise.repository.UserRepository;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.Context;


public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    // BEGIN
    public static void register(Context ctx) {
        var firstName = ctx.formParam("firstName");
        var lastName = ctx.formParam("lastName");
        var email = ctx.formParam("email");
        var password = ctx.formParam("password");
        var token = Security.generateToken();
        var user = new User(firstName, lastName, email, password, token);
        ctx.cookie("token", token);
        UserRepository.save(user);
        var userList = UserRepository.search(firstName);
        User userSaved = userList.stream()
                        .filter(u -> u.getToken().equals(token))
                                .findFirst()
                                        .orElseThrow(() -> new NotFoundResponse("User not found"));
        var id = userSaved.getId();
        ctx.redirect("/users/" + id);
    }

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var user = UserRepository.find(id).orElseThrow(() -> new NotFoundResponse("User not found"));
        var token = ctx.cookie("token");
        if (!user.getToken().equals(token)) {
            ctx.redirect("users/new");
        }
        ctx.render("users/show.jte", Collections.singletonMap("user", user));
    }
    // END
}
