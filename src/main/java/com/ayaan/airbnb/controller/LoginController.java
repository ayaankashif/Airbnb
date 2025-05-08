package com.ayaan.airbnb.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class LoginController {

    @GetMapping("/login")
    public String getLoginPage() {
        return "Login"; // Return the name of the login view (e.g., "login.html")
    }

}
