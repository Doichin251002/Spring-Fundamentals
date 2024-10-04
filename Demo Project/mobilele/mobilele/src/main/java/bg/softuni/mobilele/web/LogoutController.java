package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.dtos.UserLoginDTO;
import bg.softuni.mobilele.service.UserService;
import bg.softuni.mobilele.service.impl.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class LogoutController {

    private final CurrentUser currentUser;

    public LogoutController(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    @GetMapping("/logout")
    public String logout() {
        currentUser.cleanAll();

        return "redirect:/";
    }
}
