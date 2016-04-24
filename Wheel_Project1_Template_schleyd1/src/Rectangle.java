import java.awt.Color;
import java.awt.Graphics;


public class Rectangle {
	
	////////////////////////////////////////////////
	// FIELDS
	////////////////////////////////////////////////
	private int x;
	private int y;
	private int width;
	private int height;
	private Color fillColor;
	private Color outlineColor;
	
	////////////////////////////////////////////////
	// CONSTRUCTOR
	////////////////////////////////////////////////
	public Rectangle()
	{
		x = 0;
		y = 0;
		width = 150;
		height = 150;
		fillColor = Color.gray;
		outlineColor = Color.black;
	}	
	
	////////////////////////////////////////////////
	// METHODS
	////////////////////////////////////////////////
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
		
	public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color color) {
		this.fillColor = color;
	}
	
	public Color getOutlineColor() {
		return outlineColor;
	}

	public void setOutlineColor(Color outlineColor) {
		this.outlineColor = outlineColor;
	}

	public void setBounds(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void paintComponent(Graphics g) {
		Color tc = g.getColor();
		
		g.setColor(fillColor);
		g.fillRect(x, y, width, height);
		
		g.setColor(outlineColor);
		g.drawRect(x, y, width, height);
		
		g.setColor(tc);
	}

}
