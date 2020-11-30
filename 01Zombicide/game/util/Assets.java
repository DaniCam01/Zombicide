package util;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.ImageIcon;

public class Assets {
	
    public static ImageIcon iifondo;
    public static ImageIcon iimario;
    
    
    public static AudioClip pun;
      
    public static void loadAssets() {
		iifondo = new ImageIcon("assets/fondo.png");
		iimario = new ImageIcon("assets/mariosheet.png");
		
		try {
			pun = Applet.newAudioClip(new File("assets/pun.wav").toURI().toURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
    }
}