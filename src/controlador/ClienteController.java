package controlador;

import modelo.Cliente;
import dao.ClienteDao;
import vista.ClienteView;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteController  {
    private ClienteView view;
    private ClienteDao clienteDao;

    public ClienteController(ClienteView view, ClienteDao clienteDao) {
        this.view = view;
        this.clienteDao = clienteDao;
    }

    // Método para agregar un cliente
    public void agregarCliente(String nombre, String email, String telefono) {
        System.out.println("📌 Método agregarCliente() llamado con: " + nombre + ", " + email + ", " + telefono);

        Cliente cliente = new Cliente();
        cliente.setNombreCompleto(nombre);
        cliente.setEmail(email);
        cliente.setTelefono(telefono);

        try {
            System.out.println("🔍 Llamando a clienteDao.InsertaCliente...");
            boolean exito = clienteDao.InsertaCliente(cliente);
            System.out.println("✅ Resultado de InsertaCliente: " + exito);

            JOptionPane.showMessageDialog(view, exito ? "Cliente agregado correctamente" : "Error al agregar el cliente");
        } catch (SQLException e) {
            System.out.println("❌ Excepción al agregar cliente: " + e.getMessage());
            JOptionPane.showMessageDialog(view, "Error al agregar el cliente: " + e.getMessage());
        }
    }


    // Método para consultar clientes
    public void consultarClientes() {
        try {
            List<Cliente> listaClientes = clienteDao.obtenerClientes();  // Llamada al DAO

            if (listaClientes.isEmpty()) {
                JOptionPane.showMessageDialog(view, "No hay clientes en la base de datos");
            } else {
                List<String> clientesString = new ArrayList<>();
                for (Cliente c : listaClientes) {
                    clientesString.add("ID: " + c.getIdCliente() + " | Nombre: " + c.getNombreCompleto() +
                            " | Email: " + c.getEmail() +
                            " | Teléfono: " + c.getTelefono());
                }
                String resultado = String.join("\n", clientesString);
                JOptionPane.showMessageDialog(view, resultado, "Lista de Clientes", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Error al consultar los clientes: " + e.getMessage());
        }
    }

    // Método para actualizar un cliente
    public void actualizarCliente(int id, String nombre, String email, String telefono) {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(id);
        cliente.setNombreCompleto(nombre);
        cliente.setEmail(email);
        cliente.setTelefono(telefono);

        try {
            boolean exito = clienteDao.actualizarCliente(cliente);  // Llamada al DAO
            JOptionPane.showMessageDialog(view, exito ? "Cliente actualizado correctamente" : "Error al actualizar el cliente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Error al actualizar el cliente: " + e.getMessage());
        }
    }

    // Método para eliminar un cliente
    public void eliminarCliente(int id) {
        try {
            boolean exito = clienteDao.eliminarCliente(id);  // Llamada al DAO
            JOptionPane.showMessageDialog(view, exito ? "Cliente eliminado correctamente" : "Error al eliminar el cliente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Error al eliminar el cliente: " + e.getMessage());
        }
    }

    public void setView(ClienteView view) {
        this.view = view;
    }
}
