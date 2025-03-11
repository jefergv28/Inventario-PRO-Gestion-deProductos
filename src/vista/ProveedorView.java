package vista;

import controlador.ProveedorController;
import javax.swing.*;
import java.awt.*;

public class ProveedorView extends JPanel {
    private ProveedorController controller;
    private JTextField idField, nombreField, telefonoField, emailField;
    private JButton guardarButton, consultarButton, actualizarButton, eliminarButton;
    private JTextArea listaProveedoresArea;

    public ProveedorView() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        JLabel idLabel = new JLabel("ID:");
        idField = new JTextField(5);

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreField = new JTextField(20);

        JLabel telefonoLabel = new JLabel("Teléfono:");
        telefonoField = new JTextField(15);

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);

        guardarButton = new JButton("Guardar");
        consultarButton = new JButton("Consultar");
        actualizarButton = new JButton("Actualizar");
        eliminarButton = new JButton("Eliminar");

        listaProveedoresArea = new JTextArea(10, 40);
        listaProveedoresArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(listaProveedoresArea);

        gbc.gridy = 0; add(idLabel, gbc);
        gbc.gridy = 1; add(idField, gbc);
        gbc.gridy = 2; add(nombreLabel, gbc);
        gbc.gridy = 3; add(nombreField, gbc);
        gbc.gridy = 4; add(telefonoLabel, gbc);
        gbc.gridy = 5; add(telefonoField, gbc);
        gbc.gridy = 6; add(emailLabel, gbc);
        gbc.gridy = 7; add(emailField, gbc);

        JPanel botonesPanel = new JPanel();
        botonesPanel.add(guardarButton);
        botonesPanel.add(consultarButton);
        botonesPanel.add(actualizarButton);
        botonesPanel.add(eliminarButton);

        gbc.gridy = 8; add(botonesPanel, gbc);
        gbc.gridy = 9; gbc.gridwidth = 2; add(scrollPane, gbc);

        // Acciones de los botones
        guardarButton.addActionListener(e -> {
            if (controller == null) return;
            controller.agregarProveedor(nombreField.getText(), emailField.getText(), telefonoField.getText());
            limpiarCampos();
        });

        consultarButton.addActionListener(e -> {
            if (controller == null) return;
            java.util.List<String> proveedores = controller.consultarProveedores();
            listaProveedoresArea.setText(proveedores.isEmpty() ? "No hay proveedores registrados." : String.join("\n", proveedores));
        });


        actualizarButton.addActionListener(e -> {
            if (controller == null) return;
            try {
                int id = Integer.parseInt(idField.getText());
                controller.actualizarProveedor(id, nombreField.getText(), telefonoField.getText(), emailField.getText());
                limpiarCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "⚠️ ID no válido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        eliminarButton.addActionListener(e -> {
            if (controller == null) return;
            try {
                int id = Integer.parseInt(idField.getText());
                controller.eliminarProveedor(id);
                limpiarCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "⚠️ ID no válido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public void setController(ProveedorController controller) {
        this.controller = controller;
    }

    private void limpiarCampos() {
        idField.setText("");
        nombreField.setText("");
        telefonoField.setText("");
        emailField.setText("");
    }
}
