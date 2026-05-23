package gymbro;

import Inicio.de.sesion.Sesion;
import Pago.de.cuentas.ServicioPagos;
import Ajuste.de.rutina.ServicioRutinas;
import Ajuste.de.rutina.Rutinas;
import Ajuste.de.rutina.AjusteRutina;
import Historial.de.progreso.ServicioHistorial;
import negocio.ComunicacionUsuario;
import java.util.Scanner;

public class MenuPrincipal {

    private Scanner scanner;
    private ServicioPagos servicioPagos;
    private ServicioRutinas servicioRutinas;
    private AjusteRutina ajusteRutina;
    private ComunicacionUsuario comunicador;
    private ServicioHistorial servicioHistorial;

    private Sesion sesionActual;
    private Rutinas rutinaActual;
    private String emailActual;
    private int diasEntrenandoSeguido;
    private double progresoAnterior;

    public MenuPrincipal() {
        scanner = new Scanner(System.in);
        servicioPagos = new ServicioPagos();
        servicioRutinas = new ServicioRutinas();
        ajusteRutina = new AjusteRutina();
        comunicador = new ComunicacionUsuario();
        servicioHistorial = new ServicioHistorial();

        sesionActual = null;
        rutinaActual = null;
        emailActual = null;
        diasEntrenandoSeguido = 0;
        progresoAnterior = 50.0;
    }

    public void iniciar() {
        System.out.println("BIENVENIDO A GYMBRO");

        boolean salir = false;

        while (!salir) {
            System.out.println("              MENÚ PRINCIPAL              ");
            System.out.println("1. Registrarse");
            System.out.println("2. Suscripción");
            System.out.println("3. Generar rutina");
            System.out.println("4. Ver rutina");
            System.out.println("5. Ajustar dificultad");
            System.out.println("6. Registrar progreso");
            System.out.println("7. Ver historial");
            System.out.println("8. Ver mensajes");
            System.out.println("9. Recordatorio descanso");
            System.out.println("10. Alerta inactividad");
            System.out.println("11. Recomendación IMC");
            System.out.println("12.Salir");
            System.out.println("-----------------------------------");
            System.out.print("Opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1: MenuUsuario.registrar(scanner, this); break;
                case 2: MenuUsuario.verSuscripcion(scanner, servicioPagos, emailActual, comunicador); break;
                case 3: MenuRutina.generar(scanner, sesionActual, servicioPagos, servicioRutinas, this); break;
                case 4: MenuRutina.verActual(rutinaActual, comunicador); break;
                case 5: MenuProgreso.ajustarDificultad(scanner, rutinaActual, sesionActual, servicioPagos, ajusteRutina); break;
                case 6: MenuProgreso.registrar(scanner, sesionActual, rutinaActual, servicioHistorial, comunicador, this); break;
                case 7: MenuProgreso.verHistorial(scanner, emailActual, servicioHistorial); break;
                case 8: MenuMensajes.mostrar(sesionActual, emailActual, servicioPagos, comunicador); break;
                case 9: MenuMensajes.recordatorioDescanso(sesionActual, diasEntrenandoSeguido, comunicador); break;
                case 10: MenuMensajes.alertaInactividad(scanner, sesionActual, comunicador); break;
                case 11: MenuMensajes.recomendacionIMC(sesionActual, comunicador); break;
                case 12: salir = true; break;
                default: System.out.println("Opción inválida");
            }
        }

        System.out.println("\n GRACIAS. SIGUE ENTRENANDO!");
        scanner.close();
    }
    public void setSesionActual(Sesion sesion) { this.sesionActual = sesion; }
    public void setEmailActual(String email) { this.emailActual = email; }
    public void setRutinaActual(Rutinas rutina) { this.rutinaActual = rutina; }
    public void setDiasEntrenandoSeguido(int dias) { this.diasEntrenandoSeguido = dias; }
    public void setProgresoAnterior(double progreso) { this.progresoAnterior = progreso; }

    public Sesion getSesionActual() { return sesionActual; }
    public String getEmailActual() { return emailActual; }
    public Rutinas getRutinaActual() { return rutinaActual; }
    public int getDiasEntrenandoSeguido() { return diasEntrenandoSeguido; }
    public double getProgresoAnterior() { return progresoAnterior; }
}