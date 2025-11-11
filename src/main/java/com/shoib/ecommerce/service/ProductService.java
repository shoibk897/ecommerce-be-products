package com.shoib.ecommerce.service;

import com.shoib.ecommerce.entity.Product;
import com.shoib.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final Random random = new Random();

    public String generateProductId() {
        String id;
        do {
            int num = 10000 + random.nextInt(90000);
            id = "prod_" + num;
        } while (productRepository.existsById(id));

        return id;
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(String id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product create(Product product) {
        product.setId(generateProductId());
        return productRepository.save(product);
    }

    public List<Product> searchProducts(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }
}
