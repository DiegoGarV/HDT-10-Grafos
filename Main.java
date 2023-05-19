/*
 * Universidad del Valle de Guatemala
 * Diego García, 22404
 * César López, 22535
 * Clase para interactuar con el usuario
 * 17/5/2023
 */

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Generador generador = new Generador();
        Grafo grafo = new Grafo();
        List<List<Object>> matrizAdy = new ArrayList<>();
        matrizAdy = grafo.matrizTrabajadora(0);

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
                    int bloqSal = 0;
                    int bloqLle = 0;
                    System.out.println("----------Opción seleccionada: Modificar grafo----------");
                    System.out.println("1. Agregar camino entre ciudades");
                    System.out.println("2. Quitar camino entre ciudades");
                    System.out.print("Ingrese una opción: ");
                    op2 = scanner.nextInt();
                    if (op2==1){
                        System.out.println("Coloque el nombre de la ciudad de salida sin espacios: "); 
                        newCS = scanner.next();
                        System.out.println("Coloque el nombre de la ciudad de llegada sin espacios: "); 
                        newCL = scanner.next();
                        System.out.println("Coloque el tiempo entre las ciudades con clima normal: "); 
                        normal = scanner.nextInt();
                        System.out.println("Coloque el tiempo entre las ciudades con lluvia: "); 
                        lluvia = scanner.nextInt();
                        System.out.println("Coloque el tiempo entre las ciudades con nieve: "); 
                        nieve = scanner.nextInt();
                        System.out.println("Coloque el tiempo entre las ciudades con tormenta: "); 
                        tormenta = scanner.nextInt();
                        System.out.println(grafo.agregarCamino(matrizAdy,newCS,newCL,normal,lluvia,nieve,tormenta,0)); 
                    }
                    else {
                        System.out.println("Ciudades disponibles:");                   
                    
                        for(int i = 0; i < grafo.hm.size(); i++ ){
                            System.out.println(i + grafo.KeyFromValue(i));
                        }
                        
                        System.out.print("Ingrese la ciudad de origen: ");
                        bloqSal = scanner.nextInt();

                        System.out.print("Ingrese la ciudad de destino: ");
                        bloqLle = scanner.nextInt();

                        System.out.print(grafo.quitarCamino(matrizAdy,bloqSal,bloqLle));
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
