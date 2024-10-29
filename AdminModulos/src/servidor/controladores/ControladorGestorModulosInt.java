package servidor.controladores;

import java.rmi.Remote;
import java.rmi.RemoteException;

//Hereda de la clase Remote, lo cual la convierte en interfaz remota
public interface ControladorGestorModulosInt extends Remote {
    
    public void liberarModulo(int numeroModulo) throws RemoteException;
    public void moduloActivo(int numeroModulo) throws RemoteException;
    public void moduloInactivo(int numeroModulo) throws RemoteException;

}
