package Pago.de.cuentas;

import java.time.LocalDate;

public class Pagos {
    private Integer id;
    private Integer codigoTarjeta;
    private Short codigoSeguridad;
    private Short status;
    private String emailUsuario;
    private String nivelSuscripcion;
    private Double montoPagado;
    private String fechaInicio;
    private String fechaFin;

    // Constructor para nivel GRATIS (sin tarjeta)
    public Pagos(String emailUsuario, String nivelSuscripcion) {
        this.id = (int) (System.currentTimeMillis() % 1000000);
        this.emailUsuario = emailUsuario;
        this.codigoTarjeta = 0;
        this.codigoSeguridad = 0;
        this.nivelSuscripcion = nivelSuscripcion;
        this.montoPagado = 0.0;
        this.status = 1;
        this.fechaInicio = LocalDate.now().toString();
        this.fechaFin = LocalDate.now().plusMonths(1).toString();
    }

    // Constructor para niveles de pago (con tarjeta)
    public Pagos(String emailUsuario, Integer codigoTarjeta, Short codigoSeguridad,
                 String nivelSuscripcion, Double montoPagado) {
        this.id = (int) (System.currentTimeMillis() % 1000000);
        this.emailUsuario = emailUsuario;
        this.codigoTarjeta = codigoTarjeta;
        this.codigoSeguridad = codigoSeguridad;
        this.nivelSuscripcion = nivelSuscripcion;
        this.montoPagado = montoPagado;
        this.status = 1;
        this.fechaInicio = LocalDate.now().toString();
        this.fechaFin = LocalDate.now().plusMonths(1).toString();
    }

    // Getters
    public Integer getId() { return id; }
    public Integer getCodigoTarjeta() { return codigoTarjeta; }
    public Short getCodigoSeguridad() { return codigoSeguridad; }
    public Short getStatus() { return status; }
    public String getEmailUsuario() { return emailUsuario; }
    public String getNivelSuscripcion() { return nivelSuscripcion; }
    public Double getMontoPagado() { return montoPagado; }
    public String getFechaInicio() { return fechaInicio; }
    public String getFechaFin() { return fechaFin; }

    // Setters
    public void setStatus(Short status) { this.status = status; }

    // Verificar si la suscripción está vigente
    public boolean estaVigente() {
        if (status != 1) return false;
        String hoy = LocalDate.now().toString();
        return hoy.compareTo(fechaFin) <= 0;
    }

    // Mostrar información
    public void mostrarInfo() {
        System.out.println("\n=== SUSCRIPCION ACTIVA ===");
        System.out.println("Usuario: " + emailUsuario);
        System.out.println("Plan: " + nivelSuscripcion);
        System.out.println("Monto: $" + montoPagado);
        System.out.println("Vigente hasta: " + fechaFin);
        System.out.println("Estado: " + (estaVigente() ? "ACTIVA" : "EXPIRADA"));
        System.out.println("=========================");
    }
}