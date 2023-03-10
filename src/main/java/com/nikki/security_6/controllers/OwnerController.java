package com.nikki.security_6.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/owner")
public class OwnerController {

    @GetMapping({"", "/"})
    public String ownerMainDashboard() {
        return "Owner Main Dashboard";
    }

    @GetMapping({"/dashboard", "/dashboard/"})
    public String ownerDashboard() {
        return "Owner Dashboard";
    }
}
