package cliente.servicios;

import cliente.utilidades.UtilidadesRegistroC;
import cliente.vista.Menu;
import servidor.controladores.ControladorGestorModulosInt;

public class ClienteDeObjetos
{

	private static ControladorGestorModulosInt objRemoto;
        
	public static void main(String[] args)
	{
            int numPuertoRMIRegistry = 2022;
            String direccionIpRMIRegistry = "localhost";        
               
            /*System.out.println("Cual es el la dirección ip donde se encuentra  el rmiregistry ");
            direccionIpRMIRegistry = cliente.utilidades.UtilidadesConsola.leerCadena();
            System.out.println("Cual es el número de puerto por el cual escucha el rmiregistry ");
            numPuertoRMIRegistry = cliente.utilidades.UtilidadesConsola.leerEntero(); */
            
            objRemoto = (ControladorGestorModulosInt) UtilidadesRegistroC.obtenerObjRemoto(direccionIpRMIRegistry,numPuertoRMIRegistry, "controladorGestionModulos");
            Menu objMenu= new Menu(objRemoto);
            objMenu.ejecutarMenuPrincipal();
		
	}
	
	
	
}

