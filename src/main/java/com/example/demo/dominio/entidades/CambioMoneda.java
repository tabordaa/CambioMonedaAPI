package com.example.demo.dominio.entidades;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CambioMoneda", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"IdMoneda", "Fecha"})
})
public class CambioMoneda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "IdMoneda", nullable = false)
    private Moneda moneda;

    @Column(name = "Fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Column(name = "Cambio", nullable = false)
    private float cambio;

    public CambioMoneda() {
    }

    public CambioMoneda(int id, Moneda moneda, Date fecha, float cambio) {
        this.id = id;
        this.moneda = moneda;
        this.fecha = fecha;
        this.cambio = cambio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getCambio() {
        return cambio;
    }

    public void setCambio(float cambio) {
        this.cambio = cambio;
    }
}
