package ProblemaC;


public class LEGListaEnlazada
{
    private NodoHuerto ini;
    private NodoHuerto fin;
    
    public LEGListaEnlazada(){
        ini = null; fin = null;
    }
    
    public void add(char[][] huerto){
        NodoHuerto nodo = new NodoHuerto(huerto);
        if(ini == null){ ini = nodo; fin = nodo;}
        else{
            fin.siguiente = nodo;
            fin = nodo;
        }
    }
    
    public NodoHuerto buscar(char[][] huerto){
        NodoHuerto nodo = ini;
        while(nodo != null){
            if(igual(nodo.dato, huerto)){ break;}
            nodo = nodo.siguiente;
        }
        return nodo;
    }
    
    public boolean igual(char[][] huerto1, char[][] huerto2){
        boolean res = true;
        if(huerto1.length != huerto2.length || huerto1[0].length != huerto2[0].length){ return false;}
        for(int i = 0; i < huerto1.length; i++){
            for(int j = 0; j < huerto1[0].length; j++){
                res = huerto1[i][j] == huerto2[i][j] && res;
            }
        }
        return res;
    }
    
    public int mayorBiodiversidad(){
        NodoHuerto nodo = ini;
        int mayor = 0;
        while(nodo != null){
            int actual = 0;
            for(int i = 0; i < nodo.dato.length; i++){
                for(int j = 0; j < nodo.dato[0].length; j++){
                    switch(nodo.dato[i][j]){
                        case 'o':
                            actual += 1;
                            break;
                        case 'O':
                            actual += 2;
                            break;
                    }
                }
            }
            if(actual > mayor){ mayor = actual;}
            nodo = nodo.siguiente;
        }
        return mayor;
    }
    
    public void vaciar(){
        ini = null;
        fin = null;
    }
}