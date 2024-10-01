package kg.example.spring.ecomarket.services.impls;

import kg.example.spring.ecomarket.entities.Category;
import kg.example.spring.ecomarket.repositories.CategoryRepository;
import kg.example.spring.ecomarket.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DefaultCategoryService implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Optional<Category> findIdCategory(int id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category createCategories(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> updateCategory(int id, Category category) {
        Optional<Category> findId = categoryRepository.findById(id);
        if(findId.isPresent()){
            Category existing =  findId.get();
            existing.setName(category.getName());
            existing.setDescription(category.getDescription());

            return Optional.of(categoryRepository.save(existing));
        }
        return Optional.empty();
    }

    @Override
    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }
}
