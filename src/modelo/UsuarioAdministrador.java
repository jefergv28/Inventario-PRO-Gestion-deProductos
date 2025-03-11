package modelo;

public class UsuarioAdministrador {
    private int id;
    private String nombre;
    private String email;
    private String telefono;
    private String contrasena;
    private String rol;  // Agregado el atributo rol

    // Constructor con el atributo rol
    public UsuarioAdministrador(int id, String nombre, String email, String telefono, String contrasena, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    // Constructor vacío
    public UsuarioAdministrador() {}

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;  // Método getter para rol
    }

    public void setRol(String rol) {
        this.rol = rol;  // Método setter para rol
    }

    @Override
    public String toString() {
        return "UsuarioAdministrador [ID: " + id + ", Nombre: " + nombre + ", Email: " + email + ", Teléfono: " + telefono + ", Rol: " + rol + "]";
    }
}
