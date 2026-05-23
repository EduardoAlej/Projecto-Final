package gymbro;

import Inicio.de.sesion.Sesion;
import Pago.de.cuentas.ServicioPagos;
import Ajuste.de.rutina.ServicioRutinas;
import Ajuste.de.rutina.Rutinas;
import negocio.ComunicacionUsuario;
import java.util.Scanner;

public class MenuRutina {

    public static void generar(Scanner scanner, Sesion sesion, ServicioPagos servicioPagos,
                               ServicioRutinas servicioRutinas, MenuPrincipal menu) {
        if (sesion == null) {
            System.out.println("Primero regístrate (opción 1)");
            return;
        }

        System.out.println("\n GENERAR RUTINA");
        System.out.println("Categorías: piernas / hombros / pecho / espalda / brazos");
        System.out.print("Elige: ");
        String categoria = scanner.nextLine();

        Rutinas rutina = servicioRutinas.generarRutina(sesion, servicioPagos, categoria);
        menu.setRutinaActual(rutina);

        System.out.println("\n RUTINA HECHA");
        rutina.mostrarRutina();
    }

    public static void verActual(Rutinas rutina, ComunicacionUsuario comunicador) {
        if (rutina == null) {
            System.out.println("Primero genera una rutina (opción 3)");
            return;
        }

        System.out.println("\nRUTINA ACTUAL");
        System.out.println(comunicador.mostrarRutinaCompleta(rutina));
    }
}