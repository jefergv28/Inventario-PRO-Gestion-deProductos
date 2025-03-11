package modelo;

public class UsuarioSecundario {
    private int id;
    private String nombre;
    private String email;
    private String telefono;
    private String contrasena;
    private int idAdministrador; // Relación con el administrador

    public UsuarioSecundario(int id, String nombre, String email, String telefono, String contrasena, int idAdministrador) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.contrasena = contrasena;
        this.idAdministrador = idAdministrador;
    }

    public UsuarioSecundario() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public int getIdAdministrador() { return idAdministrador; }
    public void setIdAdministrador(int idAdministrador) { this.idAdministrador = idAdministrador; }

    @Override
    public String toString() {
        return "UsuarioSecundario [ID: " + id + ", Nombre: " + nombre + ", Email: " + email + ", Teléfono: " + telefono + ", ID Administrador: " + idAdministrador + "]";
    }
}
