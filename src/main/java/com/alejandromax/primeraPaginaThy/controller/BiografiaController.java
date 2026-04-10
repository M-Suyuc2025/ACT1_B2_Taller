package com.alejandromax.primeraPaginaThy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BiografiaController {

    @GetMapping("/bio")
    public String mostrarBiografia(Model model){
        return "bio";
    }
}
