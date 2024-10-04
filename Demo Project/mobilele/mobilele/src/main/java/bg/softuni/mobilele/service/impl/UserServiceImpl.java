package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.model.dtos.UserLoginDTO;
import bg.softuni.mobilele.model.dtos.UserRegistrationDTO;
import bg.softuni.mobilele.model.entities.UserEntity;
import bg.softuni.mobilele.repositories.UserRepository;
import bg.softuni.mobilele.service.UserService;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final CurrentUser currentUser;

    public UserServiceImpl(ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserRepository userRepository, CurrentUser currentUser) {

        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.currentUser = currentUser;
    }
    @Override

    public void registerUser(UserRegistrationDTO userRegistration) {
        userRepository.save(map(userRegistration));

        System.out.println("User received is: " + userRegistration);
    }

    @Override
    public boolean login(UserLoginDTO userLoginDTO) {
        UserEntity userEntity = userRepository
                .findByEmail(userLoginDTO.email())
                .orElse(null);

        if (userLoginDTO.password() == null
                || userEntity == null
                || userEntity.getPassword() == null) {
            return false;
        }

        boolean success = passwordEncoder.matches(userLoginDTO.password(), userEntity.getPassword());

        if (success) {
            currentUser.setFullName(userEntity.getFirstName() + " " + userEntity.getLastName());
            currentUser.setLoggedIn(true);
        } else {
            currentUser.cleanAll();
        }

        return false;
    }

    private UserEntity map(UserRegistrationDTO dto) {
        UserEntity mappedEntity = modelMapper.map(dto, UserEntity.class);

        mappedEntity.setPassword(passwordEncoder.encode(dto.getPassword()));

        return mappedEntity;
    }
}
