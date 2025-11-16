package com.shoib.ecommerce.mapper;

import com.shoib.ecommerce.dto.ProductAdminDTO;
import com.shoib.ecommerce.dto.ProductUserDTO;
import com.shoib.ecommerce.entity.Product;

public class ProductMapper {

    public static ProductAdminDTO toAdminDTO(Product product) {
        if (product == null) return null;

        ProductAdminDTO dto = new ProductAdminDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setImageURL(product.getImageURL());
        dto.setPrice(product.getPrice());
        dto.setType(product.getType());
        dto.setStock(product.getStock());

        return dto;
    }

    public static ProductUserDTO toUserDTO(Product product) {
        if (product == null) return null;

        ProductUserDTO dto = new ProductUserDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setImageURL(product.getImageURL());
        dto.setPrice(product.getPrice());
        dto.setType(product.getType());

        return dto;
    }

    public static Product toEntity(ProductAdminDTO dto) {
        if (dto == null) return null;
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setImageURL(dto.getImageURL());
        product.setPrice(dto.getPrice());
        product.setType(dto.getType());
        product.setStock(dto.getStock());
        return product;
    }
}
