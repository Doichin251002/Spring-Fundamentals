package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dtos.UserLoginDTO;
import bg.softuni.mobilele.model.dtos.UserRegistrationDTO;

public interface UserService {
    void registerUser(UserRegistrationDTO userRegistration);

    boolean login(UserLoginDTO userLogin);
}
