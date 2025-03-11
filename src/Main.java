import javax.swing.SwingUtilities;
import controlador.ProductoController;
import controlador.ClienteController;
import controlador.ProveedorController;
import vista.GestionInventarioView;
import vista.ProductoView;
import vista.ClienteView;
import vista.ProveedorView;
import dao.ClienteDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/inventario_pro";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        // Llamar al método run() en el hilo de despacho de eventos de Swing
        SwingUtilities.invokeLater(Main::run);
    }

    private static void run() {
        try {
            // Conexión a la base de datos
            Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD);

            // Crear DAO de Cliente
            ClienteDao clienteDao = new ClienteDao(conexion);

            // Crear la vista de Producto y su controlador
            ProductoView vistaProducto = new ProductoView();
            ProductoController productoController = new ProductoController(vistaProducto, conexion);
            vistaProducto.setController(productoController);

            // Crear la vista de Cliente y su controlador
            ClienteView vistaCliente = new ClienteView();
            ClienteController clienteController = new ClienteController(vistaCliente, clienteDao);
            vistaCliente.setController(clienteController);

            // Crear la vista de Proveedores y su controlador
            ProveedorView proveedorView = new ProveedorView();
            ProveedorController proveedorController = new ProveedorController(proveedorView);
            proveedorView.setController(proveedorController);

            // ✅ Corrección: Pasar correctamente la instancia de ProveedorView
            GestionInventarioView frame = new GestionInventarioView(vistaProducto, vistaCliente, proveedorView);

            // Hacer visible la vista de gestión de inventario
            frame.setVisible(true);

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }
}
