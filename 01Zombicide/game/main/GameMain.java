package main;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sprites.Fondo;
import util.Constant;

@SuppressWarnings("serial")
public class GameMain extends JFrame implements ActionListener {

	private Container container;
	private JButton btnPlayPause;
	private JButton btnChangeMap;
	private JButton btnFin;
	private JButton btnStart;
	private PanelGame panelGame;
	private AudioClip bso;
	private boolean map1=true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new GameMain();
	}

	/**
	 * Create the frame.
	 */
	public GameMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, Constant.WIDTHSCREEN, Constant.HEIGHTSCREEN+60);
		container = getContentPane();
		container.setLayout(new BorderLayout(0, 0));
		
		// Panel Game
		panelGame = new PanelGame();
		// Panel footer
		JPanel panelFooter = new JPanel();
		panelFooter.setBackground(Color.decode("#3D0000"));
		
		btnStart = new JButton("Start");
		btnStart.addActionListener(this);
		panelFooter.add(btnStart);

		btnChangeMap = new JButton("Mapa 1");
		btnChangeMap.addActionListener(this);
		panelFooter.add(btnChangeMap);
		
		btnPlayPause = new JButton("Play/Pause");
		btnPlayPause.addActionListener(this);
		btnPlayPause.setEnabled(false);
		panelFooter.add(btnPlayPause);

		btnFin = new JButton("Fin");
		btnFin.addActionListener(this);
		panelFooter.add(btnFin);

		container.add(panelGame, BorderLayout.CENTER);
		container.add(panelFooter, BorderLayout.SOUTH);
		setVisible(true);
		loadSound();
		bso.play();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object boton = e.getSource();
		if (boton == btnStart) {
			btnStartOnClick();
		} else if (boton == btnPlayPause) {
			btnPlayPauseOnClick();
		} else if (boton == btnFin) {
			btnFinOnClick();
		} else if(boton == btnChangeMap) {
			btnChangeMapOnClick();
		}
	}

	private void btnStartOnClick() {
		panelGame.gameLoop.start();
		panelGame.requestFocus();
		btnStart.setEnabled(false);
		btnChangeMap.setEnabled(false);
		btnPlayPause.setEnabled(true);
		panelGame.changeMap();
	}

	private void btnPlayPauseOnClick() {
		panelGame.isPause=!(panelGame.isPause);
		panelGame.requestFocus();
	}

	private void btnFinOnClick() {
		panelGame.isRunning = false;
		System.exit(0);
	}
	
	private void btnChangeMapOnClick() {
		 panelGame.changeMap();
		 if(map1) {
			 btnChangeMap.setText("Mapa 2");
		 }else
			 btnChangeMap.setText("Mapa 1");
		 map1=!map1;
	}
	
	private void loadSound() {
		try {
			bso = Applet.newAudioClip(new File("assets/bso.wav").toURI().toURL());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
