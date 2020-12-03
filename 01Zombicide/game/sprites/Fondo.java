package sprites;

import java.awt.Graphics;
import java.awt.Image;
import util.Assets;
import util.Constant;

public class Fondo {
	private int x = util.Constant.ANCHOFONDO/2-util.Constant.WIDTHSCREEN/2;
	
	//Mapa1
	//private int y= 0;
	//Mapa 2
	private int y = util.Constant.HEIGHTSCREEN-16;
	
	
	private Image fondo = Assets.iifondo.getImage();
	private int speed = 0;

	public Fondo() {
	}

	public void draw(Graphics g) {
		g.drawImage(fondo, 0, 0, Constant.WIDTHSCREEN, Constant.HEIGHTSCREEN, 
				x, y, x + Constant.WIDTHSCREEN, y+Constant.HEIGHTSCREEN, null);
	}

	public void update() {
		x += speed;
		if (x < 0) {
			speed=0;
			x=0;
		}
		if (x + Constant.WIDTHSCREEN > Constant.ANCHOFONDO) { 
			x=Constant.ANCHOFONDO-Constant.WIDTHSCREEN;
			speed = 0;
		}	
	}

	public void left() {
		speed = -3;
	}

	public void right() {
		speed = 3;
	}
	
	public void estatico() {
		speed = 0;
	}
}
