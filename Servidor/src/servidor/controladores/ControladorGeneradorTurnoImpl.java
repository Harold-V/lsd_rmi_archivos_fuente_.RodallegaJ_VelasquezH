package servidor.controladores;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import servidor.DTO.NodoTurnoDTO;
import servidor.Repositorios.GeneradorTurnoRepositoryImpl;

public class ControladorGeneradorTurnoImpl extends UnicastRemoteObject implements ControladorGeneradorTurnoInt {

    private final GeneradorTurnoRepositoryImpl objRepositorio;

    public ControladorGeneradorTurnoImpl(GeneradorTurnoRepositoryImpl objRepositorio) throws RemoteException {
        super();
        this.objRepositorio = objRepositorio;

    }

    @Override
    public NodoTurnoDTO generarTurno(String identificacion) throws RemoteException {
        return this.objRepositorio.generarTurno(identificacion);
    }

    @Override
    public boolean estadoActivo() throws RemoteException {
        return this.objRepositorio.estadoActivo();
    }
}
