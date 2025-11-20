package com.shoib.ecommerce.controller;

import com.shoib.ecommerce.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.shoib.ecommerce.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductUserDTO> getUserProducts() {
        return productService.getAllProductsForUser();
    }

    @GetMapping("/admin")
    public List<ProductAdminDTO> getAdminProducts() {
        return productService.getAllProductsForAdmin();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductUserDTO> getUserProduct(@PathVariable String id) {
        ProductUserDTO product = productService.getByIdUser(id);
        return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<ProductAdminDTO> getAdminProduct(@PathVariable String id) {
        ProductAdminDTO product = productService.getByIdAdmin(id);
        return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public List<ProductUserDTO> searchUserProducts(@RequestParam String keyword) {
        return productService.searchProductsUser(keyword);
    }

    @GetMapping("/admin/search")
    public List<ProductAdminDTO> searchAdminProducts(@RequestParam String keyword) {
        return productService.searchProductsAdmin(keyword);
    }

    @PostMapping("/admin/create")
    public ResponseEntity<ProductAdminDTO> createProduct(@RequestBody ProductAdminDTO product) {
        return ResponseEntity.ok(productService.create(product));
    }

    @PutMapping("/admin/update/{id}")
    public ResponseEntity<ProductAdminDTO> updateProduct(@PathVariable String id, @RequestBody ProductAdminDTO product) {
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<ProductAdminDTO> deleteProduct(@PathVariable String id) {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }
}
