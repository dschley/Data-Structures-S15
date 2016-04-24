import java.awt.Color;
import java.awt.Graphics;


public class Square {
	
	////////////////////////////////////////////////
	// FIELDS
	////////////////////////////////////////////////
	private int x;
	private int y;
	private int size;
	private Color fillColor;
	private Color outlineColor;
	
	////////////////////////////////////////////////
	// CONSTRUCTOR
	////////////////////////////////////////////////
	public Square()
	{
		x = 0;
		y = 0;
		size = 150;
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

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
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
		g.fillRect(x, y, size, size);
		
		g.setColor(outlineColor);
		g.drawRect(x, y, size, size);
		
		g.setColor(tc);
	}

}
