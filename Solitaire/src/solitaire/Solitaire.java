package solitaire;

import java.io.IOException;
import java.util.Scanner;
import java.util.Random;
import java.util.NoSuchElementException;

/**
 * This class implements a simplified version of Bruce Schneier's Solitaire Encryption algorithm.
 * 
 * @author RU NB CS112
 */
public class Solitaire {
	
	/**
	 * Circular linked list that is the deck of cards for encryption
	 */
	CardNode deckRear;
	
	/**
	 * Makes a shuffled deck of cards for encryption. The deck is stored in a circular
	 * linked list, whose last node is pointed to by the field deckRear
	 */
	public void makeDeck() {
		// start with an array of 1..28 for easy shuffling
		int[] cardValues = new int[28];
		// assign values from 1 to 28
		for (int i=0; i < cardValues.length; i++) {
			cardValues[i] = i+1;
		}
		
		// shuffle the cards
		Random randgen = new Random();
 	        for (int i = 0; i < cardValues.length; i++) {
	            int other = randgen.nextInt(28);
	            int temp = cardValues[i];
	            cardValues[i] = cardValues[other];
	            cardValues[other] = temp;
	        }
	     
	    // create a circular linked list from this deck and make deckRear point to its last node
	    CardNode cn = new CardNode();
	    cn.cardValue = cardValues[0];
	    cn.next = cn;
	    deckRear = cn;
	    for (int i=1; i < cardValues.length; i++) {
	    	cn = new CardNode();
	    	cn.cardValue = cardValues[i];
	    	cn.next = deckRear.next;
	    	deckRear.next = cn;
	    	deckRear = cn;
	    }
	}
	
	/**
	 * Makes a circular linked list deck out of values read from scanner.
	 */
	public void makeDeck(Scanner scanner) 
	throws IOException {
		CardNode cn = null;
		if (scanner.hasNextInt()) {
			cn = new CardNode();
		    cn.cardValue = scanner.nextInt();
		    cn.next = cn;
		    deckRear = cn;
		}
		while (scanner.hasNextInt()) {
			cn = new CardNode();
	    	cn.cardValue = scanner.nextInt();
	    	cn.next = deckRear.next;
	    	deckRear.next = cn;
	    	deckRear = cn;
		}
	}
	
	/**
	 * Implements Step 1 - Joker A - on the deck.
	 */
	void jokerA() {
		CardNode rear= deckRear;
		CardNode head= rear.next;
		CardNode prev=null;
		CardNode preprev=null;
		CardNode ptr=rear;
		do{
			if(ptr.next==rear){
				prev=ptr;
			}
			if(ptr.next.next==rear){
				preprev=ptr;
			}
			ptr=ptr.next;
		}while(ptr!=rear);
		if(rear.cardValue==27){
			prev.next=rear.next;
			rear.next=rear.next.next;
			head.next=rear;
			deckRear=prev.next;//this may need to be changed
			return;
		}
		if(prev.cardValue==27){
			preprev.next=rear;
			prev.next=head;
			rear.next=prev;
			deckRear=prev;
			return;
		}
		ptr=rear.next;
		prev=rear;
		do{
			if(ptr.cardValue==27){
				prev.next=ptr.next;
				ptr.next=ptr.next.next;
				prev.next.next=ptr;
				deckRear=rear;
				return;
			}
			prev=ptr;
			ptr=ptr.next;
		}while(ptr!=rear);
	}
	
	/**
	 * Implements Step 2 - Joker B - on the deck.
	 */
	void jokerB() {
	    // COMPLETE THIS METHOD
		CardNode rear= deckRear;
		//CardNode front= deckRear.next;
		CardNode head= rear.next;
		CardNode prev=null;
		CardNode preprev=null;
		CardNode bpprev=null;
		CardNode ptr=rear;
		
		do{
			if(ptr.next==rear){
				prev=ptr;
			}
			if(ptr.next.next==rear){
				preprev=ptr;
			}
			if(ptr.next.next.next==rear){
				bpprev=ptr;
			}
			ptr=ptr.next;
		}while(ptr!=rear);
		if(rear.cardValue==28){
			/*
			prev.next=rear.next;
			rear.next=rear.next.next;
			head.next=rear;
			prev=prev.next;
			prev.next=ptr.next;
			ptr.next=ptr.next.next;
			prev.next.next=ptr;
			
			deckRear=rear;//this may need to be changed
			*/
			
			deckRear=rear.next;
			
			head=head.next;
			prev.next=rear.next;
			rear.next=head.next;
			head.next=rear;
			
			
			/*//
			System.out.println("jokerB: ");
			trav(deckRear);
			//
*/			return;
		}
		
		if(prev.cardValue==28){
			deckRear=head;
			
			preprev.next=rear;
			prev.next=head.next;
			head.next=prev;
			
			/*//
			System.out.println("jokerB: ");
			trav(deckRear);
			//
*/			
			return;
		}
		
		if(preprev.cardValue==28){
			bpprev.next=prev;
			preprev.next=head;
			rear.next=preprev;
			deckRear=preprev;
			
			/*//
			System.out.println("jokerB: ");
			trav(deckRear);
			//
*/			
			return;
		}
		
		ptr=rear.next;
		prev=rear;
		do{
			if(ptr.cardValue==28){
				/*//this part works for everything but the last 3 letters
				prev.next=ptr.next;
				ptr.next=ptr.next.next;
				prev.next.next=ptr;
				prev=prev.next;
				prev.next=ptr.next;
				ptr.next=ptr.next.next;
				prev.next.next=ptr;
				
				deckRear=rear;//this may need to be changed
				*/
				
				prev.next=ptr.next;
				ptr.next=ptr.next.next.next;
				prev.next.next.next=ptr;
				
				
				
				//write out the code for this
				
				/*//
				System.out.println("jokerB: ");
				trav(deckRear);
				//
*/				return;
			}
			prev=ptr;
			ptr=ptr.next;
		}while(ptr!=rear.next);
		
	}
	
	/**
	 * Implements Step 3 - Triple Cut - on the deck.
	 */
	void tripleCut() {
		// COMPLETE THIS METHOD
		CardNode head=deckRear.next;
		CardNode rear=deckRear;
		CardNode pivotLeft=null;
		CardNode pivotRight=null;
		CardNode plprev=null;
		CardNode newptr=rear.next;
		
		
		
		CardNode ptr=rear.next;
		
		do{
			if(ptr.cardValue==27 || ptr.cardValue==28){
				if(pivotLeft==null){
					
					pivotLeft=ptr;
					
					//
					do{
						if(newptr.next==pivotLeft){
							plprev=newptr;
						}
						newptr=newptr.next;
					}while(newptr!=rear.next);
					//
					
					
				}else{
					pivotRight=ptr;
				}
			}
			ptr=ptr.next;
		}while(ptr!=rear.next);
		
		if(head==pivotLeft && rear==pivotRight){
			/*//
			System.out.println("Trip: ");
			trav(deckRear);
			//
*/			return;
		}
		
		if(head==pivotLeft){
			deckRear=pivotRight;
			
			/*//
			System.out.println("Trip: ");
			trav(deckRear);
			//
*/			
			return;
		}
		
		if(rear==pivotRight){
			deckRear=plprev;
			//System.out.println("deckrear now equals plprev at "+ plprev.cardValue);
			
			/*//
			System.out.println("Trip: ");
			trav(deckRear);
			//
*/			
			return;
		}
		
		//add the actual code
		rear.next=pivotLeft;
		plprev.next=pivotRight.next;
		pivotRight.next=head;
		deckRear=plprev;
		
		//System.out.println("plprev finished at "+ plprev.cardValue);
		
		/*//
		System.out.println("Trip: ");
		trav(deckRear);
		//
*/		//System.out.println("Trip final plprev: "+plprev.cardValue);
		return;
		
	}
	
	/**
	 * Implements Step 4 - Count Cut - on the deck.
	 */
	void countCut() {		
		// COMPLETE THIS METHOD
		int count = deckRear.cardValue;
		CardNode rear= deckRear;
		CardNode head= rear.next;
		CardNode newhead=null;
		CardNode newrear=null;
		CardNode prevrear=null;
		CardNode ptr=head;
		
		if(deckRear.cardValue==28 || deckRear.cardValue==27){
			
			/*//
			System.out.println("Count: ");
			trav(deckRear);
			//
*/			
			return;
		}
		
		do{
			if(ptr.next==deckRear){
				prevrear=ptr;
			}
			ptr=ptr.next;
		}while(ptr!=head);
		
		ptr=head;
		
		for(ptr=head; count>0; count--){
			if(count==1){
				newrear=ptr;
				newhead=ptr.next;
				break;
			}
			ptr=ptr.next;
		}
		prevrear.next=head;
		newrear.next=deckRear;
		deckRear.next=newhead;
		
		/*//
		System.out.println("Count: ");
		trav(deckRear);
		//
*/		
		return;
	}
	
	/**
	 * Gets a key. Calls the four steps - Joker A, Joker B, Triple Cut, Count Cut, then
	 * counts down based on the value of the first card and extracts the next card value 
	 * as key. But if that value is 27 or 28, repeats the whole process (Joker A through Count Cut)
	 * on the latest (current) deck, until a value less than or equal to 26 is found, which is then returned.
	 * 
	 * @return Key between 1 and 26
	 */
	int getKey() {
		// COMPLETE THIS METHOD
		// THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
		int key=0;
		CardNode ptr;
		
		
		
		
		do{
			jokerA();
			jokerB();
			tripleCut();
			countCut();
			
			CardNode head=deckRear.next;
			int count=head.cardValue;
			
			for(ptr=head; count>0; count--){
				
				if(count==1){
					key=ptr.next.cardValue;
					continue;
				}
				ptr=ptr.next;
			}
			
		}while(key==27 || key==28);
		
		//System.out.println("THE KEY IS "+key);
		
	    return key;
	    
	}
	
	/**
	 * Utility method that prints a circular linked list, given its rear pointer
	 * 
	 * @param rear Rear pointer
	 */
	private static void printList(CardNode rear) {
		if (rear == null) { 
			return;
		}
		System.out.print(rear.next.cardValue);
		CardNode ptr = rear.next;
		do {
			ptr = ptr.next;
			System.out.print("," + ptr.cardValue);
		} while (ptr != rear);
		System.out.println("\n");
	}

	/**
	 * Encrypts a message, ignores all characters except upper case letters
	 * 
	 * @param message Message to be encrypted
	 * @return Encrypted message, a sequence of upper case letters only
	 */
	public String encrypt(String message) {	
		// COMPLETE THIS METHOD
	    // THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
		char ch=' ';
		int keyval=0;
		int chval=0;
		String e="";
		
		for(int i=0;i<message.length();i++){
			if(message.charAt(i)>='A' && message.charAt(i)<='Z'){
				keyval=getKey();
				chval=(message.charAt(i)-'A')+1;
				keyval+=chval;
				if(keyval>26){
					keyval-=26;
				}
				ch=(char)(keyval+'A'-1);
				e+=ch;
			}
		}
		
		
		
		
		
	    return e;
	}
	
	/**
	 * Decrypts a message, which consists of upper case letters only
	 * 
	 * @param message Message to be decrypted
	 * @return Decrypted message, a sequence of upper case letters only
	 */
	public String decrypt(String message) {	
		// COMPLETE THIS METHOD
	    // THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
		char ch=' ';
		int keyval=0;
		int chval=0;
		String e="";
		
		for(int i=0;i<message.length();i++){
			if(message.charAt(i)>='A' && message.charAt(i)<='Z'){
				keyval=getKey();
				chval=(message.charAt(i)-'A')+1;
				if(chval<=keyval){
					chval+=26;
				}
				chval-=keyval;
				
				ch=(char)(chval+'A'-1);
				e+=ch;
			}
		}
		
	    return e;
	}
	
	private static void trav(CardNode rear){
		CardNode head=rear.next;
		CardNode ptr=head;
		do{
			System.out.print("["+ptr.cardValue+"]");
			ptr=ptr.next;
		}while(ptr!=head);
		System.out.println();
	}
	
}
