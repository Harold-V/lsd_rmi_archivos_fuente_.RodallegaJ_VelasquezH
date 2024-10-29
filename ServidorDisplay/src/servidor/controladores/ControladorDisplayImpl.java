package servidor.controladores;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import servidor.DTO.ModuloDTO;
import servidor.DTO.NotificacionDTO;

public class ControladorDisplayImpl extends UnicastRemoteObject implements ControladorDisplayInt {

    private ModuloDTO[] vectorModulos;

    public ControladorDisplayImpl() throws RemoteException {
        super();
        inicializarEstadoModulos();
    }

    // Metodo para mostrar el estado inicial de los modulos
    private void inicializarEstadoModulos() {
        System.out.println("==== Estado inicial de los modulos ====");
        System.out.printf("%-10s %-15s %-10s %-20s %-15s%n", "No modulo", "Estado", "Ocupado", "No identificacion", "Numero de Turno");

        for (int i = 0; i < 3; i++) {
            System.out.printf("%-10d %-15s %-10s %-20s %-15s%n", 
                i + 1, 
                "Inactivo", 
                "No", 
                "-", 
                "-");
        }
        System.out.println("Cantidad Usuarios en la fila virtual: 0");
    }

    @Override
    public void mostrarNotificacion(NotificacionDTO objNotificacion) throws RemoteException {
        this.vectorModulos = objNotificacion.getVectorModulos();

        System.out.println("==== Actualizacion de Turnos y Modulos ====");
        System.out.printf("%-10s %-15s %-10s %-20s %-15s%n", "No modulo", "Estado", "Ocupado", "No identificacion", "Turno");

        for (ModuloDTO modulo : vectorModulos) {
            String estado = modulo.isActivo() ? "Activo" : "Inactivo";
            String ocupado = modulo.isActivo() && modulo.isOcupado() ? "Si" : "No";
            String identificacion = modulo.isActivo() && modulo.isOcupado() ? modulo.getIdentificacion() : "-";
            String numeroTurno = modulo.isActivo() && modulo.isOcupado() ? String.valueOf(modulo.getNumeroTurno()) : "-";

            System.out.printf("%-10d %-15s %-10s %-20s %-15s%n", 
                modulo.getNumeroModulo(), 
                estado, 
                ocupado, 
                identificacion, 
                numeroTurno);
        }
        System.out.println("Cantidad Usuarios en la fila virtual: " + objNotificacion.getCantidadUsuariosFilaVirtual());
    }
}
