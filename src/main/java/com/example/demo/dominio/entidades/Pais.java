package com.example.demo.dominio.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "Pais")
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Pais", length = 50, nullable = false, unique = true)
    private String pais;

    @Column(name = "CodigoAlfa2", length = 5, nullable = false)
    private String codigoAlfa2;

    @Column(name = "CodigoAlfa3", length = 5, nullable = false)
    private String codigoAlfa3;

    @ManyToOne
    @JoinColumn(name = "IdMoneda", nullable = false)
    private Moneda moneda;

    @Column(name = "Mapa")
    private byte[] mapa;

    @Column(name = "Bandera")
    private byte[] bandera;

    public Pais() {
    }

    public Pais(int id, String pais, String codigoAlfa2, String codigoAlfa3, Moneda moneda, byte[] mapa, byte[] bandera) {
        this.id = id;
        this.pais = pais;
        this.codigoAlfa2 = codigoAlfa2;
        this.codigoAlfa3 = codigoAlfa3;
        this.moneda = moneda;
        this.mapa = mapa;
        this.bandera = bandera;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCodigoAlfa2() {
        return codigoAlfa2;
    }

    public void setCodigoAlfa2(String codigoAlfa2) {
        this.codigoAlfa2 = codigoAlfa2;
    }

    public String getCodigoAlfa3() {
        return codigoAlfa3;
    }

    public void setCodigoAlfa3(String codigoAlfa3) {
        this.codigoAlfa3 = codigoAlfa3;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public byte[] getMapa() {
        return mapa;
    }

    public void setMapa(byte[] mapa) {
        this.mapa = mapa;
    }

    public byte[] getBandera() {
        return bandera;
    }

    public void setBandera(byte[] bandera) {
        this.bandera = bandera;
    }
}
