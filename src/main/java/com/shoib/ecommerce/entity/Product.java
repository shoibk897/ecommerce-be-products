package com.shoib.ecommerce.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "products")
public class Product {

    @Id
    private String id;

    private String name;
    private String description;
    private String imageURL;
    private int price;
    private String type;
    private int stock;
}
