package gymbro;

import Inicio.de.sesion.Sesion;
import Pago.de.cuentas.ServicioPagos;
import negocio.ComunicacionUsuario;
import java.util.Scanner;

public class MenuMensajes {

    public static void mostrar(Sesion sesion, String email,
                               ServicioPagos servicioPagos, ComunicacionUsuario comunicador) {
        System.out.println("MENSAJES");
        System.out.println(comunicador.darBienvenida(sesion));
        System.out.println(comunicador.darBienvenidaPersonalizada(sesion));
        System.out.println(comunicador.mensajeNivelSuscripcion(servicioPagos, email));
    }

    public static void recordatorioDescanso(Sesion sesion, int diasEntrenando, ComunicacionUsuario comunicador) {
        System.out.println("\nRECORDATORIO DE DESCANSO");
        String msg = comunicador.recordatorioDescanso(sesion, diasEntrenando);
        if (msg != null) {
            System.out.println(msg);
        } else {
            System.out.println("Llevas " + diasEntrenando + " días entrenando.");
        }
    }

    public static void alertaInactividad(Scanner scanner, Sesion sesion, ComunicacionUsuario comunicador) {
        System.out.println("\nALERTA DE INACTIVIDAD");
        System.out.print("Días sin entrenar? ");
        int dias = scanner.nextInt();
        scanner.nextLine();

        String msg = comunicador.alertaInactividad(sesion, dias);
        if (msg != null) {
            System.out.println(msg);
        } else {
            System.out.println("Siga así.");
        }
    }

    public static void recomendacionIMC(Sesion sesion, ComunicacionUsuario comunicador) {
        System.out.println("\n RECOMENDACIÓN POR IMC");
        if (sesion == null) {
            System.out.println("Primero regístrate (opción 1)");
            return;
        }
        System.out.println(comunicador.recomendacionPorIMC(sesion));
    }
}
