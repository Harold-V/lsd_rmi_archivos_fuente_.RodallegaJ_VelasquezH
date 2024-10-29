package servidor.controladores;

import java.rmi.Remote;
import java.rmi.RemoteException;
import servidor.DTO.NodoTurnoDTO;

//Hereda de la clase Remote, lo cual la convierte en interfaz remota
public interface ControladorGeneradorTurnoInt extends Remote {
    
    public NodoTurnoDTO generarTurno(String identificacion) throws RemoteException;
    
     public boolean estadoActivo() throws RemoteException;
}
