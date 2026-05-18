package com.example.demo.dominio.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "Moneda")
public class Moneda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Moneda", length = 100, nullable = false, unique = true)
    private String moneda;

    @Column(name = "Sigla", length = 5, nullable = false)
    private String sigla;

    @Column(name = "Simbolo", length = 5)
    private String simbolo;

    @Column(name = "Emisor", length = 100)
    private String emisor;

    @Column(name = "Imagen")
    private byte[] imagen;

    public Moneda() {
    }

    public Moneda(int id, String moneda, String sigla, String simbolo, String emisor, byte[] imagen) {
        this.id = id;
        this.moneda = moneda;
        this.sigla = sigla;
        this.simbolo = simbolo;
        this.emisor = emisor;
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
}
