package com.iesdoctorbalmis.spring.controladores;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iesdoctorbalmis.spring.modelo.Alumno;
import com.iesdoctorbalmis.spring.servicios.AlumnoServicio;
import com.iesdoctorbalmis.spring.servicios.GrupoServicio;


@Controller
@RequestMapping("/app/alumno")
public class AlumnoController {

    @Autowired
    private AlumnoServicio alumnoServicio;

    @Autowired
    private GrupoServicio grupoServicio;


    @GetMapping({"/list", "", "/"})
    public String listado(@RequestParam(name = "q", required = false) String q, Model model) {

        List<Alumno> lista = alumnoServicio.findAll();

        if (q != null && !q.trim().isEmpty()) {
            String qLower = q.toLowerCase();
            lista = lista.stream()
                    .filter(a ->
                            (a.getNombre() != null && a.getNombre().toLowerCase().contains(qLower)) ||
                            (a.getApellidos() != null && a.getApellidos().toLowerCase().contains(qLower)) ||
                            (a.getEmail() != null && a.getEmail().toLowerCase().contains(qLower)) ||
                            (a.getGrupo() != null && a.getGrupo().toLowerCase().contains(qLower))
                    )
                    .collect(Collectors.toList());
        }

        model.addAttribute("listaAlumnos", lista);
        return "app/alumno/list";
    }

    @GetMapping("/new")
    public String nuevo(Model model) {
        model.addAttribute("alumnoForm", new Alumno());
        model.addAttribute("listaGrupos", grupoServicio.findAll());
        return "app/alumno/form";
    }

    @PostMapping("/new/submit")
    public String nuevoSubmit(@ModelAttribute("alumnoForm") Alumno alumnoForm) {
        alumnoServicio.guardar(alumnoForm);
        return "redirect:/app/alumno/list";
    }

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable Long id, Model model) {

        Alumno alumno = alumnoServicio.findById(id);
        if (alumno == null) {
            return "redirect:/app/alumno/list";
        }

        model.addAttribute("alumnoForm", alumno);
        model.addAttribute("listaGrupos", grupoServicio.findAll());
        return "app/alumno/form";
    }

    @PostMapping("/edit/submit")
    public String editarSubmit(@ModelAttribute("alumnoForm") Alumno alumnoForm) {
        alumnoServicio.editar(alumnoForm);
        return "redirect:/app/alumno/list";
    }

    @GetMapping("/del/{id}")
    public String borrar(@PathVariable Long id) {

        Alumno alumno = alumnoServicio.findById(id);
        if (alumno != null) {
            alumnoServicio.borrar(alumno);
        }

        return "redirect:/app/alumno/list";
    }
    
}
