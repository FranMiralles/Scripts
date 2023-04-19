package CompeACM2022.ProblemaC;


public class NodoHuerto
{
    public char[][] dato; //Huerto
    public NodoHuerto siguiente; //Nodo siguiente
    
    public NodoHuerto(char[][] dato, NodoHuerto siguiente){
        this.dato = dato;
        this.siguiente = siguiente;
    }
    
    public NodoHuerto(char[][] dato){
        this.dato = dato;
        this.siguiente = null;
    }
}