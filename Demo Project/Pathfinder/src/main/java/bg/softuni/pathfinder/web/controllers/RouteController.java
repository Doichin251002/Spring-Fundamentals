package bg.softuni.pathfinder.web.controllers;

import bg.softuni.pathfinder.model.dtos.RouteShortInfoDTO;
import bg.softuni.pathfinder.web.services.RouteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
