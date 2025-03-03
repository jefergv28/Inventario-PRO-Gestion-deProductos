package controlador;

import modelo.Producto;
import modelo.ProductoDAO;
import vista.ProductoView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoController {
    private ProductoView view;

    public ProductoController(ProductoView view) {
        this.view = view;
    }

    public void agregarProducto(String nombre, String descripcion, double precio, int cantidad) {
        boolean exito = ProductoDAO.insertarProducto(nombre, descripcion, precio, cantidad);
        JOptionPane.showMessageDialog(view, exito ? "Producto agregado correctamente" : "Error al agregar el producto");
    }

    public List<String> consultarProductos() {
        List<Producto> listaProductos = ProductoDAO.consultarProductos(); // Cambia String por Producto

        if (listaProductos.isEmpty()) {
            JOptionPane.showMessageDialog(view, "No hay productos en la base de datos");
        } else {
            List<String> productosString = new ArrayList<>();
            for (Producto p : listaProductos) {
                productosString.add("ID: " + p.getId() + " | Nombre: " + p.getNombre() +
                        " | Descripción: " + p.getDescripcion() +
                        " | Precio: " + p.getPrecio() +
                        " | Cantidad: " + p.getCantidad());
            }
            String resultado = String.join("\n", productosString);
            JOptionPane.showMessageDialog(view, resultado, "Lista de Productos", JOptionPane.INFORMATION_MESSAGE);
        }
        return null;
    }


    public void actualizarProducto(int id, String nombre, String descripcion, double precio, int cantidad) {
        boolean exito = ProductoDAO.actualizarProducto(id, nombre, descripcion, precio, cantidad);
        JOptionPane.showMessageDialog(view, exito ? "Producto actualizado correctamente" : "Error al actualizar el producto");
    }

    public void eliminarProducto(int id) {
        boolean exito = ProductoDAO.eliminarProducto(id);
        JOptionPane.showMessageDialog(view, exito ? "Producto eliminado correctamente" : "Error al eliminar el producto");
    }
}
