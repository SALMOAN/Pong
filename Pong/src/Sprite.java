import java.awt.Color;
import java.awt.Rectangle;
public class Sprite {
	
//Attributes
	private int xPosition;
	private int yPosition;
	private int xVelocity;
	private int yVelocity;
	private int height;
	private int width;
	private Color colour;
	private int initialXPosition;
	private int initialYPosition;

	

	// Getter Methods
	public int getXPosition() {
		return xPosition;
		}
	public int getYPosition() {
		return yPosition;
		}
	public int getXVelocity() {
		return xVelocity;
		}
	public int getYVelocity() {
		return yVelocity;
		}
	public int getHeight() {
		return height;
		}
	public int getWidth() {
		return width;
		}
	public Color getColour() {
		return colour;
		}
	// Setter Methods
	public void setXPosition(int newXPosition) {
		this.xPosition = newXPosition;
		}
	public void setYPosition(int newYPosition) {
		this.yPosition = newYPosition;
		}
	public void setXVelocity(int newXVelocity) {
		this.xVelocity = newXVelocity;
		}
	public void setYVelocity(int newYVelocity) {
		this.yVelocity = newYVelocity;
		}
	public void setHeight(int newHeight) {
		this.height = newHeight;
		}
	public void setWidth(int newWidth) {
		this.width = newWidth;
		}
	public void setColour(Color newColour) {
		this.colour = newColour;
		}
	//Out of Bonds Methods
	public void setXPosition(int newXPosition, int panelWidth) {
		if (newXPosition < 0) {
			newXPosition = 0;
			}
		else if(newXPosition + width > panelWidth) {
			newXPosition = panelWidth - width;
			}
		this.xPosition = newXPosition;
		}
	public void setYPosition(int newYPosition, int panelHeight) {
		if (newYPosition < 0) {
			newYPosition = 0;
			}
		else if (newYPosition + height > panelHeight) {
			newYPosition = panelHeight;
			}
		this.yPosition = newYPosition;
		}
//Initial Position
	public void setInitialPosition(int initialX, int initialY) {
		this.initialXPosition = initialX;
		this.initialYPosition = initialY;
	}
	public void resetToInitialPosition() {
		this.xPosition = initialXPosition;
		this.yPosition = initialYPosition;
	}
// Rectangle
	public Rectangle getRectangle() {
		return new Rectangle(getXPosition(), getYPosition(), getWidth(), getHeight());
	}
}