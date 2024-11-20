package bg.softuni.pathfinder.web.controllers;

import bg.softuni.pathfinder.model.dtos.UserLoginDTO;
import bg.softuni.pathfinder.model.dtos.UserProfileDTO;
import bg.softuni.pathfinder.model.dtos.UserRegisterDTO;
import bg.softuni.pathfinder.model.enums.Level;
import bg.softuni.pathfinder.web.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String viewRegister(Model model) {
        model.addAttribute("registerUser", new UserRegisterDTO());
        model.addAttribute("levels", Level.values());

        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegisterDTO user,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

//        if (bindingResult.hasErrors()) {
//            redirectAttributes.addFlashAttribute("registerUser", user);
//
//            return "register";
//        }

        this.userService.register(user);

        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public ModelAndView viewLogin() {
        ModelAndView modelAndView = new ModelAndView("login");

        modelAndView.addObject("loginData", new UserLoginDTO());

        return modelAndView;
    }

    @PostMapping("/login")
    public String login(UserLoginDTO loginData) {
        this.userService.login(loginData);

        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout() {
        this.userService.logout();

        return "redirect:/";
    }

    @GetMapping("/profile")
    public ModelAndView profile() {
        ModelAndView modelAndView = new ModelAndView("profile");

        modelAndView.addObject("profileData", this.userService.getProfileData());

        return modelAndView;
    }
}
