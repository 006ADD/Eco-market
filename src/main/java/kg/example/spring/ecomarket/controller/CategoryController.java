package kg.example.spring.ecomarket.controller;

import kg.example.spring.ecomarket.entities.Category;
import kg.example.spring.ecomarket.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/market")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("/getAllCategory")
    public ResponseEntity<List<Category>> allCategory(){
        List<Category> listCategory = categoryService.getAllCategories();
        return ResponseEntity.ok(listCategory);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Optional<Category>> getIdCategory(@PathVariable int id){
        Optional<Category> getId = categoryService.findIdCategory(id);
        return ResponseEntity.ok(getId);
    }

    @PostMapping("/create")
    public ResponseEntity<Category> createCategory(@RequestBody Category category)
    {
        Category newCategory = categoryService.createCategories(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable int id, @RequestBody Category category){
        Optional<Category> update = categoryService.updateCategory(id, category);
        return update.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public void deleteByCategory(@PathVariable int id){
        categoryService.deleteCategory(id);
    }
}
