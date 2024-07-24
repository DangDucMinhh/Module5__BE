package com.codegym.controller;

import com.codegym.model.Coupon;
import com.codegym.model.Food;
import com.codegym.service.ICouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin("*")
@RequestMapping("/coupons")

public class CouponController {
    @Autowired
    private ICouponService couponService;
    @GetMapping("")
    public ResponseEntity<?> getAll() {
        List<Coupon> list = couponService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<String> add(@RequestBody Coupon coupon) {
        couponService.save(coupon);
        String message = "Add success";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> edit(@PathVariable Long id, @RequestBody Coupon coupon) {
        coupon.setId(id);
        couponService.save(coupon);
        String message = "Edit success";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        couponService.remove(id);
        String message = "Delete success";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Coupon> getFoodById(@PathVariable Long id) {
        Coupon coupon = couponService.findById(id);
        return new ResponseEntity<>(coupon, HttpStatus.OK);

    }
}
