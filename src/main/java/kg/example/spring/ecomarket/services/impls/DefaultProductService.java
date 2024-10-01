package kg.example.spring.ecomarket.services.impls;

import kg.example.spring.ecomarket.entities.Product;
import kg.example.spring.ecomarket.repositories.ProductRepository;
import kg.example.spring.ecomarket.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultProductService implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Optional<Product> findByProduct(String prod){
        return productRepository.findProduct(prod);
    }
    @Override
    public Optional<Product> findByName(String name){
        return productRepository.findName(name);
    }
    @Override
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
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }
    @Override
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

    @Override
    public void deleteById(int id) {
        productRepository.deleteById(id);
    }
    @Override
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
    @Override
    public List<Product> findByPriceBetween(int minPrice, int maxPrice) {
        return productRepository.findPriceBetween(minPrice, maxPrice).stream().toList();
    }
}
