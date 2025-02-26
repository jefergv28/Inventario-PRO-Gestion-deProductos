import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // Insertar un producto
        DatabaseConnection.insertarProducto("lampara", "iluminacion", 3000.20, 10);

        // Consultar productos
        System.out.println("Lista de productos:");
        DatabaseConnection.consultarProductos();

        // Actualizar un producto
        DatabaseConnection.actualizarProducto(1, "Laptop Gamer", "PC con mejor rendimiento", 1500.00, 5);

        // Eliminar un producto
        DatabaseConnection.eliminarProducto(4);

        // Consultar productos después de la eliminación
        System.out.println("Lista después de eliminar:");
        DatabaseConnection.consultarProductos();
    }
}
