package Ajuste.de.rutina;

public class Ejercicio {
    private String nombre;
    private String categoria;
    private String nivel;
    private int repeticiones;
    private int series;
    private int duracionMinutos;

    public Ejercicio(String nombre, String categoria, String nivel, int repeticiones, int series, int duracionMinutos) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.nivel = nivel;
        this.repeticiones = repeticiones;
        this.series = series;
        this.duracionMinutos = duracionMinutos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    public void mostrarInfo() {
        System.out.println("Ejercicio: " + nombre);
        System.out.println("Categoría: " + categoria);
        System.out.println("Nivel: " + nivel);
        System.out.println("Repeticiones: " + repeticiones);
        System.out.println("Series: " + series);
        System.out.println("Duración: " + duracionMinutos + " min");
    }
}
