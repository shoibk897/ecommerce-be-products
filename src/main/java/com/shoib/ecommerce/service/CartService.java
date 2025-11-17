package com.shoib.ecommerce.service;

import com.shoib.ecommerce.dto.CartDTO;
import com.shoib.ecommerce.dto.CartRequestDTO;

public interface CartService {

    CartDTO addToCart(CartRequestDTO request);

    CartDTO getUserCart(String userId);

    CartDTO removeFromCart(String userId, String productId);

    CartDTO removeQtyFromCart(CartRequestDTO request);

    void clearCart(String userId);
}
