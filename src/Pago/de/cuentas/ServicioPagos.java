package Pago.de.cuentas;

import java.util.ArrayList;
import java.util.Scanner;

public class ServicioPagos {
    private ArrayList<Pagos> historialPagos = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private final String NIVEL_GRATIS = "GRATIS";
    private final String NIVEL_MEDIO = "MEDIO";
    private final String NIVEL_PREMIUM = "PREMIUM";
    private final double PRECIO_MEDIO = 5.99;
    private final double PRECIO_PREMIUM = 11.99;

    private int mostrarMenuNiveles() {
        System.out.println("\n== PLANES DISPONIBLES ==");
        System.out.println("1. GRATIS - $0");
        System.out.println("   • Acceso a toda la app");
        System.out.println("   • CON anuncios");
        System.out.println("   • SIN ejercicios personalizados");
        System.out.println();
        System.out.println("2. MEDIO - $" + PRECIO_MEDIO + "/mes");
        System.out.println("   • Acceso a toda la app");
        System.out.println("   • SIN anuncios");
        System.out.println("   • SIN ejercicios personalizados");
        System.out.println();
        System.out.println("3. PREMIUM - $" + PRECIO_PREMIUM + "/mes");
        System.out.println("   • Acceso a toda la app");
        System.out.println("   • SIN anuncios");
        System.out.println("   • CON ejercicios personalizados");
        System.out.println();
        System.out.print("Elige tu plan (1, 2 o 3): ");
        int opcion = scanner.nextInt();
        scanner.nextLine();
        return opcion;
    }
    private boolean simularPagoTarjeta() {
        System.out.println("\n== DATOS DE PAGO ==");
        System.out.print("Número de tarjeta (16 dígitos): ");
        Integer numTarjeta = null;
        try {
            numTarjeta = scanner.nextInt();
            scanner.nextLine();
            if (String.valueOf(numTarjeta).length() != 16) {
                System.out.println("Error: La tarjeta debe tener 16 dígitos");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error: Ingresa solo números");
            scanner.nextLine();
            return false;
        }

        System.out.print("Código de seguridad (3 dígitos): ");
        Short codSeguridad = null;
        try {
            codSeguridad = scanner.nextShort();
            scanner.nextLine();
            if (String.valueOf(codSeguridad).length() != 3) {
                System.out.println("Error: El código debe tener 3 dígitos");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error: Ingresa solo números");
            scanner.nextLine();
            return false;
        }

        System.out.println("\nProcesando pago");
        try { Thread.sleep(1500); } catch (Exception e) {}

        // Simulación: tarjetas pares son válidas
        if (numTarjeta % 2 == 0) {
            System.out.println("Pago aprobado!");
            return true;
        } else {
            System.out.println("Pago rechazado");
            return false;
        }
    }
    public Pagos procesarSuscripcion(String emailUsuario) {
        System.out.println("\n👤 Usuario: " + emailUsuario);

        int opcion = mostrarMenuNiveles();

        if (opcion == 1) {
            Pagos pagoGratis = new Pagos(emailUsuario, NIVEL_GRATIS);
            historialPagos.add(pagoGratis);
            System.out.println("\n Suscripción GRATIS activada!");
            pagoGratis.mostrarInfo();
            return pagoGratis;
        }

        if (opcion == 2) {
            System.out.println("\nTotal a pagar: $" + PRECIO_MEDIO);

            if (simularPagoTarjeta()) {
                System.out.print("Confirma número de tarjeta: ");
                Integer tarjeta = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Confirma código de seguridad: ");
                Short cvv = scanner.nextShort();
                scanner.nextLine();

                Pagos pago = new Pagos(emailUsuario, tarjeta, cvv, NIVEL_MEDIO, PRECIO_MEDIO);
                historialPagos.add(pago);
                System.out.println("\nPlan MEDIO activado!");
                pago.mostrarInfo();
                return pago;
            } else {
                System.out.println("\nPago fallido. Se asigna plan GRATIS.");
                Pagos pagoGratis = new Pagos(emailUsuario, NIVEL_GRATIS);
                historialPagos.add(pagoGratis);
                return pagoGratis;
            }
        }

        if (opcion == 3) {
            System.out.println("\nTotal a pagar: $" + PRECIO_PREMIUM);

            if (simularPagoTarjeta()) {
                System.out.print("Confirma número de tarjeta: ");
                Integer tarjeta = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Confirma código de seguridad: ");
                Short cvv = scanner.nextShort();
                scanner.nextLine();

                Pagos pago = new Pagos(emailUsuario, tarjeta, cvv, NIVEL_PREMIUM, PRECIO_PREMIUM);
                historialPagos.add(pago);
                System.out.println("\nPlan PREMIUM activado!");
                pago.mostrarInfo();
                return pago;
            } else {
                System.out.println("\nPago fallido. Se asigna plan GRATIS.");
                Pagos pagoGratis = new Pagos(emailUsuario, NIVEL_GRATIS);
                historialPagos.add(pagoGratis);
                return pagoGratis;
            }
        }

        System.out.println("Opción inválida. Se asigna plan GRATIS.");
        Pagos pagoDefecto = new Pagos(emailUsuario, NIVEL_GRATIS);
        historialPagos.add(pagoDefecto);
        return pagoDefecto;
    }

    public String getNivelUsuario(String emailUsuario) {
        for (int i = historialPagos.size() - 1; i >= 0; i--) {
            Pagos p = historialPagos.get(i);
            if (p.getEmailUsuario().equals(emailUsuario) && p.estaVigente()) {
                return p.getNivelSuscripcion();
            }
        }
        return "GRATIS";
    }

    public boolean tienePersonalizados(String emailUsuario) {
        return getNivelUsuario(emailUsuario).equals(NIVEL_PREMIUM);
    }

    public boolean tieneAnuncios(String emailUsuario) {
        return getNivelUsuario(emailUsuario).equals(NIVEL_GRATIS);
    }

    public void cancelarSuscripcion(String emailUsuario) {
        for (int i = 0; i < historialPagos.size(); i++) {
            Pagos p = historialPagos.get(i);
            if (p.getEmailUsuario().equals(emailUsuario) && p.estaVigente()) {
                p.setStatus((short)2);
                System.out.println("Suscripción cancelada para: " + emailUsuario);
                return;
            }
        }
        System.out.println("No hay suscripción activa para: " + emailUsuario);
    }

    public void mostrarHistorial() {
        System.out.println("\n== HISTORIAL DE PAGOS ==");
        if (historialPagos.isEmpty()) {
            System.out.println("No hay pagos registrados");
        } else {
            for (int i = 0; i < historialPagos.size(); i++) {
                Pagos p = historialPagos.get(i);
                System.out.println((i+1) + ". " + p.getEmailUsuario() +
                        " - " + p.getNivelSuscripcion() +
                        " - $" + p.getMontoPagado());
            }
        }
    }
}