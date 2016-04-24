

	import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;

	import javax.swing.BoxLayout;
import javax.swing.JComboBox.KeySelectionManager;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


	public class GameBoard extends JPanel {

		private PuzzlePanel puzzle;
		private ScorePanel scorePanel;
		private RandomString puzzleManager;
		private ListSpinner spinner;

		public GameBoard()
		{
			this.setPreferredSize(new Dimension(800, 600));
			this.setLayout(new BorderLayout());
			puzzleManager = new RandomString("phrases.txt") ;
			puzzle = new PuzzlePanel(puzzleManager.next());
			// TODO initialize the puzzle manager and puzzle fields

			this.add(BorderLayout.CENTER, puzzle);

			JPanel jp = new JPanel();
			jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
			scorePanel = new ScorePanel();
			scorePanel.setStatus("Spin and Make a Guess");
			spinner = new ListSpinner("wheel_values.txt");
			spinner.setPreferredSize(new Dimension(180, 300));
			jp.add(scorePanel);
			jp.add(spinner);
			this.add(jp, BorderLayout.EAST);

			this.setFocusable(true);
			this.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					
					
						
					char c = e.getKeyChar();
							
					if(c==KeyEvent.VK_ENTER){
						puzzle.revealPuzzle();
								if(JOptionPane.showConfirmDialog(null, "play again?")==JOptionPane.YES_OPTION)
								reset();
								if(JOptionPane.showConfirmDialog(null, "are you sure?????????? (yes to play, no to exit)")==JOptionPane.NO_OPTION)
									Runtime.getRuntime().exit(1);
							}
							
							if(spinner.hasSpun()){
							if(puzzle.isLetter(c)){
								int count = puzzle.showLetter(c);
									if(count == 0){
										scorePanel.changeScore(spinner.getSelectionIntValue()*-1);
										scorePanel.setStatus("nooope");
										spinner.resetHasSpun();
										
									}
									else{
										scorePanel.changeScore(spinner.getSelectionIntValue()*count);
										scorePanel.setStatus("yeeeeeeeeeeeeah boyyy!!!");
										spinner.resetHasSpun();
										if(puzzle.isPuzzleRevealed()){
										if(JOptionPane.showConfirmDialog(null, "play again?")==JOptionPane.YES_OPTION)
											reset();
											if(JOptionPane.showConfirmDialog(null, "are you sure?????????? (yes to play, no to exit)")==JOptionPane.NO_OPTION)
												Runtime.getRuntime().exit(1);
										}
										
									}
									
							}
						}
					
			
						else{
							scorePanel.setStatus("Spin before you guess");
						}
							
					// TODO get the letter pressed from the key event
					
					// TODO if the spinner has spun, update the puzzle,
					//      score, status, and reset spinner
					
					// TODO if the puzzle is revealed, ask user to replay
					
					// TODO make sure to follow ALL the directions in the 
					//      project description!!
					
				}
			});
		}
		
		public void reset() {
			// TODO implement
			scorePanel.changeScore(scorePanel.getScore()*-1);
			scorePanel.setStatus("spin to start a new game");
			spinner.resetHasSpun();
			puzzle.setPuzzle(puzzleManager.next());
			puzzle.repaint();
		}

		public static void main(String[] args)
		{
			JFrame frame = new JFrame("Wheel Game Board");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JPanel panel = new GameBoard();
			frame.getContentPane().add(panel);
			frame.pack();
			frame.setVisible(true);
		}

	}
