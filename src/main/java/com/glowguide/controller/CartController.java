package com.glowguide.controller;

import com.glowguide.model.CartItem;
import com.glowguide.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartItemRepository cartRepository;


    @GetMapping("/{email}")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable String email) {
        return ResponseEntity.ok(cartRepository.findByClientEmail(email));
    }

    @PostMapping
    public ResponseEntity<CartItem> addToCart(@RequestBody CartItem item) {
        return ResponseEntity.ok(cartRepository.save(item));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CartItem> updateQuantity(@PathVariable Long id, @RequestParam int quantity) {
        return cartRepository.findById(id).map(item -> {
            item.setQuantity(quantity);
            return ResponseEntity.ok(cartRepository.save(item));
        }).orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long id) {
        cartRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}