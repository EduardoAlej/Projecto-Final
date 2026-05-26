package Negocio.Rutinasehistorialfisico;

public class EjercicioRutina {
    private String nombreRutina;
    private String zona;
    private int repeticiones;
    private int series;
    private int duracionMinutos;

    public EjercicioRutina(String nombreRutina, String zona, int repeticiones, int series, int duracionMinutos) {
        this.nombreRutina = nombreRutina;
        this.zona = zona;
        this.repeticiones = repeticiones;
        this.series = series;
        this.duracionMinutos = duracionMinutos;
    }

    public String getNombreRutina() {
        return nombreRutina;
    }

    public void setNombreRutina(String nombreRutina) {
        this.nombreRutina = nombreRutina;
    }

    public String getZona() {
        return zona;
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

    public void mostrar() {
        System.out.println("Nombre de rutina: " + nombreRutina);
        System.out.println("Zona: " + zona);
        System.out.println("Repeticiones: " + repeticiones);
        System.out.println("Series: " + series);
        System.out.println("Duracion: " + duracionMinutos + " minutos");
    }
}
