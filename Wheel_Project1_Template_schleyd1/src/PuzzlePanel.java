

	import java.awt.BorderLayout;
	import java.awt.Color;
	import java.awt.Component;
	import java.awt.Dimension;
	import java.awt.FlowLayout;
	import java.awt.Font;
	import java.awt.Graphics;

	import javax.swing.BorderFactory;
	import javax.swing.BoxLayout;
	import javax.swing.JFrame;
	import javax.swing.JPanel;
	import javax.swing.border.Border;
	import javax.swing.border.CompoundBorder;
	import javax.swing.border.EmptyBorder;

	public class PuzzlePanel extends JPanel {
		private Puzzle puzzle;
		private Picture picture;

		private JPanel wordsPanel;
		private Text[] phraseComponents;
		private Text category;

		public PuzzlePanel(String s) {
			this.setLayout(new BorderLayout());

			category = new Text("");
			category.setFont(new Font("default", Font.BOLD, 18));
			category.setCentered(true);
			category.setPreferredSize(new Dimension(200, 50));
			picture = new Picture();

			wordsPanel = new JPanel();
			wordsPanel.setBackground(new Color(255,255,255,0));
			FlowLayout fl = (FlowLayout)wordsPanel.getLayout();
			fl.setHgap(20);
			fl.setVgap(10);
			
			this.add(BorderLayout.CENTER, wordsPanel);
			this.add(BorderLayout.SOUTH, category);

			setPuzzle(s);
		}

		
		/**
		 * Parses the input and creates the Puzzle object. Initializes 
		 * the letters and category panels.
		 * @param s - the unparsed string containing the category and the phrase
		 */
		public void setPuzzle(String s) {
			// parse String (e.g. "THING:ROBOT") 
			// into category and phrase
			// print error if parsing fails
			String []setp = s.split(":");
			if (setp.length<1)
				System.out.println("ERROR");
			String cat = setp[0];
			String ph = setp[1];
			// create puzzle object (passing category and phrase)
			puzzle = new Puzzle(cat,ph);
			// initialize letters
			initializeLetters();
			// set the category text
			category.setText(puzzle.getCategory());
		
		}

		private void initializeLetters() {
			wordsPanel.removeAll(); // throw away
			wordsPanel.updateUI();  // reset UI
			phraseComponents = new Text[puzzle.getPhrase().length()];
			String[] words = puzzle.getPhrase().split(" ");
			int k = 0;
			for (int j = 0; j < words.length; j++) {
				JPanel wordPanel = new JPanel();
				for (int i = 0; i < words[j].length(); i++) {
					char c = words[j].charAt(i);
					Text t = new Text(Character.toString(c));
					t.setBackgroundColor(Color.WHITE);
					t.setPreferredSize(new Dimension(30,40));
					t.setCentered(true);
					t.setFont(new Font("default", Font.BOLD, 20));
					if (isLetter(c)) {
						t.hideText();
					} else {
						t.showText();
					}

					phraseComponents[k++] = t;
					wordPanel.add(t);
				}
				wordsPanel.add(wordPanel);
				k++;
			}

			wordsPanel.invalidate();
			wordsPanel.revalidate();
			wordsPanel.doLayout();
			wordsPanel.setPreferredSize(new Dimension(300,400));
			this.invalidate();
			this.doLayout();
			this.revalidate();
		}

		/**
		 * Determines whether c is a valid letter that should be hidden
		 * @param c - the letter
		 * @return true if c is a valid letter, false otherwise
		 */
		boolean isLetter(char c) {
			// TODO should only return true if c is a valid letter
			if ((c>= 'A' && c<= 'Z') || (c>= 'a' && c<='z'))
			return true;
			
			else
			return false;
		}

		/**
		 * Show any hidden letters matching the given letter
		 * @param c - the letter to be revealed (may be lower case)
		 * @return the number of newly revealed letters 
		 * If the letter has already been previously revealed for this puzzle,
		 * showLetter should return 0. 
		 */
		public int showLetter(char c) {
			int count = 0;
			for(int i = 0; i<phraseComponents.length; i++)
				if(phraseComponents[i].getString().equals(Character.toString(c).toUpperCase())&&phraseComponents[i].isTextHidden()){
					phraseComponents[i].showText();
					count++;
				}
			return count;
		}

		/**
		 * Determine whether all the puzzle letters have been shown.
		 * @return true if the letters have been revealed, false otherwise.
		 */
		public boolean isPuzzleRevealed() {
			boolean result =  false;
			for(int i = 0; i<phraseComponents.length; i++)
				if(phraseComponents[i] != null){
					if(phraseComponents[i].isTextHidden()){
						result = false;
						break;
						}
					else
					result = true;
				}
			return result; // TODO
		}

		/**
		 * Shows all the text phraseComponents that are not null.
		 */
		public void revealPuzzle() {
			for(int i = 0; i<phraseComponents.length; i++)
				if(phraseComponents[i] != null){
					if(phraseComponents[i].isTextHidden())
						phraseComponents[i].showText();
				}
			}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			picture.paintComponent(g);
		}

		public Puzzle getPuzzle() {
			return puzzle; // TODO
		}

		public static void main(String[] args) {
			JFrame frame = new JFrame ("Puzzle Panel");
			frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

			PuzzlePanel panel = new PuzzlePanel("school:montclair");
			panel.revealPuzzle();

			frame.getContentPane().add(panel);

			frame.pack();
			frame.setVisible(true);

		}

	}

