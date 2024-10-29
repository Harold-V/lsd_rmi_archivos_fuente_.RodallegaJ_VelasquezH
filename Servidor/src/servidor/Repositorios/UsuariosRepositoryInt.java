package servidor.Repositorios;

public interface UsuariosRepositoryInt {

    public boolean iniciarSesion(String user, String password);

    public void activarSistema();

    public void desactivarSistema();

}
