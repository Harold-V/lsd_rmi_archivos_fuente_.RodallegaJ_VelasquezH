package servidor.DTO;

import java.io.Serializable;

public class UsuarioDTO implements Serializable
{	
    private String nombres;
    private String apellidos;
    private String user;
    private String password;

    public UsuarioDTO(String nombres, String apellidos, String user, String password) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.user = user;
        this.password = password;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

       
}
