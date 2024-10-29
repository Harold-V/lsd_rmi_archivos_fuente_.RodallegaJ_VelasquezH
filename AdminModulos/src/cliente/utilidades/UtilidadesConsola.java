package cliente.utilidades;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class UtilidadesConsola {
    private static final Scanner scanner = new Scanner(System.in);

    public static int leerEntero() {
        
        String linea = "";
        int opcion = 0;
        boolean valido = false;
        do {
            try {
                //System.out.println("Ingrese la opcion: ");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                linea = br.readLine();
                opcion = Integer.parseInt(linea);
                valido = true;
            } catch (Exception e) {
                System.out.println("Error intente nuevamente...");
                valido = false;
            }
        } while (!valido);

        return opcion;

    }

    public static String leerCadena() {
        String linea = "";
        boolean valido = false;
        do {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                linea = br.readLine();
                valido = true;
            } catch (Exception e) {
                System.out.println("Error intente nuevamente...");
                valido = false;
            }
        } while (!valido);

        return linea;

    }
    
    public static void limpiarPantalla(){
        for (int i = 0; i < 30; i++) {
            System.out.println("\n");
        }
    }
    
    public static void esperarTecla() {
        System.out.println("Presione Enter para Continuar...");
        scanner.nextLine();
    }
}
