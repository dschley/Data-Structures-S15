import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.NumberFormat;

import javax.swing.BoxLayout;
import javax.swing.JPanel;


public class ScorePanel extends javax.swing.JPanel {

	private int score;
	private Text scoreLabel;
	private Text scoreText;
	private Text status;
	
	public ScorePanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		scoreLabel = new Text("Your Winnings");
		scoreLabel.setFont(new Font("default", Font.PLAIN, 18));
		scoreLabel.setPreferredSize(new Dimension(100, 30));
		scoreLabel.setBackgroundColor(Color.white);
		scoreText = new Text(String.valueOf(score));
		scoreText.setBackgroundColor(Color.white);
		scoreText.setTextColor(Color.blue);
		scoreText.setFont(new Font("default", Font.BOLD, 32));
		scoreText.setPreferredSize(new Dimension(100, 50));
		scoreText.setCentered(true);
		setScore(0);
		status = new Text("Spin and Make a Guess");
		status.setBackgroundColor(Color.white);
		status.setPreferredSize(new Dimension(100, 40));
		status.setFont(new Font("default", Font.BOLD, 14));
		this.add(scoreLabel);
		this.add(scoreText);
		this.add(status);
	}

	private void setScore(int value) {
		this.score = value;
		if (score >= 0)
			scoreText.setTextColor(Color.blue);
		else scoreText.setTextColor(Color.red);
		scoreText.setText(NumberFormat.getCurrencyInstance().format(score).replaceAll("\\.00", ""));
	}

	public int getScore() {
		return score;
	}
	
	public void changeScore(int deltaValue) {
		setScore(score + deltaValue);
	}

	public void setStatus(String statusStr) {
		status.setText(statusStr);
	}
	
	public String getStatus() {
		return status.getText();
	}
}