package CompeACM2022.ProblemaC;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AnimacionCrecimiento extends Solucion
{
    private static boolean bucle;
    
    public static void main(String args[]){
        System.out.print("\u000C");
        iniciarHuerto();
        imprimirHuerto();
        Biodiversidad();
        System.out.println("Estamos en un bucle: false");
        
        Timer timer = new Timer(1000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarHuerto();
                System.out.print("\u000C");
                imprimirHuerto();
                Biodiversidad();
                if(!bucle){
                    bucle = comprobarBucle();
                }else{
                    System.out.println("Estamos en un bucle: " + bucle);
                }
            }
        });
        timer.start();
    }
    
    protected static void iniciarHuerto(){
        //Iniciamos el huerto de tamño aleatorio con objetos random minimo de tamaño 6
        huerto = new char[(3 + (int) (Math.random()*4+1))][10];
        for(int i = 0; i < huerto.length; i++){
            for(int j = 0; j < huerto[0].length; j++){
                int r = (int) (Math.random()*3+1);
                switch(r){
                    case 1:
                        huerto[i][j] = '#';
                        break;
                    case 2:
                        huerto[i][j] = 'o';
                        break;
                    case 3:
                        huerto[i][j] = 'O';
                        break;
                }
            }
        }
        lista = new LEGListaEnlazada();
        bucle = false;
    }
    
    protected static void imprimirHuerto(){
        System.out.print("\u000C        ");
        for(int i = 0; i < huerto[0].length + 4; i++){ System.out.print('-');}
        System.out.print("\n");
        for(int i = 0; i < huerto.length; i++){
            System.out.print("        | ");
            for(int j = 0; j < huerto[0].length; j++){
                System.out.print(huerto[i][j]);
            }
            System.out.print(" |\n");
        }
        System.out.print("        ");
        for(int i = 0; i < huerto[0].length + 4; i++){ System.out.print('-');}
    }
    
    private static void Biodiversidad(){
        int res = 0;
        for(int i = 0; i < huerto.length; i++){
            for(int j = 0; j < huerto[0].length; j++){
                switch(huerto[i][j]){
                    case 'o':
                        res += 1;
                        break;
                    case 'O':
                        res += 2;
                        break;
                }
            }
        }
        System.out.println("\nBiodiversidad = " + res);
    }
    
    private static boolean comprobarBucle(){
        boolean res = lista.buscar(huerto) != null;
        System.out.println("Estamos en un bucle: " + res);
        return res;
    }
}