package controlador;

import modelo.MovimientoInventario ;
import dao.MovimientoInventarioDAO;
import vista.MovimientoView;

import javax.swing.*;
import java.util.List;

public class MovimientoController {
    private MovimientoView view;

    public MovimientoController(MovimientoView view) {
        this.view = view;
    }

    public void registrarMovimiento(int productoId, int cantidad, String tipo) {
        boolean exito = MovimientoInventarioDAO.insertarMovimiento(productoId, cantidad, tipo);
        JOptionPane.showMessageDialog(view, exito ? "Movimiento registrado correctamente" : "Error al registrar el movimiento");
    }

    public void consultarMovimientos() {
        List<MovimientoInventario > movimientos = MovimientoInventarioDAO.consultarMovimientos();

        if (movimientos.isEmpty()) {
            JOptionPane.showMessageDialog(view, "No hay movimientos registrados");
        } else {
            StringBuilder resultado = new StringBuilder("Movimientos:\n");
            for (MovimientoInventario  m : movimientos) {
                resultado.append("ID: ").append(m.getId())
                        .append(" | Producto ID: ").append(m.getProductoId())
                        .append(" | Cantidad: ").append(m.getCantidad())
                        .append(" | Tipo: ").append(m.getTipo())
                        .append(" | Fecha: ").append(m.getFecha())
                        .append("\n");
            }
            JOptionPane.showMessageDialog(view, resultado.toString(), "Lista de Movimientos", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
