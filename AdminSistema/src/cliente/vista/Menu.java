package cliente.vista;

import cliente.utilidades.UtilidadesConsola;
import java.rmi.RemoteException;
import servidor.controladores.ControladorGestorUsuariosInt;

public class Menu {

    private final ControladorGestorUsuariosInt objRemoto;

    public Menu(ControladorGestorUsuariosInt objRemoto) {
        this.objRemoto = objRemoto;
    }

    public void ejecutarMenuPrincipal() {
        int opcion = 0;
        do {
            UtilidadesConsola.limpiarPantalla();
            System.out.println("=============Menu============");
            System.out.println("1. Iniciar Sesion");
            System.out.println("2. Salir");
            System.out.println("=============================");
            System.out.println("Digite una opcion:");

            opcion = UtilidadesConsola.leerEntero();

            switch (opcion) {
                case 1 ->
                    Opcion1();
                case 2 ->
                    System.out.println("Salir...");
                default ->
                    System.out.println("Opción incorrecta");
            }

        } while (opcion != 2);
    }

    private void Opcion1() {
        try {
            UtilidadesConsola.limpiarPantalla();
            System.out.println("==Iniciar Sesion==");
            System.out.println("Ingrese el nombre de usuario");
            String user = UtilidadesConsola.leerCadena();
            System.out.println("Ingrese una contraseña");
            String password = UtilidadesConsola.leerCadena();

            if ((user.length() >= 8 && user.length() <= 15) && (password.length() >= 8 && password.length() <= 15)) {
                boolean administrador = objRemoto.iniciarSesion(user, password);
                if (administrador) {
                    System.out.println("Inicio de sesion Exitoso");
                    ejecutarMenuSecundario();
                } else {
                    System.out.println("Datos Incorrectos Intente nuevamente");
                }
            } else {
                System.out.println("El usuario o la contraseña deben tener entre 8 y 15 caracteres.");
            }

        } catch (RemoteException e) {
            System.out.println("La operación no se pudo completar, intente nuevamente...");
            System.out.println("Excepcion generada: " + e.getMessage());
        }
        UtilidadesConsola.esperarTecla();
    }

    public void ejecutarMenuSecundario() {
        int opcion = 0;
        do {
            UtilidadesConsola.limpiarPantalla();
            System.out.println("=============Menu============");
            System.out.println("1. Habilitar Sistema");
            System.out.println("2. Deshabilitar Sistema");
            System.out.println("3. Salir");
            System.out.println("=============================");
            System.out.println("Digite una opcion:");

            opcion = UtilidadesConsola.leerEntero();

            switch (opcion) {
                case 1 ->
                    activarSistema();
                case 2 ->
                    desactivarSistema();
                case 3 ->
                    System.out.println("Salir...");
                default ->
                    System.out.println("Opcion incorrecta");
            }

        } while (opcion != 3);
    }

    public void activarSistema() {
        try {
            UtilidadesConsola.limpiarPantalla();
            System.out.println("Sistema habilitado");
            objRemoto.activarSistema();
            UtilidadesConsola.esperarTecla();
        } catch (RemoteException e) {
            System.out.println("La operacio no se pudo completar, intente nuevamente...");
        }
    }

    public void desactivarSistema() {
        try {
            UtilidadesConsola.limpiarPantalla();
            System.out.println("Sistema deshabilitado");
            objRemoto.desactivarSistema();
            UtilidadesConsola.esperarTecla();
        } catch (RemoteException e) {
            System.out.println("La operacion no se pudo completar, intente nuevamente...");
        }
    }

}
