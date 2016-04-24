
public class Puzzle {

	
	//fields
	private String category;
	private String phrase;
	
	//constructors
	
	public Puzzle(String CATEGORY, String PHRASE){
		category = CATEGORY.toUpperCase();
		phrase = PHRASE.toUpperCase();
	}
	
	
	//methods
	
	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getPhrase() {
		return phrase;
	}


	public void setPhrase(String phrase) {
		this.phrase = phrase;
	}

	public String toString(){
		
		return phrase + "("+category+")";
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Puzzle abc= new Puzzle("place", "montclair");
		System.out.println(abc);
	}

}
