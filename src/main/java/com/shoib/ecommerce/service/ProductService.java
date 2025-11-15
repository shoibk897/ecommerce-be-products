package com.shoib.ecommerce.service;

import com.shoib.ecommerce.dto.ProductAdminDTO;
import com.shoib.ecommerce.dto.ProductUserDTO;
import java.util.List;

public interface ProductService {
    List<ProductUserDTO> getAllProductsForUser();
    List<ProductAdminDTO> getAllProductsForAdmin();

    ProductUserDTO getById(String id);

    ProductAdminDTO create(ProductAdminDTO product);

    List<ProductUserDTO> searchProductsUser(String keyword);
    List<ProductAdminDTO> searchProductsAdmin(String keyword);

    ProductAdminDTO updateProduct(String id, ProductAdminDTO updatedProduct);

    ProductAdminDTO deleteProduct(String id);
}
