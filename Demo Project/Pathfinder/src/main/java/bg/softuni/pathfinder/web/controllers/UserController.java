package bg.softuni.pathfinder.web.controllers;

import bg.softuni.pathfinder.model.dtos.UserRegisterDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("users")
public class UserController {

    @GetMapping("/register")
    public String viewRegister(Model model) {
        model.addAttribute("registerUser", new UserRegisterDTO());

        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegisterDTO user,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerUser", user);

            return "register";
        }

        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
