package bg.softuni.pathfinder.web.services;

import bg.softuni.pathfinder.model.dtos.AddRouteDTO;
import bg.softuni.pathfinder.model.dtos.RouteShortInfoDTO;
import bg.softuni.pathfinder.model.entities.Picture;
import bg.softuni.pathfinder.model.entities.Route;
import bg.softuni.pathfinder.web.repositories.RouteRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class RouteService {
    private RouteRepository repository;
    private Random random;
    private ModelMapper modelMapper;

    public RouteService(RouteRepository repository) {
        this.repository = repository;

        this.modelMapper = new ModelMapper();
        this.random = new Random();
    }

    @Transactional
    public List<RouteShortInfoDTO> getAllRoutes() {
        return repository.findAll().stream()
                .map(this::mapToShortInfo)
                .toList();
    }

    @Transactional
    public RouteShortInfoDTO getRandomRoute() {
        long routesCount = this.repository.count();

        long randomId = this.random.nextLong(routesCount) + 1;
        Optional<Route> optionalRouteById = this.repository.findById(randomId);

        return mapToShortInfo(optionalRouteById.get());
    }

    private RouteShortInfoDTO mapToShortInfo(Route route) {
        RouteShortInfoDTO dto = modelMapper.map(route, RouteShortInfoDTO.class);

        Optional<Picture> optionalPicture = route.getPictures().stream().findFirst();
        dto.setImageUrl(optionalPicture.get().getUrl());

        return dto;
    }

    public void addRoute(AddRouteDTO data, MultipartFile gpxFile) throws IOException {
        Route route = modelMapper.map(data, Route.class);

        Path destinationFile = Paths
                .get("src", "main", "resources", "uploads", "file.gpx")
                .normalize()
                .toAbsolutePath();

        try (InputStream inputStream = gpxFile.getInputStream()) {

            Files.copy(inputStream, destinationFile,
                    StandardCopyOption.REPLACE_EXISTING);
        }

    }
}
