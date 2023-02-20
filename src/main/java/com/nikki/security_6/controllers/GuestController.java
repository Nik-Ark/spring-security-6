package com.nikki.security_6.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/guest")
public class GuestController {

    @GetMapping({"", "/"})
    public String guestMainPage() {
        return "Guest Main Page";
    }

    @GetMapping({"/welcome", "/welcome/"})
    public String guestPage() {
        return "Guest Page";
    }
}
