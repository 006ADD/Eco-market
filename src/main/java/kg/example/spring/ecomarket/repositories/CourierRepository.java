package kg.example.spring.ecomarket.repositories;

import kg.example.spring.ecomarket.entities.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourierRepository extends JpaRepository<Courier, Long> {
    List<Courier> findByUsername(String username);
}
