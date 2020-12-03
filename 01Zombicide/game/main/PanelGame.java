package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import input.InputHandler;
import sprites.Disparo;
import sprites.Fondo;
import sprites.Player;
import sprites.Zombie;
import util.Assets;

@SuppressWarnings("serial")
public class PanelGame extends JPanel {

	public boolean isRunning = true;
	public boolean isPause = false;
	private InputHandler input;

	public GameLoop gameLoop;
	private Player player;
	private Zombie zombie;
	private Fondo fondo;
	private ArrayList<Zombie> zombies;
	private int moverplayer;
	private final static int VECESMOVERPLAYER = 2;
	private int nuevaoleada;
	private int zombiesnacen=2;
	private int tiempooleada = 100;
	private int ronda = 0;
	private int posicionjugador;
	private Disparo disparo;
	private ArrayList<Disparo> disparos;
	private int nacedisparo;
	private final static int VECESDISPARO = 20;
	private boolean salir=false;
	
	
	public PanelGame() {
		super();
		gameLoop = new GameLoop(this);
		input = new InputHandler(this);
		// Load resources
		Assets.loadAssets();
		// create actores iniciales
		fondo = new Fondo();
		player = new Player();
		zombies = new ArrayList<Zombie>();
		disparos = new ArrayList<Disparo>();
	}
	
	void update() {
		for(Zombie zombie : zombies) {
			zombie.moove();
		}
		// handle inputs
		if (input.isKeyDown(KeyEvent.VK_RIGHT)) {
			fondo.estatico();
			 player.right();
			 posicionjugador = player.getX();
			if(player.limite) {
				fondo.right();
			}
		}
		if (input.isKeyDown(KeyEvent.VK_LEFT)) {
			fondo.estatico();
			player.left();
			posicionjugador = player.getX();
			if(player.limite) {
				fondo.left();
			}
		}
		
		if (input.isKeyDown(KeyEvent.VK_UP)) {
			fondo.estatico();
			player.up();
		}
		if (input.isKeyDown(KeyEvent.VK_DOWN)) {
			fondo.estatico();
			player.down();
		}
		
		
		if(!input.isKeyDown(KeyEvent.VK_LEFT) && !input.isKeyDown(KeyEvent.VK_RIGHT)) {
			fondo.estatico();
			player.estatico();
		}
		
		if (input.isKeyDown(KeyEvent.VK_SPACE)) {
			nacedisparo = ++nacedisparo % VECESDISPARO;
			if(nacedisparo==0) {
				fondo.estatico();
				player.disparar();
				disparo = new Disparo(posicionjugador, player.getDerecha());
				disparos.add(disparo);
			}
		}

		// update sprites
		fondo.update();
		moverplayer = ++moverplayer % VECESMOVERPLAYER;
		if (moverplayer==0) {
			player.update();
			for(Zombie zombie : zombies) {
				zombie.update();
			}
		}
		
		for(Disparo disparo : disparos) {
			disparo.update();
		}
		
		if (zombies.isEmpty()) {
			nuevaoleada = ++nuevaoleada % tiempooleada;
			ronda+=1;
			for(int i = 0 ; i< zombiesnacen ; i++) {
				zombie = new Zombie(posicionjugador);
				zombies.add(zombie);
			}
			zombiesnacen+=2;
		}
		
		

			for (Zombie zombie : zombies) {
				for(Disparo disparo : disparos) {
					if (zombie.colision(disparo)) {
						salir = true;
						break;
						}
				}
				if (salir) {
					salir = false;
					mataZombie(zombie, disparo);
					break;
					}
				if(player.colision(zombie)) {
					gameOver();
				}
			}
	}
	
	private void mataZombie(Zombie zombie, Disparo disparo) {
		disparos.remove(disparo);
		zombies.remove(zombie);
	}
	
	private void gameOver() {
		isRunning = false;
		JOptionPane.showMessageDialog(this, "HAS SOBREVIVIDO: "+ronda+" RONDAS");
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		fondo.draw(g2d);
		player.draw(g2d);
		drawScore(g2d);
			for(Zombie zombie : zombies) {
				zombie.draw(g2d);
			}
			for(Disparo disparo : disparos) {
				disparo.draw(g2d);
			}
	}
	
	private void drawScore(Graphics2D g) {
		g.setColor(Color.decode("#FF0000"));
		g.drawString("Ronda:"+ronda, 650, 20);
		
	}
	
	public void start() {
		gameLoop.start();
	}
	
	
}
