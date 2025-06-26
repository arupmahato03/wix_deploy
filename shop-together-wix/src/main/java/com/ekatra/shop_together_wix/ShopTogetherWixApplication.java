package com.ekatra.shop_together_wix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ShopTogetherWixApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopTogetherWixApplication.class, args);
	}

}
