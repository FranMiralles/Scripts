package fichas;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import proyecto.Ventana;

public class Reina extends Ficha{

	private ImageIcon image;
	private Jugador jug;
	
	
	public Reina(int y, int x, Jugador jug) {
		super(y, x);
		this.jug = jug;
		if(this.jug == Jugador.BLANCAS) { this.image = new ImageIcon("../Ajedrez/images/ReinaBlanca.png");}
		else { this.image = new ImageIcon("../Ajedrez/images/ReinaNegra.png");}
		image = new ImageIcon(image.getImage().getScaledInstance((int) ((Ventana.screenHeight / 10) * 0.676), (int) ((Ventana.screenHeight / 10) * 0.861), Image.SCALE_DEFAULT));
	}

	
	@Override
	public Jugador getJugador() {
		return jug;
	}

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
		ArrayList<Posicion> lista = new ArrayList<Posicion>();
		
		//Movimiento Alfil
		
		//1,1
		for(int i = 1; seguirHastaFicha(lista, new Posicion(this.pos.y + i, this.pos.x + i), fichas); i++) {
		}
				
		//-1,1
		for(int i = 1; seguirHastaFicha(lista, new Posicion(this.pos.y - i, this.pos.x + i), fichas); i++) {
		}
				
		//1,-1
		for(int i = 1; seguirHastaFicha(lista, new Posicion(this.pos.y + i, this.pos.x - i), fichas); i++) {
		}
				
		//-1,-1
		for(int i = 1; seguirHastaFicha(lista, new Posicion(this.pos.y - i, this.pos.x - i), fichas); i++) {
		}
				
		//Movimiento de Torre
		//1,0
		for(int i = 1; seguirHastaFicha(lista, new Posicion(this.pos.y + i, this.pos.x), fichas); i++) {
		}
		
		//-1, 0
		for(int i = 1; seguirHastaFicha(lista, new Posicion(this.pos.y - i, this.pos.x), fichas); i++) {
		}
		
		//0, 1
		for(int i = 1; seguirHastaFicha(lista, new Posicion(this.pos.y, this.pos.x + i), fichas); i++) {
		}
		
		//0,-1
		for(int i = 1; seguirHastaFicha(lista, new Posicion(this.pos.y, this.pos.x - i), fichas); i++) {
		}
		
		return convALToJB(lista, tabla);
	}
	
}
