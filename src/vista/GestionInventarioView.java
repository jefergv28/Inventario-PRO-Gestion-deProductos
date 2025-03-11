package vista;

import controlador.ClienteController;
import controlador.ProveedorController;
import dao.ClienteDao;
import modelo.Proveedor;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.*;

public class GestionInventarioView extends JFrame {
    private JTabbedPane tabbedPane;

    public GestionInventarioView(ProductoView vistaProducto, ClienteView vistaCliente,  ProveedorView vistaProveedor) {
        setTitle("Gestión de Inventario");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();

        // Agregar la vista Cliente al panel de pestañas
        tabbedPane.addTab("Productos", vistaProducto);
        tabbedPane.addTab("Clientes", vistaCliente);
        tabbedPane.addTab("Proveedores", vistaProveedor);
        tabbedPane.addTab("Usuarios", new UsuarioView());
        tabbedPane.addTab("Movimientos", new MovimientoView());

        add(tabbedPane);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // 1️⃣ Crear la conexión a la base de datos
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/inventario_pro", "usuario", "contraseña"
                );

                // 2️⃣ Crear el DAO
                ClienteDao clienteDao = new ClienteDao(connection);


                // 4️⃣ Crear la vista pasándole el controlador
                ClienteView clienteView = new ClienteView();
                ClienteController clienteController = new ClienteController(clienteView, clienteDao);
                clienteView.setController(clienteController);

                // 4️⃣ Crear la vista y el controlador de Proveedores
                ProveedorView proveedorView = new ProveedorView();
                ProveedorController proveedorController = new ProveedorController(proveedorView);
                proveedorView.setController(proveedorController);

                // 6️⃣ Crear la ventana principal con todas las vistas
                ProductoView vistaProducto = new ProductoView();
                ClienteView vistaCliente = new ClienteView();
                GestionInventarioView frame = new GestionInventarioView(vistaProducto, vistaCliente, proveedorView);
                frame.setVisible(true);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }





}
