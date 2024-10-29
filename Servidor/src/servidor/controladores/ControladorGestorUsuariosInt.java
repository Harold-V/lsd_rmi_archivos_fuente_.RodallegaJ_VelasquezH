package servidor.controladores;

import java.rmi.Remote;
import java.rmi.RemoteException;

//Hereda de la clase Remote, lo cual la convierte en interfaz remota
public interface ControladorGestorUsuariosInt extends Remote {

    public boolean iniciarSesion(String user, String password) throws RemoteException;

    public void activarSistema() throws RemoteException;

    public void desactivarSistema() throws RemoteException;

}
