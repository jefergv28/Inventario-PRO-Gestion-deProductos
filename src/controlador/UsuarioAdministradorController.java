package controlador;

import dao.UsuarioAdministradorDAO;
import modelo.UsuarioAdministrador;
import vista.ClienteView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioAdministradorController {
    private ClienteView view;
    private UsuarioAdministradorDAO usuarioAdministradorDAO;

    public UsuarioAdministradorController(ClienteView view) {
        this.view = view;
        this.usuarioAdministradorDAO = new UsuarioAdministradorDAO(); // Crear la instancia del DAO
    }

    // Método para agregar un usuario
    public void agregarUsuario(String nombre, String email, String telefono, String contrasena) {
        boolean exito = usuarioAdministradorDAO.insertarAdministrador(nombre, email, telefono, contrasena);
        JOptionPane.showMessageDialog(view, exito ? "Usuario agregado correctamente" : "Error al agregar el usuario");
    }

    // Método para consultar usuarios
    public void consultarUsuarios() {
        List<UsuarioAdministrador> listaUsuarios = usuarioAdministradorDAO.consultarAdministradores();

        if (listaUsuarios.isEmpty()) {
            JOptionPane.showMessageDialog(view, "No hay usuarios en la base de datos");
        } else {
            List<String> usuariosString = new ArrayList<>();
            for (UsuarioAdministrador u : listaUsuarios) {
                usuariosString.add("ID: " + u.getId() + " | Nombre: " + u.getNombre() +
                        " | Email: " + u.getEmail() +
                        " | Teléfono: " + u.getTelefono() +
                        " | Rol: " + u.getRol());
            }
            String resultado = String.join("\n", usuariosString);
            JOptionPane.showMessageDialog(view, resultado, "Lista de Usuarios", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Método para actualizar un usuario
    public void actualizarUsuario(int id, String nombre, String email, String telefono, String contrasena) {
        boolean exito = usuarioAdministradorDAO.actualizarAdministrador(id, nombre, email, telefono, contrasena);
        JOptionPane.showMessageDialog(view, exito ? "Usuario actualizado correctamente" : "Error al actualizar el usuario");
    }

    // Método para eliminar un usuario
    public void eliminarUsuario(int id) {
        boolean exito = usuarioAdministradorDAO.eliminarAdministrador(id);
        JOptionPane.showMessageDialog(view, exito ? "Usuario eliminado correctamente" : "Error al eliminar el usuario");
    }
}
