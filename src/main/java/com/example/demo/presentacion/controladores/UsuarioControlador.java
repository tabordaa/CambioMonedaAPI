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

import com.example.demo.aplicacion.servicios.UsuarioServicio;
import com.example.demo.dominio.entidades.Usuario;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio servicio;

    @GetMapping("/")
    public List<Usuario> listar() {
        return servicio.listar();
    }

    @GetMapping("/{id}")
    public Usuario get(@PathVariable int id) {
        return servicio.get(id);
    }

    @PostMapping("/")
    public Usuario agregar(@RequestBody Usuario usuario) {
        return servicio.agregar(usuario);
    }

    @PutMapping("/")
    public Usuario modificar(@RequestBody Usuario usuario) {
        return servicio.modificar(usuario);
    }

    @DeleteMapping("/{id}")
    public boolean eliminar(@PathVariable int id) {
        return servicio.eliminar(id);
    }

}
