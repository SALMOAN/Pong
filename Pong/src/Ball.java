import java.awt.Color;

public class Ball extends Sprite{
	// Declare static variables
	private Color BALL_COLOUR = Color.BLACK;
	private int BALL_HEIGHT = 25;
	private int BALL_WIDTH = 25;
	
	public Ball(int panelWidth, int panelHeight) {
		setWidth(BALL_WIDTH);
		setHeight(BALL_HEIGHT);
		setColour(BALL_COLOUR);
		setInitialPosition(((panelWidth / 2) - (getWidth() / 2)), ((panelHeight / 2) - (getHeight() / 2)));
		resetToInitialPosition();
		
	}

}
