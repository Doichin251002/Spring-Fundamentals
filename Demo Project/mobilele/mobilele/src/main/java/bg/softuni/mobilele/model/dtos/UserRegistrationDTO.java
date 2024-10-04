package bg.softuni.mobilele.model.dtos;

import jakarta.validation.constraints.*;

public class UserRegistrationDTO {
    @NotEmpty
    @Size(min = 5, max = 20)
    private String firstName;

    @NotEmpty
    @Size(min = 5, max = 20)
    private String lastName;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String password;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserRegistrationDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + (password != null ? "N/A" : "[PROVIDED]") + '\'' +
                '}';
    }
}
