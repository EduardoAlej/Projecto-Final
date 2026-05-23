package gymbro;

import Inicio.de.sesion.Sesion;
import Pago.de.cuentas.ServicioPagos;
import Ajuste.de.rutina.Rutinas;
import Ajuste.de.rutina.AjusteRutina;
import Historial.de.progreso.ServicioHistorial;
import negocio.ComunicacionUsuario;
import java.time.LocalDate;
import java.util.Scanner;

public class MenuProgreso {

    public static void ajustarDificultad(Scanner scanner, Rutinas rutina, Sesion sesion,
                                         ServicioPagos servicioPagos, AjusteRutina ajusteRutina) {
        if (rutina == null || sesion == null) {
            System.out.println("Primero genera una rutina (opción 3)");
            return;
        }

        System.out.println("\nAJUSTAR LA DIFICULTAD");
        System.out.print("Porcentaje de cumplimiento (0-100): ");
        int cumplimiento = scanner.nextInt();
        scanner.nextLine();

        System.out.print("¿Fácil o difícil? (facil/dificil): ");
        String dificultad = scanner.nextLine();

        System.out.print("Observaciones (cansancio/normal): ");
        String obs = scanner.nextLine();

        ajusteRutina.ajustarRutina(rutina, sesion, servicioPagos, cumplimiento, dificultad, obs);
    }

    public static void registrar(Scanner scanner, Sesion sesion, Rutinas rutina, ServicioHistorial servicioHistorial, ComunicacionUsuario comunicador, MenuPrincipal menu) {
        if (sesion == null || rutina == null) {
            System.out.println("Primero regístrate y genera una rutina");
            return;
        }
        System.out.println("\n REGISTRAR PROGRESO");
        System.out.print("Porcentaje de cumplimiento (0-100): ");
        int cumplimiento = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Observaciones: ");
        String obs = scanner.nextLine();

        String fecha = LocalDate.now().toString();
        servicioHistorial.registrarProgreso(sesion, rutina, fecha, cumplimiento, obs);

        menu.setDiasEntrenandoSeguido(menu.getDiasEntrenandoSeguido() + 1);

        System.out.println("\n" + comunicador.mensajePostEntreno(rutina, cumplimiento));
        System.out.println(comunicador.mensajeMotivacion(cumplimiento, menu.getProgresoAnterior()));

        menu.setProgresoAnterior(cumplimiento);
    }

    public static void verHistorial(Scanner scanner, String email, ServicioHistorial servicioHistorial) {
        if (email == null) {
            System.out.println(" Primero regístrate (opción 1)");
            return;
        }

        System.out.println("\n HISTORIAL DE PROGRESO");
        System.out.print("Fecha inicio (ANIO-MES-DIA): ");
        String inicio = scanner.nextLine();
        System.out.print("Fecha fin (ANIO-MES-DIA): ");
        String fin = scanner.nextLine();

        servicioHistorial.mostrarHistorialUsuario(email, inicio, fin);
    }
}
