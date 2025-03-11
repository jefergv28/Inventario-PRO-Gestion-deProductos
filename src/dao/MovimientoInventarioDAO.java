package dao;

import modelo.MovimientoInventario;
import modelo.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovimientoInventarioDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/inventario_pro";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static boolean registrarMovimiento(int productoId, int cantidad, String tipoMovimiento) {
        String sql = "INSERT INTO movimientos_inventario (producto_id, cantidad, tipo_movimiento) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productoId);
            stmt.setInt(2, cantidad);
            stmt.setString(3, tipoMovimiento);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<MovimientoInventario> obtenerMovimientos() {
        List<MovimientoInventario> movimientos = new ArrayList<>();
        String sql = "SELECT * FROM movimientos_inventario";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                movimientos.add(new MovimientoInventario(
                        rs.getInt("id"),
                        rs.getInt("producto_id"),
                        rs.getInt("cantidad"),
                        rs.getString("tipo_movimiento"),
                        rs.getTimestamp("fecha_movimiento")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movimientos;
    }

    public static boolean eliminarMovimiento(int id) {
        String sql = "DELETE FROM movimientos_inventario WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean insertarMovimiento(int productoId, int cantidad, String tipo) {
        return false;
    }

    public static List<MovimientoInventario> consultarMovimientos() {
        return null;
    }

    public static class ProductoDAO {
        private Connection conexion;

        public ProductoDAO(Connection conexion) {
            this.conexion = conexion;
        }

        // Método para insertar un producto
        public boolean insertarProducto(Producto producto) throws SQLException {
            String sql = "INSERT INTO productos (nombre, descripcion, precio, cantidad) VALUES (?, ?, ?, ?)";

            try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
                stmt.setString(1, producto.getNombre());
                stmt.setString(2, producto.getDescripcion());
                stmt.setDouble(3, producto.getPrecio());
                stmt.setInt(4, producto.getCantidad());
                return stmt.executeUpdate() > 0;
            }
        }

        // Método para obtener la lista de productos
        public List<Producto> consultarProductos() throws SQLException {
            List<Producto> listaProductos = new ArrayList<>(); // ✅ Ahora está definida

            String sql = "SELECT * FROM productos";
            try (PreparedStatement stmt = conexion.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    Producto producto = new Producto(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("descripcion"),
                            rs.getString("categoria"),
                            rs.getDouble("precio"),
                            rs.getInt("cantidad")
                    );
                    listaProductos.add(producto); // ✅ Ahora sí existe la lista
                }
            }
            return listaProductos;
        }

        // Método para actualizar un producto
        public boolean actualizarProducto(Producto producto) throws SQLException {
            String sql = "UPDATE productos SET nombre = ?, descripcion = ?, precio = ?, cantidad = ? WHERE id = ?";

            try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
                stmt.setString(1, producto.getNombre());
                stmt.setString(2, producto.getDescripcion());
                stmt.setDouble(3, producto.getPrecio());
                stmt.setInt(4, producto.getCantidad());
                stmt.setInt(5, producto.getId());
                return stmt.executeUpdate() > 0;
            }
        }

        // Método para eliminar un producto
        public boolean eliminarProducto(int id) throws SQLException {
            String sql = "DELETE FROM productos WHERE id = ?";

            try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
                stmt.setInt(1, id);
                return stmt.executeUpdate() > 0;
            }
        }
    }
}
