package fichas;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import proyecto.Ventana;

public class Rey extends Ficha{

	private ImageIcon image;
	private Jugador jug;
	private Ficha[] fichasContrarias;
	private Map<JButton, Posicion> mapa;
	
	
	public Rey(int y, int x, Jugador jug, Ficha[] fichasContrarias, Map<JButton, Posicion> mapa) {
		super(y, x);
		this.jug = jug;
		this.fichasContrarias = fichasContrarias;
		this.mapa = mapa;
		if(this.jug == Jugador.BLANCAS) { this.image = new ImageIcon("../Ajedrez/images/ReyBlanco.png");}
		else { this.image = new ImageIcon("../Ajedrez/images/ReyNegro.png");}
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
		
		seguirHastaFicha(lista, new Posicion(this.pos.y + 1, this.pos.x + 1), fichas);
		seguirHastaFicha(lista, new Posicion(this.pos.y - 1, this.pos.x + 1), fichas);
		seguirHastaFicha(lista, new Posicion(this.pos.y + 1, this.pos.x - 1), fichas);
		seguirHastaFicha(lista, new Posicion(this.pos.y - 1, this.pos.x - 1), fichas);
		seguirHastaFicha(lista, new Posicion(this.pos.y + 1, this.pos.x), fichas);
		seguirHastaFicha(lista, new Posicion(this.pos.y - 1, this.pos.x), fichas);
		seguirHastaFicha(lista, new Posicion(this.pos.y, this.pos.x + 1), fichas);
		seguirHastaFicha(lista, new Posicion(this.pos.y, this.pos.x - 1), fichas);
		
		//Eliminar todas las que le pueden matar con un siguiente movimiento
		for(int i = 0; i < fichasContrarias.length; i++) {
			System.out.println("cribando" + i);
			criba(lista, fichasContrarias[i].movimiento(tabla, fichas));
		}
		
		return convALToJB(lista, tabla);
	}
	

	public void criba(ArrayList<Posicion> lista, JButton[] other) {
		for(int i = 0; i < other.length; i++) {
			System.out.println("Mirando" + i);
			if(lista.contains(mapa.get(other[i]))) {
				System.out.println("Contiene");
				lista.remove(mapa.get(other[i]));
			}
		}
	}
}