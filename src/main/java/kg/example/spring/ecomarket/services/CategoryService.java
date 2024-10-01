package kg.example.spring.ecomarket.services;

import kg.example.spring.ecomarket.entities.Category;
import java.util.List;
import java.util.Optional;


public interface CategoryService {
    Optional<Category> findIdCategory(int id);
    List<Category> getAllCategories();
    Category createCategories(Category category);
    Optional<Category> updateCategory(int id,Category category);
    void deleteCategory(int id);
}
