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

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Optional<Product> findByProduct(String prod){
        return productRepository.findProduct(prod);
    }
    public Optional<Product> findByName(String name){
        return productRepository.findName(name);
    }
    public Optional<Product> findByPrice(int price){
        return productRepository.findPrice(price);
    }
    public Optional<Product> findByDescription(String description){
        return productRepository.findDescription(description);
    }

    public Product findAll() {
        return (Product) productRepository.findAll();
    }
    public Optional<Product> findById(int id){
        return productRepository.findById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> update(int id, Product updetedProduct) {
       Optional<Product> searchProduct = findById(id);
       if(searchProduct.isPresent()){
           Product newProd = searchProduct.get();
           newProd.setName(updetedProduct.getName());
           newProd.setDescription(updetedProduct.getDescription());
           newProd.setPrice(updetedProduct.getPrice());
          return Optional.of(productRepository.save(newProd));
       }
        return Optional.empty();
    }


    public void deleteById(int id) {
        productRepository.deleteById(id);
    }

    public List<Product> searchByNameAndPrice(String name, Integer price) {
        List<Product> productList;

        if(name != null && price != null){
            Optional<List<Product>> products = productRepository.findNameAndPrice(name, price);
            productList = products.orElse(Collections.emptyList());
        }else if(name != null){
            Optional<Product> product = productRepository.findName(name);
             productList = product.map(Collections::singletonList).orElse(Collections.emptyList());
        }else if (price != null) {
            Optional<Product> product = productRepository.findPrice(price);
            productList = product.map(Collections::singletonList).orElse(Collections.emptyList());
        } else {
            productList = productRepository.findAll();
        }
        Collections.sort(productList, Comparator.comparing(Product::getName));

        return productList;
    }

    public List<Product> findByPriceBetween(int minPrice, int maxPrice) {
        return productRepository.findPriceBetween(minPrice, maxPrice).stream().toList();
    }
}
