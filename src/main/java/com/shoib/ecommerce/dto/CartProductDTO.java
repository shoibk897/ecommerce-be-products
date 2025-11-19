package com.shoib.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartProductDTO {
    private String Id;
    private String name;
    private String description;
    private int price;
    private int quantity;
}
