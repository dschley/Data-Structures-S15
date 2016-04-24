import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class PictureApplet extends JPanel {

	private Circle sun;					// place to store the sun which is a Circle
	private Square ground;				// place to store the ground which is a Square
	private Triangle kite;
	private Forest forest;
	private Tree tree;
	
	public PictureApplet()
	{
		this.setSize(500, 800);
		
		sun = new Circle();				// call constructor, create object
		sun.setX(60);					// set x value of sun
		sun.setY(50);					// set y value of sun
		sun.setRadius(35);				// set radius of sun
		sun.setFillColor(Color.yellow);	// set fill color of sun
		
		ground = new Square();			// call constructor, create object
		ground.setX(0);					// set x value of ground
		ground.setY(300);				// set y value of ground
		ground.setSize(600);			// set size of ground
		ground.setFillColor(Color.green);// set fill color of ground
		
		kite = new Triangle();			// call constructor, create object
		kite.setX(360);					// set x value of kite
		kite.setY(100);					// set y value of kite
		kite.setWidth(50);				// set size of kite
		kite.setHeight(50);
		kite.setFillColor(Color.red);	// set fill color of kite
		kite.setOutlineColor(Color.black);
		
		forest = new Forest();
		tree = new Tree(0, 225, 75, 75, Color.green, Color.orange);
	}
	
	public void paint(Graphics g)
	{
		sun.paintComponent(g);			// paints the sun
		ground.paintComponent(g);		// paints the ground
		kite.paintComponent(g);			// paints the kite
		forest.paintComponent(g);
		tree.paintComponent(g);
	}
	
	public static void main(String[] args){
		JFrame frame = new JFrame("Picture Applet");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new PictureApplet();
		panel.setPreferredSize(new Dimension(500, 800));
		
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
	}
	
}
