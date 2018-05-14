package com.kyrosoft.inventory.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    @RequestMapping("/api/me")
    public Principal me(Principal principal) {
        System.out.println(principal);
        return principal;
    }
}
