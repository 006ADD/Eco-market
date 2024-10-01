package kg.example.spring.ecomarket.services;

import kg.example.spring.ecomarket.entities.Product;
import kg.example.spring.ecomarket.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> findByProduct(String prod);
    Optional<Product> findByName(String name);
    Optional<Product> findByPrice(int price);
    Optional<Product> findByDescription(String description);
    Product findAll();
    Optional<Product> findById(int id);

    Product save(Product product);

    Optional<Product> update(int id, Product updetedProduct);


    void deleteById(int id);

    List<Product> searchByNameAndPrice(String name, Integer price);

    List<Product> findByPriceBetween(int minPrice, int maxPrice);
}
