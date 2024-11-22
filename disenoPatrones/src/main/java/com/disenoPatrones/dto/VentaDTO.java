package com.disenoPatrones.dto;

import java.sql.Date;

public class VentaDTO {

    private Long idVenta;
    private String nombreUsuario;
    private String nombreCliente;
    private Date fecha;
    private double montoVenta;

    // Constructor
    public VentaDTO(Long idVenta, String nombreUsuario, String nombreCliente, Date fecha, double montoVenta) {
        this.idVenta = idVenta;
        this.nombreUsuario = nombreUsuario;
        this.nombreCliente = nombreCliente;
        this.fecha = fecha;
        this.montoVenta = montoVenta;
    }

    // Getters and Setters

    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
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
}
