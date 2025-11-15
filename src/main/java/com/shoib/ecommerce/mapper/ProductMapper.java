package com.shoib.ecommerce.mapper;

import com.shoib.ecommerce.dto.ProductAdminDTO;
import com.shoib.ecommerce.dto.ProductUserDTO;
import com.shoib.ecommerce.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductAdminDTO toAdminDTO(Product product);
    ProductUserDTO toUserDTO(Product product);
    Product toEntity(ProductAdminDTO dto);

}
