package com.example.demo.dominio.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Usuario", length = 100, nullable = false)
    private String usuario;

    @Column(name = "Nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "Clave", length = 100, nullable = false)
    private String clave;

    @Column(name = "Activo", nullable = false)
    private boolean activo = true;

    @Column(name = "Foto")
    private byte[] foto;

    @Column(name = "Roles", length = 100)
    private String roles;

    public Usuario() {
    }

    public Usuario(int id, String usuario, String nombre, String clave, boolean activo, byte[] foto, String roles) {
        this.id = id;
        this.usuario = usuario;
        this.nombre = nombre;
        this.clave = clave;
        this.activo = activo;
        this.foto = foto;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
