package bg.softuni.pathfinder.web.controllers;

import bg.softuni.pathfinder.model.dtos.UserRegisterDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @GetMapping("/users/register")
    public String viewRegister(Model model) {
        model.addAttribute("registerUser", new UserRegisterDTO());

        return "register";
    }

    @PostMapping("/users/register")
    public String register(@Valid UserRegisterDTO user,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerUser", user);

            return "register";
        }

        return "redirect:/users/login";
    }

    @GetMapping("/users/login")
    public String login() {
        return "login";
    }
}
