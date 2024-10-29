package servidor.Repositorios;

import servidor.DTO.ModuloDTO;
import servidor.DTO.NodoTurnoDTO;
import servidor.DTO.NotificacionDTO;
import servidor.controladores.ControladorDisplayInt;
import java.rmi.RemoteException;

public class ModulosRepositoryImpl implements ModulosRepositoryInt {

    private final GeneradorTurnoRepositoryImpl generadorTurnoRepository;
    private final ControladorDisplayInt objRemotoDisplay;

    public ModulosRepositoryImpl(GeneradorTurnoRepositoryImpl generadorTurnoRepository, ControladorDisplayInt objRemotoDisplay) {
        this.generadorTurnoRepository = generadorTurnoRepository;
        this.objRemotoDisplay = objRemotoDisplay;
    }

    @Override
    public void liberarModulo(int numeroModulo) {
        ModuloDTO[] vectorModulos = generadorTurnoRepository.getVectorModulos();
        if (numeroModulo >= 1 && numeroModulo <= vectorModulos.length) {
            ModuloDTO modulo = vectorModulos[numeroModulo - 1];
            modulo.setOcupado(false);
            System.out.println("El modulo " + numeroModulo + " ha sido liberado.");

            NodoTurnoDTO siguienteUsuario = generadorTurnoRepository.siguienteUsuarioEnFila();
            if (siguienteUsuario != null) {
                modulo.setOcupado(true);
                modulo.setNumeroTurno(siguienteUsuario.getNumeroTurno());
                modulo.setIdentificacion(siguienteUsuario.getIdentificacion());
                System.out.println("El usuario " + siguienteUsuario.getIdentificacion()
                        + " con el turno " + siguienteUsuario.getNumeroTurno()
                        + " ha sido asignado al módulo " + numeroModulo);
            }

            notificarDisplay();
        } else {
            System.out.println("Numero de modulo inválido.");
        }
    }

    @Override
    public void moduloActivo(int numeroModulo) {
        ModuloDTO[] vectorModulos = generadorTurnoRepository.getVectorModulos();
        if (numeroModulo >= 1 && numeroModulo <= vectorModulos.length) {
            vectorModulos[numeroModulo - 1].setActivo(true);
            System.out.println("El modulo " + numeroModulo + " esta ahora activo.");
            liberarModulo(numeroModulo);
        } else {
            System.out.println("Numero de modulo invalido.");
        }
    }

    @Override
    public void moduloInactivo(int numeroModulo) {
        ModuloDTO[] vectorModulos = generadorTurnoRepository.getVectorModulos();
        if (numeroModulo >= 1 && numeroModulo <= vectorModulos.length) {
            vectorModulos[numeroModulo - 1].setActivo(false);
            System.out.println("El modulo " + numeroModulo + " ha sido desactivado.");
            notificarDisplay();
        } else {
            System.out.println("Numero de modulo invalido.");
        }
    }

    private void notificarDisplay() {
        NotificacionDTO objNotificacion = new NotificacionDTO();
        objNotificacion.setVectorModulos(generadorTurnoRepository.getVectorModulos());
        objNotificacion.setCantidadUsuariosFilaVirtual(generadorTurnoRepository.getCantidadUsuariosFila());

        try {
            objRemotoDisplay.mostrarNotificacion(objNotificacion);
            System.out.println("Notificacion enviada al servidor display");
        } catch (RemoteException ex) {
            System.out.println("Error al notificar al servidor display: " + ex.getMessage());
        }
    }
}
