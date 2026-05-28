package com.example.demo.aplicacion.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dominio.entidades.CambioMoneda;
import com.example.demo.infraestructura.repositorios.ICambioMonedaRepositorio;

@Service
public class CambioMonedaServicio {

    @Autowired
    private ICambioMonedaRepositorio repositorio;

    public List<CambioMoneda> listar() {
        return repositorio.findAll(Sort.by("fecha"));
    }

    public CambioMoneda get(int id) {
        if (repositorio.findById(id).isPresent()) {
            return repositorio.getReferenceById(id);
        }
        return null;
    }

    public CambioMoneda agregar(CambioMoneda cambioMoneda) {
        cambioMoneda.setId(0);
        return repositorio.save(cambioMoneda);
    }

    public CambioMoneda modificar(CambioMoneda cambioMoneda) {
        if (repositorio.findById(cambioMoneda.getId()).isPresent()) {
            return repositorio.save(cambioMoneda);
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
