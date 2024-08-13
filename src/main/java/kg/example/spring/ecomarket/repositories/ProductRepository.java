package kg.example.spring.ecomarket.repositories;

import kg.example.spring.ecomarket.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findProduct(String prod);
    Optional<Product> findName(String name);
    Optional<Product> findPrice(int price);
    Optional<Product> findDescription(String description);
    Optional<List<Product>> findNameAndPrice(String name, int price);
    List<Product> findPriceBetween(int minPrice, int maxPrice);
}
