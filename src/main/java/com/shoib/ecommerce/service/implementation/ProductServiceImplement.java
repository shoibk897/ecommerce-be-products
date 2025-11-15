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
public class ProductServiceImplement implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper mapper;
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
                .map(mapper::toAdminDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductAdminDTO create(ProductAdminDTO productAdmin) {
        productAdmin.setId(generateProductId());
        Product product = mapper.toEntity(productAdmin);
        Product saved =  productRepository.save(product);
        return mapper.toAdminDTO(saved);
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
        return mapper.toAdminDTO(saved);
    }

    @Override
    public ProductAdminDTO deleteProduct(String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        productRepository.delete(product);
        return mapper.toAdminDTO(product);
    }

    @Override
    public List<ProductUserDTO> getAllProductsForUser(){
        return productRepository.findAll()
                .stream()
                .map(mapper::toUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductUserDTO getById(String id){
        Product product = productRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return mapper.toUserDTO(product);
    }

    @Override
    public List<ProductUserDTO> searchProductsUser(String keyword){
        return productRepository.findByNameContainingIgnoreCase(keyword)
                .stream()
                .map(mapper::toUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductAdminDTO> searchProductsAdmin(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword)
                .stream()
                .map(mapper::toAdminDTO)
                .collect(Collectors.toList());
    }
}
