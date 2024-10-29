package servidor.Repositorios;

import java.rmi.RemoteException;
import servidor.DTO.ModuloDTO;
import servidor.DTO.NodoTurnoDTO;
import servidor.DTO.NotificacionDTO;
import servidor.controladores.ControladorDisplayInt;

public class GeneradorTurnoRepositoryImpl implements GeneradorTurnoRepositoryInt {

    private int numeroTurno;
    private int cantidadUsuariosFila = 0;

    public int getCantidadUsuariosFila() {
        return cantidadUsuariosFila;
    }

    public void setCantidadUsuariosFila(int cantidadUsuariosFila) {
        this.cantidadUsuariosFila = cantidadUsuariosFila;
    }
    private final ModuloDTO vectorModulos[];
    private final NodoTurnoDTO usuariosFilaVirtual[];
    private final ControladorDisplayInt objRemotoDisplay;
    private final UsuariosRepositoryImpl usuariosRepository;

    public GeneradorTurnoRepositoryImpl(ControladorDisplayInt objRemotoDisplay, UsuariosRepositoryImpl usuariosRepository) {
        System.out.println("Configurando modulos");
        this.objRemotoDisplay = objRemotoDisplay;
        this.usuariosRepository = usuariosRepository;
        this.vectorModulos = new ModuloDTO[3];
        this.usuariosFilaVirtual = new NodoTurnoDTO[10];
        this.numeroTurno = 1;

        for (int i = 0; i < 3; i++) {
            this.vectorModulos[i] = new ModuloDTO();
            this.vectorModulos[i].setNumeroModulo(i + 1);
            this.vectorModulos[i].setOcupado(false);
            this.vectorModulos[i].setActivo(false);
        }

    }

    @Override
    public NodoTurnoDTO generarTurno(String identificacion) {
        int posicion = this.consultarNumeroModuloDisponible();

        if (!usuariosRepository.isEstadoSistema()) {
            System.out.println("El sistema no esta activo. No se pueden generar turnos.");
            return null;
        }

        NodoTurnoDTO objNodoTurnoDTO;
        if (posicion == -1) {
            objNodoTurnoDTO = new NodoTurnoDTO(numeroTurno, cantidadUsuariosFila + 1, identificacion);
            this.usuariosFilaVirtual[this.cantidadUsuariosFila] = objNodoTurnoDTO;
            this.cantidadUsuariosFila++;
            System.out.println("El usuario se agrego a la fila virtual con el turno " + numeroTurno);
        } else {
            System.out.println("El modulo en la posición " + posicion + " esta libre y se asignara al usuario con identificación " + identificacion);
            this.vectorModulos[posicion].setOcupado(true);
            this.vectorModulos[posicion].setNumeroTurno(this.numeroTurno);
            this.vectorModulos[posicion].setIdentificacion(identificacion);

            objNodoTurnoDTO = new NodoTurnoDTO(numeroTurno, cantidadUsuariosFila, identificacion);
        }

        this.numeroTurno++;

        notificarDisplay();

        return objNodoTurnoDTO;
    }

    private void notificarDisplay() {
        NotificacionDTO objNotificacion = new NotificacionDTO();
        objNotificacion.setVectorModulos(this.vectorModulos);
        objNotificacion.setCantidadUsuariosFilaVirtual(this.cantidadUsuariosFila);

        try {
            objRemotoDisplay.mostrarNotificacion(objNotificacion);
            System.out.println("Notificacion enviada al servidor display");
        } catch (RemoteException ex) {
            System.out.println("Error al notificar al servidor display: " + ex.getMessage());
        }
    }

    @Override
    public boolean estadoActivo() {
        if (usuariosRepository.isEstadoSistema()) {
            System.out.println("El sistema esta activo");
        } else {
            System.out.println("El sistema esta desactivado");
        }
        return usuariosRepository.isEstadoSistema();
    }

    private int consultarNumeroModuloDisponible() {
        int posicion = -1;

        for (int i = 0; i < 3; i++) {
            if (!this.vectorModulos[i].isOcupado() && this.vectorModulos[i].isActivo()) {
                posicion = i;
                break;
            } else {
                System.out.println("No hay modulos Disponibles");
            }
        }
        return posicion;
    }

    public ModuloDTO[] getVectorModulos() {
        return this.vectorModulos;
    }

    public NodoTurnoDTO siguienteUsuarioEnFila() {
        if (cantidadUsuariosFila > 0) {
            NodoTurnoDTO siguienteUsuario = usuariosFilaVirtual[0];
            for (int i = 1; i < cantidadUsuariosFila; i++) {
                usuariosFilaVirtual[i - 1] = usuariosFilaVirtual[i];
            }
            usuariosFilaVirtual[cantidadUsuariosFila - 1] = null;
            cantidadUsuariosFila--;

            return siguienteUsuario;
        }
        return null;
    }

}
