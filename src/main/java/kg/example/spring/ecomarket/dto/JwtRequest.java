package kg.example.spring.ecomarket.dto;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}
