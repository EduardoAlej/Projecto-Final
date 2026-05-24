package Usuarios.y.datos.personales;

public class Datos_fisicos {
    private float altura;
    private float peso;
    private byte capacidad;
    private String genero;

    public Datos_fisicos(float altura, float peso, byte capacidad, String genero) {
        this.altura = altura;
        this.peso = peso;
        this.capacidad = capacidad;
        this.genero = genero;
    }
    public float getAltura() { return altura; }
    public float getPeso() { return peso; }
    public byte getCapacidad() { return capacidad; }
    public String getGenero() { return genero; }

    public void setAltura(float altura) { this.altura = altura; }
    public void setPeso(float peso) { this.peso = peso; }
    public void setCapacidad(byte capacidad) { this.capacidad = capacidad; }
    public void setGenero(String genero) { this.genero = genero; }

    public boolean validarAltura() {
        return (altura >= 50 && altura <= 272);
    }

    public boolean validarPeso() {
        return (peso >= 20 && peso <= 300);
    }

    public boolean validarCapacidad() {
        return (capacidad >= 1 && capacidad <= 10);
    }

    public boolean validarGenero() {
        return genero.equals("MASCULINO") || genero.equals("FEMENINO") || genero.equals("OTRO");
    }

    public float calcularIMC() {
        if (altura <= 0) return 0;
        float alturaMetros = altura / 100;
        return peso / (alturaMetros * alturaMetros);
    }

    public String getClasificacionIMC() {
        float imc = calcularIMC();
        if (imc < 18.5) return "BAJO PESO";
        if (imc < 25) return "PESO NORMAL";
        if (imc < 30) return "SOBREPESO";
        return "OBESIDAD";
    }

    public String getNivelRutina() {
        if (capacidad <= 3) return "PRINCIPIANTE";
        if (capacidad <= 7) return "INTERMEDIO";
        return "AVANZADO";
    }

    public void mostrar() {
        System.out.println("\n=== DATOS FISICOS ===");
        System.out.println("Altura: " + altura + " cm");
        System.out.println("Peso: " + peso + " kg");
        System.out.println("Genero: " + genero);
        System.out.println("Capacidad: " + capacidad + "/10");
        System.out.println("IMC: " + String.format("%.1f", calcularIMC()));
        System.out.println("Clasificacion: " + getClasificacionIMC());
        System.out.println("Nivel rutina: " + getNivelRutina());
    }
}