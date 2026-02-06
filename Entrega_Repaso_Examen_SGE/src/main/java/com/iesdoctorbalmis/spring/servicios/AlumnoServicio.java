package com.iesdoctorbalmis.spring.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iesdoctorbalmis.spring.modelo.Alumno;
import com.iesdoctorbalmis.spring.repositories.AlumnoRepository;

@Service
public class AlumnoServicio {
    @Autowired
    AlumnoRepository repositorio;

    public List<Alumno> findAll(){
        return repositorio.findAll();
    }

    public Alumno findById(Long id){
        return repositorio.findById(id).orElse(null);
    }
    
    public Alumno guardar(Alumno alumno){
        return repositorio.save(alumno);
    }

    public Alumno editar(Alumno alumno){
        return repositorio.save(alumno);
    }

    public void borrar(Alumno alumno){
        repositorio.delete(alumno);
    }
}
