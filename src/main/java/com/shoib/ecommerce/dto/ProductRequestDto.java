package com.shoib.ecommerce.dto;

import lombok.Data;

@Data
public class ProductRequestDto {

	private String name;
	private String description;
	private String imageURL;
	private int price;
	private String type;
	private int stock;
}


