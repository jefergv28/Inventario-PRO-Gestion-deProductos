package controlador;

import modelo.Proveedor;
import dao.ProveedorDAO;
import vista.ProveedorView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorController {
    private ProveedorView view;

    public ProveedorController(ProveedorView view) {
        this.view = view;
    }



    public void agregarProveedor(String nombre, String email, String telefono) {
        boolean exito = ProveedorDAO.insertarProveedor(nombre, email, telefono);
        JOptionPane.showMessageDialog(view, exito ? "Proveedor agregado correctamente" : "Error al agregar el proveedor");
    }

    public List<String> consultarProveedores() {
        List<Proveedor> listaProveedores = ProveedorDAO.consultarProveedores();

        List<String> proveedoresString = new ArrayList<>();
        for (Proveedor p : listaProveedores) {
            proveedoresString.add("ID: " + p.getId() + " | Nombre: " + p.getNombre() +
                    " | Email: " + p.getEmail() + " | Teléfono: " + p.getTelefono());
        }
        return proveedoresString; // Retorna la lista de proveedores como String
    }

    public void actualizarProveedor(int id, String nombre, String email, String telefono) {
        boolean exito = ProveedorDAO.actualizarProveedor(id, nombre, email, telefono);
        JOptionPane.showMessageDialog(view, exito ? "Proveedor actualizado correctamente" : "Error al actualizar el proveedor");
    }

    public void eliminarProveedor(int id) {
        boolean exito = ProveedorDAO.eliminarProveedor(id);
        JOptionPane.showMessageDialog(view, exito ? "Proveedor eliminado correctamente" : "Error al eliminar el proveedor");
    }
}
