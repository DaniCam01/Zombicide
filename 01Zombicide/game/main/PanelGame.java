package main;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import input.InputHandler;
import sprites.Botiquin;
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
	private int vidas = 1;
	private final static int VECESBOTIQUIN = 2000;
	private int nacebotiquin= 400;
	private Botiquin botiquin;
	private ArrayList<Botiquin> botiquines;
	private final static int VECESACELERACION = 1000;
	private int aceleracion;
	private AudioClip shotsound;
	private AudioClip hitsound;
	private AudioClip zombiesound;
	private AudioClip oneupsound;
	private int speed=1;
	
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
		botiquines = new ArrayList<Botiquin>();
		loadSound();
		posicionjugador = player.getX();
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
				for(Zombie zombie : zombies) {
					zombie.left();
				}
				for(Botiquin botiquin : botiquines) {
					botiquin.left();
				}
			}
		}
		if (input.isKeyDown(KeyEvent.VK_LEFT)) {
			fondo.estatico();
			player.left();
			posicionjugador = player.getX();
			if(player.limite) {
				fondo.left();
				for(Zombie zombie : zombies) {
					zombie.right();
				}
				for(Botiquin botiquin : botiquines) {
					botiquin.right();
				}
			}
		}
		
		if (input.isKeyDown(KeyEvent.VK_UP)) {
		//	fondo.estatico();
		//	player.up();
		}
		if (input.isKeyDown(KeyEvent.VK_DOWN)) {
		//	fondo.estatico();
		//	player.down();
		}
		
		
		if(!input.isKeyDown(KeyEvent.VK_LEFT) && !input.isKeyDown(KeyEvent.VK_RIGHT)) {
			fondo.estatico();
			player.estatico();
		}
		
		
		nacedisparo = ++nacedisparo % VECESDISPARO;
		if (input.isKeyDown(KeyEvent.VK_SPACE)) {
			if(nacedisparo==0) {
				shotsound.stop();
				fondo.estatico();
				player.disparar();
				disparo = new Disparo(posicionjugador, player.getDerecha());
				shotsound.play();
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
			zombiesound.play();
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
					quitarVida();
					break;
				}
			}
			for(Botiquin botiquin : botiquines) {
				if(botiquin.colision(player)) {
					vidas+=1;
					oneupsound.play();
					botiquines.remove(botiquin);
					break;
				}
			}
			
			nacebotiquin = ++nacebotiquin % VECESBOTIQUIN;
			if (nacebotiquin==0 && vidas<3) {
				botiquin = new Botiquin();
				botiquines.add(botiquin);
			}
			
			aceleracion = ++aceleracion % VECESACELERACION;
			if(aceleracion==0) {
				for(Zombie zombie : zombies) {
					zombie.setSpeed(speed);
					speed++;
				}
			}
			
	}
	
	private void mataZombie(Zombie zombie, Disparo disparo) {
		hitsound.play();
		disparos.remove(disparo);
		zombies.remove(zombie);
	}
	
	private void quitarVida() {
		vidas--;
		if(vidas<1) {
			isRunning = false;
			JOptionPane.showMessageDialog(this, "HAS SOBREVIVIDO: "+ronda+" RONDAS");
		}else {
			zombies.removeAll(zombies);
		}
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
			for(Botiquin botiquin : botiquines) {
				botiquin.draw(g2d);
			}
	}
	
	private void drawScore(Graphics2D g) {
		g.setColor(Color.decode("#FF0000"));
		g.drawString("Ronda:"+ronda, 650, 20);
		g.setColor(Color.decode("#FF0000"));
		g.drawString("Vidas:"+vidas, 20, 360);
		
	}
	
	public void start() {
		gameLoop.start();
	}

	
	private void loadSound() {
		try {
			shotsound = Applet.newAudioClip(new File("assets/shotsound.wav").toURI().toURL());
			hitsound = Applet.newAudioClip(new File("assets/hit.wav").toURI().toURL());
			zombiesound = Applet.newAudioClip(new File("assets/zombiesound.wav").toURI().toURL());
			oneupsound = Applet.newAudioClip(new File("assets/1upsound.wav").toURI().toURL());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void changeMap() {
		fondo.changeMap();
	}
	
	
}
