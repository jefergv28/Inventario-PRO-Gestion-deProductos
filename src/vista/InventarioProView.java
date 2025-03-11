package vista;

import javax.swing.*;

public class InventarioProView extends JFrame {
    private JTabbedPane tabbedPane;
    private ProductoView productoView;

    public InventarioProView() {
        setTitle("Gestión de Inventario");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();

        // Agregar la pestaña de Productos
        productoView = new ProductoView();
        tabbedPane.add("Productos", productoView);

        // Agregar más pestañas aquí para Clientes, Proveedores, Usuarios y Movimientos

        add(tabbedPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InventarioProView().setVisible(true));
    }
}

