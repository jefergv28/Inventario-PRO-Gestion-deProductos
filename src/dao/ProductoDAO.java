package dao;

import modelo.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    private Connection conexion;

    public ProductoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    // 🔹 Agregar producto
    public boolean agregarProducto(Producto producto) {
        System.out.println("🔹 Método agregarProducto() llamado"); // ← Depuración

        if (conexion == null) {
            System.out.println("❌ Error: La conexión es NULL");
            return false;
        }

        String sql = "INSERT INTO productos (nombre, descripcion, precio, cantidad, categoria) VALUES (?, ?, ?, ?, ?)";
        System.out.println("🔹 Query SQL: " + sql);

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getDescripcion());
            stmt.setDouble(3, producto.getPrecio());
            stmt.setInt(4, producto.getCantidad());
            stmt.setString(5, producto.getCategoria());

            System.out.println("🔹 Datos a insertar: " + producto.getNombre() + ", " + producto.getDescripcion() +
                    ", " + producto.getPrecio() + ", " + producto.getCantidad() + ", " + producto.getCategoria());

            int filasAfectadas = stmt.executeUpdate();
            System.out.println("✔ Filas insertadas: " + filasAfectadas);

            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error al insertar producto:");
            e.printStackTrace();
            return false;
        }
    }


    // 🔹 Obtener todos los productos
    public List<Producto> obtenerTodosLosProductos() {
        List<Producto> listaProductos = new ArrayList<>();
        String sql = "SELECT * FROM productos";

        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Producto producto = new Producto(
                        rs.getInt("id"),             // ID como int
                        rs.getString("nombre"),      // Nombre como String
                        rs.getString("descripcion"), // Descripción como String
                        rs.getString("categoria"),   // Categoría como String (si existe)
                        rs.getDouble("precio"),      // Precio como double
                        rs.getInt("cantidad")        // Cantidad como int
                );
                listaProductos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaProductos;
    }


    // 🔹 Actualizar producto
    public boolean actualizarProducto(Producto producto) {
        String sql = "UPDATE productos SET nombre = ?, descripcion = ?, precio = ?, cantidad = ? WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getDescripcion());
            stmt.setDouble(3, producto.getPrecio());
            stmt.setInt(4, producto.getCantidad());
            stmt.setInt(5, producto.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 🔹 Eliminar producto por ID
    public boolean eliminarProducto(int id) {
        String sql = "DELETE FROM productos WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            System.out.println("Intentando eliminar ID: " + id); // 🔍 Verifica el ID antes de ejecutar
            stmt.setInt(1, id);

            int filasAfectadas = stmt.executeUpdate();
            System.out.println("Filas eliminadas: " + filasAfectadas);

            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
