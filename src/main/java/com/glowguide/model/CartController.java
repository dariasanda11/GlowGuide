package com.glowguide.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartItemRepository cartRepository;

    // 1. GET all items for a specific client
    @GetMapping("/{email}")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable String email) {
        return ResponseEntity.ok(cartRepository.findByClientEmail(email));
    }

    // 2. POST a new item to the cart
    @PostMapping
    public ResponseEntity<CartItem> addToCart(@RequestBody CartItem item) {
        return ResponseEntity.ok(cartRepository.save(item));
    }

    // 3. PUT (Update) the quantity of an item
    @PutMapping("/update/{id}")
    public ResponseEntity<CartItem> updateQuantity(@PathVariable Long id, @RequestParam int quantity) {
        return cartRepository.findById(id).map(item -> {
            item.setQuantity(quantity);
            return ResponseEntity.ok(cartRepository.save(item));
        }).orElse(ResponseEntity.notFound().build());
    }

    // 4. DELETE an item from the cart entirely
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long id) {
        cartRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}