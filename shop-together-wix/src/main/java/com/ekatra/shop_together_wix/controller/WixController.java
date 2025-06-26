package com.ekatra.shop_together_wix.controller;

import com.ekatra.shop_together_wix.service.WixService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wix")
@RequiredArgsConstructor
public class WixController {

    private final WixService wixService;

    @GetMapping("/products")
    public ResponseEntity<String> getProducts(@RequestParam String token) {
        String products = wixService.fetchProducts(token);
        return ResponseEntity.ok(products);
    }
}
