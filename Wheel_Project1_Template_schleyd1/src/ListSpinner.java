import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Random;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * A List Spinner to be used in the Wheel of Fortune.
 * @author Jerry Alan Fails
 */
public class ListSpinner extends JPanel implements Runnable {

	// FIELDS
	private RandomString rs;
	private int numItems;
	private JButton spinButton;
	private int selectedIndex;
	private Random random = new Random();
	
	private boolean hasSpun = false;

	// CONSTANTS
	private static Font DEFAULT_FONT = new Font("default", Font.BOLD, 18);
	private static Color DEFAULT_TEXT_COLOR = Color.black;
	private static Color DEFAULT_BACKGROUND_COLOR = Color.white;
	private static Color SELECTION_TEXT_COLOR = Color.white;
	private static Color SELECTION_BACKGROUND_COLOR = Color.gray;

	/**
	 * Creates a ListSpinner object with the values found in the specified file.
	 * @param filename the name of the file where the values are stored (each line should have a value)
	 */
	public ListSpinner(String filename)
	{
		rs = new RandomString(filename);
		numItems = rs.getStrings().size();

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// add all values to the spin list
		for (String str : rs.getStrings()) {
			int value = getIntValue(str);
			str = NumberFormat.getCurrencyInstance().format(value).replaceAll("\\.00", "");
			Text txt = new Text(str);
			txt.setUnderlineVisible(false);
			txt.showText();
			txt.setFont(DEFAULT_FONT);
			this.add(txt);
		}

		// set up the spin button
		this.spinButton = new JButton("Spin");
		this.add(spinButton);
		spinButton.setPreferredSize(new Dimension(this.getWidth(), 80));
		spinButton.setFont(DEFAULT_FONT);
		spinButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				spin();
			}
		});
		
		setSelectionIndex(0);
	}

	@Override
	/**
	 * Lays out the items in the list spinner (overridden to get the spin button to fill the width)
	 */
	public void doLayout() {
		super.doLayout();
		spinButton.setBounds(0, spinButton.getY(), this.getWidth(), spinButton.getHeight());
	}

	/**
	 * Sets the selection to be the element at the specified index.
	 * @param index the index of the item to be selected
	 */
	public void setSelectionIndex(int index) {
		selectedIndex = index;

		for (int i = 0; i < this.getComponentCount() - 1; i++)
			if (i != index) {
				Text t = (Text)this.getComponent(i);
				t.setBackgroundColor(DEFAULT_BACKGROUND_COLOR);
				t.setTextColor(DEFAULT_TEXT_COLOR);
			}

		Text t = (Text)this.getComponent(index);
		t.setBackgroundColor(SELECTION_BACKGROUND_COLOR);
		t.setTextColor(SELECTION_TEXT_COLOR);
	}

	/**
	 * Increments the selection index by one (wraps around if necessary).
	 */
	private void incSelectionIndex() {
		setSelectionIndex((getSelectionIndex() + 1) % numItems);
	}

	/**
	 * @return the index of the selected item in the list
	 */
	public int getSelectionIndex() {
		return selectedIndex;
	}

	/**
	 * @return the selection as a String
	 */
	public String getSelection() {
		return ((Text)getComponent(selectedIndex)).getString();
	}

	/**
	 * @return the selection value as an integer
	 */
	public int getSelectionIntValue() {
		return getIntValue(getSelection());
	}

	/**
	 * Gets the integer value of the specified string (taking out $ signs and commas)
	 * @param strValue String to convert to an integer
	 * @return the integer value of the specified string
	 */
	private int getIntValue(String strValue) {
		int value = -1;
		strValue = strValue.replaceAll("\\$", "");			// removes the $ sign
		strValue = strValue.replaceAll("\\,", "");			// removes all commas
		try { value = Integer.parseInt(strValue); }			// tries to parse string as an integer
		catch (Exception e) {
			System.out.println("StrValue: " + strValue);	// if an error print string tried to parse
			e.printStackTrace();							// ... and print out stack trace
		}
		return value;
	}

	/**
	 * Used to implement Runnable.  Allows "spinning" to happen in background thread so the UI doesn't freeze.
	 */
	public void run() {
		spinButton.setEnabled(false);
		int numToSpin = random.nextInt(numItems) + (random.nextInt(2) + 2) * numItems - selectedIndex;
		for (int i = 0; i < numToSpin; i++) {
			incSelectionIndex();
			try { Thread.sleep(50); }
			catch (InterruptedException e) { e.printStackTrace();}
		}
		hasSpun = true;

//		System.out.println("-------------------------------");
//		System.out.println("Selection: " + getSelection());
//		System.out.println("SelectionIndex: " + getSelectionIndex());
//		System.out.println("SelectionIntValue: " + getSelectionIntValue());
	}
	
	public boolean hasSpun() {
		return hasSpun;
	}
	
	public void resetHasSpun() {
		hasSpun = false;
		spinButton.setEnabled(true);
	}

	/**
	 * Spins the list spinner (in a background thread).
	 */
	public void spin() {
		if (!hasSpun) {
			Thread t = new Thread(ListSpinner.this);
			t.start();
		}
	}

	public static void main (String[] args)
	{
		JFrame frame=new JFrame("List Spinner Test")	;
		Component panel = new ListSpinner("wheel_values.txt");
		frame.add(panel);
		frame.setSize(200,600);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
