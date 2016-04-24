package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Map.Entry;

public class main {

	public static HashMap<String,ArrayList<Occurrence>> keywordsIndex;
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LittleSearchEngine lse=new LittleSearchEngine();
		//System.out.println(lse);
	
		
		File file=new File("AliceCh1.txt");
		//File file=new File("WowCh1.txt");
		String word;
		Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int count=0;
		while(sc.hasNext()){
			count++;
			word=sc.next();
			//System.out.println(word+":"+count);
		}
		
		
		ArrayList arr=new ArrayList();
		
		arr.add("bob");
		//System.out.println(arr.get(0));
		////System.out.println(arr.get(1));
		
		try {
			loadKeyWords("AliceCh1.txt");
			//loadKeyWords("WowCh1.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Integer> ints=new ArrayList<Integer>();
		ints.add(20);
		ints.add(15);
		ints.add(14);
		//ints.add(7);
		//ints.add(6);
		//ints.add(5);
		ints.add(12);
		ints.add(12);
		ints.add(10);
		ints.add(8);
		ints.add(10);
		System.out.println("MIDPOINTS"+insertLastOccurrence(ints));
		
		try {
			mergeKeyWords(loadKeyWords("AliceCh1.txt"));
			//mergeKeyWords(loadKeyWords("WowCh1.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void mergeKeyWords(HashMap<String,Occurrence> kws) {
		// COMPLETE THIS METHOD
		
		//i think you have to put the insertlastoccurrence after every "put" call merging kws into the master table
		//ask sesh how to access the arraylist of the master keywords class in order to add the keyword to it and apply insertlastoccurrence to
		//check the spot to be hashed to, if its empty, make a new ArrayList and add to it and apply ilo, else, just add to the list and ilo
		ArrayList arr;//
		System.out.println("iterator");
		for(Entry<String, Occurrence> s:kws.entrySet()){
			
			System.out.print(s.getValue()+", ");
		}
		
	}
	/*public static HashMap<String,Occurrence> loadKeyWords(String docFile) 
			throws FileNotFoundException {
				// COMPLETE THIS METHOD
				// THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
				
				//FileInputStream file=new FileInputStream(docFile);
				
				//use a loop to find all the occurances of a word, delete each occurence, #of occurrances++ for each delete, start the hashin!
				
				File file=new File(docFile);
				int total = 0;
				String word;
				String toAdd;
				ArrayList<String> arr=new ArrayList<String>();
				//HashMap<String,Occurrence> hash=new HashMap<String,Occurence>();
				Scanner sc=new Scanner(file);
				while(sc.hasNext()){
					word=sc.next();
				}
				
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
					////System.out.println(word);
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
							//System.out.println(s+ " : "+occurs);
							total+=occurs;
							//System.out.println("the total is: "+total);
							haspushed=true;
						}
						i=originalI;
						if(haspushed==false){
							//put the string s and occurance occurs into an occurance object and put it in a hashmap
							//System.out.println(s+ " : "+occurs);
							total+=occurs;
							//System.out.println("the total is: "+total);
							
						}
					}
				}catch(IndexOutOfBoundsException e){
					
				}
				////System.out.println(arr.get(0));
				
				FileInputStream file = null;
				try
				{
				file =new FileInputStream(docFile);
				File file=new File(docFile);
				
				int mychar;
				while((mychar = file.read()) != -1)
				{
					//System.out.println((char) mychar + "\n");
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
				}
				return null;//return the hashmap
			}*/
	
	
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
					////System.out.println(word);
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
							occ=new Occurrence(docFile,occurs);
							hash.put(s, occ);
						}
						i=originalI;
						if(haspushed==false){
							//put the string s and occurance occurs into an occurance object and put it in a hashmap
							occ=new Occurrence(docFile,occurs);
							hash.put(s, occ);
						}
					}
				}catch(IndexOutOfBoundsException e){
					
				}//maybe note out this try catch statement
				////System.out.println(arr.get(0));
				
				/*FileInputStream file = null;
				try
				{
				file =new FileInputStream(docFile);
				File file=new File(docFile);
				
				int mychar;
				while((mychar = file.read()) != -1)
				{
					//System.out.println((char) mychar + "\n");
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
				File newfile=new File("noisewords.txt");
				Scanner newscan=new Scanner(newfile);
				String p;
				while(newscan.hasNextLine()){
					p=newscan.nextLine();
					if(hash.containsKey(p)){
						System.out.print(p+", ");
					}
				}
				
				
				System.out.println();
				//System.out.println(hash.values());
				System.out.println("entry set: "+hash.entrySet());
				return hash;//return the hashmap
			}
	
	
	
	public static String getKeyWord(String word) {
		// COMPLETE THIS METHOD
		// THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
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
		
		if(word.contains(",")||word.contains("!")||word.contains(";")||word.contains(".")||word.contains("?")||word.contains(":")){//||word.contains("-")||word.contains("(")||word.contains(")")||word.contains("'")
			/*if(word.substring(0,word.length()-2).contains(".")||word.substring(0,word.length()-2).contains(",")||word.substring(0,word.length()-2).contains("!")||word.substring(0,word.length()-2).contains("-")||word.substring(0,word.length()-2).contains("?")||word.substring(0,word.length()-2).contains("'")){
				
			}*/
			//System.out.println(word+"  I GOT HERE");
			if(word.charAt(word.length()-1)=='.'||word.charAt(word.length()-1)==','||word.charAt(word.length()-1)==';'||word.charAt(word.length()-1)=='!'||word.charAt(word.length()-1)=='?'||word.charAt(word.length()-1)==':'){//||word.charAt(word.length()-1)=='-'||word.charAt(word.length()-1)=='('||word.charAt(word.length()-1)==')'||word.charAt(word.length()-1)=='\''
				//if(word.length()>1){	
				//int currentLength=word.length();
				//try{
				if(word.length()==1){
					return null;
				}
					while((word.charAt(word.length()-1)=='.'||word.charAt(word.length()-1)==','||word.charAt(word.length()-1)==';'||word.charAt(word.length()-1)=='!'||word.charAt(word.length()-1)=='?'||word.charAt(word.length()-1)==':')){//||word.charAt(word.length()-1)=='-'||word.charAt(word.length()-1)=='('||word.charAt(word.length()-1)==')'||word.charAt(word.length()-1)=='\''
						
						if(word.charAt(word.length()-1)=='.'||word.charAt(word.length()-1)==','||word.charAt(word.length()-1)==';'||word.charAt(word.length()-1)=='!'||word.charAt(word.length()-1)=='?'||word.charAt(word.length()-1)==':'){//||word.charAt(word.length()-1)=='-'||word.charAt(word.length()-1)=='('||word.charAt(word.length()-1)==')'||word.charAt(word.length()-1)=='\''
							//possibly add to these three this: ||word.charAt(word.length()-1)=='-' and possibly this:||word.charAt(word.length()-1)=='\'' and possibly this: ||word.charAt(word.length()-1)==')' and ||word.charAt(word.length()-1)=='('
							/*word=word.substring(0, word.length()-2);
							if(word.length()<=2){
								//System.out.println(word+"  I GOT HERE TOO");
								break;
							}*/
							////System.out.println(word+"  I GOT HERE TOO");
							if(word.charAt(word.length()-1)=='.'){
								////System.out.println("WHY IS THIS NOT WORKING");
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
							//System.out.println(word+"  I GOT HERE TOO AS WELL");
							
							
							
						//	currentLength=word.length();
						}
					}
				//}catch(StringIndexOutOfBoundsException e){
				//	//System.out.println(word+"I GOT HERE");
				//}
				//}
				if(word.contains(",")||word.contains("!")||word.contains(";")||word.contains(".")||word.contains("?")||word.contains(":")){//||word.contains("-")||word.contains("(")||word.contains(")")||word.contains("'")
					return null;
				}else{
					
					while(sc.hasNextLine()){
						//	arr.add(sc.nextLine());
							s=sc.nextLine();
							if(s.equals(word)){
								System.out.print(word+", ");
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
			System.out.print(word+", ");
			return null;
		}//else{
		
		while(sc.hasNextLine()){
			//	arr.add(sc.nextLine());
				s=sc.nextLine();
				if(s.equals(word)){
					System.out.print(word+", ");
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
	
	/*public ArrayList<Integer> insertLastOccurrence(ArrayList<Occurrence> occs) {
		// COMPLETE THIS METHOD
		// THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
		if(occs.size()<=1){
			return null;
		}
		Occurrence last=occs.get(occs.size()-1);
		ArrayList<Integer> mids=new ArrayList<Integer>();
		int freq=last.frequency;
		
		occs.remove(occs.size()-1);

		
		int lo=0;
		int hi=occs.size()-1;
		
		while(lo<=hi){
			//i have no idea what im doing
			int mid=(lo+hi)/2;
			mids.add(mid);
			if(occs.get(mid).frequency==freq){
				//this is where to insert the last value
				
				occs.add(mid, last);
				occs.remove(occs.size()-1);
				break;
			}else if(occs.get(mid).frequency<freq){
				lo=mid+1;
			}else{
				hi=mid-1;
			}	
		}		
		return mids;
	}*/public static ArrayList<Integer> insertLastOccurrence(ArrayList<Integer> occs) {
		// COMPLETE THIS METHOD
		// THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
		
		//READ THIS AND EDIT THE METHOD
		//insert the last thing after the mid index that you find to put it at
		
		if(occs.size()<=1){
			return null;
		}
		//Occurrence last=occs.get(occs.size()-1);
		//this is noted out to make testing work, and integers are replacing occurrence objects as well
		ArrayList<Integer> mids=new ArrayList<Integer>();
		//int freq=last.frequency;
		int last=occs.get(occs.size()-1);
		int freq=last;
		occs.remove(occs.size()-1);
		
		int lo=0;
		int hi=occs.size()-1;
		int mid = 0;
		while(lo<=hi){
			//i have no idea what im doing
			mid=(lo+hi)/2;
			mids.add(mid);
			System.out.println(mid);
			/*if(occs.get(mid).frequency==freq){
				//this is where to insert the last value
				
				occs.add(mid, last);
				occs.remove(occs.size()-1);
				break;
			}else */
			if(occs.get(mid)>=freq){
				lo=mid+1;
			}else{
				hi=mid-1;
			}	
		}	
		occs.add(mid,last);
		System.out.println(occs);
		return mids;
	}

}
