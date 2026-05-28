package com.example.demo.aplicacion.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dominio.entidades.Usuario;
import com.example.demo.infraestructura.repositorios.IUsuarioRepositorio;

@Service
public class UsuarioServicio {

    @Autowired
    private IUsuarioRepositorio repositorio;

    public List<Usuario> listar() {
        return repositorio.findAll(Sort.by("usuario"));
    }

    public Usuario get(int id) {
        if (repositorio.findById(id).isPresent()) {
            return repositorio.getReferenceById(id);
        }
        return null;
    }

    public Usuario agregar(Usuario usuario) {
        usuario.setId(0);
        return repositorio.save(usuario);
    }

    public Usuario modificar(Usuario usuario) {
        if (repositorio.findById(usuario.getId()).isPresent()) {
            return repositorio.save(usuario);
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
