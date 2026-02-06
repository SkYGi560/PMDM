package com.iesdoctorbalmis.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iesdoctorbalmis.spring.modelo.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno,Long>{
    
}
