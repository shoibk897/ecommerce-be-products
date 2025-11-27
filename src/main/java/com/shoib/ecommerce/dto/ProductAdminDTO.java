package com.shoib.ecommerce.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductAdminDTO {
    private String id;
    private String name;
    private String description;
    private String imageURL;
    private int price;
    private String type;
    private int stock;
}
