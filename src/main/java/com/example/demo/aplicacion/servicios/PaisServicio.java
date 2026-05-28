package com.example.demo.aplicacion.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dominio.entidades.Pais;
import com.example.demo.infraestructura.repositorios.IPaisRepositorio;

@Service
public class PaisServicio {

    @Autowired
    private IPaisRepositorio repositorio;

    public List<Pais> listar() {
        return repositorio.findAll(Sort.by("pais"));
    }

    public Pais get(int id) {
        if (repositorio.findById(id).isPresent()) {
            return repositorio.getReferenceById(id);
        }
        return null;
    }

    public Pais agregar(Pais pais) {
        pais.setId(0);
        return repositorio.save(pais);
    }

    public Pais modificar(Pais pais) {
        if (repositorio.findById(pais.getId()).isPresent()) {
            return repositorio.save(pais);
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
