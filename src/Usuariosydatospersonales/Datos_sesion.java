package Usuariosydatospersonales;

import java.util.ArrayList;

public class Datos_sesion {

    // Clase interna para usuario
    private class Usuario {
        private String email;
        private String contrasena;
        private String nombre;
        private boolean sesionActiva;

        public Usuario(String email, String contrasena, String nombre) {
            this.email = email;
            this.contrasena = contrasena;
            this.nombre = nombre;
            this.sesionActiva = false;
        }

        public String getEmail() { return email; }
        public String getContrasena() { return contrasena; }
        public String getNombre() { return nombre; }
        public boolean isSesionActiva() { return sesionActiva; }
        public void setSesionActiva(boolean activa) { this.sesionActiva = activa; }
    }

    private ArrayList<Usuario> usuariosRegistrados = new ArrayList<>();
    private Usuario usuarioActual = null;
    private Datos_fisicos datosFisicosActual = null;

    private boolean validarEmail(String email) {
        if (email == null || email.equals("")) return false;
        if (!email.contains("@")) return false;
        int posArroba = email.indexOf('@');
        if (posArroba + 1 >= email.length()) return false;
        String dominio = email.substring(posArroba + 1);
        if (!dominio.contains(".")) return false;
        if (email.contains(" ")) return false;
        return true;
    }

    private boolean validarContrasena(String contrasena) {
        if (contrasena == null || contrasena.equals("")) return false;
        if (contrasena.length() < 6) return false;
        return true;
    }

    private Usuario buscarUsuario(String email) {
        for (int i = 0; i < usuariosRegistrados.size(); i++) {
            if (usuariosRegistrados.get(i).getEmail().equals(email)) {
                return usuariosRegistrados.get(i);
            }
        }
        return null;
    }

    public String registrar(String email, String contrasena, String nombre) {
        if (!validarEmail(email)) {
            return "ERROR: Email invalido (debe tener @ y .)";
        }
        if (!validarContrasena(contrasena)) {
            return "ERROR: Contraseña debe tener minimo 6 caracteres";
        }
        if (buscarUsuario(email) != null) {
            return "ERROR: El email ya esta registrado";
        }
        if (nombre == null || nombre.equals("")) {
            return "ERROR: El nombre no puede estar vacio";
        }

        Usuario nuevoUsuario = new Usuario(email, contrasena, nombre);
        usuariosRegistrados.add(nuevoUsuario);

        return "EXITO: Usuario registrado. Bienvenido " + nombre + "!";
    }

    public String iniciarSesion(String email, String contrasena) {
        if (!validarEmail(email)) {
            return "ERROR: Email invalido";
        }
        if (!validarContrasena(contrasena)) {
            return "ERROR: Contraseña invalida (minimo 6 caracteres)";
        }

        Usuario usuario = buscarUsuario(email);
        if (usuario == null) {
            return "ERROR: Usuario no encontrado";
        }

        if (!usuario.getContrasena().equals(contrasena)) {
            return "ERROR: Contraseña incorrecta";
        }

        usuario.setSesionActiva(true);
        usuarioActual = usuario;

        return "EXITO: Sesion iniciada. ¡Hola " + usuario.getNombre() + "!";
    }

    public String cerrarSesion() {
        if (usuarioActual == null) {
            return "ERROR: No hay sesion activa";
        }

        usuarioActual.setSesionActiva(false);
        usuarioActual = null;
        datosFisicosActual = null;
        return "EXITO: Sesion cerrada";
    }

    public void vincularDatosFisicos(Datos_fisicos datos) {
        this.datosFisicosActual = datos;
    }

    public String getNombreActual() {
        if (usuarioActual == null) return null;
        return usuarioActual.getNombre();
    }

    public String getEmailActual() {
        if (usuarioActual == null) return null;
        return usuarioActual.getEmail();
    }

    public Datos_fisicos getDatosFisicosActual() {
        return datosFisicosActual;
    }

    public boolean haySesionActiva() {
        return usuarioActual != null;
    }

    public void listarUsuarios() {
        System.out.println("\n=== USUARIOS REGISTRADOS ==");
        if (usuariosRegistrados.isEmpty()) {
            System.out.println("No hay usuarios");
        } else {
            for (int i = 0; i < usuariosRegistrados.size(); i++) {
                Usuario u = usuariosRegistrados.get(i);
                System.out.println((i+1) + ". " + u.getNombre() + " - " + u.getEmail());
            }
        }
    }

    public void mostrarSesionActual() {
        if (usuarioActual != null) {
            System.out.println("\n== SESION ACTIVA ===");
            System.out.println("Nombre: " + usuarioActual.getNombre());
            System.out.println("Email: " + usuarioActual.getEmail());
            if (datosFisicosActual != null) {
                datosFisicosActual.mostrar();
            }
        } else {
            System.out.println("No hay sesion activa");
        }
    }
}