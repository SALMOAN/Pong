import java.awt.Color;

public class Paddle extends Sprite{
	
	//Declare constant variables
	 final private static int PADDLE_WIDTH = 10;
	 final private static int PADDLE_HEIGHT = 100;
	 final private static Color PADDLE_COLOUR = Color.BLACK;
	 final private static int PADDLE_DISTANCE_FROM_EDGE = 40;
	 
	 public Paddle(Player player, int panelWidth, int panelHeight) {
		 setWidth(PADDLE_WIDTH);
		 setHeight(PADDLE_HEIGHT);
		 setColour(PADDLE_COLOUR);
		 if (player == Player.ONE) {
			 setInitialPosition((0 + PADDLE_DISTANCE_FROM_EDGE - PADDLE_WIDTH),((panelHeight / 2) - (PADDLE_HEIGHT / 2)));
		 }else {
			 setInitialPosition((panelWidth -  PADDLE_DISTANCE_FROM_EDGE),((panelHeight / 2) - (PADDLE_HEIGHT / 2)));
		 }
		 resetToInitialPosition();
		 
	 }

}
