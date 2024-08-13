package kg.example.spring.ecomarket.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDTO {
    private String name;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private LocalDate dateOfBirth;
    private String address;
    private String phoneNumber;
}
