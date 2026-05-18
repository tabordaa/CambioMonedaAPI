package com.example.demo.infraestructura.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dominio.entidades.Moneda;

@Repository
public interface IMonedaRepositorio extends JpaRepository<Moneda, Integer> {

}
