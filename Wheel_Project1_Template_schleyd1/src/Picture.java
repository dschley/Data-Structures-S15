

	import java.awt.Color;
	import java.awt.Dimension;
	import java.awt.Graphics;

	import javax.swing.JApplet;
	import javax.swing.JFrame;
	import javax.swing.JPanel;


	public class Picture extends JPanel {

		private Circle sun;					// place to store the sun which is a Circle
		private Square ground;				// place to store the ground which is a Square
		private Triangle kite;
		private Forest forest;
		private Tree tree;
		
		public Picture()
		{
			this.setSize(500, 600);
			
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
			
			
			forest = new Forest();
			tree = new Tree(0, 225, 75, 75, Color.green, Color.orange);
		}
		
		public void paintComponent(Graphics g)
		{
			sun.paintComponent(g);			// paints the sun
			ground.paintComponent(g);		// paints the ground
			forest.paintComponent(g);
			tree.paintComponent(g);
		}
		
		public static void main(String[] args){
			JFrame frame = new JFrame("Picture");
			
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Picture myPicture = new Picture();
			
			frame.setPreferredSize(new Dimension(500, 600));
			
			frame.getContentPane().add(myPicture);
			frame.pack();
			frame.setVisible(true);
		}
		
	}


