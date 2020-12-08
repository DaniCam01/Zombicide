package util;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.ImageIcon;

public class Assets {
	
    public static ImageIcon iifondo;
    public static ImageIcon iimario;
    public static ImageIcon iizombie;
    public static ImageIcon iidisparo;
    public static ImageIcon iibotiquin;
    
    
    public static AudioClip pun;
      
    public static void loadAssets() {
		iifondo = new ImageIcon("assets/2fondo.png");
		iimario = new ImageIcon("assets/mariosheet.png");
		iizombie = new ImageIcon("assets/zombiesheet.png");
		iidisparo = new ImageIcon("assets/disparosheet.png");
		iibotiquin = new ImageIcon("assets/botiquin.png");
		
		try {
			pun = Applet.newAudioClip(new File("assets/pun.wav").toURI().toURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
    }
}