package Historial.de.progreso;

import java.util.ArrayList;

public class HistorialProgreso {
    private ArrayList<ProgresoFisico> registros;

    public HistorialProgreso() {
        registros = new ArrayList<>();
    }

    public void agregarRegistro(ProgresoFisico progreso) {
        registros.add(progreso);
    }

    public ArrayList<ProgresoFisico> getRegistros() {
        return registros;
    }

    public boolean estaVacio() {
        return registros.isEmpty();
    }
}
