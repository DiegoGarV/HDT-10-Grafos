
import org.junit.Test;
import java.util.List;

public class GrafoTest {

    // Crea una instancia de Grafo
    Grafo grafo = new Grafo();

    @Test
    public void matrizTrabajadoraTest() {       
        
        // Llama al método matrizTrabajadora con un tipo de clima específico (por ejemplo, 0)
        int tipoClima = 0;
        List<List<Object>> matrizAdy = grafo.matrizTrabajadora(tipoClima);       
        
        for (List<Object> fila : matrizAdy) {
            for (Object elemento : fila) {
                System.out.print(elemento + " ");
            }
            System.out.println();
        }     
    }

    @Test
    public void floydseloTest() {     
        
        // Llama al método floydselo para calcular las matrices de tiempos y ciudades
        List<List<Object>> matrizTiempos = grafo.floydselo();       
        
        for (List<Object> fila : matrizTiempos) {
            for (Object elemento : fila) {
                System.out.print(elemento + " ");
            }
            System.out.println();
        }
    }

    @Test
    public void KeyFromValueTest() {
 
        // Llama al método KeyFromValue para obtener la clave asociada a un valor específico

        Integer targetValue = 123; // El valor que deseas buscar

        String key = grafo.KeyFromValue(targetValue);        
  
        System.out.println("Clave encontrada: " + key);
    }

    @Test
    public void centroTest() {

        // Llama al método centro para obtener el centro del grafo
        String centro = grafo.centro();       

        System.out.println("Centro del grafo: " + centro);
    
    }
}
