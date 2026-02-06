package com.iesdoctorbalmis.spring.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iesdoctorbalmis.spring.modelo.Grupo;
import com.iesdoctorbalmis.spring.repositories.GrupoRepository;

@Service
public class GrupoServicio {
    
    @Autowired
    GrupoRepository repositorio;

    public List<Grupo> findAll() {
        return repositorio.findAll();
    }

    public Grupo findById(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    public Grupo guardar(Grupo grupo) {
        return repositorio.save(grupo);
    }

    public Grupo editar(Grupo grupo) {
        return repositorio.save(grupo);
    }

    public void borrar(Grupo grupo) {
        repositorio.delete(grupo);
    }
}
