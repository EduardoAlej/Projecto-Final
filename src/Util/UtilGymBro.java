package Util;

import Comunicacióndelprogreso.EnvioNotificaiones;
import Comunicacióndelprogreso.VisualizacionProgreso;
import Nivelmembresíasypagos.membresias;
import Rutinasehistorialfisico.AjustarNivelRutina;
import Rutinasehistorialfisico.HistorialProgresoFisico;
import Usuariosydatospersonales.Datos_fisicos;
import Usuariosydatospersonales.Datos_sesion;

import javax.swing.*;

public class UtilGymBro {

    private Datos_sesion sesion;
    private membresias membresia;
    private AjustarNivelRutina ajustarNivelRutina;
    private HistorialProgresoFisico historialProgresoFisico;
    private EnvioNotificaiones envioNotificaiones;
    private VisualizacionProgreso visualizacionProgreso;

    public UtilGymBro() {
        sesion = new Datos_sesion();
        membresia = new membresias();
        ajustarNivelRutina = new AjustarNivelRutina();
        historialProgresoFisico = new HistorialProgresoFisico();
        envioNotificaiones = new EnvioNotificaiones();
        visualizacionProgreso = new VisualizacionProgreso();
    }

    // =========================
    // MENU PRINCIPAL
    // =========================
    public void iniciarSistema() {
        int opcion;

        do {
            opcion = pedirEntero(
                    "=== MENU PRINCIPAL GYMBRO ===\n"
                            + "1. Registrarse\n"
                            + "2. Iniciar sesion\n"
                            + "3. Seleccionar membresia\n"
                            + "4. Generar rutina\n"
                            + "5. Ajustar rutina\n"
                            + "6. Registrar historial\n"
                            + "7. Mostrar historial\n"
                            + "8. Mostrar progreso\n"
                            + "9. Mostrar notificaciones\n"
                            + "10. Mostrar sesion actual\n"
                            + "11. Cerrar sesion\n"
                            + "0. Salir",
                    0, 11
            );

            switch (opcion) {
                case 1:
                    menuRegistrarUsuario();
                    break;
                case 2:
                    menuIniciarSesion();
                    break;
                case 3:
                    menuSeleccionarMembresia();
                    break;
                case 4:
                    menuGenerarRutina();
                    break;
                case 5:
                    menuAjustarRutina();
                    break;
                case 6:
                    menuRegistrarHistorial();
                    break;
                case 7:
                    mostrarHistorialUsuarioActual();
                    break;
                case 8:
                    menuMostrarProgreso();
                    break;
                case 9:
                    menuMostrarNotificaciones();
                    break;
                case 10:
                    mostrarSesionActual();
                    break;
                case 11:
                    JOptionPane.showMessageDialog(null, cerrarSesion());
                    break;
                case 0:
                    JOptionPane.showMessageDialog(null, "Programa finalizado.");
                    break;
            }

        } while (opcion != 0);
    }

    // =========================
    // USUARIO Y SESION
    // =========================
    public String registrarUsuario(String email, String contrasena, String nombre) {
        return sesion.registrar(email, contrasena, nombre);
    }

    public String iniciarSesion(String email, String contrasena) {
        return sesion.iniciarSesion(email, contrasena);
    }

    public String cerrarSesion() {
        return sesion.cerrarSesion();
    }

    public void vincularDatosFisicos(float altura, float peso, byte capacidad, String genero) {
        Datos_fisicos datos = new Datos_fisicos(altura, peso, capacidad, genero);
        sesion.vincularDatosFisicos(datos);
    }

    public void mostrarSesionActual() {
        sesion.mostrarSesionActual();
    }

    public boolean haySesionActiva() {
        return sesion.haySesionActiva();
    }

    public String getEmailUsuarioActual() {
        return sesion.getEmailActual();
    }

    public String getNombreUsuarioActual() {
        return sesion.getNombreActual();
    }

    // =========================
    // MEMBRESIA
    // =========================
    public void seleccionarMembresia() {
        membresia.seleccionarMembresia();
    }

    public boolean tieneAccesoPremium() {
        return membresia.tieneAccesoPremium();
    }

    public String getTipoMembresiaActual() {
        return membresia.getTipoMembresiaActual();
    }

    public double getCostoActual() {
        return membresia.getCostoActual();
    }

    // =========================
    // RUTINAS
    // =========================
    public String generarRutina(String zona) {
        return ajustarNivelRutina.generarRutina(sesion, membresia, zona);
    }

    public void mostrarRutinaActual() {
        ajustarNivelRutina.mostrarRutinaActual(sesion);
    }

    public String ajustarRutina(int porcentajeCumplimiento,
                                String dificultadPercibida,
                                int tiempoQueTardo,
                                String observaciones) {

        return ajustarNivelRutina.ajustarRutina(
                membresia,
                porcentajeCumplimiento,
                dificultadPercibida,
                tiempoQueTardo,
                observaciones
        );
    }

    // =========================
    // HISTORIAL
    // =========================
    public String registrarHistorial(String fecha, boolean cumplioObjetivo) {
        return historialProgresoFisico.registrarHistorial(
                sesion,
                ajustarNivelRutina,
                fecha,
                cumplioObjetivo
        );
    }

    public void mostrarHistorialUsuarioActual() {
        if (!sesion.haySesionActiva()) {
            JOptionPane.showMessageDialog(null, "ERROR: No hay sesion activa.");
            return;
        }

        historialProgresoFisico.mostrarHistorialUsuario(sesion.getEmailActual());
    }

    // =========================
    // VISUALIZACION DEL PROGRESO
    // =========================
    public void mostrarResumenProgreso() {
        visualizacionProgreso.mostrarResumen(sesion);
    }

    public void mostrarRecomendacion(int porcentajeCumplimiento) {
        visualizacionProgreso.mostrarRecomendacion(porcentajeCumplimiento);
    }

    public void mostrarAlerta(String observacion) {
        visualizacionProgreso.mostrarAlerta(observacion);
    }

    // =========================
    // NOTIFICACIONES
    // =========================
    public void enviarRecordatorio() {
        envioNotificaiones.enviarRecordatorio(sesion);
    }

    public void enviarEstadoRutina() {
        envioNotificaiones.enviarEstadoRutina(sesion, ajustarNivelRutina);
    }

    public void enviarMensajePremium() {
        envioNotificaiones.enviarMensajePremium(sesion, membresia);
    }

    // =========================
    // MENUS INTERNOS
    // =========================
    private void menuRegistrarUsuario() {
        String email = pedirTexto("Ingrese el email:");
        String contrasena = pedirTexto("Ingrese la contrasena:");
        String nombre = pedirTexto("Ingrese el nombre:");

        String resultado = registrarUsuario(email, contrasena, nombre);
        JOptionPane.showMessageDialog(null, resultado);

        if (resultado.startsWith("EXITO")) {
            float altura = pedirFloat("Ingrese la altura en cm:", 50, 272);
            float peso = pedirFloat("Ingrese el peso en kg:", 20, 300);
            byte capacidad = (byte) pedirEntero("Ingrese la capacidad fisica del 1 al 10:", 1, 10);
            String genero = pedirGenero();

            vincularDatosFisicos(altura, peso, capacidad, genero);
            JOptionPane.showMessageDialog(null, "Datos fisicos vinculados correctamente.");
        }
    }

    private void menuIniciarSesion() {
        String email = pedirTexto("Ingrese el email:");
        String contrasena = pedirTexto("Ingrese la contrasena:");

        String resultado = iniciarSesion(email, contrasena);
        JOptionPane.showMessageDialog(null, resultado);

        if (resultado.startsWith("ERROR")) {
            JOptionPane.showMessageDialog(null,
                    "Si aun no esta registrado, primero debe usar la opcion Registrarse.");
            return;
        }

        int opcion = pedirEntero("Desea ingresar datos fisicos ahora?\n1. Si\n2. No", 1, 2);

        if (opcion == 1) {
            float altura = pedirFloat("Ingrese la altura en cm:", 50, 272);
            float peso = pedirFloat("Ingrese el peso en kg:", 20, 300);
            byte capacidad = (byte) pedirEntero("Ingrese la capacidad fisica del 1 al 10:", 1, 10);
            String genero = pedirGenero();

            vincularDatosFisicos(altura, peso, capacidad, genero);
            JOptionPane.showMessageDialog(null, "Datos fisicos vinculados correctamente.");
        }
    }

    private void menuSeleccionarMembresia() {
        if (!validarSesionActiva()) return;
        seleccionarMembresia();
    }

    private void menuGenerarRutina() {
        if (!validarSesionActiva()) return;

        String zona = pedirZona();
        String resultado = generarRutina(zona);
        JOptionPane.showMessageDialog(null, resultado);

        if (resultado.startsWith("EXITO")) {
            mostrarRutinaActual();
        }
    }

    private void menuAjustarRutina() {
        if (!validarSesionActiva()) return;

        int porcentaje = pedirEntero("Ingrese el porcentaje de cumplimiento:", 0, 100);
        String dificultad = pedirDificultad();
        int tiempo = pedirEntero("Ingrese el tiempo que tardo en minutos:", 1, 1000);
        String observaciones = pedirTexto("Ingrese observaciones:\nEjemplo: cansancio o sin problemas");

        String resultado = ajustarRutina(porcentaje, dificultad, tiempo, observaciones);
        JOptionPane.showMessageDialog(null, resultado);

        if (resultado.startsWith("EXITO")) {
            mostrarRutinaActual();
        }
    }

    private void menuRegistrarHistorial() {
        if (!validarSesionActiva()) return;

        String fecha = pedirTexto("Ingrese la fecha en formato YYYY-MM-DD:");
        int opcion = pedirEntero("Cumplio el objetivo?\n1. Si\n2. No", 1, 2);
        boolean cumplioObjetivo = (opcion == 1);

        String resultado = registrarHistorial(fecha, cumplioObjetivo);
        JOptionPane.showMessageDialog(null, resultado);
    }

    private void menuMostrarProgreso() {
        if (!validarSesionActiva()) return;

        mostrarResumenProgreso();

        int porcentaje = pedirEntero("Ingrese porcentaje para mostrar recomendacion:", 0, 100);
        mostrarRecomendacion(porcentaje);

        String observacion = pedirTexto("Ingrese observacion para alerta:");
        mostrarAlerta(observacion);
    }

    private void menuMostrarNotificaciones() {
        if (!validarSesionActiva()) return;

        enviarRecordatorio();
        enviarEstadoRutina();
        enviarMensajePremium();
    }

    // =========================
    // VALIDACIONES
    // =========================
    private boolean validarSesionActiva() {
        if (!haySesionActiva()) {
            JOptionPane.showMessageDialog(null,
                    "Primero debe iniciar sesion.\nSi no esta registrado, primero debe registrarse.");
            return false;
        }
        return true;
    }

    private String pedirTexto(String mensaje) {
        while (true) {
            String valor = JOptionPane.showInputDialog(null, mensaje);

            if (valor == null) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un valor.");
                continue;
            }

            valor = valor.trim();

            if (!valor.equals("")) {
                return valor;
            }

            JOptionPane.showMessageDialog(null, "El campo no puede estar vacio.");
        }
    }

    private int pedirEntero(String mensaje, int minimo, int maximo) {
        while (true) {
            try {
                String valor = JOptionPane.showInputDialog(null, mensaje);

                if (valor == null || valor.trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un numero.");
                    continue;
                }

                int numero = Integer.parseInt(valor.trim());

                if (numero < minimo || numero > maximo) {
                    JOptionPane.showMessageDialog(null,
                            "Ingrese un numero entre " + minimo + " y " + maximo + ".");
                    continue;
                }

                return numero;

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada invalida. Ingrese un numero correcto.");
            }
        }
    }

    private float pedirFloat(String mensaje, float minimo, float maximo) {
        while (true) {
            try {
                String valor = JOptionPane.showInputDialog(null, mensaje);

                if (valor == null || valor.trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un numero.");
                    continue;
                }

                float numero = Float.parseFloat(valor.trim());

                if (numero < minimo || numero > maximo) {
                    JOptionPane.showMessageDialog(null,
                            "Ingrese un valor entre " + minimo + " y " + maximo + ".");
                    continue;
                }

                return numero;

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada invalida. Ingrese un numero correcto.");
            }
        }
    }

    private String pedirGenero() {
        while (true) {
            String genero = pedirTexto("Ingrese el genero:\nMASCULINO, FEMENINO u OTRO").toUpperCase();

            if (genero.equals("MASCULINO") || genero.equals("FEMENINO") || genero.equals("OTRO")) {
                return genero;
            }

            JOptionPane.showMessageDialog(null, "Genero invalido. Intente de nuevo.");
        }
    }

    private String pedirZona() {
        while (true) {
            String zona = pedirTexto("Ingrese la zona:\npiernas, hombros, pecho, espalda o brazos").toLowerCase();

            if (zona.equals("piernas") || zona.equals("hombros") || zona.equals("pecho")
                    || zona.equals("espalda") || zona.equals("brazos")) {
                return zona;
            }

            JOptionPane.showMessageDialog(null, "Zona invalida. Intente de nuevo.");
        }
    }

    private String pedirDificultad() {
        while (true) {
            String dificultad = pedirTexto("Ingrese la dificultad percibida:\nfacil, media o dificil").toLowerCase();

            if (dificultad.equals("facil") || dificultad.equals("media") || dificultad.equals("dificil")) {
                return dificultad;
            }

            JOptionPane.showMessageDialog(null, "Dificultad invalida. Intente de nuevo.");
        }
    }
}
