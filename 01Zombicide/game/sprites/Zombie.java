package sprites;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import util.Assets;

public class Zombie {
	protected int x;
	protected int y;
	private Image zombie;
	private int rowFrame;
	protected int ANCHO;
	protected int ALTO;
	private final static int COLUMNAS = 8;
	private final static int FILAS = 6;
	private int columnframe;
	private Player personaje;
	private boolean derecha;
	//private int speed;

	public Zombie(int posicionjugador) {
		x = (int)((Math.random()*1000)+500);
		int negativePositive = (int)(Math.random()*2);
		y = 345;
		if(negativePositive==0) {
			derecha = true;
			x = posicionjugador-x;
		}else {
			x= posicionjugador+x;
			derecha = false;
		}
		columnframe = 0;
		ANCHO = Assets.iizombie.getIconWidth() / COLUMNAS;
		ALTO = Assets.iizombie.getIconHeight() / FILAS;
		zombie = Assets.iizombie.getImage();
	}
	public void moove(){
		rowFrame = 4;
		if(derecha) {
			x+=3;
		}else
			x-=3;
		
	}
	

	public void update() {
		columnframe = ++columnframe % 8;
	}

	public void draw(Graphics g) {
		int frameX = columnframe * ANCHO;
		int frameY = rowFrame * ALTO;
		g.drawImage(zombie, x, y, x + ANCHO, y + ALTO, frameX, frameY, frameX + ANCHO, frameY + ALTO, null);
	}
	
	public boolean colision(Disparo disparo) {
		Rectangle recZombie = new Rectangle(x , y, ANCHO, ALTO);
		Rectangle recBola = new Rectangle(disparo.x, disparo.y, disparo.ANCHO, disparo.ALTO );
		return recZombie.intersects(recBola);
	}
}
