package com.shoib.ecommerce.service.implementation;

import com.shoib.ecommerce.dto.ProductAdminDTO;
import com.shoib.ecommerce.entity.Product;
import com.shoib.ecommerce.mapper.ProductMapper;
import com.shoib.ecommerce.repository.ProductRepository;
import com.shoib.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.shoib.ecommerce.dto.ProductUserDTO;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final Random random = new Random();
    private String generateProductId() {
        String id;
        do {
            int num = 10000 + random.nextInt(90000);
            id = "prod_" + num;
        } while (productRepository.existsById(id));
        return id;
    }

    @Override
    public List<ProductAdminDTO> getAllProductsForAdmin() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toAdminDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductAdminDTO create(ProductAdminDTO productAdmin) {
        productAdmin.setId(generateProductId());
        Product product = ProductMapper.toEntity(productAdmin);
        Product saved =  productRepository.save(product);
        return ProductMapper.toAdminDTO(saved);
    }

    @Override
    public ProductAdminDTO updateProduct(String id, ProductAdminDTO updatedProduct) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setImageURL(updatedProduct.getImageURL());
        product.setPrice(updatedProduct.getPrice());
        product.setType(updatedProduct.getType());
        product.setStock(updatedProduct.getStock());
        Product saved = productRepository.save(product);
        return ProductMapper.toAdminDTO(saved);
    }

    @Override
    public ProductAdminDTO updateProductInventory(String id, int quantity){
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        int updated = product.getStock() + quantity;
        if (updated < 0) {
            throw new RuntimeException("Inventory cannot be negative");
        }
        product.setStock(updated);
        productRepository.save(product);
        return ProductMapper.toAdminDTO(product);
    }

    @Override
    public ProductAdminDTO deleteProduct(String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        productRepository.delete(product);
        return ProductMapper.toAdminDTO(product);
    }

    @Override
    public List<ProductUserDTO> getAllProductsForUser(){
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductUserDTO getByIdUser(String id){
        Product product = productRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ProductMapper.toUserDTO(product);
    }

    @Override
    public ProductAdminDTO getByIdAdmin(String id){
        Product product = productRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ProductMapper.toAdminDTO(product);
    }

    @Override
    public List<ProductUserDTO> searchProductsUser(String keyword){
        return productRepository.findByNameContainingIgnoreCase(keyword)
                .stream()
                .map(ProductMapper::toUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductAdminDTO> searchProductsAdmin(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword)
                .stream()
                .map(ProductMapper::toAdminDTO)
                .collect(Collectors.toList());
    }
}
