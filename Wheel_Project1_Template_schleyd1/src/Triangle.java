import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;


public class Triangle {
	
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
	public Triangle()
	{
		x = 0;
		y = 0;
		width = 150;
		height = 150;
		fillColor = Color.gray;
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
		
		Polygon triangle = new Polygon();
		triangle.addPoint(x, y);
		triangle.addPoint(x - width/2, y + height);
		triangle.addPoint(x + width/2, y + height);
		
		// fill the shape
		g.setColor(fillColor);
		g.fillPolygon(triangle);
		
		// draw the outline of the shape
		g.setColor(outlineColor);
		g.drawPolygon(triangle);

		g.setColor(tc);
	}

}
