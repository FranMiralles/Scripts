package TresEnRaya;

public class Tablero {
    
    char[][] tablero = new char[3][3];
    static final char J1 = 'x';
    static final char J2 = 'o';
    static final char vacio = '_';
    int numVacios;
    
    
    public Tablero(){
        IniTab();
        numVacios = 9;
    }
    
    void IniTab(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                tablero[i][j] =  vacio;
            }
        }
        numVacios = 9;
    }
    
    void GrafTab(){
        System.out.println();
        for(int i = 0; i < 3; i++){
            System.out.print("\t");
            for(int j = 0; j < 3; j++){
                System.out.print(tablero[i][j]);
                if(j != 2){
                    System.out.print('|');
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    
    int ColocJug(int pos, char sim){
        if(pos < 1 || pos > 9){
            System.out.println("Posición incorrecta");
            return -1;
        }
        pos = pos - 1;
        int i = pos / 3;
        int j = pos % 3;
        if(tablero[i][j] != vacio){
            System.out.println("Posición incorrecta");
            return -1;
        }
        tablero[i][j] = sim;
        numVacios--;
        if(Gana(sim)){ return 1;}
        return 0;
    }
    
    int ColocCPU(){
        int elec = elecganCPU() - 1;
        if(elec == -1){
            elec = elecbloqCPU() - 1;
            if(elec == -1){
                int[] pos = new int[numVacios];
                int x = 0;
                for(int i = 0; i < 3; i++){
                    for(int j = 0; j < 3; j++){
                        if(tablero[i][j] == vacio){
                            pos[x] = i * 3 + (j + 1);
                            x++;
                        }
                    }
                }
                int ran = (int) (Math.random() * numVacios);
                elec = pos[ran] - 1;
            }
        }
        int i = elec / 3;
        int j = elec % 3;
        tablero[i][j] = J2;
        numVacios--;
        if(Gana(J2)){ return 1;}
        return 0;
    }
    
    //Ganar si es posible
    int elecganCPU(){
        int contVacio = 0;
        int pos = 0;
        int contJ2 = 0;
        //Comprobar Horizontales
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(tablero[i][j] == J2){ contJ2++;}
                if(tablero[i][j] == vacio){ contVacio++; pos = i * 3 + (j + 1);}
            }
            if(contVacio == 1 && contJ2 == 2){
                return pos;
            }
            contVacio = 0;
            contJ2 = 0;
        }
        //Comprobar Verticales
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(tablero[j][i] == J2){ contJ2++;}
                if(tablero[j][i] == vacio){ contVacio++; pos = j * 3 + (i + 1);}
            }
            if(contVacio == 1 && contJ2 == 2){
                return pos;
            }
            contVacio = 0;
            contJ2 = 0;
        }
        //Comprobar Diagonal
        for(int i = 0; i < 3; i++){
            if(tablero[i][i] == J2){ contJ2++;}
            if(tablero[i][i] == vacio){ contVacio++; pos = i * 3 + (i + 1);}
        }
        if(contVacio == 1 && contJ2 == 2){
                return pos;
        }
        contVacio = 0;
        contJ2 = 0;
        
        if(tablero[0][2] == J2){ contJ2++;}
        if(tablero[0][2] == vacio){ contVacio++; pos = 3;}
        if(tablero[1][1] == J2){ contJ2++;}
        if(tablero[1][1] == vacio){ contVacio++; pos = 5;}
        if(tablero[2][0] == J2){ contJ2++;}
        if(tablero[2][0] == vacio){ contVacio++; pos = 7;}
        
        if(contVacio == 1 && contJ2 == 2){
                return pos;
        }
        
        return 0;
    }
    
    //Bloquear victoria del jugador
    int elecbloqCPU(){
        int contVacio = 0;
        int pos = 0;
        int contJ1 = 0;
        //Comprobar Horizontales
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(tablero[i][j] == J1){ contJ1++;}
                if(tablero[i][j] == vacio){ contVacio++; pos = i * 3 + (j + 1);}
            }
            if(contVacio == 1 && contJ1 == 2){
                return pos;
            }
            contVacio = 0;
            contJ1 = 0;
        }
        //Comprobar Verticales
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(tablero[j][i] == J1){ contJ1++;}
                if(tablero[j][i] == vacio){ contVacio++; pos = j * 3 + (i + 1);}
            }
            if(contVacio == 1 && contJ1 == 2){
                return pos;
            }
            contVacio = 0;
            contJ1 = 0;
        }
        //Comprobar Diagonal
        for(int i = 0; i < 3; i++){
            if(tablero[i][i] == J1){ contJ1++;}
            if(tablero[i][i] == vacio){ contVacio++; pos = i * 3 + (i + 1);}
        }
        if(contVacio == 1 && contJ1 == 2){
                return pos;
        }
        contVacio = 0;
        contJ1 = 0;
        
        if(tablero[0][2] == J1){ contJ1++;}
        if(tablero[0][2] == vacio){ contVacio++; pos = 3;}
        if(tablero[1][1] == J1){ contJ1++;}
        if(tablero[1][1] == vacio){ contVacio++; pos = 5;}
        if(tablero[2][0] == J1){ contJ1++;}
        if(tablero[2][0] == vacio){ contVacio++; pos = 7;}
        
        if(contVacio == 1 && contJ1 == 2){
                return pos;
        }
        
        return 0;
    }
    
    boolean Gana(char sim){
        //Comprobar Horizontales
        for(int i = 0; i < 3; i++){
            if(tablero[i][0] == sim && tablero[i][1] == sim && tablero[i][2] == sim){
                return true;
            }   
        }
        //Comprobar Verticales
        for(int j = 0; j < 3; j++){
            if(tablero[0][j] == sim && tablero[1][j] == sim && tablero[2][j] == sim){
                return true;
            }   
        }
        //Comprobar Diagonales
        if(tablero[0][0] == sim && tablero[1][1] == sim && tablero[2][2] == sim){
            return true;
        }
        if(tablero[0][2] == sim && tablero[1][1] == sim && tablero[2][0] == sim){
            return true;
        }
        return false;
    }
    
    void Espacio(){
        System.out.println();
        for(int i = 0; i < 6; i++){
            System.out.println("****");
        }
        System.out.println();
    }
}