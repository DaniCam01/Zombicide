package sprites;

import java.awt.Graphics;
import java.awt.Image;
import util.Assets;
import util.Constant;

public class Fondo {
	private int x = 0;
	private Image fondo = Assets.iifondo.getImage();
	private int speed = 0;

	public Fondo() {
	}

	public void draw(Graphics g) {
		g.drawImage(fondo, 0, 0, Constant.WIDTHSCREEN, Constant.HEIGHTSCREEN, x, 0, x + Constant.WIDTHSCREEN,
				Constant.HEIGHTSCREEN, null);
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
		speed = -5;
	}

	public void right() {
		speed = 5;
	}
}
