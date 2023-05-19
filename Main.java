/*
 * Universidad del Valle de Guatemala
 * Diego García, 22404
 * César López, 22535
 * Clase para interactuar con el usuario
 * 17/5/2023
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Generador generador = new Generador();
        Grafo grafo = new Grafo();

        generador.leerArchivo();

        int opcion;
        do {
            System.out.println("---- Menú ----");
            System.out.println("1. Generar ruta");
            System.out.println("2. Ciudad en el Centro");
            System.out.println("3. Modificar grafo");
            System.out.println("4. Finalizar Programa");
            System.out.print("Ingrese una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("----------Opción seleccionada: Generar ruta----------");

                    System.out.println("Ciudades disponibles:");                   
                    
                    for(int i = 0; i < grafo.hm.size(); i++ ){
	                    System.out.println(i + grafo.KeyFromValue(i));
                    }
                    
                    System.out.print("Ingrese la ciudad de origen: ");
                    int origen = scanner.nextInt();

                    System.out.print("Ingrese la ciudad de destino: ");
                    int destino = scanner.nextInt();

                    System.out.println(grafo.ruta(origen,destino));

                    break;

                case 2:
                    System.out.println("----------Opción seleccionada: Ciudad en el Centro----------");
                    
                    
                    String ciudadCentro = grafo.centro();
                    System.out.println("La ciudad en el centro es: " + ciudadCentro);

                    break;
                case 3:
                    int op2 = 0;
                    String newCS = "";
                    String newCL = "";
                    int normal = 0;
                    int lluvia = 0;
                    int nieve = 0;
                    int tormenta = 0;
                    System.out.println("----------Opción seleccionada: Modificar grafo----------");
                    System.out.println("1. Agregar camino entre ciudades");
                    System.out.println("2. Quitar camino entre ciudades");
                    System.out.print("Ingrese una opción: ");
                    op2 = scanner.nextInt();
                    if (op2==1){
                        
                    }
                    
                    break;
                case 4:
                    System.out.println("------------------------------Programa finalizado------------------------------");

                    System.out.println("Programa finalizado");
                    System.exit(0); // Termina el programa
                    
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
            System.out.println();
        } while (opcion != 4);

        scanner.close();
    }
}
