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

import com.example.demo.aplicacion.servicios.CambioMonedaServicio;
import com.example.demo.dominio.entidades.CambioMoneda;

@RestController
@RequestMapping("/api/cambiomonedas")
public class CambioMonedaControlador {

    @Autowired
    private CambioMonedaServicio servicio;

    @GetMapping("/")
    public List<CambioMoneda> listar() {
        return servicio.listar();
    }

    @GetMapping("/{id}")
    public CambioMoneda get(@PathVariable int id) {
        return servicio.get(id);
    }

    @PostMapping("/")
    public CambioMoneda agregar(@RequestBody CambioMoneda cambioMoneda) {
        return servicio.agregar(cambioMoneda);
    }

    @PutMapping("/")
    public CambioMoneda modificar(@RequestBody CambioMoneda cambioMoneda) {
        return servicio.modificar(cambioMoneda);
    }

    @DeleteMapping("/{id}")
    public boolean eliminar(@PathVariable int id) {
        return servicio.eliminar(id);
    }

}
