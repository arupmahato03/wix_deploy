package com.ekatra.shop_together_wix.service;

import com.ekatra.shop_together_wix.dto.WixTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class WixAuthService {

    @Value("${wix.app-id}")
    private String appId;

    @Value("${wix.app-secret}")
    private String appSecret;

    private final RestTemplate restTemplate = new RestTemplate();

    public WixTokenResponse exchangeCodeForToken(String code, String redirectUri) {
        String url = "https://www.wixapis.com/oauth2/token";

        Map<String, Object> body = new HashMap<>();
        body.put("clientId", appId);
        body.put("clientSecret", appSecret);
        body.put("grantType", "authorization_code");
        body.put("redirectUri", redirectUri);
        body.put("code", code);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<WixTokenResponse> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                request,
                WixTokenResponse.class
        );

        return response.getBody();
    }

    public void saveTokens(String instanceId, WixTokenResponse tokens) {
        // TODO: Replace this with real DB storage
        System.out.println("🔐 Saving tokens for site: " + instanceId);
        System.out.println("Access Token: " + tokens.getAccessToken());
        System.out.println("Refresh Token: " + tokens.getRefreshToken());
    }
}
