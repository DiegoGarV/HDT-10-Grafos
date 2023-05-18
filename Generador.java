
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Generador {
    private List<List<Object>> matriz;
    private HashMap<String, Integer> hm;

    public Generador() {
        matriz = new ArrayList<>();
        hm = new HashMap<>();
    }

    public void leerArchivo() {
        String nombreArchivo = "logistica.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                List<Object> fila = new ArrayList<>();
                String[] elementos = linea.split(" "); // Puedes cambiar el separador si es necesario

                for (String elemento : elementos) {
                    fila.add((Object) elemento); // Puedes cambiar el tipo de objeto si es necesario
                }

                if(fila.size() != 6){

                    throw new IllegalArgumentException("Existe un error con la cantidad de datos en una de las filas");
                }
                
                if (!(hm.containsKey(elementos[0]))) {
                    hm.put(elementos[0], hm.size());
                    matriz.add(new ArrayList<>());
                }
                if (!(hm.containsKey(elementos[1]))) {

                    hm.put(elementos[1], hm.size());
                    matriz.add(new ArrayList<>());
                }

                for (int i = 0; i < hm.size(); i++) {
                    List<Object> DivMatriz = matriz.get(i);
                    int o = 0;
                    while (DivMatriz.size() < hm.size()) {
                        if (o == i) {
                            DivMatriz.add(0);
                        } else {
                            DivMatriz.add("NA");
                        }
                        o++;
                    }
                }

                matriz.add(fila);
            }
        }
        catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public void imprimirMatriz() {
        for (List<Object> fila : matriz) {
            for (Object elemento : fila) {
                System.out.print(elemento + " ");
            }
            System.out.println();
        }
    }
    
    public HashMap<String, Integer> getHm() {
        return hm;
    }
    
    public List<List<Object>> getMatriz() {
        return matriz;
    }
}


