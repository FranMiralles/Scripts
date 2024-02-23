package fichas;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;


public abstract class Ficha {
	
	Posicion pos;
	
	public abstract Jugador getJugador();
	public abstract void setPos(int x, int y);
	public abstract Posicion getPos();
	public abstract ImageIcon getImagen();
	public abstract JButton[] movimiento(JButton[][] tabla, Ficha[][] fichas);
	
	
	public Ficha(int y, int x) {
		this.pos = new Posicion(y, x);
	}
	
	//Colocamos si no se sale de la tabla y si no hay una ficha en esa posición
	public boolean colocarNoHayFicha(ArrayList<Posicion> lista, Posicion pos, Ficha[][] fichas){
		boolean colocado = false;
		if(cabeTabla(pos) && fichas[pos.getY()][pos.getX()] == null){
			lista.add(pos);
			colocado = true;
		}
		return colocado;
	}
	
	//Colocamos si no se sale de la tabla y si hay una ficha en esa posición del mismo jugador
	public boolean colocarSiHayFichaMJ(ArrayList<Posicion> lista, Posicion pos, Ficha[][] fichas){
		boolean colocado = false;
		if(cabeTabla(pos) && fichas[pos.getY()][pos.getX()] != null && fichas[pos.getY()][pos.getX()].getJugador() == getJugador()){
			lista.add(pos);
			colocado = true;
		}
		return colocado;
	}
	
	//Colocamos si no se sale de la tabla y si hay una ficha en esa posición del otro jugador
	public boolean colocarSiHayFichaDJ(ArrayList<Posicion> lista, Posicion pos, Ficha[][] fichas){
		boolean colocado = false;
		if(cabeTabla(pos) && fichas[pos.getY()][pos.getX()] != null && fichas[pos.getY()][pos.getX()].getJugador() != getJugador()){
			lista.add(pos);
		}
		return colocado;
	}
	
	//Colocamos si no se sale de la tabla y si hay una ficha en esa posición del otro jugador
	public boolean colocarSiHayFichaDJoNoHayFicha(ArrayList<Posicion> lista, Posicion pos, Ficha[][] fichas){
		boolean colocado = false;
		if(cabeTabla(pos) && fichas[pos.getY()][pos.getX()] != null && fichas[pos.getY()][pos.getX()].getJugador() != getJugador()){
			lista.add(pos);
			colocado = true;
		}else if(cabeTabla(pos) && fichas[pos.getY()][pos.getX()] == null){
			lista.add(pos);
			colocado = true;
		}
		return colocado;
	}
	
	//Método para usarlo en el movimiento de la torre y el alfil
	public boolean seguirHastaFicha(ArrayList<Posicion> lista, Posicion pos, Ficha[][] fichas) {
		if(cabeTabla(pos) && fichas[pos.getY()][pos.getX()] == null) {
			//En el caso de que no haya ficha
			lista.add(pos);
			return true;
		}
		if(cabeTabla(pos) && fichas[pos.getY()][pos.getX()].getJugador() == this.getJugador()) {
			//En el caso de ser del mismo jugador, no se incluye la ficha
			return false;
		}
		if(cabeTabla(pos) && fichas[pos.getY()][pos.getX()].getJugador() != this.getJugador()) {
			//En el caso de ser dedistinto jugador, se incluye la ficha
			lista.add(pos);
			return false;
		}
		return false;
	}
	
	//Devuelve true si no se sale de la tabla, false si sale 
	public boolean cabeTabla(Posicion pos){
		return pos.getY() >= 0 && pos.getY() <= 7 && pos.getX() >= 0 && pos.getX() <= 7;
	}
	
	//Método para pasar de ArrayList<Posiciones> -> JButton[]
	public JButton[] convALToJB(ArrayList<Posicion> lista, JButton[][] tabla) {
		Posicion[] listaAr = lista.toArray(new Posicion[lista.size()]);
		JButton[] res = new JButton[listaAr.length];
		for(int i = 0; i < listaAr.length; i++) {
			res[i] = tabla[listaAr[i].getY()][listaAr[i].getX()];
		}
		return res;
	}
}