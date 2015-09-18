package controllers;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import models.SecurityRole;
import models.User;
import play.data.Form;
import play.i18n.Messages;
import play.mvc.Result;
import views.html.users.*;

import java.util.Map;

import static play.data.Form.form;

/**
 * Created by plesse on 11/6/14.
 */
public class UsersView extends HecticusController {
    final static Form<User> UserViewForm = form(User.class);

    public static Result GO_HOME = redirect(routes.UsersView.list(0, "name", "asc", ""));

    @Restrict(@Group(Application.ADMIN_ROLE))
    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(list.render(User.page(page, 25, sortBy, order, filter), sortBy, order, filter, false));
    }

    @Restrict(@Group(Application.ADMIN_ROLE))
    public static Result edit(Long id) {
        Form<User> filledForm = UserViewForm.fill(User.find.byId(id));
        return ok(edit.render(id, filledForm));
    }

    @Restrict(@Group(Application.ADMIN_ROLE))
    public static Result update(Long id) {
        Form<User> filledForm = UserViewForm.bindFromRequest();
        if(filledForm.hasErrors()) {
            return badRequest(edit.render(id, filledForm));
        }

        User user = User.find.byId(id);
        int i = 0;
        Map<String, String> data = filledForm.data();
        System.out.println(data.toString());

        user.active = data.containsKey("active");


        if(data.containsKey("name")){
            String name = data.get("name");
            if(!user.name.equalsIgnoreCase(name)){
                user.name = name;
            }
        }

        if(data.containsKey("email")){
            String email = data.get("email");
            if(!user.email.equalsIgnoreCase(email)){
                user.email = email;
            }
        }

        if(data.containsKey("firstName")){
            String firstName = data.get("firstName");
            if(!firstName.isEmpty() && !user.firstName.equalsIgnoreCase(firstName)){
                user.firstName = firstName;
            }
        }

        if(data.containsKey("lastName")){
            String lastName = data.get("lastName");
            if(!lastName.isEmpty() && !user.lastName.equalsIgnoreCase(lastName)){
                user.lastName = lastName;
            }
        }
        user.securityRoles.clear();
        while(data.containsKey("securityRoles[" + i + "].id")) {
            long idRole = Long.parseLong(data.get("securityRoles[" + i + "].id"));
            SecurityRole securityRole = SecurityRole.find.byId(idRole);
            if(securityRole != null){
                if(user.securityRoles !=  null && !user.securityRoles.contains(securityRole)){
                    user.securityRoles.add(securityRole);
                }
            }
            ++i;
        }

        user.update();
        flash("success", Messages.get("users.java.updated", user.name));
        return GO_HOME;

    }

    @Restrict(@Group(Application.ADMIN_ROLE))
    public static Result delete(Long id) {
        User user = User.find.byId(id);
        user.delete();
        flash("success", Messages.get("users.java.deleted", user.name));
        return GO_HOME;

    }


}
