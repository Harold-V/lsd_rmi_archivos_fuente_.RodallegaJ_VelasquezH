/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.servicios;

import servidor.utilidades.UtilidadesRegistroS;
import java.rmi.RemoteException;
import servidor.Repositorios.GeneradorTurnoRepositoryImpl;
import servidor.Repositorios.ModulosRepositoryImpl;
import servidor.Repositorios.UsuariosRepositoryImpl;
import servidor.controladores.ControladorDisplayInt;
import servidor.controladores.ControladorGeneradorTurnoImpl;
import servidor.controladores.ControladorGestorModulosImpl;
import servidor.controladores.ControladorGestorUsuariosImpl;
import servidor.utilidades.UtilidadesRegistroC;

public class ServidorDeObjetos {

    public static void main(String args[]) throws RemoteException {

        int numPuertoRMIRegistryServidorTurnos = 2023;
        String direccionIpRMIRegistryServidorTurnos = "localhost";

        int numPuertoRMIRegistryServidorDisplay = 2020;
        String direccionIpRMIRegistryServidorDisplay = "localhost";

        int numPuertoRMIRegistryAdminSistema = 2021;
        String direccionIpRMIRegistryAdminSistema = "localhost";

        int numPuertoRMIRegistryAdminModulo = 2022;
        String direccionIpRMIRegistryAdminModulo = "localhost";

        /* System.out.println("Cual es el la dirección ip donde se encuentra  el rmiRegistry ");
        direccionIpRMIRegistryAdminSistema = UtilidadesConsola.leerCadena();
        System.out.println("Cual es el número de puerto por el cual escucha el rmiRegistry ");
        numPuertoRMIRegistryAdminSistema = UtilidadesConsola.leerEntero();

        System.out.println("Cual es el la dirección ip donde se encuentra  el rmiRegistry ");
        direccionIpRMIRegistryAdminModulo = UtilidadesConsola.leerCadena();
        System.out.println("Cual es el número de puerto por el cual escucha el rmiRegistry ");
        numPuertoRMIRegistryAdminModulo = UtilidadesConsola.leerEntero();

        System.out.println("Cual es el la dirección ip donde se encuentra  el rmiRegistry ");
        direccionIpRMIRegistryServidorTurnos = UtilidadesConsola.leerCadena();
        System.out.println("Cual es el número de puerto por el cual escucha el rmiRegistry ");
        numPuertoRMIRegistryServidorTurnos = UtilidadesConsola.leerEntero();

        System.out.println("Cual es el la dirección ip donde se encuentra  el rmiRegistry ");
        direccionIpRMIRegistryServidorDisplay = UtilidadesConsola.leerCadena();
        System.out.println("Cual es el número de puerto por el cual escucha el rmiRegistry ");
        numPuertoRMIRegistryServidorDisplay = UtilidadesConsola.leerEntero();*/
        ControladorDisplayInt objRemotoDisplay = (ControladorDisplayInt) UtilidadesRegistroC.obtenerObjRemoto(
                direccionIpRMIRegistryServidorDisplay,
                numPuertoRMIRegistryServidorDisplay,
                "controladorDisplay");

        UsuariosRepositoryImpl objRepositoryAdminSistema = new UsuariosRepositoryImpl();
        ControladorGestorUsuariosImpl objRemotoAdminSistema = new ControladorGestorUsuariosImpl(objRepositoryAdminSistema);//se leasigna el puerto de escucha del objeto remoto

        GeneradorTurnoRepositoryImpl objRepository = new GeneradorTurnoRepositoryImpl(objRemotoDisplay, objRepositoryAdminSistema);
        ControladorGeneradorTurnoImpl objRemoto = new ControladorGeneradorTurnoImpl(objRepository);//se leasigna el puerto de escucha del objeto remoto

        ModulosRepositoryImpl objRepositoryAdminModulo = new ModulosRepositoryImpl(objRepository, objRemotoDisplay);
        ControladorGestorModulosImpl objRemotoAdminModulo = new ControladorGestorModulosImpl(objRepositoryAdminModulo);//se leasigna el puerto de escucha del objeto remoto

        try {
            UtilidadesRegistroS.arrancarNS(numPuertoRMIRegistryServidorTurnos);
            UtilidadesRegistroS.RegistrarObjetoRemoto(objRemoto,
                    direccionIpRMIRegistryServidorTurnos,
                    numPuertoRMIRegistryServidorTurnos,
                    "controladorGeneradorTurno");

            UtilidadesRegistroS.arrancarNS(numPuertoRMIRegistryAdminSistema);
            UtilidadesRegistroS.RegistrarObjetoRemoto(objRemotoAdminSistema,
                    direccionIpRMIRegistryAdminSistema,
                    numPuertoRMIRegistryAdminSistema,
                    "controladorGestionUsuarios");

            UtilidadesRegistroS.arrancarNS(numPuertoRMIRegistryAdminModulo);
            UtilidadesRegistroS.RegistrarObjetoRemoto(objRemotoAdminModulo,
                    direccionIpRMIRegistryAdminModulo,
                    numPuertoRMIRegistryAdminModulo,
                    "controladorGestionModulos");

        } catch (RemoteException e) {
            System.err.println("No fue posible Arrancar el NS o Registrar el objeto remoto" + e.getMessage());
        }

    }
}
