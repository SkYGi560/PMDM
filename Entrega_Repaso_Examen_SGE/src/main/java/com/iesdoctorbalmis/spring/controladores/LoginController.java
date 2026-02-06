package com.iesdoctorbalmis.spring.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.iesdoctorbalmis.spring.modelo.Tutor; 
import com.iesdoctorbalmis.spring.servicios.TutorServicio;

@Controller
public class LoginController {

    @Autowired
    TutorServicio tutorServicio;

    @GetMapping("/auth/login")
    public String login(Model model) {
        model.addAttribute("usuario", new Tutor());
        return "login";
    }

    @PostMapping("/auth/register")
    public String register(@ModelAttribute Tutor tutor) {
        tutorServicio.guardar(tutor);
        return "redirect:/auth/login";
    }

}
