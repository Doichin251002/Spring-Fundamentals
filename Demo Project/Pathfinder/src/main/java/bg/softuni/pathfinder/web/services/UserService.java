package bg.softuni.pathfinder.web.services;

import bg.softuni.pathfinder.model.CurrentUser;
import bg.softuni.pathfinder.model.dtos.UserLoginDTO;
import bg.softuni.pathfinder.model.dtos.UserProfileDTO;
import bg.softuni.pathfinder.model.dtos.UserRegisterDTO;
import bg.softuni.pathfinder.model.entities.User;
import bg.softuni.pathfinder.web.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private ModelMapper modelMapper;
    private CurrentUser currentUser;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    public void register(UserRegisterDTO userRegisterDTO) {
        User user = this.modelMapper.map(userRegisterDTO, User.class);
        user.setPassword(this.passwordEncoder.encode(userRegisterDTO.getPassword()));

        this.userRepository.save(user);
    }

    public void login(UserLoginDTO loginData) {
        User user = this.userRepository.findByUsername(loginData.getUsername());

        if (user == null) {
            throw new RuntimeException("Username is invalid");
        }

        if (this.passwordEncoder.matches(loginData.getPassword(), user.getPassword()) && !this.currentUser.isLoggedIn()) {
            this.currentUser.setUser(user);
            return;
        }

        throw new RuntimeException("Password is invalid");
    }

    public void logout() {
        this.currentUser.setUser(null);
    }

    public UserProfileDTO getProfileData() {
        return this.modelMapper.map(this.currentUser.getUser(), UserProfileDTO.class);
    }
}
