package com.example.demo.infraestructura.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dominio.entidades.Pais;

@Repository
public interface IPaisRepositorio extends JpaRepository<Pais, Integer> {

}
