package Historial.de.progreso;

import Ajuste.de.rutina.Rutinas;
import Inicio.de.sesion.Sesion;

public class ServicioHistorial {
    private HistorialProgreso historial;

    public ServicioHistorial() {
        historial = new HistorialProgreso();
    }

    public void registrarProgreso(Sesion sesion, Rutinas rutina, String fecha,
                                  int nivelCumplimiento, String observaciones) {

        if (sesion == null || rutina == null) {
            System.out.println("No se puede registrar el progreso.");
            return;
        }

        if (nivelCumplimiento < 0 || nivelCumplimiento > 100) {
            System.out.println("El nivel de cumplimiento debe estar entre 0 y 100.");
            return;
        }

        ProgresoFisico progreso = new ProgresoFisico(
                sesion.getEmail(),
                fecha,
                rutina,
                nivelCumplimiento,
                observaciones
        );

        historial.agregarRegistro(progreso);
        System.out.println("Progreso registrado correctamente.");
    }

    public void mostrarHistorialUsuario(String emailUsuario, String fechaInicio, String fechaFin) {
        if (historial.estaVacio()) {
            System.out.println("No hay registros de progreso.");
            return;
        }

        boolean encontrado = false;
        int sumaCumplimiento = 0;
        int contador = 0;

        System.out.println("=== HISTORIAL DE PROGRESO FISICO ===");

        for (int i = 0; i < historial.getRegistros().size(); i++) {
            ProgresoFisico p = historial.getRegistros().get(i);

            if (p.getEmailUsuario().equalsIgnoreCase(emailUsuario)
                    && p.getFecha().compareTo(fechaInicio) >= 0
                    && p.getFecha().compareTo(fechaFin) <= 0) {

                p.mostrarInfo();
                sumaCumplimiento += p.getNivelCumplimiento();
                contador++;
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontraron registros para ese usuario en el periodo consultado.");
            return;
        }

        int promedio = sumaCumplimiento / contador;

        System.out.println("Resumen del periodo:");
        System.out.println("Total de registros encontrados: " + contador);
        System.out.println("Promedio de cumplimiento: " + promedio + "%");
        System.out.println("Evaluacion general: " + evaluarPromedio(promedio));
    }

    private String evaluarPromedio(int promedio) {
        if (promedio >= 80) {
            return "Buen avance y buena constancia en el entrenamiento.";
        } else if (promedio >= 50) {
            return "Avance moderado. Se recomienda mantener la rutina y mejorar la constancia.";
        } else {
            return "Bajo cumplimiento. Se recomienda revisar la rutina y realizar ajustes.";
        }
    }
}