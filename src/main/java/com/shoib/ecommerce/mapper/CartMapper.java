package com.shoib.ecommerce.mapper;

import com.shoib.ecommerce.dto.CartDTO;
import com.shoib.ecommerce.dto.CartItemDTO;
import com.shoib.ecommerce.dto.CartRequestDTO;
import com.shoib.ecommerce.entity.Cart;
import com.shoib.ecommerce.entity.CartItem;

import java.util.ArrayList;
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
}
