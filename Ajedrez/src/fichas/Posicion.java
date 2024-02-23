package fichas;

public class Posicion {
	//Dupla de x e y
	int x; //Variable j => columnas
	int y; //Variable i => filas 
	
	public Posicion(int y, int x) {
		this.x = x;
		this.y = y;
	}
	
	public void setPos(int y, int x) {
		this.x = x;
		this.y = y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
