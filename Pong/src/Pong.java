import javax.swing.JFrame;

public class Pong extends JFrame {
	
	//Declare constant variables
	private final int SCREEN_WIDTH = 800;
	private final int SCREEN_HEIGHT = 600;
	private final String GAME_TITLE = "Pong";
	
	public Pong() {
		setTitle(GAME_TITLE);
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setResizable(false);
		add(new PongPanel());
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Pong();
			}
		});
	}
}
		

