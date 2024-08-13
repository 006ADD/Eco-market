package kg.example.spring.ecomarket.services;

import kg.example.spring.ecomarket.entities.Category;
import kg.example.spring.ecomarket.exception.NotFoundException;
import kg.example.spring.ecomarket.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }
    public Optional<Category> findIdCategory(int id){
        return categoryRepository.findById(id);
    }
    public Category createCategories(Category category){
        return categoryRepository.save(category);
    }

    public Optional<Category> updateCategory(int id,Category category){
        Optional<Category> findId = categoryRepository.findById(id);
        if(findId.isPresent()){
            Category existing =  findId.get();
            existing.setName(category.getName());
            existing.setDescription(category.getDescription());

            return Optional.of(categoryRepository.save(existing));
        }
        return Optional.empty();
    }

    public void deleteCategory(int id){
        categoryRepository.deleteById(id);
    }
}
