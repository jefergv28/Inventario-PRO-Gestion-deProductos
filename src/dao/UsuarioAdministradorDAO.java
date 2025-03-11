package dao;

import modelo.UsuarioAdministrador;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioAdministradorDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/inventario_pro";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static boolean insertarAdministrador(String nombre, String email, String telefono, String contrasena) {
        String sql = "INSERT INTO administradores (nombre, email, telefono, contrasena) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, email);
            stmt.setString(3, telefono);
            stmt.setString(4, contrasena);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<UsuarioAdministrador> consultarAdministradores() {
        List<UsuarioAdministrador> lista = new ArrayList<>();
        String sql = "SELECT * FROM administradores";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new UsuarioAdministrador(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        rs.getString("contrasena"),
                        rs.getString("rol")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public static boolean actualizarAdministrador(int id, String nombre, String email, String telefono, String contrasena) {
        String sql = "UPDATE administradores SET nombre = ?, email = ?, telefono = ?, contrasena = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, email);
            stmt.setString(3, telefono);
            stmt.setString(4, contrasena);
            stmt.setInt(5, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean eliminarAdministrador(int id) {
        String sql = "DELETE FROM administradores WHERE id = ?";
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
