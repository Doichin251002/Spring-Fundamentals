package bg.softuni.pathfinder.web.controllers;

import bg.softuni.pathfinder.model.dtos.AddRouteDTO;
import bg.softuni.pathfinder.model.dtos.RouteShortInfoDTO;
import bg.softuni.pathfinder.model.enums.CategoryType;
import bg.softuni.pathfinder.model.enums.Level;
import bg.softuni.pathfinder.web.services.RouteService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
public class RouteController {
    private RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/routes")
    public String routes(Model model) {
        List<RouteShortInfoDTO> allRoutes = routeService.getAllRoutes();

        model.addAttribute("allRoutes", allRoutes);
        return "routes";
    }

    @GetMapping("/add-route")
    public ModelAndView viewAddRoute() {
        ModelAndView modelAndView = new ModelAndView("add-route");

        modelAndView.addObject("route", new RouteShortInfoDTO());
        modelAndView.addObject("levels", Level.values());
        modelAndView.addObject("categories", CategoryType.values());

        return modelAndView;
    }

    @ModelAttribute("routeData")
    public AddRouteDTO routeData() {
        return new AddRouteDTO();
    }

    @PostMapping("/add-route")
    public String doAddRoute(@Valid AddRouteDTO data,
                             @RequestParam("gpxCoordinates") MultipartFile file,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) throws IOException {

        routeService.addRoute(data, file);

        return "redirect:/add-route";
    }
}
