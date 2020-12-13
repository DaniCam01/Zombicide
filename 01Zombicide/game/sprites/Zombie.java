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
	private final static int FILAS = 12;
	private int columnframe;
	private boolean derecha;
	private int velocidad;
	private int speed=0;

	public Zombie(int posicionjugador, int speed) {
		x = (int)((Math.random()*800)+500);
		int negativePositive = (int)(Math.random()*2);
		y = 340;
		if(negativePositive==0) {
			derecha = true;
			x = posicionjugador-x;
		}else {
			x= posicionjugador+x;
			derecha = false;
		}
		velocidad = (int)(Math.random()*3+1);
		columnframe = 0;
		ANCHO = Assets.iizombie.getIconWidth() / COLUMNAS;
		ALTO = Assets.iizombie.getIconHeight() / FILAS;
		zombie = Assets.iizombie.getImage();
		if(speed+velocidad < 4) {
			this.speed = speed;
		}else {
			if((int)Math.random()*5 == 1) {
				this.speed = speed;
			}else
				velocidad = 3;
		}
	}
	public void moove(){
		if(derecha) {
			x+=velocidad+speed;
			rowFrame = 4;
		}else {
			x-=(velocidad+speed);
			rowFrame = 10;
		}
	}
	
	public void left() {
		x-=3;
	}
	public void right() {
		x+=3;
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

	public void setSpeed(int speed) {
		this.speed+=speed;
	}
}
