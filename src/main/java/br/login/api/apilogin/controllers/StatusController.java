package br.login.api.apilogin.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ws/home")
public class StatusController {

    @GetMapping({"", "/"})
    public String getStatus() {
        var user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "Servidor está no ar!!!";
    }

}