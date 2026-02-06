package com.iesdoctorbalmis.spring.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.iesdoctorbalmis.spring.modelo.Tutor;
import com.iesdoctorbalmis.spring.repositories.TutorRepository;

@Service
public class TutorServicio {

    @Autowired
    TutorRepository repositorio;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public List<Tutor> findAll() {
        return repositorio.findAll();
    }

    public Tutor findById(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    public Tutor guardar(Tutor tutor) {
        tutor.setPassword(passwordEncoder.encode(tutor.getPassword()));
        return repositorio.save(tutor);
    }

    public Tutor editar(Tutor tutor) {
        return repositorio.save(tutor);
    }

    public void borrar(Tutor tutor) {
        repositorio.delete(tutor);
    }

    public Tutor findByEmail(String email){
        return repositorio.findFirstByEmail(email);
    }
}
