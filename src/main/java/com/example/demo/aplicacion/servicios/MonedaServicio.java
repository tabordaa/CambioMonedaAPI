package com.example.demo.aplicacion.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dominio.entidades.Moneda;
import com.example.demo.infraestructura.repositorios.IMonedaRepositorio;

@Service
public class MonedaServicio {

    @Autowired
    private IMonedaRepositorio repositorio;

    public List<Moneda> listar() {
        return repositorio.findAll(Sort.by("moneda"));
    }

    public Moneda get(int id) {
        if (repositorio.findById(id).isPresent()) {
            return repositorio.getReferenceById(id);
        }
        return null;
    }

    public Moneda agregar(Moneda moneda) {
        moneda.setId(0);
        return repositorio.save(moneda);
    }

    public Moneda modificar(Moneda moneda) {
        if (repositorio.findById(moneda.getId()).isPresent()) {
            return repositorio.save(moneda);
        }
        return null;
    }

    public boolean eliminar(int id) {
        try {
            if (repositorio.findById(id).isPresent()) {
                repositorio.deleteById(id);
                return true;
            }
        } catch (Exception ex) {

        }
        return false;
    }

}
