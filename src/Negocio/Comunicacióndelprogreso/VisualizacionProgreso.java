package Negocio.Comunicacióndelprogreso;

import Negocio.Usuariosydatospersonales.Datos_fisicos;
import Negocio.Usuariosydatospersonales.Datos_sesion;

public class VisualizacionProgreso {

    public void mostrarResumen(Datos_sesion sesion) {

        if (!sesion.haySesionActiva()) {
            System.out.println("ERROR,No hay sesion activa.");
            return;
        }

        Datos_fisicos datos = sesion.getDatosFisicosActual();

        System.out.println("\nRESUMEN DE PROGRESO");
        System.out.println("Usuario: " + sesion.getNombreActual());

        if (datos != null) {
            System.out.println("IMC actual: "
                    + String.format("%.1f", datos.calcularIMC()));

            System.out.println("Clasificacion: "
                    + datos.getClasificacionIMC());

            System.out.println("Nivel de rutina: "
                    + datos.getNivelRutina());
        }
    }

    public void mostrarRecomendacion(int porcentajeCumplimiento) {

        System.out.println("\nRECOMENDACION");

        if (porcentajeCumplimiento >= 80) {
            System.out.println("Excelente progreso.Aumenta la intensidad.");
        }
        else if (porcentajeCumplimiento >= 50) {
            System.out.println("Buen avance. Mantén constancia.");
        }
        else {
            System.out.println("Se recomienda disminuir intensidad y mejorar constancia.");
        }
    }

    public void mostrarAlerta(String observacion) {

        System.out.println("\nALERTA DEL SISTEMA");

        if (observacion.equalsIgnoreCase("cansancio")) {
            System.out.println("Se detectó cansancio. Descansa antes de continuar.");
        }
        else {
            System.out.println("No existen alertas importantes.");
        }
    }
}