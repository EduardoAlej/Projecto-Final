package Nivelmembresíasypagos;

import java.util.Scanner;

public class ProcesadorPago {

    public ProcesadorPago() {
        // Constructor vacío
    }

    // Método principal de la pasarela de pago
    public boolean procesarTransaccion(double monto) {
        if (monto <= 0) {
            System.out.println("El monto es $0.00. No se requiere tarjeta.");
            return true;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== PASARELA DE PAGO SEGURA GYMBRO ===");
        System.out.println("Total a pagar: $" + monto);

        System.out.print("Ingrese el número de su tarjeta (16 dígitos): ");
        String numeroTarjeta = scanner.nextLine();

        System.out.print("Ingrese la fecha de expiración (MM/YY): ");
        String expiracion = scanner.nextLine();

        System.out.print("Ingrese el código de seguridad (CVV): ");
        String cvv = scanner.nextLine();

        // Validación básica simulada
        if (numeroTarjeta.length() >= 16 && cvv.length() >= 3) {
            System.out.println("\n[!] Procesando pago...");
            System.out.println("[!] Transacción aprobada. Pago de $" + monto + " recibido.");
            return true;
        } else {
            System.out.println("\n[X] Transacción rechazada. Verifique los datos de su tarjeta e intente nuevamente.");
            return false;
        }
    }
}
