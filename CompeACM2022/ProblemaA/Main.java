package CompeACM2022.ProblemaA;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

//12345\n17845\n89078\n67894\n\n\n4


public class Main
{
    public static Scanner sc; //Scanner por donde leemos las instrucciones y datos
    public static Huerto huerto; //Variable huerto
    public static int[] pos = new int[2]; //Indica posicion del jugador
    public static int numSem; //Numero de semillas disponibles
    public static int plants; //Numero de plantas maduras que tienes
    
    public static void main(String args[]){
        try{
            sc = new Scanner(new File("CompeACM2022/ProblemaA/input.txt"));
        }catch(FileNotFoundException e){}
        //Entrada huerto
        iniciarHuerto();
        //Entrada posicion inicio y semillas inicio
        iniciarPosSemPlants();
        //Metodo para jugar con las instrucciones
        jugar();
        //Escribir el numero de plantas maduras finales
        System.out.println(plants);
    }
    
    //El problema te da el huerto en string, cuando acaba hay una linea en blanco
    private static void iniciarHuerto(){
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
        huerto = new Huerto(numRow, numCol);
        
        for(int i = 0; i < numRow; i++){
            for(int j = 0; j < numCol; j++){
                huerto.setIJ(i, j, rows[i].charAt(j));
            }
        }
    }
    
    //Vendra dada la posicion y semillas en el siguiente formato
    // 0 0 <-- posicion i, j
    // 4 <-- numero de semillas inicial
    private static void iniciarPosSemPlants(){
        pos[0] = sc.nextInt();
        pos[1] = sc.nextInt();
        sc.nextLine();
        numSem = sc.nextInt();
        plants = 0;
        sc.nextLine(); sc.nextLine(); // El "\n" y la linea en blanco siguiente
    }
    
    //Elegir metodo a usar
    private static void jugar(){
        while(sc.hasNext()){
            String method = sc.next();
            switch(method){
                case "mv":
                    mv(sc.nextInt(), sc.nextInt());
                    break;
                case "pk":
                    pk();
                    break;
                case "sd":
                    sd();
                    break;
                case "wt":
                    wt();
                    break;
            }
            sc.nextLine();
        }
    }
    
    //Mueve el numero de veces que le pasen por parametros en cada coordenada
    //Si se sale del huerto, no lo mueve
    protected static void mv(int dx, int dy){
        dy += pos[0];
        dx += pos[1];
        if(!(dy < 0 || dx < 0 || dy >= huerto.getNumRow() || dx >= huerto.getNumColumn())){
            pos[0] = dy;
            pos[1] = dx;
            System.out.println("¡El jugador se movera a: " + pos[1] + ", " + pos[0] + "!");
        }else{ System.out.println("¡Podrias haber salido del huero!");}
    }
    
    //Coger una planta si es madura -> O
    protected static void pk(){
        if(huerto.getIJ(pos[0], pos[1]) == 'O'){ 
            plants++;
            huerto.setIJ(pos[0], pos[1], '#');
            System.out.println("¡Planta cosechada!");
        }else{ System.out.println("No ha pasado nada...");}
    }
    
    //Planta una semilla si tiene y la tierra no tiene ninguna planta ya
    protected static void sd(){
        if(huerto.getIJ(pos[0], pos[1]) == '#' && numSem > 0){
            numSem--;
            huerto.setIJ(pos[0], pos[1], 'o');
            System.out.println("¡Semillada plantada!");
        }else{ System.out.println("No ha pasado nada...");}
    }
    
    //Riega haciendo crecer una planta de o -> O
    protected static void wt(){
        if(huerto.getIJ(pos[0], pos[1]) == 'o'){
            huerto.setIJ(pos[0], pos[1], 'O');
            System.out.println("¡La planta ha crecido!");
        }else{ System.out.println("No ha pasado nada...");}
    }
}