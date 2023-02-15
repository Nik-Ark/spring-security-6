package com.nikki.security5.custom_auth.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/owner")
public class OwnerController {

    @GetMapping("/")
    public String ownerDashboard() {
        return "Owner Dashboard";
    }
}
