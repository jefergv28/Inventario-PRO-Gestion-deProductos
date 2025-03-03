package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controlador.ProductoController;

public class ProductoView extends JFrame {
    private JTextField txtId, txtNombre, txtDescripcion, txtPrecio, txtCantidad;
    private JButton btnAgregar, btnConsultar, btnActualizar, btnEliminar;
    private JTextArea textArea;
    private ProductoController controller;

    public ProductoView(ProductoController controller) {
        this.controller = controller;
        setTitle("Gestión de Productos");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configurar el layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Crear componentes
        JLabel lblId = new JLabel("ID:");
        txtId = new JTextField(10);
        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField(20);
        JLabel lblDescripcion = new JLabel("Descripción:");
        txtDescripcion = new JTextField(20);
        JLabel lblPrecio = new JLabel("Precio:");
        txtPrecio = new JTextField(10);
        JLabel lblCantidad = new JLabel("Cantidad:");
        txtCantidad = new JTextField(10);

        btnAgregar = new JButton("Agregar Producto");
        btnConsultar = new JButton("Consultar Productos");
        btnActualizar = new JButton("Actualizar Producto");
        btnEliminar = new JButton("Eliminar Producto");
        textArea = new JTextArea(10, 40);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Agregar componentes a la interfaz
        gbc.gridx = 0; gbc.gridy = 0;
        add(lblId, gbc);
        gbc.gridx = 1;
        add(txtId, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(lblNombre, gbc);
        gbc.gridx = 1;
        add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(lblDescripcion, gbc);
        gbc.gridx = 1;
        add(txtDescripcion, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(lblPrecio, gbc);
        gbc.gridx = 1;
        add(txtPrecio, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        add(lblCantidad, gbc);
        gbc.gridx = 1;
        add(txtCantidad, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        add(btnAgregar, gbc);
        gbc.gridx = 1;
        add(btnConsultar, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        add(btnActualizar, gbc);
        gbc.gridx = 1;
        add(btnEliminar, gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        add(scrollPane, gbc);

        // Agregar eventos
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.agregarProducto(
                        txtNombre.getText(),
                        txtDescripcion.getText(),
                        Double.parseDouble(txtPrecio.getText()),
                        Integer.parseInt(txtCantidad.getText())
                );
            }
        });

        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.consultarProductos();
            }
        });

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.actualizarProducto(
                        Integer.parseInt(txtId.getText()),
                        txtNombre.getText(),
                        txtDescripcion.getText(),
                        Double.parseDouble(txtPrecio.getText()),
                        Integer.parseInt(txtCantidad.getText())
                );
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.eliminarProducto(Integer.parseInt(txtId.getText()));
            }
        });

        setVisible(true);
    }
}
