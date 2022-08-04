/**
 * 
 * Possible improvement both move keys held at once, when one is released the paddle is motionless
 * 
 */

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Font;

public class PongPanel extends JPanel implements ActionListener, KeyListener {
	
	// Set constant variables
	
	private final int BALL_MOVE_SPEED = 4;
	private final int PADDLE_MOVE_SPEED = 4;
	private final int SCORE_X_PADDING = 100;
	private final int SCORE_Y_PADDING = 100;
	private final int SCORE_FONT_PADDING = 50;
	private final static String SCORE_FONT_FAMILY = "Serif";
	private final int WIN_X_PADDING = 200;
	private final int WIN_Y_PADDING = 200;
	private final static int POINTS_TO_WIN = 3;
	private final Color BACKGROUND_COLOUR = Color.BLACK;
	private final int TIMER_DELAY = 5;
	
	
	GameState gameState = GameState.INITIALISING;
		Ball ball;
		Paddle paddle1;
		Paddle paddle2;
		int player1Score = 0;
		int player2Score = 0;
		Player gameWinner;
	
	public PongPanel() {
		
		//Define Constant variables
		
		
		
		//Set Attributes
		setBackground(BACKGROUND_COLOUR);
		//
		Timer timer = new Timer(TIMER_DELAY, this);
		timer.start();
		addKeyListener(this);
		setFocusable(true);
		
	}

	@Override
	public void keyTyped(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent event) {
		if(event.getKeyCode() == KeyEvent.VK_UP) {
			paddle2.setYVelocity(-PADDLE_MOVE_SPEED);
		}else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
			paddle2.setYVelocity(PADDLE_MOVE_SPEED);
		}
		
		if(event.getKeyCode() == KeyEvent.VK_W) {
			paddle1.setYVelocity(-PADDLE_MOVE_SPEED);
		}else if (event.getKeyCode() == KeyEvent.VK_S) {
			paddle1.setYVelocity(PADDLE_MOVE_SPEED);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_UP || event.getKeyCode() == KeyEvent.VK_DOWN) {
			paddle2.setYVelocity(0);
		}
		if (event.getKeyCode() == KeyEvent.VK_W || event.getKeyCode() == KeyEvent.VK_S) {
			paddle1.setYVelocity(0);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		update();
		repaint();
		
	}
	private void update() {
		switch(gameState) {
			case INITIALISING: {
				createObjects();
				gameState = GameState.PLAYING;
				ball.setXVelocity(BALL_MOVE_SPEED);
				ball.setYVelocity(BALL_MOVE_SPEED);
				break;
				}
				case PLAYING: {
					moveObject(paddle1);
					moveObject(paddle2);
					moveObject(ball);
					checkWallBounce();
					checkPaddleBounce();
					checkWin();
					break;
				}
			
				case GAMEOVER: {
					
					break;
				}
		}
		
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintDottedLine(g);
		if (gameState != GameState.INITIALISING) {
			paintSprite(g, ball);
			paintSprite(g, paddle1);
			paintSprite(g, paddle2);
			paintScores(g);
			paintWin(g);
		}

}
	private void paintDottedLine(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] {9}, 0);
		g2d.setStroke(dashed);
		g2d.setPaint(Color.WHITE);
		g2d.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
		g2d.dispose();
		
	}
	public void createObjects() {
		ball = new Ball(getWidth(), getHeight());
		paddle1 = new Paddle(Player.ONE, getWidth(), getHeight());
		paddle2 = new Paddle(Player.TWO, getWidth(), getHeight());
	}
	private void paintSprite(Graphics g, Sprite sprite) {
		g.setColor(sprite.getColour());
		g.fillRect(sprite.getXPosition(), sprite.getYPosition(), sprite.getWidth(), sprite.getHeight());
	}
	private void moveObject(Sprite obj) {
		obj.setXPosition(obj.getXPosition() + obj.getXVelocity(), getWidth());
		obj.setYPosition(obj.getYPosition() + obj.getYVelocity(), getHeight());
	}
	private void checkWallBounce() {
		if (ball.getXPosition() <= 0) {
			ball.setXVelocity(ball.getXVelocity() * -1);
			addScore(Player.ONE);
			resetBall();
		}
		else if(ball.getXPosition() >= getWidth() - ball.getWidth()) {
			ball.setXVelocity(ball.getXVelocity() * -1);
			addScore(Player.TWO);
			resetBall();
		}
		
		if (ball.getYPosition() <= 0 || ball.getYPosition() >= getHeight() - ball.getHeight()) {
			ball.setYVelocity(ball.getYVelocity() * -1);
		}
		
	}
	private void resetBall() {
		ball.resetToInitialPosition();
	}
	private void checkPaddleBounce() {
		if (ball.getXVelocity() < 0 && ball.getRectangle().intersects(paddle1.getRectangle())) {
			ball.setXVelocity(BALL_MOVE_SPEED);
		}
		if (ball.getXVelocity() > 0 && ball.getRectangle().intersects(paddle2.getRectangle())) {
			ball.setXVelocity(-BALL_MOVE_SPEED);
		}
	}
	private void addScore(Player player){
		if (player == Player.ONE) {
			player1Score++;
		}else if (player == Player.TWO) {
			player2Score++;
		}
	}
	private void checkWin() {
		if (player1Score >= POINTS_TO_WIN) {
			gameWinner = Player.ONE;
			gameState = GameState.GAMEOVER;
		}else if (player2Score >= POINTS_TO_WIN) {
			gameWinner = Player.TWO;
			gameState = GameState.GAMEOVER;
		}
	}
	private void paintScores(Graphics g){

		Font scoreFont = new Font(SCORE_FONT_FAMILY, Font.BOLD, SCORE_FONT_PADDING);
		String leftScore = Integer.toString(player1Score);
		String rightScore = Integer.toString(player2Score);
		g.setFont(scoreFont);
		g.drawString(leftScore, SCORE_X_PADDING, SCORE_Y_PADDING);
		g.drawString(rightScore, getWidth() - SCORE_X_PADDING, SCORE_Y_PADDING);
		
	}
	private void paintWin(Graphics g) {
		if(gameWinner != null) {
			Font scoreFont = new Font(SCORE_FONT_FAMILY, Font.BOLD, SCORE_FONT_PADDING);
			g.setFont(scoreFont);
			String win = "WIN!";
			if (gameWinner == Player.ONE) {
				g.drawString(win, WIN_X_PADDING, WIN_Y_PADDING);
			}else if (gameWinner == Player.TWO) {
				g.drawString(win, getWidth() - WIN_X_PADDING, WIN_Y_PADDING);
			}
		}
	}
}
