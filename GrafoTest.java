
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GrafoTest {

    private Grafo grafo;

    @BeforeEach
    public void setUp() {
        grafo = new Grafo();
    }

    @Test
    public void testMatrizTrabajadora() {
        int tipoClima = 0; 
        List<List<Object>> expectedMatrizAdy = new ArrayList<>();
        
        
        List<List<Object>> matrizAdy = grafo.matrizTrabajadora(tipoClima);
        
        Assertions.assertEquals(expectedMatrizAdy, matrizAdy);
        
    }

    @Test
    public void testFloydselo() {
        List<List<Object>> expectedMatTiempos = new ArrayList<>();
        

        List<List<Object>> matTiempos = grafo.floydselo();

        Assertions.assertEquals(expectedMatTiempos, matTiempos);
        
    }

    @Test
    public void testCentro() {
        String expectedCentro = "Centro esperado";
        

        String centro = grafo.centro();

        Assertions.assertEquals(expectedCentro, centro);
        
    }
}
