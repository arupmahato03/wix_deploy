package com.ekatra.shop_together_wix.controller;

import com.ekatra.shop_together_wix.dto.WixTokenResponse;
import com.ekatra.shop_together_wix.service.WixAuthService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/wix")
public class WixOAuthController {

    @Value("${wix.app-id}")
    private String appId;

    @Value("${wix.app-secret}")
    private String appSecret;

    @Value("${wix.redirect-uri}")
    private String redirectUri;

    private final WixAuthService wixAuthService;

    public WixOAuthController(WixAuthService wixAuthService) {
        this.wixAuthService = wixAuthService;
    }

    @GetMapping("/install")
    public void handleInstall(@RequestParam("token") String token, HttpServletResponse response) throws IOException {
        // Step 1: Redirect to Wix Installer
        String installRedirect = "https://www.wix.com/installer/install?token=" + token +
                "&appId=" + appId +
                "&redirectUrl=" + URLEncoder.encode(redirectUri, StandardCharsets.UTF_8);
        response.sendRedirect(installRedirect);
    }

    @GetMapping("/callback")
    public void handleCallback(@RequestParam("code") String code,
                               @RequestParam("instanceId") String instanceId,
                               HttpServletResponse response) throws IOException {
        // Step 2: Exchange code for tokens
        WixTokenResponse tokenResponse = wixAuthService.exchangeCodeForToken(code, redirectUri);

        // Step 3: Save tokens to DB (here simulated)
        wixAuthService.saveTokens(instanceId, tokenResponse);

        // Step 4: Redirect to your onboarding UI
        String onboardingUrl = "https://your-platform.com/onboarding?siteId=" + instanceId;
        response.sendRedirect(onboardingUrl);
    }
}
