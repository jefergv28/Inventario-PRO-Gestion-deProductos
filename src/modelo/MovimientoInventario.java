package modelo;

import java.util.Date;

public class MovimientoInventario {
    private int id;
    private int productoId;
    private int cantidad;
    private String tipoMovimiento; // "entrada" o "salida"
    private Date fechaMovimiento;

    public MovimientoInventario(int id, int productoId, int cantidad, String tipoMovimiento, Date fechaMovimiento) {
        this.id = id;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.tipoMovimiento = tipoMovimiento;
        this.fechaMovimiento = fechaMovimiento;
    }

    public MovimientoInventario() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getProductoId() { return productoId; }
    public void setProductoId(int productoId) { this.productoId = productoId; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public String getTipoMovimiento() { return tipoMovimiento; }
    public void setTipoMovimiento(String tipoMovimiento) { this.tipoMovimiento = tipoMovimiento; }

    public Date getFechaMovimiento() { return fechaMovimiento; }
    public void setFechaMovimiento(Date fechaMovimiento) { this.fechaMovimiento = fechaMovimiento; }

    @Override
    public String toString() {
        return "MovimientoInventario [ID: " + id + ", Producto ID: " + productoId +
                ", Cantidad: " + cantidad + ", Tipo: " + tipoMovimiento +
                ", Fecha: " + fechaMovimiento + "]";
    }

    public char[] getTipo() {
        return null;
    }

    public char[] getFecha() {
        return null;
    }
}
