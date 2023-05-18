
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Generador {
    private List<List<Object>> matriz;

    public Generador() {
        matriz = new ArrayList<>();
    }

    public void leerArchivo() throws IOException{
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

                matriz.add(fila);
            }
        } catch (IOException e) {
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
}


