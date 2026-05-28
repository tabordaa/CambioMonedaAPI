package com.example.demo.presentacion.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.aplicacion.servicios.PaisServicio;
import com.example.demo.dominio.entidades.Pais;

@RestController
@RequestMapping("/api/paises")
public class PaisControlador {

    @Autowired
    private PaisServicio servicio;

    @GetMapping("/")
    public List<Pais> listar() {
        return servicio.listar();
    }

    @GetMapping("/{id}")
    public Pais get(@PathVariable int id) {
        return servicio.get(id);
    }

    @PostMapping("/")
    public Pais agregar(@RequestBody Pais pais) {
        return servicio.agregar(pais);
    }

    @PutMapping("/")
    public Pais modificar(@RequestBody Pais pais) {
        return servicio.modificar(pais);
    }

    @DeleteMapping("/{id}")
    public boolean eliminar(@PathVariable int id) {
        return servicio.eliminar(id);
    }

}
