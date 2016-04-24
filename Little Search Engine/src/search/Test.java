package search;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

/**
 * This class encapsulates an occurrence of a keyword in a document. It stores the
 * document name, and the frequency of occurrence in that document. Occurrences are
 * associated with keywords in an index hash table.
 * 
 * @author Sesh Venugopal
 * 
 */
/*class Occurrence {
	*//**
	 * Document in which a keyword occurs.
	 *//*
	String document;
	
	*//**
	 * The frequency (number of times) the keyword occurs in the above document.
	 *//*
	int frequency;
	
	*//**
	 * Initializes this occurrence with the given document,frequency pair.
	 * 
	 * @param doc Document name
	 * @param freq Frequency
	 *//*
	public Occurrence(String doc, int freq) {
		document = doc;
		frequency = freq;
	}
	
	 (non-Javadoc)
	 * @see java.lang.Object#toString()
	 
	public String toString() {
		return "(" + document + "," + frequency + ")";
	}
}*/

/**
 * This class builds an index of keywords. Each keyword maps to a set of documents in
 * which it occurs, with frequency of occurrence in each document. Once the index is built,
 * the documents can searched on for keywords.
 *
 */
public class Test {
	
	public static void main(String[] args){
		File file=new File("AliceCh1.txt");
		File secfile=new File("WowCh1.txt");
		
		/*try {
			loadKeyWords("AliceCh1.txt");
			//loadKeyWords("WowCh1.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/*try {
			mergeKeyWords(loadKeyWords("AliceCh1.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		try {
			mergeKeyWords(loadKeyWords("WowCh1.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(top5search("happy","pewp"));
	}
	
	
	
	/**
	 * This is a hash table of all keywords. The key is the actual keyword, and the associated value is
	 * an array list of all occurrences of the keyword in documents. The array list is maintained in descending
	 * order of occurrence frequencies.
	 */
	public static HashMap<String,ArrayList<Occurrence>> keywordsIndex=new HashMap<String,ArrayList<Occurrence>>();
	
	/**
	 * The hash table of all noise words - mapping is from word to itself.
	 */
	HashMap<String,String> noiseWords;
	
	/**
	 * Creates the keyWordsIndex and noiseWords hash tables.
	 */
	public Test() {
		keywordsIndex = new HashMap<String,ArrayList<Occurrence>>(1000,2.0f);
		noiseWords = new HashMap<String,String>(100,2.0f);
	}
	
	/**
	 * This method indexes all keywords found in all the input documents. When this
	 * method is done, the keywordsIndex hash table will be filled with all keywords,
	 * each of which is associated with an array list of Occurrence objects, arranged
	 * in decreasing frequencies of occurrence.
	 * 
	 * @param docsFile Name of file that has a list of all the document file names, one name per line
	 * @param noiseWordsFile Name of file that has a list of noise words, one noise word per line
	 * @throws FileNotFoundException If there is a problem locating any of the input files on disk
	 */
	public void makeIndex(String docsFile, String noiseWordsFile) 
	throws FileNotFoundException {
		// load noise words to hash table
		Scanner sc = new Scanner(new File(noiseWordsFile));
		while (sc.hasNext()) {
			String word = sc.next();
			noiseWords.put(word,word);
		}
		
		// index all keywords
		sc = new Scanner(new File(docsFile));
		while (sc.hasNext()) {
			String docFile = sc.next();
			HashMap<String,Occurrence> kws = loadKeyWords(docFile);
			mergeKeyWords(kws);
		}
		
	}

	/**
	 * Scans a document, and loads all keywords found into a hash table of keyword occurrences
	 * in the document. Uses the getKeyWord method to separate keywords from other words.
	 * 
	 * @param docFile Name of the document file to be scanned and loaded
	 * @return Hash table of keywords in the given document, each associated with an Occurrence object
	 * @throws FileNotFoundException If the document file is not found on disk
	 */
	public static HashMap<String,Occurrence> loadKeyWords(String docFile) 
	throws FileNotFoundException {
		// COMPLETE THIS METHOD
		// THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
		
		//FileInputStream file=new FileInputStream(docFile);
		
		//use a loop to find all the occurances of a word, delete each occurence, #of occurrances++ for each delete, start the hashin!
		
		File file=new File(docFile);
		String word;
		String toAdd;
		Occurrence occ=null;//new Occurrence();
		ArrayList<String> arr=new ArrayList<String>();
		HashMap<String,Occurrence> hash=new HashMap<String,Occurrence>();
		//HashMap<String,Occurrence> hash=new HashMap<String,Occurence>();
		/*Scanner sc=new Scanner(file);
		while(sc.hasNext()){
			word=sc.next();
		}*/
		
		Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new FileNotFoundException();
		}
		while(sc.hasNext()){
			word=sc.next();
			//System.out.println(word);
			toAdd=getKeyWord(word);
			if(toAdd!=null){
				arr.add(toAdd);
			}
			
		}
		
		int i=0;
		int originalI=0;
		int occurs=0;
		String s;
		boolean haspushed=false;
		try{
			while(!arr.isEmpty()){
				haspushed=false;
				s=arr.get(i);
				arr.remove(i);
				occurs=1;
				try{
					while(!arr.isEmpty()){
						if(s.equals(arr.get(i))){
							occurs++;
							arr.remove(i);
							continue;
						}
						i++;
					}
				}catch(IndexOutOfBoundsException e){
					//put the string s and occuranec occurs into an occurance object and put it in a hashmap
					haspushed=true;
					occ=new Occurrence(docFile,occurs);//for this line and the next, i thought this was the right way at first
					hash.put(s, occ);//however it prints [(AliceCh1.txt,1), (AliceCh1.txt,3), (AliceCh1.txt,1)... etc which idk if its right
					
					
				}
				i=originalI;
				if(haspushed==false){
					//put the string s and occurance occurs into an occurance object and put it in a hashmap
					occ=new Occurrence(docFile,occurs);
					hash.put(s, occ);
					
					
				}
			}
		}catch(IndexOutOfBoundsException e){
			
		}
		//System.out.println(arr.get(0));
		
		/*FileInputStream file = null;
		try
		{
		file =new FileInputStream(docFile);
		File file=new File(docFile);
		
		int mychar;
		while((mychar = file.read()) != -1)
		{
			System.out.println((char) mychar + "\n");
		}
		}
		catch(IOException t)
		{
			t.printStackTrace();
		}
		finally
		{
			try
			{
				
				file.close();
			}
			catch(IOException t)
			{
				t.printStackTrace();
			}
		}*/
		return hash;//return the hashmap
	}
	
	/**
	 * Merges the keywords for a single document into the master keywordsIndex
	 * hash table. For each keyword, its Occurrence in the current document
	 * must be inserted in the correct place (according to descending order of
	 * frequency) in the same keyword's Occurrence list in the master hash table. 
	 * This is done by calling the insertLastOccurrence method.
	 * 
	 * @param kws Keywords hash table for a document
	 */
	public static void mergeKeyWords(HashMap<String,Occurrence> kws) {
		// COMPLETE THIS METHOD
		
		//i think you have to put the insertlastoccurrence after every "put" call merging kws into the master table
		//ask sesh how to access the arraylist of the master keywords class in order to add the keyword to it and apply insertlastoccurrence to
		//check the spot to be hashed to, if its empty, make a new ArrayList and add to it, else, just add to the list and ilo
		ArrayList<Occurrence> arr;//not initialized yet because it may not be needed
		int poop =0;
		poop++;
		for(Entry<String, Occurrence> s:kws.entrySet()){
			String p=s.getKey();
			if(keywordsIndex.containsKey(p)==true){
				keywordsIndex.get(p).add(s.getValue());
				insertLastOccurrence(keywordsIndex.get(s.getKey()));
			}else{
				arr=new ArrayList<Occurrence>();
				arr.add(s.getValue());
				keywordsIndex.put(s.getKey(), arr);
			}
		}
		
	}
	
	/**
	 * Given a word, returns it as a keyword if it passes the keyword test,
	 * otherwise returns null. A keyword is any word that, after being stripped of any
	 * TRAILING punctuation, consists only of alphabetic letters, and is not
	 * a noise word. All words are treated in a case-INsensitive manner.
	 * 
	 * Punctuation characters are the following: '.', ',', '?', ':', ';' and '!'
	 * 
	 * @param word Candidate word
	 * @return Keyword (word without trailing punctuation, LOWER CASE)
	 */
	public static String getKeyWord(String word) {
		// COMPLETE THIS METHOD
		// THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
		//READ THIS AND THEN EDIT
		//so take away trailing punction that is listed in the details and then if there is ANY non letter character, its no bueno
		
		File file=new File("noisewords.txt");
		Scanner sc = null;//maybe take away the =null part and then take away the try catch
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		word=word.toLowerCase();
		//ArrayList arr=new ArrayList();
		String s;
		
		/*while(sc.hasNextLine()){
		//	arr.add(sc.nextLine());
			s=sc.nextLine();
			if(s==word){
				return null;
			}
		}*/
		
		if(word.contains(",")||word.contains("!")||word.contains(";")||word.contains(".")||word.contains("?")||word.contains(":")||word.contains("-")){//||word.contains("-")||word.contains("(")||word.contains(")")||word.contains("'")
			/*if(word.substring(0,word.length()-2).contains(".")||word.substring(0,word.length()-2).contains(",")||word.substring(0,word.length()-2).contains("!")||word.substring(0,word.length()-2).contains("-")||word.substring(0,word.length()-2).contains("?")||word.substring(0,word.length()-2).contains("'")){
				
			}*/
			if(word.charAt(word.length()-1)=='.'||word.charAt(word.length()-1)==','||word.charAt(word.length()-1)==';'||word.charAt(word.length()-1)=='!'||word.charAt(word.length()-1)=='?'||word.charAt(word.length()-1)==':'){//||word.charAt(word.length()-1)=='-'||word.charAt(word.length()-1)=='('||word.charAt(word.length()-1)==')'||word.charAt(word.length()-1)=='\''
				//if(word.length()>1){	
				if(word.length()==1){
					return null;
				}
					while(word.charAt(word.length()-1)=='.'||word.charAt(word.length()-1)==','||word.charAt(word.length()-1)==';'||word.charAt(word.length()-1)=='!'||word.charAt(word.length()-1)=='?'||word.charAt(word.length()-1)==':'){//||word.charAt(word.length()-1)=='-'||word.charAt(word.length()-1)=='('||word.charAt(word.length()-1)==')'||word.charAt(word.length()-1)=='\''
						
						if(word.charAt(word.length()-1)=='.'||word.charAt(word.length()-1)==','||word.charAt(word.length()-1)==';'||word.charAt(word.length()-1)=='!'||word.charAt(word.length()-1)=='?'||word.charAt(word.length()-1)==':'){//||word.charAt(word.length()-1)=='-'||word.charAt(word.length()-1)=='('||word.charAt(word.length()-1)==')'||word.charAt(word.length()-1)=='\''
							//possibly add to these three this: ||word.charAt(word.length()-1)=='-' and possibly this:||word.charAt(word.length()-1)=='\'' and possibly this: ||word.charAt(word.length()-1)==')' and ||word.charAt(word.length()-1)=='('
							//word=word.substring(0, word.length()-2);
							
							//System.out.println(word+"  I GOT HERE TOO");
							if(word.charAt(word.length()-1)=='.'){
								//System.out.println("WHY IS THIS NOT WORKING");
								word=word.replace(".","");
							}else if(word.charAt(word.length()-1)==','){
								word=word.replace(",","");
							}else if(word.charAt(word.length()-1)==';'){
								word=word.replace(";","");
							}else if(word.charAt(word.length()-1)==':'){
								word=word.replace(":","");
							}else if(word.charAt(word.length()-1)=='?'){
								word=word.replace("?","");
							}else if(word.charAt(word.length()-1)=='!'){
								word=word.replace("!","");
							}/*else if(word.charAt(word.length()-1)=='-'){
								word=word.substring(0,word.length()-2);
							}else if(word.charAt(word.length()-1)=='('){
								word=word.substring(0,word.length()-2);
							}else if(word.charAt(word.length()-1)==')'){
								word=word.substring(0,word.length()-2);
							}else if(word.charAt(word.length()-1)=='\''){
								word=word.substring(0,word.length()-2);
							}*/
							
						}
					}
				//}	
				
				if(word.contains(",")||word.contains("!")||word.contains(";")||word.contains(".")||word.contains("?")||word.contains(":")||word.contains("-")){//||word.contains("-")||word.contains("(")||word.contains(")")||word.contains("'")
					return null;
				}else{
					
					while(sc.hasNextLine()){
						//	arr.add(sc.nextLine());
							s=sc.nextLine();
							if(s.equals(word)){
								return null;
							}
					}
					
					for(int i=0;i<word.length();i++){
						//if ((c>= 'A' && c<= 'Z') || (c>= 'a' && c<='z'))
						if(!(word.charAt(i)>='a'&& word.charAt(i)<='z')){
							return null;
						}
					}
					
					
					return word;
				}
				
			}
			
			return null;
		}//else{
		
		while(sc.hasNextLine()){
			//	arr.add(sc.nextLine());
				s=sc.nextLine();
				if(s.equals(word)){
					return null;
				}
		}
		
		for(int i=0;i<word.length();i++){
			//if ((c>= 'A' && c<= 'Z') || (c>= 'a' && c<='z'))
			if(!(word.charAt(i)>='a'&& word.charAt(i)<='z')){
				return null;
			}
		}
		
			return word;
		//}
		
		
		//return null;
	}
	
	/**
	 * Inserts the last occurrence in the parameter list in the correct position in the
	 * same list, based on ordering occurrences on descending frequencies. The elements
	 * 0..n-2 in the list are already in the correct order. Insertion of the last element
	 * (the one at index n-1) is done by first finding the correct spot using binary search, 
	 * then inserting at that spot.
	 * 
	 * @param occs List of Occurrences
	 * @return Sequence of mid point indexes in the input list checked by the binary search process,
	 *         null if the size of the input list is 1. This returned array list is only used to test
	 *         your code - it is not used elsewhere in the program.
	 */
	public static ArrayList<Integer> insertLastOccurrence(ArrayList<Occurrence> occs) {
		// COMPLETE THIS METHOD
		// THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
		
		//READ THIS AND EDIT THE METHOD
		//insert the last thing after the mid index that you find to put it at
		
		if(occs.size()<=1){
			return null;
		}
		Occurrence last=occs.get(occs.size()-1);
		ArrayList<Integer> mids=new ArrayList<Integer>();
		int freq=last.frequency;
		
		occs.remove(occs.size()-1);
		
		int lo=0;
		int hi=occs.size()-1;
		int mid = 0;
		while(lo<=hi){
			//i have no idea what im doing
			mid=(lo+hi)/2;
			mids.add(mid);
			/*if(occs.get(mid).frequency==freq){
				//this is where to insert the last value
				
				occs.add(mid, last);
				occs.remove(occs.size()-1);
				break;
			}else */
			if(occs.get(mid).frequency>=freq){
				lo=mid+1;
			}else{
				hi=mid-1;
			}	
		}	
		occs.add(mid,last);
		return mids;
	}
	
	/**
	 * Search result for "kw1 or kw2". A document is in the result set if kw1 or kw2 occurs in that
	 * document. Result set is arranged in descending order of occurrence frequencies. (Note that a
	 * matching document will only appear once in the result.) Ties in frequency values are broken
	 * in favor of the first keyword. (That is, if kw1 is in doc1 with frequency f1, and kw2 is in doc2
	 * also with the same frequency f1, then doc1 will appear before doc2 in the result. 
	 * The result set is limited to 5 entries. If there are no matching documents, the result is null.
	 * 
	 * @param kw1 First keyword
	 * @param kw1 Second keyword
	 * @return List of NAMES of documents in which either kw1 or kw2 occurs, arranged in descending order of
	 *         frequencies. The result size is limited to 5 documents. If there are no matching documents,
	 *         the result is null.
	 */
	public static ArrayList<String> top5search(String kw1, String kw2) {
		// COMPLETE THIS METHOD
		// THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
		
		ArrayList<Occurrence> w1=keywordsIndex.get(kw1);
		ArrayList<Occurrence> w2=keywordsIndex.get(kw2);
		ArrayList<Occurrence> top5=new ArrayList<Occurrence>();
		ArrayList<String> results=new ArrayList<String>();
		boolean contains=false;
		boolean nofirst=false;
		boolean nosec=false;
		
		if(!(w1==null)){
			
			for(int i=0;i<5 && w1.size()-i>0;i++){
				top5.add(w1.get(i));
			}
		}else{
			nofirst=true;
		}
		
		/////
		
		if(!(w2==null)){
			for(int i=0;i<5 && w2.size()-i>0;i++){
				contains=false;
				for(int j=0;j<top5.size();j++){
					if(top5.get(j).document.equals(w2.get(i).document)){
						if(top5.get(j).frequency<w2.get(i).frequency){
							top5.remove(j);
							top5.add(w2.get(i));
							insertLastOccurrence(top5);
							j=top5.size();
							contains=true;
						}
						contains=true;
					}
				}
				
				if(contains==false){
					top5.add(w2.get(i));
					insertLastOccurrence(top5);
				}
				
			}
		}else{
			nosec=true;
		}
		
		if(nofirst==true && nosec==false){
			for(int i=0;i<5 && i<w2.size();i++){
				results.add(w2.get(i).document);
			}
		}else if(nofirst==false && nosec==true){
			for(int i=0;i<5 && i<w1.size();i++){
				results.add(w1.get(i).document);
			}
		}else if(nofirst==true && nosec==true){
			return null;
		}else{
			for(int i=0;i<5 && i<top5.size();i++){
				results.add(top5.get(i).document);
			}
		}
		
		
		
		/////
		
		
		/*//this is the strategy where i put the top 5 of 1 list into a list, then fill it if its not at 5 then for the rest of the top 5 in the second list i replace
		int count=0;
		
		while(top5.size()<5){
			if(count==5){
				break;
			}
			
			if(top5.contains(w2.get(count))){
				for(int i=0;i<top5.size();i++){
					if(top5.get(i).document.equals(w2.get(count).document)){
						if(top5.get(i).frequency<w2.get(count).frequency){
							top5.remove(i);
							top5.add(w2.get(count));//get(i)=w2.get(count);
							insertLastOccurrence(top5);
						}
					}
				}
			}else{
				top5.add(w2.get(count));
				insertLastOccurrence(top5);
			}
			count++;
		}
		if(count!=0){
			count--;
		}
		while(count<5){
			
		}
		*/
		/*for(int i=0;i<5 && w1.size()-i>0;i++){
			results.add(w1.get(i).document);
		}
		
		for(int i=0;i<5 && w2.size()-i>0;i++){
			
			if(results.size()<5){
				if(!results.contains(w2.get(i).document)){
					results.add(w2.get(i).document);
				}
			}
			if(results.contains(w2.get(i).document)){
				
			}
		}*/
		
		if(results.isEmpty()){
			return null;
		}
		return results;
	}
}
