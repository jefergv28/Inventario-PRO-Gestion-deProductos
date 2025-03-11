package dao;

import modelo.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao {
    private Connection connection;

    // Constructor para recibir la conexión
    public ClienteDao(Connection connection) {
        this.connection = connection;
    }

    // Método para insertar un cliente en la base de datos
    public boolean InsertaCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO cliente (nombreCompleto, email, telefono) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNombreCompleto());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getTelefono());
            return stmt.executeUpdate() > 0;
        }
    }


    // Método para obtener los clientes
    public List<Cliente> obtenerClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Cliente cliente = new Cliente(
                        resultSet.getInt("idCliente"),
                        resultSet.getString("nombreCompleto"),
                        resultSet.getString("email"),
                        resultSet.getString("telefono") // Añadido el teléfono aquí
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los clientes: " + e.getMessage());
            e.printStackTrace();
            throw new SQLException("Error al obtener los clientes", e);
        }
        return clientes;
    }

    // Método para actualizar un cliente
    public boolean actualizarCliente(Cliente cliente) throws SQLException {
        String sql = "UPDATE cliente SET nombreCompleto = ?, email = ?, telefono = ? WHERE idCliente = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cliente.getNombreCompleto());
            statement.setString(2, cliente.getEmail());
            statement.setString(3, cliente.getTelefono());
            statement.setInt(4, cliente.getIdCliente());
            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0; // Retorna true si la actualización fue exitosa
        } catch (SQLException e) {
            System.err.println("Error al actualizar el cliente: " + e.getMessage());
            e.printStackTrace();
            throw new SQLException("Error al actualizar el cliente", e);
        }
    }

    // Método para eliminar un cliente
    public boolean eliminarCliente(int idCliente) throws SQLException {
        String sql = "DELETE FROM cliente WHERE idCliente = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idCliente);
            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0; // Retorna true si la eliminación fue exitosa
        } catch (SQLException e) {
            System.err.println("Error al eliminar el cliente: " + e.getMessage());
            e.printStackTrace();
            throw new SQLException("Error al eliminar el cliente", e);
        }
    }
}
