package servidor.Repositorios;

public class UsuariosRepositoryImpl implements UsuariosRepositoryInt {

    private boolean estadoSistema = false;

    private String userAdmin = "haroldrmi";
    private String passwordAdmin = "12345678";

    @Override
    public boolean iniciarSesion(String user, String password) {
        System.out.println("==Entrando a iniciar Sesion==");
        boolean bandera = false;

        if ((user.length() >= 8 && user.length() <= 15) && (password.length() >= 8 && password.length() <= 15)) {
            if (userAdmin.equals(user) && passwordAdmin.equals(password)) {
                System.out.println("Inicio de sesion Exitoso");
                bandera = true;
            } else {
                System.out.println("No se encontró el usuario: \nUser: " + user + "\nPassword: " + password);
            }
        } else {
            System.out.println("El usuario o la contraseña deben tener entre 8 y 15 caracteres.");
        }

        return bandera;
    }

    public boolean isEstadoSistema() {
        return estadoSistema;
    }

    @Override
    public void activarSistema() {
        estadoSistema = true;
    }

    @Override
    public void desactivarSistema() {
        estadoSistema = false;
    }
}
