package com.iesdoctorbalmis.spring.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iesdoctorbalmis.spring.modelo.Tutor;
import com.iesdoctorbalmis.spring.servicios.TutorServicio;

@Controller
@RequestMapping("/app/tutor")
public class TutorController {

    @Autowired
    private TutorServicio tutorServicio;

    @GetMapping({"/list", "", "/"})
    public String list(@RequestParam(name = "q", required = false) String q, Model model) {

        List<Tutor> lista = tutorServicio.findAll();

        if (q != null && !q.trim().isEmpty()) {
            String qLower = q.toLowerCase();

            lista = lista.stream().filter(t ->
                    (t.getNombre() != null && t.getNombre().toLowerCase().contains(qLower)) ||
                    (t.getApellidos() != null && t.getApellidos().toLowerCase().contains(qLower)) ||
                    (t.getEmail() != null && t.getEmail().toLowerCase().contains(qLower))
            ).toList();
        }

        model.addAttribute("listaTutores", lista);
        return "app/tutor/list"; // <-- plantilla listado
    }

    @GetMapping("/new")
    public String newTutor(Model model) {
        model.addAttribute("tutorForm", new Tutor());
        return "app/tutor/form";
    }


    @PostMapping("/new/submit")
    public String newSubmit(@ModelAttribute("tutorForm") Tutor tutorForm) {
        tutorServicio.guardar(tutorForm);
        return "redirect:/app/tutor/list";
    }

    @GetMapping("/edit/{id}")
    public String editTutor(@PathVariable Long id, Model model) {

        Tutor tutor = tutorServicio.findById(id);
        if (tutor == null) return "redirect:/app/tutor/list";

        model.addAttribute("tutorForm", tutor);
        return "app/tutor/form";
    }

    @PostMapping("/edit/submit")
    public String editSubmit(@ModelAttribute("tutorForm") Tutor tutorForm) {
        tutorServicio.editar(tutorForm);
        return "redirect:/app/tutor/list";
    }
    
    @GetMapping("/del/{id}")
    public String del(@PathVariable Long id) {

        Tutor tutor = tutorServicio.findById(id);
        if (tutor != null) tutorServicio.borrar(tutor);

        return "redirect:/app/tutor/list";
    }
}

