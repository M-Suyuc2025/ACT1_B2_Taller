package com.alejandromax.primeraPaginaThy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
public class RegisterController {

    @Autowired
    private InMemoryUserDetailsManager memoryUserDetailsManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String mostrar(){
        return "register";
    }

    @PostMapping("/register")
    public String register(
        @RequestParam String username,
        @RequestParam String password,
        Model model){

        if (memoryUserDetailsManager.userExists(username)){
            model.addAttribute("mensaje", "El Usuario Ya Existe");
            return "register";
        }

        UserDetails nuevoUsuario = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .roles("USER")
                .build();

        memoryUserDetailsManager.createUser(nuevoUsuario);

        model.addAttribute("mensaje", "Usuario Registrado Correctamente");

        memoryUserDetailsManager.createUser(nuevoUsuario);

        System.out.println("Usuario creado: " + username);
        System.out.println("Existe?: " + memoryUserDetailsManager.userExists(username));

        return "redirect:/login";
    }
}
