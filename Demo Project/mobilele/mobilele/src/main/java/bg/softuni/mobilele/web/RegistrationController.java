package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.dtos.UserRegistrationDTO;
import bg.softuni.mobilele.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class RegistrationController {
    private UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("registerDTO")
    public UserRegistrationDTO registerDTO() {
        return new UserRegistrationDTO();
    }

    @GetMapping("/register")
    public String register() {
        return "auth-register";
    }

    @PostMapping("/register")
    public String register(UserRegistrationDTO userRegistrationDTO) {
        userService.registerUser(userRegistrationDTO);

        return "redirect:/";
    }
}
