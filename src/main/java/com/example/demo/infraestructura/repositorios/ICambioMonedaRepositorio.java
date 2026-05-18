package com.example.demo.infraestructura.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dominio.entidades.CambioMoneda;

@Repository
public interface ICambioMonedaRepositorio extends JpaRepository<CambioMoneda, Integer> {

}
