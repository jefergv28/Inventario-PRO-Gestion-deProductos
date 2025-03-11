package modelo;

public class Producto {
    private int id;  // Se usa solo si se lee desde la BD
    private String nombre;
    private String descripcion;
    private String categoria;
    private double precio;
    private int cantidad;

    // 🔹 Constructor para INSERTAR productos (sin ID porque MySQL lo genera)
    public Producto(String nombre, String descripcion, String categoria, double precio, int cantidad) {

        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    // 🔹 Constructor para LEER productos desde la BD (con ID)
    public Producto(int id, String nombre, String descripcion, String categoria, double precio, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    // 🔹 Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    // 🔹 Método toString para depuración
    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre + " | Descripción: " + descripcion +
                " | Categoría: " + categoria + " | Precio: " + precio + " | Cantidad: " + cantidad;
    }
}
