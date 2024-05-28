package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.model.UserRegistrationDTO;
import bg.softuni.mobilele.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public void registerUser(UserRegistrationDTO userRegistration) {
        System.out.println("User received is: " + userRegistration);
    }
}
