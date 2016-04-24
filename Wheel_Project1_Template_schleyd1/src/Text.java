import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JPanel;


public class Text extends JPanel {
	
	public static final int SPACE = 2;
	
	private Color textColor;
	private Color underlineColor;
	private Color backgroundColor;
	private String str;
	private Font font;
	
	// TODO DO we want a hidden BG color separate from bg color for behind text???

	private boolean textHidden;
	private boolean underlineVisible;
	private boolean centered;
	
	public Text(String str)
	{
		this.str = str;
		this.textColor = Color.black;
		//this.backgroundColor = Color.white;
		this.setBackgroundColor(new Color(0,0,0,0));
		this.underlineColor = Color.black;
		
		this.textHidden = false;
		this.underlineVisible = false;
		this.centered = true;
		
		this.setPreferredSize(new Dimension(20,20));
		
		// FOR TESTING PURPOSES
		/*
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Random r = new Random();
				setTextColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
			}
		});
		*/
	}
	
	public void setText(String t) {
		str = t; 
		repaint();
	}
	
	public String getText() {
		return str;
	}
	
	public Color getBackgroundColor() {
		return backgroundColor;
	}
	
	public void setBackgroundColor(Color color) {
		if (this.backgroundColor != null && this.backgroundColor.equals(color))
			return;
		
		this.backgroundColor = color;
		this.setBackground(color);
		repaint();
	}
	
	public Color getTextColor() {
		return textColor;
	}
	public void setTextColor(Color color) {
		if (this.textColor != null && this.textColor.equals(color))
			return;
		
		this.textColor = color;
		repaint();
	}
	
	public Color getUnderlineColor() {
		return underlineColor;
	}
	public void setUnderlineColor(Color color) {
		this.underlineColor = color;
		repaint();
	}
	
	public String getString() {
		return str;
	}
	public void setString(String str) {
		this.str = str;
		repaint();
	}

	public Font getFont() {
		return font;
	}
	public void setFont(Font font) {
		this.font = font;
		repaint();
	}
	
	public boolean isTextHidden() {
		return textHidden;
	}
	public void setTextHidden(boolean textVisible) {
		this.textHidden = textVisible;
		repaint();
	}
	
	public void showText() {
		textHidden = false;
		repaint();
	}
	
	public void hideText() {
		textHidden = true;
		repaint();
	}
	
	public boolean isUnderlineVisible() {
		return underlineVisible;
	}
	public void setUnderlineVisible(boolean underlineVisible) {
		this.underlineVisible = underlineVisible;
		repaint();
	}
	
	public boolean isCentered() {
		return centered;
	}
	public void setCentered(boolean centered) {
		this.centered = centered;
		repaint();
	}
	
	@Override public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int w = this.getWidth();
		int h = this.getHeight();
		
		Color tc = g.getColor();
		
		g.setColor(backgroundColor);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		Font f = g.getFont();
		g.setFont(f);
		
		if (underlineVisible)
		{
			g.setColor(underlineColor);
			g.fillRect(SPACE, h - SPACE, w - 2*SPACE, h);
		}

		if (!textHidden)
		{
			g.setColor(textColor);
			int xoffset = SPACE;
			if (centered) {
				float sw = g.getFontMetrics().stringWidth(str);
				xoffset = (int)(w - sw) / 2;
			}
			
			if (centered)
				g.drawString(str, xoffset, h/2 + g.getFontMetrics().getAscent()/2 - 1);
			else g.drawString(str, xoffset, h - SPACE - 1);
		}
		
		g.setColor(tc);
		g.setFont(f);
	}

}
