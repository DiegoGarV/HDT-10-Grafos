/*
 * Universidad del Valle de Guatemala
 * Diego García, 22404
 * César López, 22535
 * Clase para trabajar con el grafo
 * 17/5/2023
 */

import java.util.*;

public class Grafo {
    private List<List<Object>> tiempos = new ArrayList<>();
    private List<List<Object>> ciudades = new ArrayList<>();
    private Generador generador = new Generador();
    public HashMap<String,Integer> hm = new HashMap<>();
    private List<List<Object>> lp = new ArrayList<>();
    
    
    public Grafo(){
        generador.leerArchivo();
        hm = generador.getHm();
        lp = generador.getMatriz();
    }

    

    public List<List<Object>> matrizTrabajadora(int tipoClima){
        List<List<Object>> matrizAdy = new ArrayList<>(); 
        for(int i=0;i<lp.size();i++){
            List<Object> ciudadSalida = lp.get(i);
            List<Object> ciudadLlegada = new ArrayList<>();
            int misma = 0;
            for (int j=0;j<ciudadSalida.size();j++){
                //Si la ciudad de destido y de salida son la misma pone 0 automáticamente
                if (misma==i){
                   ciudadLlegada.add(0); 
                }
                else{
                    Object valor = ciudadSalida.get(j);
                    //Si el valor es una lista entonces busca el tiempo dado por el usuario
                    if(valor instanceof List){
                        List<String> clima = (List<String>) valor;
                        int tiempoEnClima = 0;
                        //Mira que el clima dado sea una opción correcta, sino asume que es normal
                        if (tipoClima==0 || tipoClima==1 || tipoClima==2 || tipoClima==3){
                            tiempoEnClima = Integer.parseInt(clima.get(tipoClima));
                        }
                        else {
                            tiempoEnClima = Integer.parseInt(clima.get(0));
                        }
                        ciudadLlegada.add(tiempoEnClima);
                    }
                    //Si no es lista pone infinito
                    else{
                        ciudadLlegada.add("INF");
                    }
                }
                misma++;
            }
            matrizAdy.add(ciudadLlegada);
        }
        return matrizAdy;
    } 

    public List<List<Object>> floydselo(List<List<Object>> matrizAdy){
        List<List<Object>>  matTiempos = new ArrayList<>(); 
        List<List<Object>> matCiudades = new ArrayList<>(); 
        int mismo = 0;
        for(int i=0;i<matrizAdy.size();i++){
            List<Object> mut = new ArrayList<>();
            for(int j=0;j<hm.size();j++){
                //Si es el mismo solo se agruega 0
                if(mismo==j){
                    mut.add(0);
                }
                //Si no es el mismo agrega el nombre de la ciudad
                else {
                    mut.add(KeyFromValue(j));
                }
            }
            mismo++;
            matCiudades.add(mut);
        }

        for (int i=0;i<matrizAdy.size();i++){
            List<Object> llegada = matrizAdy.get(i);
            List<Object> mut = new ArrayList<>();
            for (int j=0;j<llegada.size();j++){
                mut.add(llegada.get(j));
            }
            matTiempos.add(mut);
        }

        //Algoritmo de Floyd
        for (int k=0;k<hm.size();k++){
            for (int i=0;i<hm.size();i++){
                if (k!=i){ //k e i no pueden ser el mismo porque en el algoritmo de Floyd esa linea no se afecta en ese momento
                    for (int j=0;j<hm.size();j++){
                        if (k!=j){ //Pasa lo mismo aqui, esa fila no se toma en cuenta
                            if (!(matTiempos.get(i).get(k).equals("INF")) || !(matTiempos.get(k).get(j).equals("INF"))){ //Si alguno de los dos es infinito ni se hace la suma porque eya se sabe que no puede ser menor
                                int suma = (int) matTiempos.get(i).get(k) + (int) matTiempos.get(k).get(j);
                                if (matTiempos.get(i).get(j).equals("INF")){ //Si es infinito hace el cambio de una vez
                                    matTiempos.get(i).set(j, suma);
                                    matCiudades.get(i).set(j, KeyFromValue(k));  //Cambia la matriz de nombre también
                                }
                                else if (suma <  (int) matTiempos.get(i).get(j)){ //Si si es un número y es mayor a la suma entonces esta última toma ese lugar
                                    matTiempos.get(i).set(j, suma);
                                    matCiudades.get(i).set(j,KeyFromValue(k));
                                }
                            }
                        }
                    }
                }
            }
        }

        ciudades = matCiudades;
        tiempos = matTiempos;

        return this.tiempos;
    } 

    public String KeyFromValue(Integer targetValue){
        String key = null;
        for (Map.Entry<String, Integer> entry : hm.entrySet()) {
            if (entry.getValue().equals(targetValue)) {
                key = entry.getKey();
                break; // Stop the loop once the key is found
            }
        }
        return key;
    }

    public String centro(){
        List<Object> maximos = new Vector<>();
        for (int i=0;i<hm.size();i++){
            List<Object> lugar = ciudades.get(i);

            if (i==0){
                maximos = lugar;
            }
            else {
                for (int j=0;j<lugar.size();j++){
                    if (!maximos.get(j).equals("INF")){
                        if (lugar.get(j).equals("INF")){
                            maximos.set(j, "INF");
                        }
                        else if ((int) maximos.get(j)<(int) lugar.get(j)){
                            maximos.set(j, lugar.get(j));
                        }
                    }
                }
            }
        }

        Object centro = 0;
        int contador = 0;

        while (contador<maximos.size()){
            if (contador==0){
                centro = maximos.get(0);
            }
            else if (!maximos.get(contador).equals("INF")){
                if (centro.equals("INF")){
                    centro = maximos.get(contador);
                }
                else {
                    if ((int) maximos.get(contador)<(int) centro){
                        centro = maximos.get(contador);
                    }
                }
            }
            contador++;
        }
        int valor = maximos.indexOf(centro);
        return KeyFromValue(valor);
    }

    public String quitarCamino(List<List<Object>> matrizAdy, int ciudadSalida, int ciudadLlegada){
        String mensaje = "";
        matrizAdy.get(ciudadSalida).set(ciudadLlegada, "INF");
        floydselo(matrizAdy);
        mensaje = "El camino de "+KeyFromValue(ciudadSalida)+" a "+KeyFromValue(ciudadLlegada)+" se quito.";
        return mensaje;
    }

    public List<List<Object>> mostrarMatriz(){
        return tiempos;
    }

    public String agregarCamino(List<List<Object>> matrizAdy, String ciudadSalida, String ciudadLlegada, int tiempoN, int tiempoL, int tiempoNi, int tiempoT, int clima){
        String mensaje = "";
        List<Object> lista = new ArrayList<>();
        lista.add(tiempoN);
        lista.add(tiempoL);
        lista.add(tiempoNi);
        lista.add(tiempoT);
        if (!hm.containsKey(ciudadSalida)){
            hm.put(ciudadSalida,hm.size());
            lp.add(new ArrayList<>());
        }
        if (!hm.containsKey(ciudadLlegada)){
            hm.put(ciudadLlegada, hm.size());
            lp.add(new ArrayList<>());
        }
        lp.get(hm.get(ciudadSalida)).set(hm.get(ciudadLlegada), Arrays.asList(tiempoN, tiempoL, tiempoNi, tiempoT));
        matrizTrabajadora(clima);
        floydselo(matrizAdy);

        return mensaje;
    }

    public String ruta(int cSalida,int cLlegada){
        String mensaje = "";
        int timeR=0;
        String cityR="";
        if ((cSalida >= 0 && cSalida<hm.size()) && (cLlegada >= 0 && cLlegada<hm.size())){
            if(tiempos.get(cSalida).get(cLlegada).equals("INF")){
                mensaje = "Lo sentimos, no hay forma de llegar a "+KeyFromValue(cLlegada);
            }
            else {
                timeR = (int) tiempos.get(cSalida).get(cLlegada);
                cityR = (String) ciudades.get(cSalida).get(cLlegada);
                mensaje = "La ruta mas corta tarda "+timeR+".\n Su recorrido es de "+KeyFromValue(cSalida)+" a "+cityR+" a "+KeyFromValue(cLlegada);
            }
        }
        else{
            mensaje = "Lo sentimos, el número que marcó no es una opcion disponible.";
        }

        return mensaje;
    }
}
