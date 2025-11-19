package com.shoib.ecommerce.controller;

import com.shoib.ecommerce.dto.CartDTO;
import com.shoib.ecommerce.dto.CartRequestDTO;
import com.shoib.ecommerce.dto.CartUserDTO;
import com.shoib.ecommerce.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class  CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<List<CartDTO>> getCart() {
        return ResponseEntity.ok(cartService.getCarts());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CartUserDTO> getUserCart(@PathVariable String userId) {
        return ResponseEntity.ok(cartService.getUserCart(userId));
    }

    @PostMapping("/addToCart")
    public ResponseEntity<CartDTO> addToCart(@RequestBody CartRequestDTO request) {
        return ResponseEntity.ok(cartService.addToCart( request));
    }

    @DeleteMapping("/remove/{userId}/{productId}")
    public ResponseEntity<CartDTO> removeFromCart(@PathVariable String userId, @PathVariable String productId) {
        return ResponseEntity.ok(cartService.removeFromCart(userId, productId));
    }

    @PutMapping("/removeQty")
    public ResponseEntity<CartDTO> removeQtyFromCart(@RequestBody CartRequestDTO request) {
        return ResponseEntity.ok(cartService.removeQtyFromCart(request));
    }

    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<String> clearCart(@PathVariable String userId) {
        cartService.clearCart(userId);
        return ResponseEntity.ok("Cart cleared");
    }
}
