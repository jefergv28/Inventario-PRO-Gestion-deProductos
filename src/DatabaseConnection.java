import java.sql.*;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/inventario_pro";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Método para obtener la conexión
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Cargar el driver
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println(" Conexión exitosa a la base de datos");
            return conn;
        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se encontró el driver de MySQL");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error de conexión a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // Método para cerrar la conexión
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexión cerrada");
            } catch (SQLException e) {
                System.out.println(" Error al cerrar la conexión");
                e.printStackTrace();
            }
        }
    }

    // Método para insertar un producto
    public static void insertarProducto(String nombre, String descripcion, double precio, int cantidad) {
        String sql = "INSERT INTO productos(nombre, descripcion, precio, cantidad) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            stmt.setString(2, descripcion);
            stmt.setDouble(3, precio);
            stmt.setInt(4, cantidad);

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println(" Producto insertado correctamente.");
            } else {
                System.out.println(" No se pudo insertar el producto.");
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar el producto.");
            e.printStackTrace();
        }
    }

    // Método para consultar productos
    public static void consultarProductos() {
        String sql = "SELECT * FROM productos";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (!rs.isBeforeFirst()) { // Verifica si hay resultados
                System.out.println("No se encontraron productos en la base de datos.");
                return;
            }

            System.out.println("Listado de Productos:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        " | Nombre: " + rs.getString("nombre") +
                        " | Descripción: " + rs.getString("descripcion") +
                        " | Precio: " + rs.getDouble("precio") +
                        " | Cantidad: " + rs.getInt("cantidad"));
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar productos.");
            e.printStackTrace();
        }
    }

    // Método para actualizar un producto
    public static void actualizarProducto(int id, String nombre, String descripcion, double precio, int cantidad) {
        String sql = "UPDATE productos SET nombre = ?, descripcion = ?, precio = ?, cantidad = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            stmt.setString(2, descripcion);
            stmt.setDouble(3, precio);
            stmt.setInt(4, cantidad);
            stmt.setInt(5, id);

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println(" Producto actualizado correctamente.");
            } else {
                System.out.println("⚠No se encontró el producto con ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println(" Error al actualizar el producto.");
            e.printStackTrace();
        }
    }

    // Método para eliminar un producto
    public static void eliminarProducto(int id) {
        String sql = "DELETE FROM productos WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println(" Producto eliminado correctamente.");
            } else {
                System.out.println(" No se encontró el producto con ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar el producto.");
            e.printStackTrace();
        }
    }

 
}
