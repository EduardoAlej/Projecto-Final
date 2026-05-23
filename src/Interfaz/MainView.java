package Interfaz;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    public MainView() {
        // Configuración de la ventana
        setTitle("GymBro - Evolución Científica");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Elementos de la interfaz
        JLabel titulo = new JLabel("GymBro: Evolución Científica");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        JButton btnPlanificar = new JButton("Generar mi entrenamiento");
        JLabel resultado = new JLabel("Aquí aparecerá tu rutina...");

        // Añadir elementos a la ventana
        add(titulo);
        add(btnPlanificar);
        add(resultado);

        // Acción del botón (Aquí conectas el código de tus compañeros)
        btnPlanificar.addActionListener(e -> {
            resultado.setText("Rutina generada con éxito");
            JOptionPane.showMessageDialog(this, "Ejecutando lógica de entrenamiento...");
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainView();
    }
}
