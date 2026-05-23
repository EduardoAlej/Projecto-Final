package Historial.de.progreso;

import Ajuste.de.rutina.Rutinas;

public class ProgresoFisico {
    private String emailUsuario;
    private String fecha;
    private String categoriaRutina;
    private String nivelRutina;
    private int cantidadEjercicios;
    private int nivelCumplimiento;
    private String observaciones;

    public ProgresoFisico(String emailUsuario, String fecha, Rutinas rutina,
                          int nivelCumplimiento, String observaciones) {
        this.emailUsuario = emailUsuario;
        this.fecha = fecha;
        this.categoriaRutina = rutina.getCategoria();
        this.nivelRutina = rutina.getNivelUsuario();
        this.cantidadEjercicios = rutina.getEjercicios().size();
        this.nivelCumplimiento = nivelCumplimiento;
        this.observaciones = observaciones;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public String getFecha() {
        return fecha;
    }

    public String getCategoriaRutina() {
        return categoriaRutina;
    }

    public String getNivelRutina() {
        return nivelRutina;
    }

    public int getCantidadEjercicios() {
        return cantidadEjercicios;
    }

    public int getNivelCumplimiento() {
        return nivelCumplimiento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void mostrarInfo() {
        System.out.println("Fecha: " + fecha);
        System.out.println("Usuario: " + emailUsuario);
        System.out.println("Rutina: " + categoriaRutina);
        System.out.println("Nivel de rutina: " + nivelRutina);
        System.out.println("Cantidad de ejercicios: " + cantidadEjercicios);
        System.out.println("Cumplimiento: " + nivelCumplimiento + "%");
        System.out.println("Observaciones: " + observaciones);
    }
}
