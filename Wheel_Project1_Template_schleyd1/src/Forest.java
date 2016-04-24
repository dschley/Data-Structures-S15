import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JApplet;


public class Forest extends JApplet {


	private Tree tree;
	private Tree tree1;
	private Tree tree2;
	
	public Forest()
	{
		this.setSize(500, 800);
		
		
		
		tree = new Tree(150, 125, 200, 200, Color.green, Color.orange);
		tree1 = new Tree(50, 100, 100, 200, Color.green, Color.orange);
		tree2 = new Tree(400, 55, 75, 300, Color.green, Color.orange);
				
	}
	
	public void paintComponent(Graphics g)
	{
		
		tree.paintComponent(g);
		tree1.paintComponent(g);
		tree2.paintComponent(g);
	}
}
