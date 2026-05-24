package Rutinas.e.historial.fisico;

import Nivel.membresías.y.pagos.membresias;
import Usuarios.y.datos.personales.Datos_fisicos;
import Usuarios.y.datos.personales.Datos_sesion;

import java.util.ArrayList;

public class AjustarNivelRutina {
    private ArrayList<EjercicioRutina> rutinaActual;

    public AjustarNivelRutina() {
        rutinaActual = new ArrayList<>();
    }

    public String generarRutina(Datos_sesion sesion, membresias membresia, String zona) {
        rutinaActual.clear();

        if (!sesion.haySesionActiva()) {
            return "ERROR: No hay una sesion activa.";
        }

        Datos_fisicos datos = sesion.getDatosFisicosActual();
        if (datos == null) {
            return "ERROR: El usuario no tiene datos fisicos vinculados.";
        }

        String nivel = datos.getNivelRutina();
        boolean premium = membresia.tieneAccesoPremium();

        if (zona.equalsIgnoreCase("piernas")) {
            cargarPiernas(nivel, premium);
        } else if (zona.equalsIgnoreCase("hombros")) {
            cargarHombros(nivel, premium);
        } else if (zona.equalsIgnoreCase("pecho")) {
            cargarPecho(nivel, premium);
        } else if (zona.equalsIgnoreCase("espalda")) {
            cargarEspalda(nivel, premium);
        } else if (zona.equalsIgnoreCase("brazos")) {
            cargarBrazos(nivel, premium);
        } else {
            return "ERROR: Zona no valida. Use piernas, hombros, pecho, espalda o brazos.";
        }

        return "EXITO: Rutina generada correctamente para " + zona + ".";
    }

    private void cargarPiernas(String nivel, boolean premium) {
        if (nivel.equals("PRINCIPIANTE")) {
            rutinaActual.add(new EjercicioRutina("Sentadillas", "Piernas", 12, 3, 10));
            rutinaActual.add(new EjercicioRutina("Zancadas", "Piernas", 10, 3, 8));
        } else if (nivel.equals("INTERMEDIO")) {
            rutinaActual.add(new EjercicioRutina("Sentadillas con salto", "Piernas", 15, 4, 12));
            rutinaActual.add(new EjercicioRutina("Desplantes", "Piernas", 12, 4, 10));
        } else {
            rutinaActual.add(new EjercicioRutina("Sentadilla bulgara", "Piernas", 18, 5, 15));
            rutinaActual.add(new EjercicioRutina("Jump squats", "Piernas", 15, 5, 12));
        }

        if (premium) {
            rutinaActual.add(new EjercicioRutina("Peso muerto unilateral", "Piernas", 12, 4, 10));
        }
    }

    private void cargarHombros(String nivel, boolean premium) {
        if (nivel.equals("PRINCIPIANTE")) {
            rutinaActual.add(new EjercicioRutina("Elevaciones laterales", "Hombros", 10, 3, 8));
            rutinaActual.add(new EjercicioRutina("Rotacion de hombros", "Hombros", 12, 2, 6));
        } else if (nivel.equals("INTERMEDIO")) {
            rutinaActual.add(new EjercicioRutina("Press militar", "Hombros", 12, 4, 10));
            rutinaActual.add(new EjercicioRutina("Elevaciones frontales", "Hombros", 12, 4, 8));
        } else {
            rutinaActual.add(new EjercicioRutina("Press Arnold", "Hombros", 15, 5, 12));
            rutinaActual.add(new EjercicioRutina("Elevaciones lentas", "Hombros", 15, 5, 10));
        }

        if (premium) {
            rutinaActual.add(new EjercicioRutina("Face pull", "Hombros", 12, 4, 8));
        }
    }

    private void cargarPecho(String nivel, boolean premium) {
        if (nivel.equals("PRINCIPIANTE")) {
            rutinaActual.add(new EjercicioRutina("Flexiones con apoyo", "Pecho", 10, 3, 8));
            rutinaActual.add(new EjercicioRutina("Aperturas basicas", "Pecho", 12, 3, 8));
        } else if (nivel.equals("INTERMEDIO")) {
            rutinaActual.add(new EjercicioRutina("Flexiones tradicionales", "Pecho", 15, 4, 10));
            rutinaActual.add(new EjercicioRutina("Aperturas controladas", "Pecho", 12, 4, 10));
        } else {
            rutinaActual.add(new EjercicioRutina("Flexiones explosivas", "Pecho", 18, 5, 12));
            rutinaActual.add(new EjercicioRutina("Press inclinado", "Pecho", 15, 5, 10));
        }

        if (premium) {
            rutinaActual.add(new EjercicioRutina("Flexiones diamante", "Pecho", 12, 4, 8));
        }
    }

    private void cargarEspalda(String nivel, boolean premium) {
        if (nivel.equals("PRINCIPIANTE")) {
            rutinaActual.add(new EjercicioRutina("Superman", "Espalda", 12, 3, 8));
            rutinaActual.add(new EjercicioRutina("Remo basico", "Espalda", 10, 3, 8));
        } else if (nivel.equals("INTERMEDIO")) {
            rutinaActual.add(new EjercicioRutina("Remo inclinado", "Espalda", 12, 4, 10));
            rutinaActual.add(new EjercicioRutina("Superman extendido", "Espalda", 15, 4, 10));
        } else {
            rutinaActual.add(new EjercicioRutina("Dominadas asistidas", "Espalda", 10, 5, 12));
            rutinaActual.add(new EjercicioRutina("Remo avanzado", "Espalda", 15, 5, 12));
        }

        if (premium) {
            rutinaActual.add(new EjercicioRutina("Pullover", "Espalda", 12, 4, 8));
        }
    }

    private void cargarBrazos(String nivel, boolean premium) {
        if (nivel.equals("PRINCIPIANTE")) {
            rutinaActual.add(new EjercicioRutina("Curl de biceps", "Brazos", 10, 3, 8));
            rutinaActual.add(new EjercicioRutina("Fondos en banco", "Brazos", 8, 3, 8));
        } else if (nivel.equals("INTERMEDIO")) {
            rutinaActual.add(new EjercicioRutina("Curl martillo", "Brazos", 12, 4, 10));
            rutinaActual.add(new EjercicioRutina("Extension de triceps", "Brazos", 12, 4, 8));
        } else {
            rutinaActual.add(new EjercicioRutina("Curl concentrado", "Brazos", 15, 5, 10));
            rutinaActual.add(new EjercicioRutina("Fondos avanzados", "Brazos", 12, 5, 10));
        }

        if (premium) {
            rutinaActual.add(new EjercicioRutina("Curl 21", "Brazos", 21, 3, 10));
        }
    }

    public void mostrarRutinaActual(Datos_sesion sesion) {
        if (rutinaActual.isEmpty()) {
            System.out.println("No hay rutina generada.");
            return;
        }

        Datos_fisicos datos = sesion.getDatosFisicosActual();

        System.out.println("\n=== RUTINA ACTUAL ===");
        System.out.println("Usuario: " + sesion.getNombreActual());
        System.out.println("Email: " + sesion.getEmailActual());

        if (datos != null) {
            System.out.println("Genero: " + datos.getGenero());
            System.out.println("Nivel de rutina: " + datos.getNivelRutina());
        }

        for (int i = 0; i < rutinaActual.size(); i++) {
            rutinaActual.get(i).mostrar();
        }
    }

    public String ajustarRutina(membresias membresia,
                                int porcentajeCumplimiento,
                                String dificultadPercibida,
                                int tiempoQueTardo,
                                String observaciones) {

        if (rutinaActual.isEmpty()) {
            return "ERROR: Primero debe generarse una rutina.";
        }

        if (!membresia.tieneAccesoPremium()) {
            return "ERROR: Solo la membresia Premium permite ajuste automatico.";
        }

        if (porcentajeCumplimiento < 0 || porcentajeCumplimiento > 100) {
            return "ERROR: El porcentaje de cumplimiento debe estar entre 0 y 100.";
        }

        boolean subir = false;
        boolean bajar = false;

        if (porcentajeCumplimiento >= 80 &&
                dificultadPercibida.equalsIgnoreCase("facil") &&
                !observaciones.equalsIgnoreCase("cansancio")) {
            subir = true;
        } else if (porcentajeCumplimiento < 50 ||
                dificultadPercibida.equalsIgnoreCase("dificil") ||
                observaciones.equalsIgnoreCase("cansancio")) {
            bajar = true;
        }

        for (int i = 0; i < rutinaActual.size(); i++) {
            EjercicioRutina e = rutinaActual.get(i);

            if (subir) {
                e.setRepeticiones(e.getRepeticiones() + 2);
                e.setSeries(e.getSeries() + 1);
                e.setDuracionMinutos(e.getDuracionMinutos() + 5);
            } else if (bajar) {
                if (e.getRepeticiones() > 2) {
                    e.setRepeticiones(e.getRepeticiones() - 2);
                }
                if (e.getSeries() > 1) {
                    e.setSeries(e.getSeries() - 1);
                }
                if (e.getDuracionMinutos() > 5) {
                    e.setDuracionMinutos(e.getDuracionMinutos() - 5);
                }
            }
        }

        if (subir) {
            return "EXITO: La rutina fue ajustada a un nivel mas exigente.";
        } else if (bajar) {
            return "EXITO: La rutina fue ajustada a un nivel mas ligero.";
        } else {
            return "La rutina se mantiene igual. Tiempo registrado: " + tiempoQueTardo + " minutos.";
        }
    }

    public String getZonaActual() {
        if (rutinaActual.isEmpty()) {
            return "SIN RUTINA";
        }
        return rutinaActual.get(0).getZona();
    }
}
