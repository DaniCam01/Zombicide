package main;

public class GameLoop extends Thread {
	private static final int FPS = 30;
	private PanelGame panelGame;

	public GameLoop(PanelGame gamePanel) {
		super();
		this.panelGame = gamePanel;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		try {
			while (panelGame.isRunning) {
				long time = System.currentTimeMillis();

				if (!panelGame.isPause) {
					panelGame.update();
					panelGame.repaint();
				}
				// delay for each frame - time it took for one frame
				time = (1000 / FPS) - (System.currentTimeMillis() - time);

				if (time > 0) {
					try {
						Thread.sleep(time);
					} catch (Exception e) {
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
