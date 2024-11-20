package bg.softuni.pathfinder.model;

import bg.softuni.pathfinder.model.entities.Role;
import bg.softuni.pathfinder.model.entities.User;
import bg.softuni.pathfinder.model.enums.UserRole;
import org.springframework.stereotype.Component;

@Component
public class CurrentUser {
    private User user;

    public boolean isLoggedIn() {
        return this.user != null;
    }

    public boolean isAdmin() {
        return this.user.getRoles()
                .stream()
                .map(Role::getName)
                .anyMatch(r -> r.equals(UserRole.ADMIN));
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
