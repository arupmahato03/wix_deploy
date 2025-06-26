package com.ekatra.shop_together_wix.utils.externalServices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "wixClient", url = "https://www.wixapis.com")
public interface WixFeignClient {

    @GetMapping("/stores-reader/v1/products")
    String getAllProducts(@RequestHeader("Authorization") String authorizationHeader);
}
