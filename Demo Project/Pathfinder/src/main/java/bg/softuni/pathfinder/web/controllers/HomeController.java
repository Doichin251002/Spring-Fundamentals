package bg.softuni.pathfinder.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Random;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index(Model model) {
        int sofiaTemp = new Random().nextInt();

        model.addAttribute("sofiaTemperature", sofiaTemp);

        return "index";
    }
}
