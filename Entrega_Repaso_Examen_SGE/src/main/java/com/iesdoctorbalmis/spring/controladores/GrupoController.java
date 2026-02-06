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

import com.iesdoctorbalmis.spring.modelo.Grupo;
import com.iesdoctorbalmis.spring.servicios.GrupoServicio;
import com.iesdoctorbalmis.spring.servicios.TutorServicio;

@Controller
@RequestMapping("/app/grupo")
public class GrupoController {

    @Autowired
    private GrupoServicio grupoServicio;

    @Autowired
    private TutorServicio tutorServicio;

    @GetMapping({"/list", "", "/"})
    public String list(@RequestParam(name = "q", required = false) String q, Model model) {

        List<Grupo> lista = grupoServicio.findAll();

        if (q != null && !q.trim().isEmpty()) {
            String qLower = q.toLowerCase();

            lista = lista.stream().filter(g ->
                    (g.getGrupo() != null && g.getGrupo().toLowerCase().contains(qLower)) ||
                    (g.getCiclo() != null && g.getCiclo().toLowerCase().contains(qLower)) ||
                    (g.getTutor() != null && g.getTutor().toLowerCase().contains(qLower))
            ).toList();
        }

        model.addAttribute("listaGrupos", lista);
        return "app/grupo/list";
    }

    @GetMapping("/new")
    public String newGrupo(Model model) {
        model.addAttribute("grupoForm", new Grupo());
        model.addAttribute("listaTutores", tutorServicio.findAll());
        return "app/grupo/form";
    }

    @PostMapping("/new/submit")
    public String newSubmit(@ModelAttribute("grupoForm") Grupo grupoForm) {
        grupoServicio.guardar(grupoForm);
        return "redirect:/app/grupo/list";
    }

    @GetMapping("/edit/{id}")
    public String editGrupo(@PathVariable Long id, Model model) {

        Grupo grupo = grupoServicio.findById(id);
        if (grupo == null) return "redirect:/app/grupo/list";

        model.addAttribute("grupoForm", grupo);
        model.addAttribute("listaTutores", tutorServicio.findAll());
        return "app/grupo/form";
    }

    @PostMapping("/edit/submit")
    public String editSubmit(@ModelAttribute("grupoForm") Grupo grupoForm) {
        grupoServicio.editar(grupoForm);
        return "redirect:/app/grupo/list";
    }

    @GetMapping("/del/{id}")
    public String del(@PathVariable Long id) {

        Grupo grupo = grupoServicio.findById(id);
        if (grupo != null) grupoServicio.borrar(grupo);

        return "redirect:/app/grupo/list";
    }
}

