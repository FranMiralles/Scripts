package proyecto;

import javax.swing.JFrame;

import fichas.Alfil;
import fichas.Caballo;
import fichas.Ficha;
import fichas.Jugador;
import fichas.Peon;
import fichas.Posicion;
import fichas.Reina;
import fichas.Rey;
import fichas.Torre;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;


@SuppressWarnings("serial")
public class Ventana extends JFrame{
	
	//Array que tendrá referencias a todos los botones del grid
	static JButton[][] tabla = new JButton[8][8];
	//Array que tendrá referencias a las fichas del grid
	static Ficha[][] fichas = new Ficha[8][8];
	
	static Map<JButton, Posicion> mapa = new HashMap<>();
	
	
	static Color marron = new Color(185, 107, 40, 255); // Crea color marrón utilizando valores RGB b96b28ff
	static Color carne = new Color(230, 200, 162, 255); // Crea color carne utilizando valores RGB e6c8a2ff
	static Color marcado = new Color(255, 246, 0, 255); // Crea color para usarse al marcar las posibles cuadrículas dónde se puede mover RGB fff60080
	static Color selec = new Color(231, 205, 0 ,255); // Crea color para usarse al seleccionar una ficha RGB e7cd00ff
	
	//Indica a qué jugador le toca mover
	static Jugador turno = Jugador.NEGRAS;
	//Indica qué ficha se ha seleccionado, si es null, ninguna
	static Ficha seleccionada= null;
	
	
	public static int screenHeight = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
	
	
	
	public static void main(String args[]) {
		@SuppressWarnings("unused")
		Ventana ven = new Ventana();
		crearFichasInicio();
		//tabla[3][3].setIcon(p1.getImagen());
		//colorearMarcados(p1.movimiento(tabla, fichas));
	}
	
	
	//Método constructor de Ventana
	public Ventana() {
		//setSize(1500,1500);
		setVisible(true);
		setResizable(false);
		getContentPane().setLayout(new GridLayout(8, 8));
		
		//Creando las 64 posiciones (8x8) y metiéndo referencias de las instancias en un array con length de 64
		Color actual = marron;
		for(int i = 0; i < 8; i++) {
			if(actual == carne) { actual = marron;}
			else { actual = carne;}
			for(int j = 0; j < 8; j++) {
				tabla[i][j] = new JButton();
				tabla[i][j].setBorder(null);
				tabla[i][j].setFocusPainted(false);
				tabla[i][j].setPreferredSize(new Dimension(screenHeight / 10, screenHeight / 10));
				tabla[i][j].setBackground(actual);
				getContentPane().add(tabla[i][j]);
				
				mapa.put(tabla[i][j], new Posicion(i, j));
				if(actual == carne) { actual = marron;}
				else { actual = carne;}
			}
		}
		setButtons();
		pack();
	}
	
	//Función de los botones
	public void setButtons() {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				JButton boton = tabla[i][j];
				boton.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				    	//Seleccionar si no hay ninguna seleccionada y la ficha tocada pertenece a quien le pertenece el turno
				    	if(seleccionada == null && fichas[mapa.get(boton).getY()][mapa.get(boton).getX()] != null && fichas[mapa.get(boton).getY()][mapa.get(boton).getX()].getJugador() == turno) {
				    		seleccionada = fichas[mapa.get(boton).getY()][mapa.get(boton).getX()];
				    		boton.setBackground(selec);
				    		colorearMarcados(fichas[mapa.get(boton).getY()][mapa.get(boton).getX()].movimiento(tabla, fichas));
				    	}else if(seleccionada != null && boton.getBackground() != marcado) {
				    		if(fichas[mapa.get(boton).getY()][mapa.get(boton).getX()] != null && fichas[mapa.get(boton).getY()][mapa.get(boton).getX()].getJugador() == seleccionada.getJugador()) {
				    			setColorDefecto();
						    	seleccionada = fichas[mapa.get(boton).getY()][mapa.get(boton).getX()];
						    	boton.setBackground(selec);
						    	colorearMarcados(fichas[mapa.get(boton).getY()][mapa.get(boton).getX()].movimiento(tabla, fichas));
				    		}else {
				    			seleccionada = null;
						    	setColorDefecto();
				    		}
					    }else {	
					    }
					    //Mover
					    if(boton.getBackground() == marcado) {
					    	//Si es peón, eliminar el doble avance inicial
					    	if (seleccionada instanceof Peon) {
					    		Peon aux = (Peon) seleccionada;
					    		aux.eliminarDobleMovimiento();
					    		seleccionada = aux;
					    	}
					    	//Eliminar la ficha de donde estaba
					    	fichas[seleccionada.getPos().getY()][seleccionada.getPos().getX()] = null;
					    	tabla[seleccionada.getPos().getY()][seleccionada.getPos().getX()].setIcon(null);
					    	//Meter la ficha en el nuevo lugar, actualizando su posición y su lugar en el array
					    	seleccionada.setPos(mapa.get(boton).getY(), mapa.get(boton).getX());
					    	fichas[mapa.get(boton).getY()][mapa.get(boton).getX()] = seleccionada;
				    		boton.setIcon(seleccionada.getImagen());
				    		//Eliminar selección y cambiar de turno
				    		seleccionada = null;
				    		if(turno == Jugador.BLANCAS) { turno = Jugador.NEGRAS;}
				    		else { turno = Jugador.BLANCAS;}
				    		setColorDefecto();
				    	}
					    
				        
						
				    }
				});
			}
		}
	}
	
	public static void mostrarFichas() {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(fichas[i][j] != null) {
					tabla[i][j].setIcon(fichas[i][j].getImagen());
				}
			}
		}
	}
	
	public static void crearFichasInicio() {
		
		Ficha[] eqNegras = new Ficha[15];
		Ficha[] eqBlancas = new Ficha[15];
		
		//Peones
		for(int j = 0; j < 8; j++) {
			fichas[1][j] = new Peon(1, j, Jugador.NEGRAS);
			fichas[6][j] = new Peon(6, j, Jugador.BLANCAS);
		}
		//Negras
		fichas[0][0] = new Torre(0, 0, Jugador.NEGRAS);
		fichas[0][7] = new Torre(0, 7, Jugador.NEGRAS);
		fichas[0][1] = new Caballo(0, 1, Jugador.NEGRAS);
		fichas[0][6] = new Caballo(0, 6, Jugador.NEGRAS);
		fichas[0][2] = new Alfil(0, 2, Jugador.NEGRAS);
		fichas[0][5] = new Alfil(0, 5, Jugador.NEGRAS);
		fichas[0][3] = new Reina(0, 3, Jugador.NEGRAS);
			
		//Negras
		fichas[7][0] = new Torre(7, 0, Jugador.BLANCAS);
		fichas[7][7] = new Torre(7, 7, Jugador.BLANCAS);
		fichas[7][1] = new Caballo(7, 1, Jugador.BLANCAS);
		fichas[7][6] = new Caballo(7, 6, Jugador.BLANCAS);
		fichas[7][2] = new Alfil(7, 2, Jugador.BLANCAS);
		fichas[7][5] = new Alfil(7, 5, Jugador.BLANCAS);
		fichas[7][3] = new Reina(7, 3, Jugador.BLANCAS);
		
		//Reyes
		for(int i = 0; i < 8; i++) {
			eqNegras[i] = fichas[1][i];
			eqBlancas[i] = fichas[6][i];
		}
		for(int i = 0; i < 4; i++) {
			eqNegras[i + 8] = fichas[0][i];
			eqBlancas[i + 8] = fichas[7][i];
		}
		for(int i = 0; i < 3; i++) {
			eqNegras[i + 12] = fichas[0][i + 5];
			eqBlancas[i + 12] = fichas[7][i + 5];
		}
		
		fichas[0][4] = new Rey(0, 4, Jugador.NEGRAS, eqBlancas, mapa);
		fichas[7][4] = new Rey(7, 4, Jugador.BLANCAS, eqNegras, mapa);
		 
		mostrarFichas();
	}
	
	public static void colorearMarcados(JButton[] marcados) {
		for(int i = 0; i < marcados.length; i++) {
			marcados[i].setBackground(marcado);
		}
	}
	
	//Método para devolver los colores de las casillas a su origen
	public void setColorDefecto() {
		Color actual = marron;
		for(int i = 0; i < 8; i++) {
			if(actual == carne) { actual = marron;}
			else { actual = carne;}
			for(int j = 0; j < 8; j++) {
				tabla[i][j].setBackground(actual);
				if(actual == carne) { actual = marron;}
				else { actual = carne;}
			}
		}
	}
} 