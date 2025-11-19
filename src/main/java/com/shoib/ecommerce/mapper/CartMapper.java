package com.shoib.ecommerce.mapper;

import com.shoib.ecommerce.dto.*;
import com.shoib.ecommerce.entity.Cart;
import com.shoib.ecommerce.entity.CartItem;
import com.shoib.ecommerce.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CartMapper {

    public static CartDTO toCartDTO(Cart cart) {
        if (cart == null) {
            throw new  IllegalArgumentException("Cart is null");
        }
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCartId(cart.getId());
        cartDTO.setUserId(cart.getUserId());
        if (cart.getItems() == null) {
            cartDTO.setItems(new ArrayList<>());
        } else {
            cartDTO.setItems(
                    cart.getItems()
                            .stream()
                            .map(item -> {
                                CartItemDTO dto = new CartItemDTO();
                                dto.setProductId(item.getProductId());
                                dto.setQuantity(item.getQuantity());
                                return dto;
                            })
                            .collect(Collectors.toList())
            );
        }
        return cartDTO;
    }

    public static CartUserDTO toCartUserDTO(Cart cart, List<CartProductDTO> items) {
        CartUserDTO dto = new CartUserDTO();
        dto.setCartId(cart.getId());
        dto.setUserId(cart.getUserId());
        dto.setItems(items);
        return dto;
    }

    public static CartProductDTO toCartProductDTO(Product product, int qty) {
        CartProductDTO dto = new CartProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setQuantity(qty);
        return dto;
    }
}
