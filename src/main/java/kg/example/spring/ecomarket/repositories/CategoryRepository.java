package kg.example.spring.ecomarket.repositories;

import kg.example.spring.ecomarket.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findByCategories(String category);
    Optional<Category> findByCategoryName(String name);
    Category updateCategory(int id, Category category);
}
