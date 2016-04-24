import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JApplet;


public class Tree extends JApplet{

	//field//
	private Triangle top;
	private Rectangle trunk;
	
	//constructor//
	public Tree(){
		top = new Triangle();
		trunk = new Rectangle();
		setBounds(0,0,50,100);
	}
	
	public Tree(int x, int y, int width, int height){
		top =  new Triangle();
		trunk = new Rectangle();
		setBounds(x, y, width, height);
	}
	
	public Tree(int x, int y, int width, int height, Color topColor, Color trunkColor){
		top = new Triangle();
		top.setFillColor(topColor);
		trunk = new Rectangle();
		trunk.setFillColor(trunkColor);
		setBounds(x, y, width, height);
	}
		
	//method//	
	
	public Triangle getTopColor(){
		return top;
	}
	
	public void setTopColor(Triangle top){
		this.top = top;
	}
	
	public Rectangle getTrunkColor(){
		return trunk;
	}
	
	public void setTrunkColor(Rectangle trunk){
		this.trunk = trunk;
	}
	
	public void setBounds(int x, int y, int width, int height){
		top.setBounds(x+width/2, y, width, (height*3)/4);
		trunk.setBounds(x+2*width/5, (y+(height*3)/4), width/5, height/4);
	}
	
	public void paintComponent(Graphics g){
		top.paintComponent(g);
		trunk.paintComponent(g);
	}
	
	
	
	
}
