package Inicio.de.sesion;

import java.time.LocalDateTime;
import java.util.UUID;

public class Sesion {
    private String email;
    private String contraseña;
    private UUID idSesion;
    private LocalDateTime fechaInicio;
    private boolean activa;
    private Float peso;
    private Float estatura;
    private Byte capacidad;
    private String clasificacion;

    public Sesion(String email, String contraseña, Float peso, Float estatura,
                  Byte capacidad, String clasificacion) throws Exception {

        validarEmail(email);
        validarContraseña(contraseña);
        validarPeso(peso);
        validarEstatura(estatura);
        validarCapacidad(capacidad);
        this.email = email;
        this.contraseña = contraseña;
        this.peso = peso;
        this.estatura = estatura;
        this.capacidad = capacidad;
        this.clasificacion = clasificacion;
        this.idSesion = UUID.randomUUID();
        this.fechaInicio = LocalDateTime.now();
        this.activa = true;
    }

    private void validarEmail(String email) throws Exception {
        if (email == null || email.equals("")) {
            throw new Exception("El email no puede estar vacío");
        }

        if (!email.contains("@")) {
            throw new Exception("El email debe contener @");
        }

        int posicionArroba = email.indexOf('@');
        String dominio = email.substring(posicionArroba + 1);

        if (!dominio.contains(".")) {
            throw new Exception("El email debe tener un punto después del @ (ej: @gmail.com)");
        }

        if (email.contains(" ")) {
            throw new Exception("El email no puede tener espacios");
        }

        if (email.startsWith("@") || email.endsWith("@")) {
            throw new Exception("El email no puede empezar o terminar con @");
        }
    }

    private void validarContraseña(String contraseña) throws Exception {
        if (contraseña == null || contraseña.equals("")) {
            throw new Exception("La contraseña no puede estar vacía");
        }

        if (contraseña.length() < 6) {
            throw new Exception("La contraseña debe tener mínimo 6 caracteres");
        }

        if (contraseña.contains(" ")) {
            throw new Exception("La contraseña no puede tener espacios");
        }
    }
    private void validarPeso(Float peso) throws Exception {
        if (peso == null) {
            throw new Exception("El peso es obligatorio (en KILOGRAMOS)");
        }

        if (peso <= 0) {
            throw new Exception("El peso debe ser mayor a 0 kg");
        }

        if (peso < 20) {
            throw new Exception("El peso mínimo es 20 kg");
        }

        if (peso > 300) {
            throw new Exception("El peso máximo es 300 kg");
        }
    }

    private void validarEstatura(Float estatura) throws Exception {
        if (estatura == null) {
            throw new Exception("La estatura es obligatoria (en CENTÍMETROS)");
        }

        if (estatura <= 0) {
            throw new Exception("La estatura debe ser mayor a 0 cm");
        }

        if (estatura < 50) {
            throw new Exception("La estatura mínima es 50 cm");
        }

        if (estatura > 272) {
            throw new Exception("La estatura máxima es 272 cm");
        }

        if (estatura < 3) {
            throw new Exception("ERROR: Ingresaste " + estatura + " - La estatura debe ser en CENTÍMETROS.\n" +
                    "Si mides 1.75 metros, escribe 175");
        }
    }

    private void validarCapacidad(Byte capacidad) throws Exception {
        if (capacidad == null) {
            throw new Exception("La capacidad es obligatoria (1-10)");
        }

        if (capacidad < 1 || capacidad > 10) {
            throw new Exception("La capacidad debe ser un número entre 1 y 10");
        }
    }

    public Float calcularIMC() {
        if (peso == null || estatura == null || estatura <= 0) {
            return null;
        }
        float estaturaMetros = estatura / 100;
        return peso / (estaturaMetros * estaturaMetros);
    }

    public String getNivelDificultad() {
        if (capacidad == null) return "INTERMEDIO";

        if (capacidad <= 3) return "PRINCIPIANTE";
        if (capacidad <= 7) return "INTERMEDIO";
        return "AVANZADO";
    }

    public String[] prepararDatosParaEjercicios() {
        String[] datos = new String[6];
        datos[0] = this.email;
        datos[1] = String.valueOf(this.peso);
        datos[2] = String.valueOf(this.estatura);
        datos[3] = String.valueOf(this.capacidad);
        datos[4] = this.clasificacion;
        datos[5] = this.getNivelDificultad();

        return datos;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getContraseña() { return contraseña; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }

    public UUID getIdSesion() { return idSesion; }

    public LocalDateTime getFechaInicio() { return fechaInicio; }

    public boolean isActiva() { return activa; }
    public void setActiva(boolean activa) { this.activa = activa; }

    public Float getPeso() { return peso; }
    public void setPeso(Float peso) { this.peso = peso; }

    public Float getEstatura() { return estatura; }
    public void setEstatura(Float estatura) { this.estatura = estatura; }

    public Byte getCapacidad() { return capacidad; }
    public void setCapacidad(Byte capacidad) { this.capacidad = capacidad; }

    public String getClasificacion() { return clasificacion; }
    public void setClasificacion(String clasificacion) { this.clasificacion = clasificacion; }

    public void mostrarInfo() {
        System.out.println("== DATOS DEL USUARIO ==");
        System.out.println("Email: " + email);
        System.out.println("Peso: " + peso + " kg");
        System.out.println("Estatura: " + estatura + " cm");
        System.out.println("Capacidad: " + capacidad + "/10");
        System.out.println("Clasificación: " + clasificacion);
        System.out.println("Nivel ejercicios: " + getNivelDificultad());
        if (calcularIMC() != null) {
            System.out.println("IMC: " + String.format("%.1f", calcularIMC()));
        }
        System.out.println("========================");
    }
}