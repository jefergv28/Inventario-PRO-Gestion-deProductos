package controlador;

import dao.ProductoDAO;
import modelo.Producto;
import vista.ProductoView;
import java.sql.Connection;
import java.util.List;
import javax.swing.JOptionPane;

public class ProductoController {
    private ProductoView vistaProducto;
    private ProductoDAO productoDAO;

    public ProductoController(ProductoView vistaProducto, Connection conexion) {
        this.vistaProducto = vistaProducto;
        this.productoDAO = new ProductoDAO(conexion);
        System.out.println("✅ Conexión establecida correctamente con la base de datos.");
        System.out.println("📌 ProductoController inicializado correctamente");


        this.vistaProducto.addInsertarListener(e -> {
            System.out.println("🛠 Botón 'Insertar' presionado"); // <-- Depuración

            try {
                String nombre = vistaProducto.getNombre();
                String descripcion = vistaProducto.getDescripcion();
                String categoria = vistaProducto.getCategoria();
                double precio = vistaProducto.getPrecio();
                int cantidad = vistaProducto.getCantidad();

                System.out.println("📌 Datos obtenidos: " + nombre + ", " + descripcion + ", " + categoria + ", " + precio + ", " + cantidad);

                agregarProducto(nombre, descripcion, categoria, precio, cantidad);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vistaProducto, "Precio y cantidad deben ser valores numéricos.");
            }
        });



        this.vistaProducto.addActualizarListener(e -> actualizarProducto());
        this.vistaProducto.addEliminarListener(e -> eliminarProducto());
        cargarProductos();
    }

    // 🔹 Agregar producto
    private void agregarProducto(String nombre, String descripcion, String categoria, double precio, int cantidad) {
        System.out.println("🚀 Llamando a agregarProducto() con datos: " + nombre + ", " + descripcion + ", " + categoria + ", " + precio + ", " + cantidad);
        Producto nuevoProducto = new Producto(nombre, descripcion, categoria, precio, cantidad);
        if (productoDAO.agregarProducto(nuevoProducto)) {
            JOptionPane.showMessageDialog(vistaProducto, "Producto agregado correctamente.");
            cargarProductos();
        } else {
            JOptionPane.showMessageDialog(vistaProducto, "Error al agregar producto.");
        }
    }



    // 🔹 Actualizar producto
    private void actualizarProducto() {
        try {
            int id = vistaProducto.getIdSeleccionado();
            String nombre = vistaProducto.getNombre();
            String descripcion = vistaProducto.getDescripcion();
            String categoria = vistaProducto.getCategoria();
            double precio = Double.parseDouble(String.valueOf(vistaProducto.getPrecio()));
            int cantidad = Integer.parseInt(String.valueOf(vistaProducto.getCantidad()));


            Producto productoActualizado = new Producto(id, nombre, descripcion,categoria, precio, cantidad); // ✅ Agregar id
            if (productoDAO.actualizarProducto(productoActualizado)) {
                JOptionPane.showMessageDialog(vistaProducto, "Producto actualizado correctamente.");
                cargarProductos();
            } else {
                JOptionPane.showMessageDialog(vistaProducto, "Error al actualizar producto.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vistaProducto, "Precio y cantidad deben ser numéricos.");
        }
    }


    // 🔹 Eliminar producto
    private void eliminarProducto() {
        int id = vistaProducto.getIdSeleccionado();
        if (id != -1) {
            if (productoDAO.eliminarProducto(id)) {
                JOptionPane.showMessageDialog(vistaProducto, "Producto eliminado correctamente.");
                cargarProductos();
            } else {
                JOptionPane.showMessageDialog(vistaProducto, "Error al eliminar producto.");
            }
        } else {
            JOptionPane.showMessageDialog(vistaProducto, "Seleccione un producto para eliminar.");
        }
    }

    // 🔹 Cargar lista de productos
    private void cargarProductos() {
        List<Producto> productos = productoDAO.obtenerTodosLosProductos();
        vistaProducto.mostrarProductos(productos);
    }
}
