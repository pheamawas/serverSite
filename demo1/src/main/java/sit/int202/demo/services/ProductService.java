package sit.int202.demo.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sit.int202.demo.entities.Product;
import sit.int202.demo.repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;
    public ProductService(ProductRepository productRepository) {
        this.repository = productRepository;
    }
    public List<Product> findAll() {
        return repository.findAll();
    }
    public Product findById(String productCode) {
        return repository.findById(productCode).orElse(null);
    }

    public List<Product> findProductByText(String searchParam) {
        String keyword = "%" + searchParam + "%";
        return repository.findAllProductByKeyword(keyword, keyword, keyword);
    }
    public List<Product> findProductByPrice(int lower, int upper) {
        return repository.findProductByBuyPriceBetween(BigDecimal.valueOf(lower), BigDecimal.valueOf(upper));
    }

    public Page<Product> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
