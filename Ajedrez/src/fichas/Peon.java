package fichas;


import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import proyecto.Ventana;


public class Peon extends Ficha{
	
	private ImageIcon image;
	private Jugador jug;
	boolean dobleMovimiento;
	
	
	public Peon(int y, int x, Jugador jug) {
		super(y, x);
		this.jug = jug;
		if(this.jug == Jugador.BLANCAS) { this.image = new ImageIcon("../Ajedrez/images/PeonBlanco.png");}
		else { this.image = new ImageIcon("../Ajedrez/images/PeonNegro.png");}
		dobleMovimiento = true;
		image = new ImageIcon(image.getImage().getScaledInstance((int) ((Ventana.screenHeight / 10) * 0.676), (int) ((Ventana.screenHeight / 10) * 0.861), Image.SCALE_DEFAULT));
	}
	
	@Override
	public Jugador getJugador() {
		return jug;
	}
	
	@Override
	public void setPos(int y, int x) {
		pos.setPos(y, x);
	}
	
	@Override
	public Posicion getPos() {
		return pos;
	}
	
	@Override
	public ImageIcon getImagen() {
		return image;
	}
	
	@Override
	public JButton[] movimiento(JButton[][] tabla, Ficha[][] fichas) {
		//Debe devolver todas las posiciones que puede llegar en el siguiente movimiento
		ArrayList<Posicion> lista = new ArrayList<Posicion>();
		if(jug == Jugador.BLANCAS) {
			//Avanza arriba -> posiciones posibles: delante o cruzado sólo si hay fichas
			//Lados diagonales si hay una ficha del otro jugador
			colocarSiHayFichaDJ(lista, new Posicion(this.pos.y - 1, this.pos.x - 1), fichas);
			colocarSiHayFichaDJ(lista, new Posicion(this.pos.y - 1, this.pos.x + 1), fichas);
			
			//Movimiento para delante
			if(colocarNoHayFicha(lista, new Posicion(this.pos.y - 1, this.pos.x), fichas)) {
				if(dobleMovimiento) {
					colocarNoHayFicha(lista, new Posicion(this.pos.y - 2, this.pos.x), fichas);
				}
			}
			
		}
		if(jug == Jugador.NEGRAS) {
			//Avanza arriba -> posiciones posibles: delante o cruzado sólo si hay fichas
			//Lados diagonales si hay una ficha del otro jugador
			colocarSiHayFichaDJ(lista, new Posicion(this.pos.y + 1, this.pos.x - 1), fichas);
			colocarSiHayFichaDJ(lista, new Posicion(this.pos.y + 1, this.pos.x + 1), fichas);
			
			//Movimiento para delante
			if(colocarNoHayFicha(lista, new Posicion(this.pos.y + 1, this.pos.x), fichas)) {
				if(dobleMovimiento) {
					colocarNoHayFicha(lista, new Posicion(this.pos.y + 2, this.pos.x), fichas);
				}
			}
		}
		
		return convALToJB(lista, tabla);
	}
	
	public void eliminarDobleMovimiento() {
		dobleMovimiento = false;
	}
}
