package Ajuste.de.rutina;

import Inicio.de.sesion.Sesion;
import Pago.de.cuentas.ServicioPagos;
import java.util.ArrayList;

public class AjusteRutina {
    public void ajustarRutina(Rutinas rutina, Sesion sesion, ServicioPagos servicioPagos,
                              int porcentajeCumplimiento, String dificultadPercibida, String observaciones) {

        if (rutina == null) {
            System.out.println("No existe una rutina para ajustar.");
            return;
        }
        String emailUsuario = sesion.getEmail();
        String nivelSuscripcion = servicioPagos.getNivelUsuario(emailUsuario);
        if (!nivelSuscripcion.equalsIgnoreCase("PREMIUM")) {
            System.out.println("Solo los usuarios PREMIUM pueden usar ajuste automatico de rutina.");
            return;
        }
        ArrayList<Ejercicio> ejercicios = rutina.getEjercicios();
        if (porcentajeCumplimiento >= 80 &&
                dificultadPercibida.equalsIgnoreCase("facil") &&
                !observaciones.equalsIgnoreCase("cansancio")) {
            subirNivel(rutina);
            for (int i = 0; i < ejercicios.size(); i++) {
                Ejercicio e = ejercicios.get(i);
                e.setRepeticiones(e.getRepeticiones() + 2);
                e.setSeries(e.getSeries() + 1);
                e.setDuracionMinutos(e.getDuracionMinutos() + 5);
                e.setNivel(rutina.getNivelUsuario());
            }
            System.out.println("La rutina fue ajustada a una dificultad mayor.");
        } else if (porcentajeCumplimiento < 50 ||
                dificultadPercibida.equalsIgnoreCase("dificil") ||
                observaciones.equalsIgnoreCase("cansancio")) {
            bajarNivel(rutina);
            for (int i = 0; i < ejercicios.size(); i++) {
                Ejercicio e = ejercicios.get(i);
                if (e.getRepeticiones() > 2) {
                    e.setRepeticiones(e.getRepeticiones() - 2);
                }

                if (e.getSeries() > 1) {
                    e.setSeries(e.getSeries() - 1);
                }

                if (e.getDuracionMinutos() > 5) {
                    e.setDuracionMinutos(e.getDuracionMinutos() - 5);
                }

                e.setNivel(rutina.getNivelUsuario());
            }

            System.out.println("La rutina fue ajustada a una dificultad menor.");

        } else {
            System.out.println("La rutina se mantiene igual.");
        }
    }

    private void subirNivel(Rutinas rutina) {
        if (rutina.getNivelUsuario().equalsIgnoreCase("PRINCIPIANTE")) {
            rutina.setNivelUsuario("INTERMEDIO");
        } else if (rutina.getNivelUsuario().equalsIgnoreCase("INTERMEDIO")) {
            rutina.setNivelUsuario("AVANZADO");
        }
    }

    private void bajarNivel(Rutinas rutina) {
        if (rutina.getNivelUsuario().equalsIgnoreCase("AVANZADO")) {
            rutina.setNivelUsuario("INTERMEDIO");
        } else if (rutina.getNivelUsuario().equalsIgnoreCase("INTERMEDIO")) {
            rutina.setNivelUsuario("PRINCIPIANTE");
        }
    }
}