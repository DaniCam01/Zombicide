package sprites;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import util.Assets;

public class Disparo {
	public int x;
	protected int y;
	private Image disparo;
	private int rowFrame;
	protected int ANCHO;
	protected int ALTO;
	private final static int COLUMNAS = 1;
	private final static int FILAS = 2;
	private int columnframe;
	private Player personaje;
	private boolean derecha;
	private int posicionjugador;
	//private int speed;

	public Disparo(int posicionjugador, boolean derecha) {
		this.derecha = derecha;
		y = 375;
		if(derecha) {
			x = posicionjugador+35;
		}else {
			x= posicionjugador-5;
		}
		columnframe = 0;
		ANCHO = Assets.iidisparo.getIconWidth()/COLUMNAS;
		ALTO = Assets.iidisparo.getIconHeight()/FILAS;
		disparo = Assets.iidisparo.getImage();
	}

	

	public void update() {
		if(derecha) {
			x+=15;
			rowFrame = 0;
		}else {
			x-=15;
			rowFrame=1;
		}
	}

	public void draw(Graphics g) {
		int frameX = columnframe * ANCHO;
		int frameY = rowFrame * ALTO;
		g.drawImage(disparo, x, y, x + ANCHO, y + ALTO, frameX, frameY, frameX + ANCHO, frameY + ALTO, null);
	}
	
	public boolean colision(Player bola) {
		Rectangle recZombie = new Rectangle(x , y, ANCHO, ALTO);
		Rectangle recBola = new Rectangle(bola.x, bola.y, bola.ANCHO, bola.ALTO );
		return recZombie.intersects(recBola);
	}
}
