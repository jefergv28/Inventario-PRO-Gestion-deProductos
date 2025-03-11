package controlador;

import modelo.UsuarioSecundario;
import dao.UsuarioSecundarioDAO;
import vista.ClienteView;


import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioSecundarioController {
    private ClienteView view;

    public UsuarioSecundarioController(ClienteView view) {
        this.view = view;
    }

    public void agregarUsuarioSecundario(String nombre, String email, String contraseña) {
        boolean exito = UsuarioSecundarioDAO.insertarUsuarioSecundario(nombre, email, contraseña);
        JOptionPane.showMessageDialog(view, exito ? "Usuario secundario agregado correctamente" : "Error al agregar el usuario secundario");
    }

    public List<String> consultarUsuariosSecundarios() {
        List<UsuarioSecundario> listaUsuarios = UsuarioSecundarioDAO.consultarUsuariosSecundarios();

        if (listaUsuarios.isEmpty()) {
            JOptionPane.showMessageDialog(view, "No hay usuarios secundarios en la base de datos");
        } else {
            List<String> usuariosString = new ArrayList<>();
            for (UsuarioSecundario u : listaUsuarios) {
                usuariosString.add("ID: " + u.getId() + " | Nombre: " + u.getNombre() +
                        " | Email: " + u.getEmail());
            }
            String resultado = String.join("\n", usuariosString);
            JOptionPane.showMessageDialog(view, resultado, "Lista de Usuarios Secundarios", JOptionPane.INFORMATION_MESSAGE);
        }
        return null;
    }

    public void actualizarUsuarioSecundario(int id, String nombre, String email, String contraseña) {
        boolean exito = UsuarioSecundarioDAO.actualizarUsuarioSecundario(id, nombre, email, contraseña);
        JOptionPane.showMessageDialog(view, exito ? "Usuario secundario actualizado correctamente" : "Error al actualizar el usuario secundario");
    }

    public void eliminarUsuarioSecundario(int id) {
        boolean exito = UsuarioSecundarioDAO.eliminarUsuarioSecundario(id);
        JOptionPane.showMessageDialog(view, exito ? "Usuario secundario eliminado correctamente" : "Error al eliminar el usuario secundario");
    }
}
