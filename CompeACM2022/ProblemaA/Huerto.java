package CompeACM2022.ProblemaA;


public class Huerto
{
    private char[][] huerto;
    
    public Huerto(int lenI, int lenJ){
        huerto = new char[lenI][lenJ];
    }
    
    public Huerto(char[][] huerto){
        this.huerto = huerto;
    }
    
    public char getIJ(int i, int j){
        return huerto[i][j];
    }
    
    public void setIJ(int i, int j, char c){
        huerto[i][j] = c;
    }
    
    public int getNumRow(){
        return huerto.length;
    }
    
    public int getNumColumn(){
        return huerto[0].length;
    }
}