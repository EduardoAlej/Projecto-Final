package Ajuste.de.rutina;

import java.util.ArrayList;

public class Rutinas {
    private String emailUsuario;
    private String categoria;
    private String nivelUsuario;
    private String nivelSuscripcion;
    private ArrayList<Ejercicio> ejercicios;

    public Rutinas(String emailUsuario, String categoria, String nivelUsuario, String nivelSuscripcion) {
        this.emailUsuario = emailUsuario;
        this.categoria = categoria;
        this.nivelUsuario = nivelUsuario;
        this.nivelSuscripcion = nivelSuscripcion;
        this.ejercicios = new ArrayList<>();
    }

    public void agregarEjercicio(Ejercicio ejercicio) {
        ejercicios.add(ejercicio);
    }

    public ArrayList<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getNivelUsuario() {
        return nivelUsuario;
    }

    public void setNivelUsuario(String nivelUsuario) {
        this.nivelUsuario = nivelUsuario;
    }

    public String getNivelSuscripcion() {
        return nivelSuscripcion;
    }

    public void mostrarRutina() {
        System.out.println("=== RUTINA GENERADA ===");
        System.out.println("Usuario: " + emailUsuario);
        System.out.println("Categoría: " + categoria);
        System.out.println("Nivel: " + nivelUsuario);
        System.out.println("Suscripción: " + nivelSuscripcion);

        for (int i = 0; i < ejercicios.size(); i++) {
            ejercicios.get(i).mostrarInfo();
        }
    }
}