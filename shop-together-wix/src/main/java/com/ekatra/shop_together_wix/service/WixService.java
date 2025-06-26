package com.ekatra.shop_together_wix.service;

import com.ekatra.shop_together_wix.utils.externalServices.WixFeignClient;
import org.springframework.stereotype.Service;

@Service
public class WixService {
    private final WixFeignClient wixFeignClient;

    public WixService(WixFeignClient wixFeignClient) {
        this.wixFeignClient = wixFeignClient;
    }

    public String fetchProducts(String accessToken) {
        String bearerToken = "Bearer " + accessToken;
        return wixFeignClient.getAllProducts(bearerToken);
    }
}
