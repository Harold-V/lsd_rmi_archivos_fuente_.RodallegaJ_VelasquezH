package cliente.servicios;

import cliente.utilidades.UtilidadesRegistroC;
import cliente.vista.Menu;
import servidor.controladores.ControladorGestorUsuariosInt;

public class ClienteDeObjetos
{

	private static ControladorGestorUsuariosInt objRemoto;
        
	public static void main(String[] args)
	{
            int numPuertoRMIRegistry = 2021;
            String direccionIpRMIRegistry = "localhost";        
               
            /*System.out.println("Cual es el la dirección ip donde se encuentra  el rmiregistry ");
            direccionIpRMIRegistry = cliente.utilidades.UtilidadesConsola.leerCadena();
            System.out.println("Cual es el número de puerto por el cual escucha el rmiregistry ");
            numPuertoRMIRegistry = cliente.utilidades.UtilidadesConsola.leerEntero(); */
            
            objRemoto = (ControladorGestorUsuariosInt) UtilidadesRegistroC.obtenerObjRemoto(direccionIpRMIRegistry,numPuertoRMIRegistry, "controladorGestionUsuarios");
            Menu objMenu= new Menu(objRemoto);
            objMenu.ejecutarMenuPrincipal();
		
	}
	
	
	
}

