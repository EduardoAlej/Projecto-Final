package gymbro;

import Inicio.de.sesion.Sesion;
import Pago.de.cuentas.ServicioPagos;
import negocio.ComunicacionUsuario;
import java.util.Scanner;

public class MenuUsuario {

    public static void registrar(Scanner scanner, MenuPrincipal menu) {
        System.out.println("\n REGISTRO DE USUARIO");

        try {
            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("Contraseña (mínimo 6): ");
            String password = scanner.nextLine();

            System.out.print("Peso (kg): ");
            float peso = scanner.nextFloat();

            System.out.print("Estatura (cm): ");
            float estatura = scanner.nextFloat();

            System.out.print("Capacidad (1-10): ");
            byte capacidad = scanner.nextByte();
            scanner.nextLine();

            System.out.print("Clasificación: ");
            String clasificacion = scanner.nextLine();

            Sesion sesion = new Sesion(email, password, peso, estatura, capacidad, clasificacion);

            menu.setSesionActual(sesion);
            menu.setEmailActual(email);

            System.out.println("\n usuario registrado con exito");
            sesion.mostrarInfo();

            System.out.println("\n Elija un plan de suscripción");
            ServicioPagos sp = new ServicioPagos();
            sp.procesarSuscripcion(email);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void verSuscripcion(Scanner scanner, ServicioPagos servicioPagos,String email, ComunicacionUsuario comunicador) {
        if (email == null) {
            System.out.println("Primero regístrate (opción 1)");
            return;
        }

        System.out.println("\nSUSCRIPCIÓN ACTUAL");
        System.out.println(comunicador.mensajeNivelSuscripcion(servicioPagos, email));

        System.out.print("\n¿Cambiar plan? (1=Sí / 2=No): ");
        int cambiar = scanner.nextInt();
        scanner.nextLine();

        if (cambiar == 1) {
            servicioPagos.procesarSuscripcion(email);
        }
    }
}