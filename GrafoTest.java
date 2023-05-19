
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class GrafoTest {
    private Grafo grafo;

    @BeforeEach
    public void setup() {
        grafo = new Grafo();
    }

    @Test
    public void matrizTrabajadoraTest() {
        int tipoClima = 0;
        List<List<Object>> matrizTrabajadora = grafo.matrizTrabajadora(tipoClima);

        // Verifica que el tamaño de la matriz trabajadora sea correcto
        Assertions.assertEquals(grafo.lp.size(), matrizTrabajadora.size());
        for (List<Object> ciudadLlegada : matrizTrabajadora) {
            Assertions.assertEquals(grafo.lp.get(0).size(), ciudadLlegada.size());
        }

        // Verifica que los valores de la matriz trabajadora sean correctos
        for (int i = 0; i < matrizTrabajadora.size(); i++) {
            List<Object> ciudadLlegada = matrizTrabajadora.get(i);
            List<Object> ciudadSalida = grafo.lp.get(i);
            for (int j = 0; j < ciudadLlegada.size(); j++) {
                Object valor = ciudadSalida.get(j);
                if (i == j) {
                    Assertions.assertEquals(0, ciudadLlegada.get(j));
                } else if (valor instanceof List) {
                    List<String> clima = (List<String>) valor;
                    int tiempoEnClima = 0;
                    if (tipoClima == 0 || tipoClima == 1 || tipoClima == 2 || tipoClima == 3) {
                        tiempoEnClima = Integer.parseInt(clima.get(tipoClima));
                    } else {
                        tiempoEnClima = Integer.parseInt(clima.get(0));
                    }
                    Assertions.assertEquals(tiempoEnClima, ciudadLlegada.get(j));
                } else {
                    Assertions.assertEquals("INF", ciudadLlegada.get(j));
                }
            }
        }
    }

    @Test
    public void floydseloTest() {
        List<List<Object>> matTiempos = grafo.floydselo();

        // Verifica que el tamaño de la matriz de tiempos sea correcto
        Assertions.assertEquals(grafo.tiempos.size(), matTiempos.size());
        for (List<Object> llegada : matTiempos) {
            Assertions.assertEquals(grafo.tiempos.get(0).size(), llegada.size());
        }

        // Verifica que los valores de la matriz de tiempos sean correctos
        for (int i = 0; i < matTiempos.size(); i++) {
            List<Object> llegada = matTiempos.get(i);
            List<Object> originalLlegada = grafo.tiempos.get(i);
            for (int j = 0; j < llegada.size(); j++) {
                Object valor = originalLlegada.get(j);
                if (i == j) {
                    Assertions.assertEquals(0, llegada.get(j));
                } else if (valor.equals("INF")) {
                    Assertions.assertEquals("INF", llegada.get(j));
                } else {
                    int suma = (int) originalLlegada.get(i) + (int) originalLlegada.get(j);
                    Assertions.assertEquals(suma, llegada.get(j));
                }
            }
        }

        // Verifica que la matriz de ciudades también se actualice correctamente
        Assertions.assertEquals(matTiempos, grafo.tiempos);
        Assertions.assertEquals(grafo.ciudades, grafo.matCiudades);
    }

    @Test
    public void centroTest() {
        grafo.ciudades = createCiudades();
        String centro = grafo.centro();

        // Verifica que el centro sea una de las ciudades del grafo
        Assertions.assertTrue(grafo.hm.containsKey(centro));

        // Verifica que el centro tenga el menor valor en la lista de máximos
        List<Object> maximos = grafo.ciudades.get(0);
        for (int i = 1; i < grafo.ciudades.size(); i++) {
            List<Object> ciudad = grafo.ciudades.get(i);
            for (int j = 0; j < ciudad.size(); j++) {
                if (!maximos.get(j).equals("INF") && !ciudad.get(j).equals("INF")) {
                    Assertions.assertTrue((int) ciudad.get(j) >= (int) maximos.get(j));
                }
            }
        }
    }

    private List<List<Object>> createCiudades() {
        List<List<Object>> ciudades = new Vector<>();

        List<Object> ciudad1 = new Vector<>();
        ciudad1.add(0);
        ciudad1.add("INF");
        ciudad1.add(5);
        ciudades.add(ciudad1);

        List<Object> ciudad2 = new Vector<>();
        ciudad2.add("INF");
        ciudad2.add(0);
        ciudad2.add(10);
        ciudades.add(ciudad2);

        List<Object> ciudad3 = new Vector<>();
        ciudad3.add("INF");
        ciudad3.add(8);
        ciudad3.add(0);
        ciudades.add(ciudad3);

        return ciudades;
    }

    @Test
    public void keyFromValueTest() {
        grafo.hm = createHashMap();

        String key1 = grafo.KeyFromValue(1);
        Assertions.assertEquals("B", key1);

        String key2 = grafo.KeyFromValue(3);
        Assertions.assertEquals("D", key2);

        String key3 = grafo.KeyFromValue(5);
        Assertions.assertEquals(null, key3);
    }

    private HashMap<String, Integer> createHashMap() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("A", 0);
        hashMap.put("B", 1);
        hashMap.put("C", 2);
        hashMap.put("D", 3);
        return hashMap;
    }
}