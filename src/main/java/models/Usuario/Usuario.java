package main.java.models.Usuario;

public class Usuario {

    private final int id;
    private String nombre;
    private String password;
    private TipoUsuario tipo;

    public Usuario(int id, String nombre, String password, TipoUsuario tipo) {
        this.id = id;
        this.nombre = nombre;
        this.password = password;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public boolean validar(String nombre, String password) {
        return this.nombre.equals(nombre) && this.password.equals(password);
    }

}
