package modelo;

public class Cliente {
    private int idCliente;
    private String nombreCompleto;
    private String email;
    private String telefono; // Si lo necesitas en el futuro, puedes agregarlo

    // Constructor con parámetros
    public Cliente(int idCliente, String nombreCompleto, String email, String telefono) {
        this.idCliente = idCliente;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.telefono = telefono;  // Agregado para manejar el teléfono si lo necesitas
    }

    // Constructor vacío
    public Cliente() {}

    // Getters y Setters
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;  // Implementado para que puedas usarlo en el futuro
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
