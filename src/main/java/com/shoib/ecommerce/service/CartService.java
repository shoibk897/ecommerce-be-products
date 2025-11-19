package com.shoib.ecommerce.service;

import com.shoib.ecommerce.dto.CartDTO;
import com.shoib.ecommerce.dto.CartRequestDTO;
import com.shoib.ecommerce.dto.CartUserDTO;

import java.util.List;

public interface CartService {

    List<CartDTO> getCarts();

    CartDTO addToCart(CartRequestDTO request);

    CartUserDTO getUserCart(String userId);

    CartDTO removeFromCart(String userId, String productId);

    CartDTO removeQtyFromCart(CartRequestDTO request);

    void clearCart(String userId);
}
