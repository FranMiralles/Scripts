package fichas;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import proyecto.Ventana;

public class Caballo extends Ficha{

	private ImageIcon image;
	private Jugador jug;
	
	
	public Caballo(int y, int x, Jugador jug) {
		super(y, x);
		this.jug = jug;
		if(this.jug == Jugador.BLANCAS) { this.image = new ImageIcon("../Ajedrez/images/CaballoBlanco.png");}
		else { this.image = new ImageIcon("../Ajedrez/images/CaballoNegro.png");}
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
		//Arriba
		colocarSiHayFichaDJoNoHayFicha(lista, new Posicion(this.pos.y - 2, this.pos.x - 1), fichas);
		colocarSiHayFichaDJoNoHayFicha(lista, new Posicion(this.pos.y - 2, this.pos.x + 1), fichas);
		colocarSiHayFichaDJoNoHayFicha(lista, new Posicion(this.pos.y - 1, this.pos.x - 2), fichas);
		colocarSiHayFichaDJoNoHayFicha(lista, new Posicion(this.pos.y - 1, this.pos.x + 2), fichas);
		//Abajo
		colocarSiHayFichaDJoNoHayFicha(lista, new Posicion(this.pos.y + 2, this.pos.x - 1), fichas);
		colocarSiHayFichaDJoNoHayFicha(lista, new Posicion(this.pos.y + 2, this.pos.x + 1), fichas);
		colocarSiHayFichaDJoNoHayFicha(lista, new Posicion(this.pos.y + 1, this.pos.x - 2), fichas);
		colocarSiHayFichaDJoNoHayFicha(lista, new Posicion(this.pos.y + 1, this.pos.x + 2), fichas);
		return convALToJB(lista, tabla);
	}
	
}