package com.shoib.ecommerce.service;

import com.shoib.ecommerce.dto.ProductResponseDto;
import com.shoib.ecommerce.entity.Product;
import com.shoib.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

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


    public Product updateProduct(String id, Product updatedProduct) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(updatedProduct.getName());
                    existingProduct.setDescription(updatedProduct.getDescription());
                    existingProduct.setImageURL(updatedProduct.getImageURL());
                    existingProduct.setPrice(updatedProduct.getPrice());
                    existingProduct.setType(updatedProduct.getType());
                    existingProduct.setStock(updatedProduct.getStock());
                    return productRepository.save(existingProduct);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    public Product deleteProduct(String id) {
        Product product = productRepository.findById(id).orElse(null);
        productRepository.deleteById(id);
        return product;
    }
}
