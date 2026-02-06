package com.iesdoctorbalmis.spring;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.iesdoctorbalmis.spring.modelo.Alumno;
import com.iesdoctorbalmis.spring.modelo.Grupo;
import com.iesdoctorbalmis.spring.modelo.Tutor;
import com.iesdoctorbalmis.spring.servicios.AlumnoServicio;
import com.iesdoctorbalmis.spring.servicios.GrupoServicio;
import com.iesdoctorbalmis.spring.servicios.TutorServicio;

@SpringBootApplication
public class Application {

        // Run | Debug
        public static void main(String[] args) {
                SpringApplication.run(Application.class, args);
        }

        @Bean
        public CommandLineRunner initData(
                        TutorServicio tutorServicio,
                        GrupoServicio grupoServicio,
                        AlumnoServicio alumnoServicio) {

                return args -> {
                        Tutor tutor1 = new Tutor(
                                        "Manuel",
                                        "Madrigal Losa",
                                        "manuel@iesdoctorbalmis.com",
                                        "1234");
                        tutor1 = tutorServicio.guardar(tutor1);

                        Tutor tutor2 = new Tutor(
                                        "Xuxa",
                                        "García Benavente",
                                        "xuxa@iesdoctorbalmis.com",
                                        "1234");
                        tutor2 = tutorServicio.guardar(tutor2);

                        Tutor tutor3 = new Tutor(
                                        "Vicente",
                                        "Martínez Martínez",
                                        "vicente@iesdoctorbalmis.com",
                                        "1234");
                        tutor3 = tutorServicio.guardar(tutor3);

                        Grupo grupo1 = new Grupo(
                                        "2º DAM A",
                                        "Desarrollo de Aplicaciones Multiplataforma",
                                        "tutor1");
                        grupo1 = grupoServicio.guardar(grupo1);

                        Grupo grupo2 = new Grupo(
                                        "2º DAM B",
                                        "Desarrollo de Aplicaciones Multiplataforma",
                                        "tutor2");
                        grupo2 = grupoServicio.guardar(grupo2);

                        Grupo grupo3 = new Grupo(
                                        "2º DAM D",
                                        "Desarrollo de Aplicaciones Multiplataforma",
                                        "tutor3");
                        grupo3 = grupoServicio.guardar(grupo3);

                        List<Alumno> alumnos = Arrays.asList(

                                        new Alumno(
                                                        "",
                                                        "Carmen",
                                                        "Flores Mayo",
                                                        "carmen.flores@iesdoctorbalmis.com",
                                                        "grupo3"),
                                        new Alumno(
                                                        "",
                                                        "Ana",
                                                        "Molino Maya",
                                                        "ana.molino@iesdoctorbalmis.com",
                                                        "grupo2"),
                                        new Alumno(
                                                        "",
                                                        "Marta",
                                                        "Martín Torres",
                                                        "marta.martin@iesdoctorbalmis.com",
                                                        "grupo1"),
                                        new Alumno(
                                                        "",
                                                        "Diego",
                                                        "Solís Velasco",
                                                        "diego.solis@iesdoctorbalmis.com",
                                                        "grupo3"),
                                        new Alumno(
                                                        "",
                                                        "Sara",
                                                        "Sánchez Rodríguez",
                                                        "sara.sanchez@iesdoctorbalmis.com",
                                                        "grupo2"),
                                        new Alumno(
                                                        "",
                                                        "Paula",
                                                        "Torres Molina",
                                                        "paula.torres@iesdoctorbalmis.com",
                                                        "grupo3"),
                                        new Alumno(
                                                        "",
                                                        "María",
                                                        "Álvarez Sánchez",
                                                        "maria.alvarez@iesdoctorbalmis.com",
                                                        "grupo3"));

                        alumnos.forEach(alumnoServicio::guardar);
                };
        }

}
