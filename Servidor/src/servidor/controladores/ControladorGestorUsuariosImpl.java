package servidor.controladores;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import servidor.Repositorios.UsuariosRepositoryInt;

public class ControladorGestorUsuariosImpl extends UnicastRemoteObject implements ControladorGestorUsuariosInt {

    private final UsuariosRepositoryInt objUsuariosRepository;

    public ControladorGestorUsuariosImpl(UsuariosRepositoryInt objUsuariosRepository) throws RemoteException {
        super();
        this.objUsuariosRepository = objUsuariosRepository;
    }

    @Override
    public boolean iniciarSesion(String user, String password) throws RemoteException {
        return this.objUsuariosRepository.iniciarSesion(user, password);
    }

    @Override
    public void activarSistema() throws RemoteException {
        this.objUsuariosRepository.activarSistema();
    }

    @Override
    public void desactivarSistema() throws RemoteException {
        this.objUsuariosRepository.desactivarSistema();
    }

}
