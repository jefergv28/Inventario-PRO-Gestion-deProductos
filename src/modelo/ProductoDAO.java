package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/inventario_pro";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static boolean insertarProducto(String nombre, String descripcion, double precio, int cantidad) {
        String sql = "INSERT INTO productos(nombre, descripcion, precio, cantidad) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, descripcion);
            stmt.setDouble(3, precio);
            stmt.setInt(4, cantidad);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Producto> consultarProductos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                productos.add(new Producto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("precio"),
                        rs.getInt("cantidad")
                ));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }


    public static boolean actualizarProducto(int id, String nombre, String descripcion, double precio, int cantidad) {
        String sql = "UPDATE productos SET nombre = ?, descripcion = ?, precio = ?, cantidad = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, descripcion);
            stmt.setDouble(3, precio);
            stmt.setInt(4, cantidad);
            stmt.setInt(5, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean eliminarProducto(int id) {
        String sql = "DELETE FROM productos WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
