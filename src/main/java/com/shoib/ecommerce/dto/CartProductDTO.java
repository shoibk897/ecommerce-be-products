package com.shoib.ecommerce.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartProductDTO {
    private String Id;
    private String name;
    private String imageUrl;
    private String description;
    private int price;
    private int quantity;
}
