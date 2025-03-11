package dao;

import modelo.UsuarioSecundario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioSecundarioDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/inventario_pro";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static String contrasena;

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static boolean insertarUsuarioSecundario(String nombre, String email, String telefono) {
        String sql = "INSERT INTO usuarios_secundarios (nombre, email, telefono, contrasena, id_administrador) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, email);
            stmt.setString(3, telefono);
            stmt.setString(4, contrasena);
            int idAdministrador = 0;
            stmt.setInt(5, idAdministrador);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<UsuarioSecundario> consultarUsuariosSecundarios() {
        List<UsuarioSecundario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios_secundarios";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new UsuarioSecundario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        rs.getString("contrasena"),
                        rs.getInt("id_administrador")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public static boolean actualizarUsuarioSecundario(int id, String nombre, String email, String telefono) {
        String sql = "UPDATE usuarios_secundarios SET nombre = ?, email = ?, telefono = ?, contrasena = ?, id_administrador = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, email);
            stmt.setString(3, telefono);
            stmt.setString(4, contrasena);
            int idAdministrador = 0;
            stmt.setInt(5, idAdministrador);
            stmt.setInt(6, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean eliminarUsuarioSecundario(int id) {
        String sql = "DELETE FROM usuarios_secundarios WHERE id = ?";
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
