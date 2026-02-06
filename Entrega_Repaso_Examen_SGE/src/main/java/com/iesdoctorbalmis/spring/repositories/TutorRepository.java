package com.iesdoctorbalmis.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iesdoctorbalmis.spring.modelo.Tutor;

public interface TutorRepository extends JpaRepository<Tutor,Long>{
    Tutor findFirstByEmail(String email);
}
