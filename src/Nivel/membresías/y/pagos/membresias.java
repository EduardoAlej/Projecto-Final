package Nivel.membresías.y.pagos;

import java.util.Scanner;
// Importamos la clase de pago desde el paquete de tus compañeros


public class membresias {

    // Atributos de la clase
    private String tipoMembresiaActual;
    private double costoActual;

    // Constructor: Al iniciar, el usuario tiene membresía gratuita por defecto
    public membresias() {
        this.tipoMembresiaActual = "Gratuita";
        this.costoActual = 0.0;
    }

    // Método principal para seleccionar y procesar la membresía
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
                System.out.println("\n[!] Has seleccionado el Plan Gratuito. Funciones básicas habilitadas.");
            }
            else if (opcion == 2) {
                System.out.println("\n[!] Has seleccionado el Plan Premium.");
                System.out.println("Procesando transición al módulo de pagos...");

                // 1. Instancias la clase de pagos del paquete "Pago.de.cuentas"
                ProcesadorPago pago = new ProcesadorPago();

                // 2. Ejecutas el cobro
                boolean pagoExitoso = pago.procesarTransaccion(21.90);

                // 3. Validar si el pago pasó
                if (pagoExitoso) {
                    tipoMembresiaActual = "Premium";
                    costoActual = 21.90;
                    System.out.println("\n[!] Bienvenido a GymBro Premium. Todos los beneficios están activados.");
                } else {
                    System.out.println("\n[X] No se pudo activar el plan Premium. Manteniendo plan Gratuito.");
                }
            }
            else {
                System.out.println("\n[X] Opción inválida. Manteniendo plan actual: " + tipoMembresiaActual);
            }
        } catch (NumberFormatException e) {
            System.out.println("\n[X] Error: Entrada no válida. Debe ingresar un número.");
        }
    }

    // Método de validación que tus compañeros usarán para bloquear o permitir accesos
    public boolean tieneAccesoPremium() {
        return tipoMembresiaActual.equals("Premium");
    }

    // Getters
    public String getTipoMembresiaActual() {
        return tipoMembresiaActual;
    }

    public double getCostoActual() {
        return costoActual;
    }
}