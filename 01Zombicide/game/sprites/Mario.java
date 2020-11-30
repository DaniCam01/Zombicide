package sprites;

import java.awt.Graphics;
import java.awt.Image;

import util.Assets;

public class Mario {
	private int x;
	private int y;
	private Image mario;
	private int rowFrame;
	private int ANCHO;
	private int ALTO;
	private final static int COLUMNAS = 8;
	private final static int FILAS = 2;
	private int columnframe;
	//private int speed;

	public Mario() {
		x = 240;
		y = 415;
		columnframe = 0;
		ANCHO = Assets.iimario.getIconWidth() / COLUMNAS;
		ALTO = Assets.iimario.getIconHeight() / FILAS;
		mario = Assets.iimario.getImage();
	}
	public void left(){
		rowFrame = 0;
	}
	
	public void right(){
		rowFrame = 1;
	}


	public void update() {
		columnframe = ++columnframe % 8;
	}

	public void draw(Graphics g) {
		int frameX = columnframe * ANCHO;
		int frameY = rowFrame * ALTO;
		g.drawImage(mario, x, y, x + ANCHO, y + ALTO, frameX, frameY, frameX + ANCHO, frameY + ALTO, null);
	}

}
