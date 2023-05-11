package com.gestion.empleados.controlador;

import org.springframework.web.bind.annotation.GetMapping;

public class Main {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String home() {
        return "listar";
    }
}
