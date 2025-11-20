package com.shoib.ecommerce.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartUserDTO {
    private String cartId;
    private String userId;
    private List<CartProductDTO> items;
}
