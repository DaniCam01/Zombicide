package sprites;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import util.Assets;
import util.Constant;

public class Player {
	protected int x;
	protected int y;
	private Image mario;
	private int rowFrame;
	protected int ANCHO;
	protected int ALTO;
	private final static int COLUMNAS = 9;
	private final static int FILAS = 12;
	protected int limitd = util.Constant.WIDTHSCREEN/2+100;
	protected int limiti = util.Constant.WIDTHSCREEN/2-100;
	private int columnframe;
	public boolean limite = false;
	private boolean derecha = true;
	//private int speed;

	public Player() {  
		columnframe = 0;
		ANCHO = Assets.iimario.getIconWidth() / COLUMNAS;
		ALTO = Assets.iimario.getIconHeight() / FILAS;
		mario = Assets.iimario.getImage();
		x = util.Constant.WIDTHSCREEN/2-ANCHO/2;
		y = 345;
		y=335;
		y=355;
		limiti-=ANCHO;
		limitd-=ANCHO/2;
	}
	public void left(){
		rowFrame = 11;
		derecha=false;
		if(x-3 < limiti) {
			x=limiti;
		}else {
			x-=3;
		}
		comprobarLimite();
		
	}
	
	public void right(){
		rowFrame = 5;
		derecha = true;
		if(x+3 > limitd) {
			x=limitd;
		}else {
			x+=3;
		}
		comprobarLimite();
	}
	public void up(){
		estatico();
		if(y>335) {
			y-=10;
		}
	}
	public void down(){
		estatico();
		if(y<355) {
			y+=10;
		}
	}
	
	public void estatico(){
		if (derecha) {
			rowFrame = 0;
		}else
			rowFrame = 6;
		
	}
	
	public void comprobarLimite() {
		if(x<limitd && x>limiti) {
			limite=false;
		}else
			limite = true;
	}
	
	public void disparar(){
		if(derecha) {
			rowFrame=2;
		}else
			rowFrame=8;
	}


	public void update() {
		if(rowFrame==2 || rowFrame == 8) {
			columnframe = ++columnframe % 5;
			
		}else if(rowFrame==0 || rowFrame==6) {
			columnframe = ++columnframe % 6;
		}else {
			columnframe = ++columnframe % 8;
		}
	}

	public void draw(Graphics g) {
		int frameX = columnframe * ANCHO;
		int frameY = rowFrame * ALTO;
		g.drawImage(mario, x, y, x + ANCHO, y + ALTO, frameX, frameY, frameX + ANCHO, frameY + ALTO, null);
	}
	
	public int getX() {
		return x;
	}
	
	public boolean getDerecha() {
		return derecha;
	}
	
	public boolean colision(Zombie zombie) {
		Rectangle recPersonaje = new Rectangle(x , y, ANCHO, ALTO);
		Rectangle recZombie = new Rectangle(zombie.x, zombie.y, zombie.ANCHO, zombie.ALTO );
		return recPersonaje.intersects(recZombie);
	}

}
