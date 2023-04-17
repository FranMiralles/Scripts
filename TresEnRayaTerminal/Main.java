package TresEnRaya;
import java.util.Scanner;
import java.util.InputMismatchException;



public class Main
{
    
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.println("¡Bienvenido!");
        boolean contMal = false;
        while(true){
            System.out.println("¿Quieres jugar multijugador o contra el ordenador?(M/O)");
            char c = sc.next().charAt(0);
            switch(c){
                case 'M':
                case 'm':
                    vsLocal();
                    break;
                case 'O':
                case 'o':
                    vsOrdenador();
                    break;
                default:
                    System.out.println("Escriba: (M/O)");
                    contMal = true;
                    break;
            }
            if(!contMal){
                System.out.println("\n¿Quieres volver a jugar?(S/N)");
                char v = sc.next().charAt(0);
                System.out.println();
                if(v != 'S' && v != 's'){
                    System.out.println("Desconectando...");
                    break;
                }
                System.out.print("\u000C");
            }
            contMal = false;
        }
    }
    
    static void vsLocal(){
        Tablero tab = new Tablero();
        Scanner sc = new Scanner(System.in);
        System.out.println("**TABLERO**");
        tab.GrafTab();
        int in;
        int jug;
        while(tab.numVacios > 0){
            System.out.println("**ELIGE NUMERO, JUGADOR 1**");
            try{
                in =sc.nextInt();
                sc.nextLine();
                jug = tab.ColocJug(in, tab.J1);
            }catch(InputMismatchException e){
                jug = -1;
                sc.nextLine();
            }
            while(jug == -1){
                tab.GrafTab();
                System.out.println("Vuelva a elegir");
                try{
                    in = sc.nextInt();
                    jug = tab.ColocJug(in, tab.J1);
                }catch(InputMismatchException e){
                    jug = -1;
                    sc.nextLine();
                }
            }
            if(jug == 1){ 
                tab.GrafTab();
                System.out.println("****¡HAS GANADO, JUGADOR 1!****");
                break;
            }
            tab.GrafTab();
            if(tab.numVacios <= 0){ 
                System.out.println("**EMPATE**");
                break;
            }
            System.out.println("**ELIGE NUMERO, JUGADOR 2**");
            try{
                in =sc.nextInt();
                sc.nextLine();
                jug = tab.ColocJug(in, tab.J2);
            }catch(InputMismatchException e){
                jug = -1;
                sc.nextLine();
            }
            while(jug == -1){
                tab.GrafTab();
                System.out.println("Vuelva a elegir");
                try{
                    in = sc.nextInt();
                    jug = tab.ColocJug(in, tab.J2);
                }catch(InputMismatchException e){
                    jug = -1;
                    sc.nextLine();
                }
            }
            if(jug == 1){ 
                tab.GrafTab();
                System.out.println("****¡HAS GANADO, JUGADOR 2!****");
                break;
            }
            tab.GrafTab();
        }
        sc.close();
    }
    
    static void vsOrdenador(){
        Tablero tab = new Tablero();
        Scanner sc = new Scanner(System.in);
        System.out.println("**TABLERO**");
        tab.GrafTab();
        int in;
        int jug;
        while(tab.numVacios > 0){
            System.out.println("**ELIGE NUMERO**");
            try{
                in =sc.nextInt();
                sc.nextLine();
                jug = tab.ColocJug(in, tab.J1);
            }catch(InputMismatchException e){
                jug = -1;
                sc.nextLine();
            }
            while(jug == -1){
                tab.GrafTab();
                System.out.println("Vuelva a elegir");
                try{
                    in = sc.nextInt();
                    jug = tab.ColocJug(in, tab.J1);
                }catch(InputMismatchException e){
                    jug = -1;
                    sc.nextLine();
                }
            }
            if(jug == 1){ 
                tab.GrafTab();
                System.out.println("****¡HAS GANADO!****");
                break;
            }
            tab.GrafTab();
            if(tab.numVacios <= 0){ 
                System.out.println("**EMPATE**");
                break;
            }
            System.out.println("**TURNO DEL ORDENADOR**");
            int cpu = tab.ColocCPU();
            if(cpu == 1){
                tab.GrafTab();
                System.out.println("*****HAS PERDIDO...****");
                break;
            }
            tab.GrafTab();
        }
        sc.close();
    }
}