package servidor.controladores;

import java.rmi.Remote;
import java.rmi.RemoteException;
import servidor.DTO.NotificacionDTO;

//Hereda de la clase Remote, lo cual la convierte en interfaz remota
public interface ControladorDisplayInt extends Remote {
    
    public void mostrarNotificacion(NotificacionDTO objNotificacion) throws RemoteException;
    
}
