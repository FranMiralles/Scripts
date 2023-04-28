package CompeACM2022.ProblemaB;

import java.util.Scanner;


public class CuestionDeFechas
{
    //Entrada de esta forma: leaps(2016, 2017):
    //Calcular cuantos dias bisiestos segun el Calendario "Juliano" hay entre esos dos años
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String line = sc.nextLine();
            line = line.substring(6, line.length() - 1);
            String[] nums = line.split(", ");
            System.out.print("leaps(" + nums[0] + ", " + nums[1] + ") => " + numBisiestos(Integer.parseInt(nums[0]), Integer.parseInt(nums[1])));
        }
    }
    
    public static int numBisiestos(int ini, int fin){
        int res = 0;
        for(;ini < fin;ini++){
            if(isBisiesto(ini)) res++;
        }
        return res;
    }
    
    public static boolean isBisiesto(int year){
        if(year % 900 == 200 || year % 900 == 600) return true;
        if(year % 100 == 0) return false;
        if(year % 4 == 0) return true;
        return false;
    }
}