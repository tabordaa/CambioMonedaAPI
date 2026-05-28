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

import com.example.demo.aplicacion.servicios.MonedaServicio;
import com.example.demo.dominio.entidades.Moneda;

@RestController
@RequestMapping("/api/monedas")
public class MonedaControlador {

    @Autowired
    private MonedaServicio servicio;

    @GetMapping("/")
    public List<Moneda> listar() {
        return servicio.listar();
    }

    @GetMapping("/{id}")
    public Moneda get(@PathVariable int id) {
        return servicio.get(id);
    }

    @PostMapping("/")
    public Moneda agregar(@RequestBody Moneda moneda) {
        return servicio.agregar(moneda);
    }

    @PutMapping("/")
    public Moneda modificar(@RequestBody Moneda moneda) {
        return servicio.modificar(moneda);
    }

    @DeleteMapping("/{id}")
    public boolean eliminar(@PathVariable int id) {
        return servicio.eliminar(id);
    }

}
