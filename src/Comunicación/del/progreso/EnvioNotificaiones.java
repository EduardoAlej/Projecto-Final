package Comunicación.del.progreso;

import Usuarios.y.datos.personales.Datos_sesion;
import Rutinas.e.historial.fisico.AjustarNivelRutina;
import Nivel.membresías.y.pagos.membresias;

public class EnvioNotificaiones {

    public void enviarRecordatorio(Datos_sesion sesion) {

        if (!sesion.haySesionActiva()) {
            System.out.println("ERROR: No hay una sesión activa.");
            return;
        }

        System.out.println("\nNOTIFICACION");
        System.out.println("Hola " + sesion.getNombreActual());
        System.out.println("Recuerda realizar tu entrenamiento de hoy.");
    }

    public void enviarEstadoRutina(Datos_sesion sesion, AjustarNivelRutina rutina) {

        if (!sesion.haySesionActiva()) {
            System.out.println("ERROR: No hay sesión activa.");
            return;
        }

        System.out.println("\nSTADO DE RUTINA");
        System.out.println("Usuario: " + sesion.getNombreActual());
        System.out.println("Zona actual entrenada: " + rutina.getZonaActual());
    }

    public void enviarMensajePremium(Datos_sesion sesion,membresias membresia) {

        if (!sesion.haySesionActiva()) {
            System.out.println("ERROR: No hay sesión activa.");
            return;
        }

        if (membresia.tieneAccesoPremium()) {
            System.out.println("\nMENSAJE PREMIUM");
            System.out.println("Hola " + sesion.getNombreActual());
            System.out.println("Tienes acceso a ajustes automáticos y rutinas avanzadas.");
        } else {
            System.out.println("\nMENSAJE");
            System.out.println("Activa Premium para desbloquear funciones avanzadas.");
        }
    }
}