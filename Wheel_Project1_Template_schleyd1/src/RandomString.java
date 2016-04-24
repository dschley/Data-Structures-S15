import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/**
 * RandomString gets a set of strings from a file.  You can use next() 
 * to get a random string from the set.  It will reload the strings from
 * the file after every string is used.
 * 
 * @author Jerry Alan Fails
 * @version 2010-11-10
 */
public class RandomString {

	String file;
	ArrayList<String> myList;
	ArrayList<String> wheel_values = new ArrayList<String>();
	
	public RandomString(String filename) {
		// TODO Auto-generated constructor stub
		file = filename;
		loadStringsFromFile();
	}
	
	/**
	 * This gets the strings from the file.
	 */
	private void loadStringsFromFile()
	{
			// TODO
		myList = new ArrayList<String>();
		
		try {
			Scanner s = new Scanner(new File(file));

			while (s.hasNextLine()){
				
				wheel_values.add(s.nextLine());
			}
			s.close();
			myList = wheel_values;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Shuffles the strings.
	 */
	public void shuffle()
	{
		// TODO
		Collections.shuffle(wheel_values);
	}
	
	/**
	 * Returns the strings read in from the file.
	 * @return
	 */
	public ArrayList<String> getStrings() {
		// TODO Auto-generated method stub
		return wheel_values;
	}
	
	/**
	 * Returns a randomly chosen string from the set.  
	 * It will not repeat a string until it has used all of them.
	 */
	public String next()
	{
		// TODO
		if(myList.isEmpty())
			loadStringsFromFile();
		Random gen = new Random();
		int index = gen.nextInt(myList.size());
		String result = myList.get(index);
		myList.remove(index);
		
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomString schley = new RandomString("wheel_values.txt");
		System.out.println(schley.next());
	}


}
