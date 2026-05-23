package Ajuste.de.rutina;

import Inicio.de.sesion.Sesion;
import Pago.de.cuentas.ServicioPagos;

public class ServicioRutinas {
    public Rutinas generarRutina(Sesion sesion, ServicioPagos servicioPagos, String categoria) {
        String emailUsuario = sesion.getEmail();
        String nivelUsuario = sesion.getNivelDificultad();
        String nivelSuscripcion = servicioPagos.getNivelUsuario(emailUsuario);

        Rutinas rutina = new Rutinas(emailUsuario, categoria, nivelUsuario, nivelSuscripcion);

        if (categoria.equalsIgnoreCase("piernas")) {
            cargarPiernas(rutina);
        } else if (categoria.equalsIgnoreCase("hombros")) {
            cargarHombros(rutina);
        } else if (categoria.equalsIgnoreCase("pecho")) {
            cargarPecho(rutina);
        } else if (categoria.equalsIgnoreCase("espalda")) {
            cargarEspalda(rutina);
        } else if (categoria.equalsIgnoreCase("brazos")) {
            cargarBrazos(rutina);
        } else {
            cargarBasica(rutina);
        }

        return rutina;
    }

    private void cargarPiernas(Rutinas rutina) {
        String nivel = rutina.getNivelUsuario();
        String suscripcion = rutina.getNivelSuscripcion();

        if (nivel.equalsIgnoreCase("PRINCIPIANTE")) {
            rutina.agregarEjercicio(new Ejercicio("Sentadillas", "Piernas", nivel, 12, 3, 10));
            rutina.agregarEjercicio(new Ejercicio("Zancadas", "Piernas", nivel, 10, 3, 8));
        } else if (nivel.equalsIgnoreCase("INTERMEDIO")) {
            rutina.agregarEjercicio(new Ejercicio("Sentadillas con salto", "Piernas", nivel, 15, 4, 12));
            rutina.agregarEjercicio(new Ejercicio("Zancadas alternadas", "Piernas", nivel, 12, 4, 10));
        } else {
            rutina.agregarEjercicio(new Ejercicio("Sentadilla bulgara", "Piernas", nivel, 18, 4, 15));
            rutina.agregarEjercicio(new Ejercicio("Jump squats", "Piernas", nivel, 15, 5, 12));
        }

        if (suscripcion.equalsIgnoreCase("PREMIUM")) {
            rutina.agregarEjercicio(new Ejercicio("Peso muerto unilateral", "Piernas", nivel, 12, 4, 10));
        }
    }

    private void cargarHombros(Rutinas rutina) {
        String nivel = rutina.getNivelUsuario();
        String suscripcion = rutina.getNivelSuscripcion();

        if (nivel.equalsIgnoreCase("PRINCIPIANTE")) {
            rutina.agregarEjercicio(new Ejercicio("Elevaciones laterales", "Hombros", nivel, 10, 3, 8));
            rutina.agregarEjercicio(new Ejercicio("Rotaciones de hombro", "Hombros", nivel, 12, 2, 6));
        } else if (nivel.equalsIgnoreCase("INTERMEDIO")) {
            rutina.agregarEjercicio(new Ejercicio("Press militar", "Hombros", nivel, 12, 4, 10));
            rutina.agregarEjercicio(new Ejercicio("Elevaciones frontales", "Hombros", nivel, 12, 4, 8));
        } else {
            rutina.agregarEjercicio(new Ejercicio("Press Arnold", "Hombros", nivel, 15, 4, 12));
            rutina.agregarEjercicio(new Ejercicio("Elevaciones laterales lentas", "Hombros", nivel, 15, 5, 10));
        }

        if (suscripcion.equalsIgnoreCase("PREMIUM")) {
            rutina.agregarEjercicio(new Ejercicio("Face pull adaptado", "Hombros", nivel, 12, 4, 8));
        }
    }

    private void cargarPecho(Rutinas rutina) {
        String nivel = rutina.getNivelUsuario();
        String suscripcion = rutina.getNivelSuscripcion();

        if (nivel.equalsIgnoreCase("PRINCIPIANTE")) {
            rutina.agregarEjercicio(new Ejercicio("Flexiones con apoyo", "Pecho", nivel, 10, 3, 8));
            rutina.agregarEjercicio(new Ejercicio("Aperturas basicas", "Pecho", nivel, 12, 3, 8));
        } else if (nivel.equalsIgnoreCase("INTERMEDIO")) {
            rutina.agregarEjercicio(new Ejercicio("Flexiones tradicionales", "Pecho", nivel, 15, 4, 10));
            rutina.agregarEjercicio(new Ejercicio("Aperturas controladas", "Pecho", nivel, 12, 4, 10));
        } else {
            rutina.agregarEjercicio(new Ejercicio("Flexiones explosivas", "Pecho", nivel, 18, 4, 12));
            rutina.agregarEjercicio(new Ejercicio("Press inclinado", "Pecho", nivel, 15, 5, 10));
        }

        if (suscripcion.equalsIgnoreCase("PREMIUM")) {
            rutina.agregarEjercicio(new Ejercicio("Flexiones diamante", "Pecho", nivel, 12, 4, 8));
        }
    }

    private void cargarEspalda(Rutinas rutina) {
        String nivel = rutina.getNivelUsuario();
        String suscripcion = rutina.getNivelSuscripcion();

        if (nivel.equalsIgnoreCase("PRINCIPIANTE")) {
            rutina.agregarEjercicio(new Ejercicio("Remo basico", "Espalda", nivel, 10, 3, 8));
            rutina.agregarEjercicio(new Ejercicio("Superman", "Espalda", nivel, 12, 3, 8));
        } else if (nivel.equalsIgnoreCase("INTERMEDIO")) {
            rutina.agregarEjercicio(new Ejercicio("Remo inclinado", "Espalda", nivel, 12, 4, 10));
            rutina.agregarEjercicio(new Ejercicio("Superman extendido", "Espalda", nivel, 15, 4, 10));
        } else {
            rutina.agregarEjercicio(new Ejercicio("Dominadas asistidas", "Espalda", nivel, 10, 5, 12));
            rutina.agregarEjercicio(new Ejercicio("Remo avanzado", "Espalda", nivel, 15, 5, 12));
        }

        if (suscripcion.equalsIgnoreCase("PREMIUM")) {
            rutina.agregarEjercicio(new Ejercicio("Pullover", "Espalda", nivel, 12, 4, 8));
        }
    }

    private void cargarBrazos(Rutinas rutina) {
        String nivel = rutina.getNivelUsuario();
        String suscripcion = rutina.getNivelSuscripcion();

        if (nivel.equalsIgnoreCase("PRINCIPIANTE")) {
            rutina.agregarEjercicio(new Ejercicio("Curl de biceps basico", "Brazos", nivel, 10, 3, 8));
            rutina.agregarEjercicio(new Ejercicio("Fondos en banco", "Brazos", nivel, 8, 3, 8));
        } else if (nivel.equalsIgnoreCase("INTERMEDIO")) {
            rutina.agregarEjercicio(new Ejercicio("Curl martillo", "Brazos", nivel, 12, 4, 10));
            rutina.agregarEjercicio(new Ejercicio("Extension de triceps", "Brazos", nivel, 12, 4, 8));
        } else {
            rutina.agregarEjercicio(new Ejercicio("Curl concentrado", "Brazos", nivel, 15, 5, 10));
            rutina.agregarEjercicio(new Ejercicio("Fondos avanzados", "Brazos", nivel, 12, 5, 10));
        }

        if (suscripcion.equalsIgnoreCase("PREMIUM")) {
            rutina.agregarEjercicio(new Ejercicio("Curl 21", "Brazos", nivel, 21, 3, 10));
        }
    }

    private void cargarBasica(Rutinas rutina) {
        String nivel = rutina.getNivelUsuario();
        String suscripcion = rutina.getNivelSuscripcion();

        rutina.agregarEjercicio(new Ejercicio("Jumping jacks", "General", nivel, 20, 3, 10));
        rutina.agregarEjercicio(new Ejercicio("Sentadillas", "General", nivel, 12, 3, 10));

        if (suscripcion.equalsIgnoreCase("PREMIUM")) {
            rutina.agregarEjercicio(new Ejercicio("Plancha dinamica", "General", nivel, 10, 3, 8));
        }
    }
}