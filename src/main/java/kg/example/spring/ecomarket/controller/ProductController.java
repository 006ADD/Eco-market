package kg.example.spring.ecomarket.controller;

import kg.example.spring.ecomarket.entities.Product;
import kg.example.spring.ecomarket.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @GetMapping("/all")
    public ResponseEntity<Product> getAllCategory() {
        Product allProduct = productService.findAll();
        return ResponseEntity.ok(allProduct);
    }
    @GetMapping("/getId/{id}")
    public ResponseEntity<Optional<Product>> findById(@PathVariable int id){
        Optional<Product> findByIdProduct = productService.findById(id);
        return ResponseEntity.ok(findByIdProduct);
    }

    @GetMapping("/getProduct/{product}")
    public ResponseEntity<Optional<Product>> findByProduct(@PathVariable String product){
        Optional<Product> findProduct = productService.findByProduct(product);
        return ResponseEntity.ok(findProduct);
    }

    @GetMapping("/findName/{name}")
    public ResponseEntity<Optional<Product>> findProductName(@PathVariable String name){
        Optional<Product> productName = productService.findByName(name);
        return ResponseEntity.ok(productName);
    }

    @GetMapping("/findPrice/{price}")
    public ResponseEntity<Optional<Product>> findProductPrice(@PathVariable int price){
        Optional<Product> productPrice = productService.findByPrice(price);
        return ResponseEntity.ok(productPrice);
    }

    @GetMapping("/findDescription/{description}")
    public ResponseEntity<Optional<Product>> findDescription(@PathVariable String description){
        Optional<Product> newDescription = productService.findByDescription(description);
        return ResponseEntity.ok(newDescription);
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product saveProduct = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveProduct);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product){
        Optional<Product> update = productService.update(id, product);
        return update.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable int id) {
        productService.deleteById(id);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam(required = false) String name, @RequestParam(required = false) Integer price) {
        List<Product>search = productService.searchByNameAndPrice(name, price);
        return ResponseEntity.ok(search);
    }

    @GetMapping("/priceRange")
    public ResponseEntity<List<Product>> getProductsByPriceRange(@RequestParam int minPrice, @RequestParam int maxPrice) {
        List<Product>priceBetween = productService.findByPriceBetween(minPrice, maxPrice);
        return ResponseEntity.ok(priceBetween);
    }
}
