package com.shoib.ecommerce.service.implementation;

import com.shoib.ecommerce.dto.CartDTO;
import com.shoib.ecommerce.dto.CartRequestDTO;
import com.shoib.ecommerce.mapper.CartMapper;
import com.shoib.ecommerce.entity.Cart;
import com.shoib.ecommerce.entity.CartItem;
import com.shoib.ecommerce.repository.CartRepository;
import com.shoib.ecommerce.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final Random random = new Random();
    private String generateCartId() {
        String id;
        do {
            int num = 10000 + random.nextInt(90000);
            id = "cart_" + num;
        } while (cartRepository.existsById(id));
        return id;
    }

    @Override
    public CartDTO getUserCart(String userId) {
        return cartRepository.findByUserId(userId)
                .map(CartMapper::toCartDTO)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setId(generateCartId());
                    newCart.setUserId(userId);
                    newCart.setItems(new ArrayList<>());

                    cartRepository.save(newCart);
                    return CartMapper.toCartDTO(newCart);
                });
    }

    @Override
    public CartDTO addToCart(CartRequestDTO request) {
        return cartRepository.findByUserId(request.getUserId())
                .map(cart -> {
                    boolean found = false;

                    for(CartItem item : cart.getItems()) {
                        if(item.getProductId().equals(request.getProductId())) {
                            item.setQuantity(item.getQuantity() + request.getQuantity());
                            found = true;
                            break;
                        }
                    }

                    if(!found) {
                        CartItem item = new CartItem();
                        item.setProductId(request.getProductId());
                        item.setQuantity(request.getQuantity());
                        cart.getItems().add(item);
                    }

                    Cart updatedCart = cartRepository.save(cart);
                    return CartMapper.toCartDTO(updatedCart);
                })
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setId(generateCartId());
                    newCart.setUserId(request.getUserId());

                    CartItem item = new CartItem();
                    item.setProductId(request.getProductId());
                    item.setQuantity(request.getQuantity());

                    newCart.setItems(new ArrayList<>(List.of(item)));

                    Cart saved = cartRepository.save(newCart);
                    return CartMapper.toCartDTO(saved);
                });
    }

    @Override
    public CartDTO removeFromCart(String userId, String productId) {
        return cartRepository.findByUserId(userId)
                .map(cart -> {
                    cart.getItems().removeIf(item -> item.getProductId().equals(productId));
                    Cart saved = cartRepository.save(cart);
                    return CartMapper.toCartDTO(saved);
                }).orElse(null);
    }

    @Override
    public CartDTO removeQtyFromCart(CartRequestDTO request) {
        return cartRepository.findByUserId(request.getUserId())
                .map(cart -> {
                    for(CartItem item : cart.getItems()) {
                        if(item.getProductId().equals(request.getProductId())) {
                            item.setQuantity(item.getQuantity() - request.getQuantity());
                            break;
                        }
                    }
                    return CartMapper.toCartDTO(saved);
                }).orElse(null);
    }

    @Override
    public void clearCart(String userId) {
        cartRepository.findByUserId(userId).ifPresent(userCart -> {
            userCart.setItems(Collections.emptyList());
            cartRepository.save(userCart);
        });
    }
}
