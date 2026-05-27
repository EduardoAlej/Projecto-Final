package Negocio.Nivelmembresíasypagos;

import java.util.Scanner;

public class membresias {


    private String tipoMembresiaActual;
    private double costoActual;


    public membresias() {
        this.tipoMembresiaActual = "Gratuita";
        this.costoActual = 0.0;
    }


    public void seleccionarMembresia() {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        System.out.println("\n=== CATÁLOGO DE MEMBRESÍAS GYMBRO ===");
        System.out.println("1. Plan Gratuito ($0.00)");
        System.out.println("   - Acceso a rutinas estándar.");
        System.out.println("   - Contiene anuncios publicitarios.\n");

        System.out.println("2. Plan Premium ($21.90/mes)");
        System.out.println("   - Ajuste automático de sobrecarga progresiva.");
        System.out.println("   - Historial de progreso físico avanzado.");
        System.out.println("   - Sin anuncios.\n");

        System.out.print("Ingrese el número del plan que desea activar (1 o 2): ");

        try {
            opcion = Integer.parseInt(scanner.nextLine());

            if (opcion == 1) {
                tipoMembresiaActual = "Gratuita";
                costoActual = 0.0;
                System.out.println("\nHas seleccionado el Plan Gratuito. Funciones básicas habilitadas.");
            }
            else if (opcion == 2) {
                System.out.println("\n Has seleccionado el Plan Premium.");
                System.out.println("Procesando transición al módulo de pagos...");


                ProcesadorPago pago = new ProcesadorPago();


                boolean pagoExitoso = pago.procesarTransaccion(21.90);


                if (pagoExitoso) {
                    tipoMembresiaActual = "Premium";
                    costoActual = 21.90;
                    System.out.println("\nBienvenido a GymBro Premium. Todos los beneficios están activados.");
                } else {
                    System.out.println("\n No se pudo activar el plan Premium. Manteniendo plan Gratuito.");
                }
            }
            else {
                System.out.println("\n Opción inválida. Manteniendo plan actual: " + tipoMembresiaActual);
            }
        } catch (NumberFormatException e) {
            System.out.println("\nError: Entrada no válida. Debe ingresar un número.");
        }
    }


    public boolean tieneAccesoPremium() {
        return tipoMembresiaActual.equals("Premium");
    }


    public String getTipoMembresiaActual() {
        return tipoMembresiaActual;
    }

    public double getCostoActual() {
        return costoActual;
    }
}