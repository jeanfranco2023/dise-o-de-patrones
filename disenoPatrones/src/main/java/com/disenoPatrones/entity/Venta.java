package com.disenoPatrones.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "venta")
public class Venta {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;

    @ManyToOne
    @JoinColumn(name = "usuario_id")  // FK hacia la tabla Usuario
    private usuario usuario;

    @ManyToOne
    @JoinColumn(name = "cliente_id")  // FK hacia la tabla Cliente
    private cliente cliente;

    @OneToMany(mappedBy = "venta")
    private List<DetalleVenta> detalles;

    private Date fecha;
    private double montoVenta;
    private String nombreUsuario;

    public Venta() {
    }

    public Venta(Long idVenta, usuario usuario, cliente cliente, List<DetalleVenta> detalles, Date fecha, double montoVenta, String nombreUsuario) {
        this.idVenta = idVenta;
        this.usuario = usuario;
        this.cliente = cliente;
        this.detalles = detalles;
        this.fecha = fecha;
        this.montoVenta = montoVenta;
        this.nombreUsuario = nombreUsuario;
    }

    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(usuario usuario) {
        this.usuario = usuario;
    }

    public cliente getCliente() {
        return cliente;
    }

    public void setCliente(cliente cliente) {
        this.cliente = cliente;
    }

    public List<DetalleVenta> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVenta> detalles) {
        this.detalles = detalles;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getMontoVenta() {
        return montoVenta;
    }

    public void setMontoVenta(double montoVenta) {
        this.montoVenta = montoVenta;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }


}