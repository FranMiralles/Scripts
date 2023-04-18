package CompeACM2022.ProblemaA;
import java.util.Scanner;


public class JuegoInteractivo extends Main
{
    private static Scanner sc = new Scanner(System.in);
    private static boolean salir = false;    
    
    public static void main(String args[]){
        iniciarValores();
        while(!salir){
            escribirHuerto();
            jugar();
        }
    }
    
    private static void iniciarValores(){
        //Iniciamos el huerto de tamño aleatorio con objetos random minimo de tamaño 6
        huerto = new Huerto(6 + (int) (Math.random()*4+1), 17);
        for(int i = 0; i < huerto.getNumRow(); i++){
            for(int j = 0; j < huerto.getNumColumn(); j++){
                int r = (int) (Math.random()*3+1);
                switch(r){
                    case 1:
                        huerto.setIJ(i, j, 'O');
                        break;
                    case 2:
                        huerto.setIJ(i, j, 'o');
                        break;
                    case 3:
                        huerto.setIJ(i, j, '#');
                        break;
                }
            }
        }
        //Iniciamos posicion en 0,0
        pos[0] = 0; pos[1] = 0;
        //Definimos el numero de semillas inicial
        numSem = (int) (Math.random()*5+5);
        plants = 0;
    }
    
    private static void escribirHuerto(){
        System.out.print("\u000C        ");
        for(int i = 0; i < huerto.getNumColumn() + 4; i++){ System.out.print('-');}
        System.out.print("\n");
        for(int i = 0; i < huerto.getNumRow(); i++){
            System.out.print("        | ");
            for(int j = 0; j < huerto.getNumColumn(); j++){
                //El codigo -> \033[34m, se refiere a color azul (pinta la posicion actual del jugador)
                if(pos[0] == i && pos[1] == j){ System.out.print("A");}
                else{ System.out.print(huerto.getIJ(i, j));}
            }
            System.out.print(" |\n");
        }
        System.out.print("        ");
        for(int i = 0; i < huerto.getNumColumn() + 4; i++){ System.out.print('-');}
        System.out.print("\n\nElemento en la posicion del jugador: " + huerto.getIJ(pos[0], pos[1]));
        System.out.print("\nNumero de semillas: " + numSem);
        System.out.print("\nNumero de plantas: " + plants);
    }
    
    private static void jugar(){
        System.out.print("\nFunciones:\n-mv x y\n-sd\n-wt\n-pk\n-exit\n\n:");
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
            case "exit":
                salir = true;
                System.out.println("Cerrando programa...");
                break;
            default:
                System.out.println("Error en la instruccion elegida");
                break;
        }
        sc.nextLine(); //Ultimo \n
        sc.nextLine(); //Enter para seguir
    }
}