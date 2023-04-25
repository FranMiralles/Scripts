package ProblemaC;


import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;



public class Solucion
{
    public static Scanner sc; //Scanner por donde leemos las instrucciones y datos
    protected static LEGListaEnlazada lista;
    protected static char[][] huerto;
    
    //Programa que dado un huerto de tamaño variable devuelva la iteraccion con mayor biodiversidad
    public static void main(String args[]){
        System.out.print("\u000C");
        sc = new Scanner(System.in);
        //Entrada huerto
        lista = new LEGListaEnlazada();
        iniciarHuerto();
        //Metodo por cada iteracion
        int cont = 0;
        //Crea iteraciones hasta que encuentre un huerto repetido
        while(lista.buscar(huerto) == null){
            cont++;
            imprimirHuerto();
            cambiarHuerto();
        }
        System.out.println("Primer huerto repetido: \n");
        imprimirHuerto();
        System.out.println("Numero de iteracion: " + cont + "\n");
        //Al encontrar el primero del bucle, vaciamos la lista y solo unimos los que pertenecen al bucle
        lista.vaciar();
        //Volvemos a buscar la siguiente repeticion
        cont = 0;
        while(lista.buscar(huerto) == null){
            cont++;
            imprimirHuerto();
            cambiarHuerto();
        }
        System.out.println("Mayor Biodiversidad: " + lista.mayorBiodiversidad());
        System.out.println("Numero de huertos en bucle: " + cont);
    }
    
    protected static void iniciarHuerto(){
        String input = "";
        String line;
        
        //Recibimos el huerto hasta la primera linea en blanco
        while(sc.hasNextLine()){
            line = sc.nextLine();
            if(line.equals("")){ break;}
            input += line + "\n";
        }
        
        //Dividimos el string en filas, rellenamos la variable estatica huerto
        String[] rows = input.split("\n");
        int numRow = rows.length;
        int numCol = rows[0].length();
        huerto = new char[numRow][numCol];
        
        for(int i = 0; i < numRow; i++){
            for(int j = 0; j < numCol; j++){
                huerto[i][j] = rows[i].charAt(j);
            }
        }
    }
    
    //Si es un terreno -> # y hay una planta madura adyacente -> O, convertir en brote -> o
    //Si es un brote -> o y hay 3 plantas maduras o mas adyacentes -> O, convertir en -> #, caso contrario en -> O
    //Si es una planta -> O y hay 6 plantas, maduras o brotes en casillas adyacentes, convertir en -> #
    protected static void cambiarHuerto(){
        char[][] aux = new char[huerto.length][huerto[0].length];
        boolean cambiado = false;
        int cont = 0;
        for(int i = 0; i < huerto.length; i++){
            for(int j = 0; j < huerto[0].length; j++){
                switch(huerto[i][j]){
                    case '#':
                        //Si es un terreno -> # y hay una planta madura adyacente -> O, convertir en brote -> o
                        cambiado = false;
                        for(int k = -1; k < 2 && !cambiado; k++){
                            for(int w = -1; w < 2 && !cambiado; w++){
                                try{
                                    if(huerto[i + k][j + w] == 'O'){ aux[i][j] = 'o'; cambiado = true;}
                                }catch(ArrayIndexOutOfBoundsException e){
                            
                                }
                            }
                        }
                        if(!cambiado){ aux[i][j] = '#';}
                        break;
                    case 'o':
                        //Si es un brote -> o y hay 3 plantas maduras o mas adyacentes -> O, convertir en -> #, caso contrario en -> O
                        cont = 0;
                        for(int k = -1; k < 2; k++){
                            for(int w = -1; w < 2; w++){
                                try{
                                    if(huerto[i + k][j + w] == 'O'){ cont++;}
                                }catch(ArrayIndexOutOfBoundsException e){
                            
                                }
                            }
                        }
                        if(cont >= 3){ aux[i][j] = '#';}
                        else{ aux[i][j] = 'O';}
                        break;
                    case 'O':
                        cont = 0;
                        for(int k = -1; k < 2; k++){
                            for(int w = -1; w < 2; w++){
                                try{
                                    if(k != 0 || w != 0){
                                        if(huerto[i + k][j + w] == 'O' || huerto[i + k][j + w] == 'o'){ cont++;}
                                    }
                                }catch(ArrayIndexOutOfBoundsException e){
                            
                                }
                            }
                        }
                        if(cont >= 6){ aux[i][j] = '#';}
                        else{ aux[i][j] = huerto[i][j];}
                        break;
                }
            }
        }
        
        lista.add(huerto);
        huerto = aux;
    }
    
    protected static void imprimirHuerto(){
        for(int i = 0; i < huerto.length; i++){
            for(int j = 0; j < huerto[0].length; j++){
                System.out.print(huerto[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
