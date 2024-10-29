package cliente.vista;

import cliente.utilidades.UtilidadesConsola;
import java.rmi.RemoteException;
import servidor.DTO.NodoTurnoDTO;
import servidor.controladores.ControladorGeneradorTurnoInt;

public class Menu {

    private final ControladorGeneradorTurnoInt objRemoto;

    public Menu(ControladorGeneradorTurnoInt objRemoto) {
        this.objRemoto = objRemoto;
    }

    public void ejecutarMenuPrincipal() {
        int opcion = 0;
        do {
            UtilidadesConsola.limpiarPantalla();
            System.out.println("=============Menu============");
            System.out.println("1. Generar Turno");
            System.out.println("2. Salir");
            System.out.println("=============================");
            System.out.println("Digite una opcion:");

            opcion = UtilidadesConsola.leerEntero();

            switch (opcion) {
                case 1:
                    Opcion1();
                    break;
                case 2:
                    System.out.println("Salir...");
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }

        } while (opcion != 4);
    }

    private void Opcion1() {
        try {
            UtilidadesConsola.limpiarPantalla();
            System.out.println("==Generador de turnos==");
            if (!objRemoto.estadoActivo()) {
                System.out.println("El sistema está inactivo. El admin debe activar el sistema");
                UtilidadesConsola.esperarTecla();
                return;
            }

            System.out.println("Ingrese la identificacion:");
            String identificacion = UtilidadesConsola.leerCadena();
            NodoTurnoDTO nodo = objRemoto.generarTurno(identificacion);

            if (nodo != null) {
                System.out.println("Datos generados por el sistema");
                System.out.println("Numero identificacion: " + nodo.getIdentificacion());
                System.out.println("Numero de turno: " + nodo.getNumeroTurno());
                System.out.println("Cantidad de usuarios en la fila virtual: " + nodo.getCantidadUsuariosFilaVirtual());
            }

        } catch (Exception e) {
            System.out.println("La operacion no se pudo completar, intente nuevamente...");
            System.out.println(e);
        }
        UtilidadesConsola.esperarTecla();
    }

}
