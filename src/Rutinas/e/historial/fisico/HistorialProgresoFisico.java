package Rutinas.e.historial.fisico;

import Usuarios.y.datos.personales.Datos_sesion;

import java.util.ArrayList;

public class HistorialProgresoFisico {
    private ArrayList<RegistroHistorial> historial;

    public HistorialProgresoFisico() {
        historial = new ArrayList<>();
    }

    public String registrarHistorial(Datos_sesion sesion, AjustarNivelRutina ajusteRutina,
                                     String fecha, boolean cumplioObjetivo) {

        if (!sesion.haySesionActiva()) {
            return "ERROR: No hay sesion activa.";
        }

        String zona = ajusteRutina.getZonaActual();
        if (zona.equals("SIN RUTINA")) {
            return "ERROR: No hay rutina generada.";
        }

        RegistroHistorial nuevo = new RegistroHistorial(
                sesion.getEmailActual(),
                fecha,
                zona,
                cumplioObjetivo
        );

        historial.add(nuevo);
        return "EXITO: Historial guardado correctamente.";
    }

    public void mostrarHistorialUsuario(String emailUsuario) {
        boolean encontrado = false;

        System.out.println("\n=== HISTORIAL DE PROGRESO FISICO ===");
        for (int i = 0; i < historial.size(); i++) {
            RegistroHistorial r = historial.get(i);

            if (r.getEmailUsuario().equalsIgnoreCase(emailUsuario)) {
                r.mostrar();
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No hay registros para este usuario.");
        }
    }

    private class RegistroHistorial {
        private String emailUsuario;
        private String fecha;
        private String zonaRutina;
        private boolean cumplioObjetivo;

        public RegistroHistorial(String emailUsuario, String fecha, String zonaRutina, boolean cumplioObjetivo) {
            this.emailUsuario = emailUsuario;
            this.fecha = fecha;
            this.zonaRutina = zonaRutina;
            this.cumplioObjetivo = cumplioObjetivo;
        }

        public String getEmailUsuario() {
            return emailUsuario;
        }

        public void mostrar() {
            System.out.println("Fecha: " + fecha);
            System.out.println("Usuario: " + emailUsuario);
            System.out.println("Rutina: " + zonaRutina);
            System.out.println("Resultado: " + (cumplioObjetivo ? "Cumplio" : "No cumplio"));
        }
    }
}
