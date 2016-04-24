import java.awt.Color;
import java.awt.Graphics;


public class Circle {
	
	////////////////////////////////////////////////
	// FIELDS
	////////////////////////////////////////////////
	private int x;
	private int y;
	private int radius;
	private Color fillColor;
	private Color outlineColor;
	
	////////////////////////////////////////////////
	// CONSTRUCTOR
	////////////////////////////////////////////////
	public Circle()
	{
		x = 0;
		y = 0;
		radius = 100;
		fillColor = Color.white;
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

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
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

	public void paintComponent(Graphics g) {
		Color tc = g.getColor();
		
		g.setColor(fillColor);
		g.fillOval(x - radius, y - radius, radius*2, radius*2);
		
		g.setColor(outlineColor);
		g.drawOval(x - radius, y - radius, radius*2, radius*2);
		
		g.setColor(tc);
	}

}
