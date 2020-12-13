package sprites;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import util.Assets;

public class Botiquin {
	public int x;
	protected int y;
	private Image botiquin;
	private int rowFrame;
	protected int ANCHO;
	protected int ALTO;
	private int columnframe;
	private Player personaje;
	private boolean derecha;
	private int posicionjugador;

	public Botiquin() {
		y = 375;
		x = (int)((Math.random()*800));
		int random = (int) Math.random()*2;
		if(random ==1) {
			x*=-1;
			x-=util.Constant.WIDTHSCREEN;
		}else
			x+=util.Constant.WIDTHSCREEN;
		columnframe = 0;
		ANCHO = Assets.iibotiquin.getIconWidth();
		ALTO = Assets.iibotiquin.getIconHeight();
		botiquin = Assets.iibotiquin.getImage();
	}

	

	public void update() {

	}
	public void left() {
		x-=3;
	}
	public void right() {
		x+=3;
	}

	public void draw(Graphics g) {
		int frameX = columnframe * ANCHO;
		int frameY = rowFrame * ALTO;
		g.drawImage(botiquin, x, y, x + ANCHO, y + ALTO, frameX, frameY, frameX + ANCHO, frameY + ALTO, null);
	}
	
	public boolean colision(Player player) {
		Rectangle recBotiquin = new Rectangle(x , y, ANCHO, ALTO);
		Rectangle recPlayer = new Rectangle(player.x, player.y, player.ANCHO, player.ALTO );
		return recBotiquin.intersects(recPlayer);
	}
}
