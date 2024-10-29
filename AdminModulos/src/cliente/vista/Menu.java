package cliente.vista;

import cliente.utilidades.UtilidadesConsola;
import java.rmi.RemoteException;
import servidor.controladores.ControladorGestorModulosInt;

public class Menu {

    private final ControladorGestorModulosInt objRemoto;

    public Menu(ControladorGestorModulosInt objRemoto) {
        this.objRemoto = objRemoto;
    }

    public void ejecutarMenuPrincipal() {
        int opcion = 0;
        do {
            UtilidadesConsola.limpiarPantalla();
            System.out.println("=============Menu============");
            System.out.println("1. Activar modulo");
            System.out.println("2. Desactivar modulo");
            System.out.println("3. liberar modulo");
            System.out.println("4. Salir");
            System.out.println("=============================");
            System.out.println("Digite una opcion:");

            opcion = UtilidadesConsola.leerEntero();

            switch (opcion) {
                case 1 -> {activarModulo();
                }
                case 2 -> {desactivarModulo();
                }
                case 3 -> {liberarModulo();
                }
                case 4 -> System.out.println("Salir...");
                default -> System.out.println("Opción incorrecta");
            }

        } while (opcion != 4);
    }

    public void activarModulo() {
        try {
            UtilidadesConsola.limpiarPantalla();
            System.out.println("Habilitar un modulo");
            int opcion = UtilidadesConsola.leerEntero();
            objRemoto.moduloActivo(opcion);
            System.out.println("se Activo el modulo: " + opcion);
            UtilidadesConsola.esperarTecla();
        } catch (RemoteException e) {
            System.out.println("La operacion no se pudo completar, intente nuevamente...");
        }
    }

    public void desactivarModulo() {
        try {
            UtilidadesConsola.limpiarPantalla();
            System.out.println("Desactivar un modulo");
            int opcion = UtilidadesConsola.leerEntero();
            objRemoto.moduloInactivo(opcion);
            System.out.println("se Desactivo el modulo: " + opcion);
            UtilidadesConsola.esperarTecla();
        } catch (RemoteException e) {
            System.out.println("La operacion no se pudo completar, intente nuevamente...");
        }
    }

    public void liberarModulo() {
        try {
            UtilidadesConsola.limpiarPantalla();
            System.out.println("Liberar un modulo");
            int opcion = UtilidadesConsola.leerEntero();
            objRemoto.liberarModulo(opcion);
            System.out.println("se libero el modulo: " + opcion);
            UtilidadesConsola.esperarTecla();
        } catch (RemoteException e) {
            System.out.println("La operacion no se pudo completar, intente nuevamente...");
        }
    }

}
