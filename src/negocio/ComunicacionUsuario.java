package negocio;

import Inicio.de.sesion.Sesion;
import Pago.de.cuentas.ServicioPagos;
import Ajuste.de.rutina.Rutinas;
import Ajuste.de.rutina.Ejercicio;
import java.util.ArrayList;

public class ComunicacionUsuario {
    public String darBienvenida(Sesion sesion) {
        if (sesion == null) {
            return "Bienvenido a GymBro Regístrate porfavor";
        }
        String nivel = sesion.getNivelDificultad();

        if (nivel.equals("PRINCIPIANTE")) {
            return "¡Hola! Empieza tranquilo";
        } else if (nivel.equals("INTERMEDIO")) {
            return "Qué bueno verte de nuevo";
        } else if (nivel.equals("AVANZADO")) {
            return "Otro dia mas de entrenamiento";
        } else {
            return "Hola como estas, prepárate para entrenar.";
        }
    }
    public String darBienvenidaPersonalizada(Sesion sesion) {
        if (sesion == null) {
            return "Bienvenido a GymBro regístrate para empezar.";
        }
        String email = sesion.getEmail();
        String nombre = extraerNombreDeEmail(email);
        String nivel = sesion.getNivelDificultad();
        if (nivel.equals("PRINCIPIANTE")) {
            return "Hola " + nombre + " Empieza tranquilo";
        } else if (nivel.equals("INTERMEDIO")) {
            return "Nos volvemos a ver " + nombre + "veo que ya te esta gustando esto ";
        } else if (nivel.equals("AVANZADO")) {
            return "Hola, como estas" + nombre + "ya tienes un nivel alto";
        } else {
            return "Hola " + nombre + ", prepárate para entrenar.";
        }
    }

    private String extraerNombreDeEmail(String email) {
        if (email == null || !email.contains("@")) {
            return "Usuario";
        }
        return email.substring(0, email.indexOf("@"));
    }
    public String mensajePostEntreno(Rutinas rutina, int porcentajeCompletado) {
        if (rutina == null) {
            return "Completaste tu entrenamiento";
        }

        if (porcentajeCompletado >= 90) {
            return "Completaste el " + porcentajeCompletado + "% de " + rutina.getCategoria();
        } else if (porcentajeCompletado >= 60) {
            return "Completaste el " + porcentajeCompletado + "% de " + rutina.getCategoria();
        } else {
            return "Completaste el " + porcentajeCompletado + "% de " + rutina.getCategoria() ;
        }
    }
    public String mostrarRutinaCompleta(Rutinas rutina) {
        if (rutina == null) {
            return "No hay rutina disponible.";
        }

        ArrayList<Ejercicio> ejercicios = rutina.getEjercicios();

        String mensaje = "------------------------------------\n";
        mensaje += "RUTINA DEL DIA DE HOY\n";
        mensaje += "Categoría: " + rutina.getCategoria() + "\n";
        mensaje += "Nivel: " + rutina.getNivelUsuario() + "\n";
        mensaje += "Suscripción: " + rutina.getNivelSuscripcion() + "\n";
        for (int i = 0; i < ejercicios.size(); i++) {
            Ejercicio e = ejercicios.get(i);
            mensaje += (i+1) + e.getNombre() + "\n";
            mensaje += "Series: " + e.getSeries() + "/Repeticiones: " + e.getRepeticiones() +
                    "/Tiempo: " + e.getDuracionMinutos() + " min\n";
        }
        mensaje += " Debes de calentar antes de empezar.\n";
        return mensaje;
    }
    public String mostrarEjerciciosDeHoy(Rutinas rutina) {
        if (rutina == null) {
            return "No hay ejercicios para mostrar.";
        }
        ArrayList<Ejercicio> ejercicios = rutina.getEjercicios();
        String mensaje = "EJERCICIOS DE HOY:\n";
        for (int i = 0; i < ejercicios.size(); i++) {
            mensaje += (i+1) + ". " + ejercicios.get(i).getNombre() + "\n";
        }
        return mensaje;
    }
    public String recordatorioDescanso(Sesion sesion, int diasEntrenandoSeguido) {
        if (sesion == null) return null;

        String nombre = extraerNombreDeEmail(sesion.getEmail());

        if (diasEntrenandoSeguido >= 4) {
            return "Cuidado" + nombre.toUpperCase() +"Has entrenado " + diasEntrenandoSeguido + " días seguidos debes descansar.\n" ;
        } else if (diasEntrenandoSeguido >= 3) {
            return "Oye " + nombre + ", llevas " + diasEntrenandoSeguido + " días entrenando creo que mañana sería bueno descansar.";
        }
        return null;
    }
    public String alertaInactividad(Sesion sesion, int diasSinEntrenar) {
        if (sesion == null) return null;

        String nombre = extraerNombreDeEmail(sesion.getEmail());

        if (diasSinEntrenar >= 7) {
            return "Oye" + nombre + "Llevas " + diasSinEntrenar + " días sin entrenar," + "vuelve cuando puedas";
        } else if (diasSinEntrenar >= 4) {
            return "Oye " + nombre + ", han pasado " + diasSinEntrenar + " creo que es hora que regreses";
        } else if (diasSinEntrenar >= 2) {
            return "Oye" + nombre + ", llevas " + diasSinEntrenar + "tines muchos dias sin entrenar ";
        }
        return null;
    }
    public String mensajeMotivacion(double progresoActual, double progresoAnterior) {
        double diferencia = progresoActual - progresoAnterior;

        if (diferencia > 10) {
            return "WOW, Subiste un " + String.format("%.1f", diferencia);
        } else if (diferencia > 0) {
            return "Vas mejorando, Subiste un " + String.format("%.1f", diferencia);
        } else if (diferencia < 0) {
            return "Que paso, bajaste un " + String.format("%.1f", Math.abs(diferencia));
        } else {
            return "Tu progreso se amntiene igual, intenta incrementarlo";
        }
    }
    public String resumenSemanal(Sesion sesion, int entrenosCompletados, int entrenosTotales) {
        String nombre = (sesion != null) ? extraerNombreDeEmail(sesion.getEmail()) : "Usuario";
        double porcentaje = (entrenosCompletados * 100.0) / entrenosTotales;

        String mensaje = "--------------------------------------\n";
        mensaje += "RESUMEN SEMANAL\n";
        mensaje += "Usuario: " + nombre + "\n";
        mensaje += "Entrenamientos: " + entrenosCompletados + " de " + entrenosTotales + "\n";
        mensaje += "Cumplimiento: " + String.format("%.0f", porcentaje) + "%\n";
        if (porcentaje >= 80) {
            mensaje += "Muy buena semana\n";
        } else if (porcentaje >= 60) {
            mensaje += " Buena semana\n";
        } else if (porcentaje >= 40) {
            mensaje += "Vas progresando\n";
        } else {
            mensaje += "Intenta ser más constante\n";
        }
        return mensaje;
    }
    public String recomendacionPorIMC(Sesion sesion) {
        if (sesion == null) {
            return "Completa tus datos para recibir recomendaciones.";
        }
        Float imc = sesion.calcularIMC();
        if (imc == null) {
            return "No se pudo calcular tu IMC. Verifica peso y estatura.";
        }
        if (imc < 18.5) {
            return "Tu IMC es " + String.format("%.1f", imc) + " (bajo peso). ";
        } else if (imc < 25) {
            return "Tu IMC es " + String.format("%.1f", imc) + " (normal). ";
        } else if (imc < 30) {
            return "Tu IMC es " + String.format("%.1f", imc) + " (sobrepeso). ";
        } else {
            return "Tu IMC es " + String.format("%.1f", imc) + " (obesidad). ";
        }
    }
    public String mensajeError(Exception e) {
        return "ERROR: " + e.getMessage() + "\n" +
                "Por favor, verifica sus datos";
    }
    public String notificacionSistema(String tipo, String mensaje) {
        if (tipo.equals("recordatorio")) {
            return "RECORDATORIO: " + mensaje;
        } else if (tipo.equals("alerta")) {
            return "ALERTA: " + mensaje;
        } else if (tipo.equals("logro")) {
            return "NUEVO LOGRO" + mensaje;
        } else {
            return "NOTIFICACIÓN: " + mensaje;
        }
    }
    public String mensajeNivelSuscripcion(ServicioPagos servicioPagos, String email) {
        String nivel = servicioPagos.getNivelUsuario(email);

        if (nivel.equalsIgnoreCase("PREMIUM")) {
            return "💎 Eres un usuario premium, disfrutas de todos nuetsros beneficios";
        } else {
            return "Eres usuario gratis.Si deseas puedes actualizar a premium resivir mas beneficios";
        }
    }
}