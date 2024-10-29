package servidor.controladores;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import servidor.Repositorios.ModulosRepositoryInt;

public class ControladorGestorModulosImpl extends UnicastRemoteObject implements ControladorGestorModulosInt {

    private final ModulosRepositoryInt objModulosRepository;

    public ControladorGestorModulosImpl(ModulosRepositoryInt objModulosRepository) throws RemoteException {
        super();
        this.objModulosRepository = objModulosRepository;
    }

    @Override
    public void liberarModulo(int numeroModulo) throws RemoteException {
        this.objModulosRepository.liberarModulo(numeroModulo);
    }

    @Override
    public void moduloActivo(int numeroModulo) throws RemoteException {
        this.objModulosRepository.moduloActivo(numeroModulo);
    }

    @Override
    public void moduloInactivo(int numeroModulo) throws RemoteException {
        this.objModulosRepository.moduloInactivo(numeroModulo);
    }

}
