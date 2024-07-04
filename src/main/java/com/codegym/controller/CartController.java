package com.codegym.controller;

import com.codegym.model.Cart;
import com.codegym.model.Food;
import com.codegym.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin("*")
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ICartService cartService;

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCart(@PathVariable Long id ) {
           Cart cart  = cartService.findByUserId(id);
           return new ResponseEntity<>(cart,HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<?> saveCart(@RequestBody Long id ,@RequestBody Food food){
        Cart cart = cartService.findByUserId(id);
        cart.getFood().add(food);
        cartService.save(cart);
        return new ResponseEntity<>("OK",HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteFood(@RequestBody Long id,@RequestBody Food food){
           Cart cart = cartService.findByUserId(id);
           cart.getFood().remove(food);
           cartService.save(cart);
           return new ResponseEntity<>("DELETING DONE",HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteAll(@RequestBody Long id){
        Cart cart = cartService.findByUserId(id);
        cart.getFood().clear();
        cartService.save(cart);
        return new ResponseEntity<>("DELETING ALL",HttpStatus.OK);
    }
}
