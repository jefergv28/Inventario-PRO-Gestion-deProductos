package vista;

import controlador.ProductoController;
import modelo.Producto;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProductoView extends JPanel {
    private JTextField tfNombre, tfCantidad, tfPrecio, tfCategoria, tfDescripcion, tfId;
    private JButton btnAgregar, btnConsultar, btnActualizar, btnEliminar;
    private JTextArea areaConsulta;

    public ProductoView() {
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10)); // Se agregó una fila más para ID

        formPanel.add(new JLabel("ID:"));
        tfId = new JTextField();
        formPanel.add(tfId);

        formPanel.add(new JLabel("Nombre del Producto:"));
        tfNombre = new JTextField();
        formPanel.add(tfNombre);

        formPanel.add(new JLabel("Descripción:"));
        tfDescripcion = new JTextField();
        formPanel.add(tfDescripcion);

        formPanel.add(new JLabel("Cantidad:"));
        tfCantidad = new JTextField();
        formPanel.add(tfCantidad);

        formPanel.add(new JLabel("Precio:"));
        tfPrecio = new JTextField();
        formPanel.add(tfPrecio);

        formPanel.add(new JLabel("Categoría:"));
        tfCategoria = new JTextField();
        formPanel.add(tfCategoria);

        JPanel buttonPanel = new JPanel();
        btnAgregar = new JButton("Insertar");
        btnConsultar = new JButton("Consultar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");

        buttonPanel.add(btnAgregar);
        buttonPanel.add(btnConsultar);
        buttonPanel.add(btnActualizar);
        buttonPanel.add(btnEliminar);

        areaConsulta = new JTextArea(5, 30);
        areaConsulta.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaConsulta);

        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
    }

    public void addInsertarListener(ActionListener listener) {
        btnAgregar.addActionListener(listener);
    }

    public void addConsultarListener(ActionListener listener) {
        btnConsultar.addActionListener(listener);
    }

    public void addActualizarListener(ActionListener listener) {
        btnActualizar.addActionListener(listener);
    }

    public void addEliminarListener(ActionListener listener) {
        btnEliminar.addActionListener(listener);
    }

    public void mostrarConsulta(String resultado) {
        areaConsulta.setText(resultado);
    }

    public String getNombre() {
        return tfNombre.getText();
    }

    public int getCantidad() {
        try {
            return Integer.parseInt(tfCantidad.getText().trim());  // ✅ Conversión a int
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Cantidad debe ser un número entero.");
            return 0; // Valor por defecto
        }
    }

    public double getPrecio() {
        try {
            return Double.parseDouble(tfPrecio.getText().trim());  // ✅ Conversión a double
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Precio debe ser un número válido.");
            return 0.0; // Valor por defecto
        }
    }



    public String getCategoria() {
        return tfCategoria.getText();
    }

    public int getIdSeleccionado() {
        String idText = tfId.getText().trim();
        if (idText.isEmpty()) {
            return -1;
        }
        try {
            return Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public String getDescripcion() {
        return tfDescripcion.getText();
    }

    public void mostrarProductos(List<Producto> productos) {
        StringBuilder sb = new StringBuilder();

        if (productos.isEmpty()) {
            sb.append("No hay productos registrados.\n");
        } else {
            for (Producto producto : productos) {
                sb.append(producto.toString()).append("\n");
            }
        }

        areaConsulta.setText(sb.toString());
    }


    public void setController(ProductoController productoController) {

    }
}
