package vista;

import javax.swing.*;
import java.awt.*;

public class MovimientoView extends JPanel {  // Cambiado a JPanel en vez de JFrame
    private JTextArea movimientosTextArea; // Área para mostrar los movimientos
    private JButton consultarButton; // Botón para consultar los movimientos

    public MovimientoView() {
        setLayout(new BorderLayout()); // Usamos BorderLayout para organizar los componentes

        // Área de texto para mostrar los movimientos
        movimientosTextArea = new JTextArea();
        movimientosTextArea.setEditable(false);  // No editable, solo para mostrar datos
        JScrollPane scrollPane = new JScrollPane(movimientosTextArea);
        add(scrollPane, BorderLayout.CENTER);

        // Botón para consultar movimientos
        consultarButton = new JButton("Consultar Movimientos");
        add(consultarButton, BorderLayout.SOUTH);
    }

    // Método para agregar un listener al botón de consultar
    public void consultarMovimientosListener(java.awt.event.ActionListener listener) {
        consultarButton.addActionListener(listener);
    }

    // Método para mostrar los movimientos en el área de texto
    public void mostrarMovimientos(String movimientos) {
        movimientosTextArea.setText(movimientos);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MovimientoView view = new MovimientoView();
            JFrame frame = new JFrame("Vista de Movimientos"); // Crear un JFrame solo para mostrar la vista
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
            frame.add(view);
            frame.setVisible(true);
        });
    }
}
