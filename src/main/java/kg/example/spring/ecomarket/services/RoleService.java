package kg.example.spring.ecomarket.services;

import kg.example.spring.ecomarket.entities.Role;
import kg.example.spring.ecomarket.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role findByName(String name){
        return roleRepository.findByName("ROLE_USER").get();
    }

}
