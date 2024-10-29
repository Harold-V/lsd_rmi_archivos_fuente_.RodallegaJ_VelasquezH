package cliente.servicios;

import cliente.utilidades.UtilidadesRegistroC;
import cliente.vista.Menu;
import servidor.controladores.ControladorGeneradorTurnoInt;

public class ClienteDeObjetos {

    private static ControladorGeneradorTurnoInt objRemoto;

    public static void main(String[] args) {
        int numPuertoRMIRegistry = 2023;
        String direccionIpRMIRegistry = "localhost";

        //System.out.println("Cual es el la direccion ip donde se encuentra  el rmiregistry ");
        //direccionIpRMIRegistry = cliente.utilidades.UtilidadesConsola.leerCadena();
        //System.out.println("Cual es el numero de puerto por el cual escucha el rmiregistry ");
        //numPuertoRMIRegistry = cliente.utilidades.UtilidadesConsola.leerEntero();

        objRemoto = (ControladorGeneradorTurnoInt) UtilidadesRegistroC.obtenerObjRemoto(
                direccionIpRMIRegistry,
                numPuertoRMIRegistry,
                "controladorGeneradorTurno");
        Menu objMenu = new Menu(objRemoto);
        objMenu.ejecutarMenuPrincipal();
    }

}
