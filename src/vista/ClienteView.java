package vista;

import controlador.ClienteController;
import javax.swing.*;
import java.awt.*;

public class ClienteView extends JPanel {
    private ClienteController controller;
    private JTextField nombreField, emailField, telefonoField, idField;
    private JButton guardarButton, consultarButton, eliminarButton;

    public ClienteView() {
        this.controller = null;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        // Campo para el ID (necesario para eliminar o actualizar clientes)
        JLabel idLabel = new JLabel("ID:");
        idField = new JTextField(5);

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreField = new JTextField(20);

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);

        JLabel telefonoLabel = new JLabel("Teléfono:");
        telefonoField = new JTextField(20);

        guardarButton = new JButton("Guardar");
        consultarButton = new JButton("Consultar");
        eliminarButton = new JButton("Eliminar");

        gbc.gridy = 0; add(idLabel, gbc);
        gbc.gridy = 1; add(idField, gbc);
        gbc.gridy = 2; add(nombreLabel, gbc);
        gbc.gridy = 3; add(nombreField, gbc);
        gbc.gridy = 4; add(emailLabel, gbc);
        gbc.gridy = 5; add(emailField, gbc);
        gbc.gridy = 6; add(telefonoLabel, gbc);
        gbc.gridy = 7; add(telefonoField, gbc);

        JPanel botonesPanel = new JPanel();
        botonesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
        botonesPanel.add(guardarButton);
        botonesPanel.add(consultarButton);
        botonesPanel.add(eliminarButton);

        gbc.gridy = 8; add(botonesPanel, gbc);

        // Acciones de los botones
        guardarButton.addActionListener(e -> {
            if (controller == null) {
                JOptionPane.showMessageDialog(this, "⚠️ El controlador no está inicializado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String nombre = nombreField.getText();
            String email = emailField.getText();
            String telefono = telefonoField.getText();
            controller.agregarCliente(nombre, email, telefono);
            limpiarCampos();
        });

        consultarButton.addActionListener(e -> {
            if (controller == null) {
                JOptionPane.showMessageDialog(this, "⚠️ El controlador no está inicializado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            controller.consultarClientes();
        });

        eliminarButton.addActionListener(e -> {
            if (controller == null) {
                JOptionPane.showMessageDialog(this, "⚠️ El controlador no está inicializado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                int id = Integer.parseInt(idField.getText());
                controller.eliminarCliente(id);
                limpiarCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "⚠️ ID no válido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public void setController(ClienteController controller) {
        this.controller = controller;
    }

    public ClienteController getController() {
        return this.controller;
    }

    private void limpiarCampos() {
        idField.setText("");
        nombreField.setText("");
        emailField.setText("");
        telefonoField.setText("");
    }
}
