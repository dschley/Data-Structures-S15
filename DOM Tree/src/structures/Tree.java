package structures;

import java.util.*;

/**
 * This class implements an HTML DOM Tree. Each node of the tree is a TagNode, with fields for
 * tag/text, first child and sibling.
 * 
 */
public class Tree {
	
	/**
	 * Root node
	 */
	TagNode root=null;
	
	/**
	 * Scanner used to read input HTML file when building the tree
	 */
	Scanner sc;
	
	/**
	 * Initializes this tree object with scanner for input HTML file
	 * 
	 * @param sc Scanner for input HTML file
	 */
	public Tree(Scanner sc) {
		this.sc = sc;
		root = null;
	}
	
	/**
	 * Builds the DOM tree from input HTML file. The root of the 
	 * tree is stored in the root field.
	 */
	public void build() {
		/** COMPLETE THIS METHOD **/
		if(!this.sc.hasNextLine()){
			return;
		}
		//TagNode parent=null;
		//TagNode leftsib=null;
		String input=this.sc.nextLine();
		//String stkinput=null;
		Stack<TagNode> stk=new Stack<TagNode>();
		TagNode node=null;
		TagNode ptr=null;
		root=new TagNode(input.substring(1, input.length()-1),null,null);
		
		stk.push(root);
		
		while(this.sc.hasNextLine()){
			input=this.sc.nextLine();
			
			if(input.charAt(0)=='<'){
				
				if(input.contains("/")){
				
					stk.pop();
				}else{
					if(stk.peek().firstChild==null){
						try{
							node=new TagNode(input.substring(1,input.length()-1),null,null);
						}catch(StringIndexOutOfBoundsException e){
							node=new TagNode(input,null,null);
						}
						stk.peek().firstChild=node;
						stk.push(node);
					}else{
						ptr=stk.peek().firstChild;
						
						while(ptr.sibling!=null){
							ptr=ptr.sibling;
						}
						
						try{
							node=new TagNode(input.substring(1,input.length()-1),null,null);
						}catch(StringIndexOutOfBoundsException e){
							node=new TagNode(input,null,null);
						}
						
						ptr.sibling=node;
						stk.push(node);
						
					}
				}
			}else{
				
				if(stk.peek().firstChild==null){
					stk.peek().firstChild= new TagNode(input,null,null);
				}else{
					ptr=stk.peek().firstChild;
					
					while(ptr.sibling!=null){
						ptr=ptr.sibling;
					}
					
					ptr.sibling=new TagNode(input,null,null);
				}
				
			}
		}
		
		
		//TagNode ptr=root;
		
		/*while(this.sc.hasNextLine()){
			
			input=this.sc.nextLine();
			
			if(input.charAt(0)=='<'){
				
				
				
				
				
				ptr.firstChild=new TagNode(input.substring(1, input.length()-1),null,null);	
				ptr=ptr.firstChild;
				stkinput=this.sc.nextLine();
				
				while(!input.substring(1,input.length()-1).equals('/'+stkinput.substring(1,stkinput.length()-1))){
					if(stkinput.charAt(0)=='<'){
						if(stkinput.charAt(1)=='/'){
							System.out.println(stkinput);
							stkinput=this.sc.nextLine();
							continue;
						}
						stk.push(stkinput.substring(1,stkinput.length()-1));
					}else{
					stk.push(stkinput);
					}
					stkinput=this.sc.nextLine();
				}
				
				while(!stk.isEmpty()){
					ptr.firstChild=new TagNode(stk.pop(),null,null);
					ptr=ptr.firstChild;
				}
				
				//continue; //i may need this idk though just keep it noted in case
			}else{
				ptr.firstChild=new TagNode(input,null,null);
			}
			
			ptr=ptr.firstChild;
		}*/
		
		/*while(this.sc.hasNextLine()){
			input=this.sc.nextLine();
			if(input.charAt(0)=='<'){
				//if(input.charAt(1)=='p'){
				//	while(!input.substring(1, input.length()-1).equals("/p")){
						
				//	}
				//}
			//	else  
					if(input.charAt(1)=='/'){
					continue;
				}else{
			ptr.firstChild=new TagNode(input.substring(1, input.length()-1),null,null);
			System.out.println(input.substring(1,input.length()-1));
			}
			}else{
				ptr.firstChild=new TagNode(input,null,null);
				System.out.println(input);

			}
			ptr=ptr.firstChild;

		}*/
		
		
		
		
		
		
		
		return;
	}
	
	
	/**
	 * Replaces all occurrences of an old tag in the DOM tree with a new tag
	 * 
	 * @param oldTag Old tag
	 * @param newTag Replacement tag
	 */
	public void replaceTag(String oldTag, String newTag) {
		/** COMPLETE THIS METHOD **/
		replaceRecurse(root,oldTag,newTag);
		return;
	}
			
	/**
	 * Boldfaces every column of the given row of the table in the DOM tree. The boldface (b)
	 * tag appears directly under the td tag of every column of this row.
	 * 
	 * @param row Row to bold, first row is numbered 1 (not 0).
	 */
	public void boldRow(int row) {
		/** COMPLETE THIS METHOD **/
		//bold the item in the td not the child of tr
		boldRecurse(root,root.firstChild,0,row,"tr");
		return;
	}
	
	/**
	 * Remove all occurrences of a tag from the DOM tree. If the tag is p, em, or b, all occurrences of the tag
	 * are removed. If the tag is ol or ul, then All occurrences of such a tag are removed from the tree, and, 
	 * in addition, all the li tags immediately under the removed tag are converted to p tags. 
	 * 
	 * @param tag Tag to be removed, can be p, em, b, ol, or ul
	 */
	public void removeTag(String tag) {
		/** COMPLETE THIS METHOD **/
		//boolean changeList=false;
		removeDirectory(tag);
		return;
	}
	
	/**
	 * Adds a tag around all occurrences of a word in the DOM tree.
	 * 
	 * @param word Word around which tag is to be added
	 * @param tag Tag to be added
	 */
	public void addTag(String word, String tag) {
		/** COMPLETE THIS METHOD **/
		word=word.toLowerCase();
		addRecurse(word,tag,root);//,0);
		return;
	}	
	
	/**
	 * Gets the HTML represented by this DOM tree. The returned string includes
	 * new lines, so that when it is printed, it will be identical to the
	 * input file from which the DOM tree was built.
	 * 
	 * @return HTML string, including new lines. 
	 */
	public String getHTML() {
		StringBuilder sb = new StringBuilder();
		getHTML(root, sb);
		return sb.toString();
	}
	
	private void getHTML(TagNode root, StringBuilder sb) {
		for (TagNode ptr=root; ptr != null;ptr=ptr.sibling) {
			if (ptr.firstChild == null) {
				sb.append(ptr.tag);
				sb.append("\n");
			} else {
				sb.append("<");
				sb.append(ptr.tag);
				sb.append(">\n");
				getHTML(ptr.firstChild, sb);
				sb.append("</");
				sb.append(ptr.tag);
				sb.append(">\n");	
			}
		}
	}
	
	private static void replaceRecurse(TagNode ptr,String old, String rep){
		if(ptr==null){
			return;
		}
		
		if(ptr.tag.equals(old)){
			ptr.tag=rep;
		}
		replaceRecurse(ptr.firstChild,old,rep);
		replaceRecurse(ptr.sibling,old,rep);
	}
	
	private static void boldRecurse(TagNode prev,TagNode ptr,int curr,int target,String row){
		if(ptr==null){
			return;
		}
				
		if(target==curr&&ptr.firstChild==null){
			prev.firstChild=new TagNode("b",ptr,null);
		}
		if(ptr.tag.equals(row)){
			boldRecurse(ptr,ptr.firstChild,curr+1,target,"tr");
			boldRecurse(ptr,ptr.sibling,curr+1,target,"tr");
		}else{
			boldRecurse(ptr,ptr.firstChild,curr,target,"tr");
			boldRecurse(ptr,ptr.sibling,curr,target,"tr");
		}
	}
	
	private static void addRecurse(String word, String tag, TagNode ptr){//,int count){
		
		if(ptr==null){
			return;
		}
		String oldTag;
		String left;
		String right;
		String nonc;
		TagNode sib = ptr.sibling;
		//System.out.println(count);
		if(ptr.tag.toLowerCase().contains(word)){			
			if(ptr.tag.equalsIgnoreCase(word)){
				oldTag=ptr.tag;
				ptr.tag=tag;
				ptr.firstChild=new TagNode(oldTag,ptr.firstChild,null);
			}else{
				left=ptr.tag.substring(0,ptr.tag.toLowerCase().indexOf(word));
				right=ptr.tag.substring(ptr.tag.toLowerCase().indexOf(word)+word.length());
				nonc="";
				//oldTag = ptr.tag.substring(ptr.tag.toLowerCase().indexOf(word), ptr.tag.toLowerCase().indexOf(word) + word.length());
				if(right.length()>0){
					if(right.length()>1&&(right.charAt(0)=='.'||right.charAt(0)==','||right.charAt(0)=='?'||right.charAt(0)=='!'||right.charAt(0)==';'||right.charAt(0)==':')&&!(right.charAt(1)=='.'||right.charAt(1)==','||right.charAt(1)=='?'||right.charAt(1)=='!'||right.charAt(1)==';'||right.charAt(1)==':')){
						nonc+=right.charAt(0);
						right=right.substring(1);
					}
				}
				if(right.length()==0||(right.length()>=1&&(right.charAt(0)==' '||(right.charAt(0)=='.'||right.charAt(0)==','||right.charAt(0)=='?'||right.charAt(0)=='!'||right.charAt(0)==';'||right.charAt(0)==':')))){
					if(right.equals("!")||right.equals(",")||right.equals(".")||right.equals("?")||right.equals(":")||right.equals(";")){
						word+=right;
						right="";
					}
					ptr.tag=left;
					ptr.sibling=new TagNode(tag,null,null);
					ptr.sibling.firstChild=new TagNode(word+nonc,null,null);
					if(right.length()>0){
						if(sib!=null)
							ptr.sibling.sibling= new TagNode(right,null,sib);
						else
							ptr.sibling.sibling=new TagNode(right,null,null);
					}else if(sib!=null){
						ptr.sibling.sibling=sib;
					} 
				} 
			}
			//System.out.println("got here");
			if(ptr.sibling!=null){
				addRecurse(word,tag,ptr.sibling.sibling);//,count+1);
			}else{
				//System.out.println("idk how");
				addRecurse(word,tag,ptr.sibling);//,count+1);
			}
			/*boolean adw=false;
			if(ptr.tag.toLowerCase().contains(word.toLowerCase())){
				if(ptr.tag.equalsIgnoreCase(word.toLowerCase())){
					ptr.firstChild=new TagNode(tag,ptr,null);
					ptr.tag=word;
				}else{
					String left=ptr.tag.substring(0,ptr.tag.toLowerCase().indexOf(word.toLowerCase()));
					String mid=ptr.tag.substring((left.length()),left.length()+word.length());//may need to add -1, -1
					String right=ptr.tag.substring(left.length()+mid.length(),ptr.tag.toLowerCase().indexOf(word.toLowerCase()+word.length()));
					
					if(right.length()>0){
						if(right.charAt(0)=='.'||right.charAt(0)==','||right.charAt(0)=='?'||right.charAt(0)=='!'||right.charAt(0)==':'||right.charAt(0)==';'){
							try{
								if(right.charAt(1)==' '){//may need to change this one
								
									mid+=right.charAt(0);
									right=right.substring(1);
									adw=true;
								}
							}catch(StringIndexOutOfBoundsException e){
								mid+=right.charAt(0);
								right="";
								adw=true;
							}
						}
					}
					
					if(right.length()==0 || (right.length()>=1 && (right.charAt(0)==' ' ||right.charAt(0)=='.'||right.charAt(0)==','||right.charAt(0)=='?'||right.charAt(0)=='!'||right.charAt(0)==':'||right.charAt(0)==';'))){
						
						if(right.equals(".") ||right.equals(",") ||right.equals("!") ||right.equals("?") ||right.equals(";") ||right.equals(":")){
							if (adw==false){
								mid=mid+right;
								right="";
							}
						}
						
						if(left.length()==0){//may need to change this
							ptr.tag=tag;
							ptr.firstChild=new TagNode(word,null,null);
							if(!(right.length()==0)){
								if(ptr.sibling==null){
									ptr.sibling=new TagNode(right,null,null);
								}else{
									ptr.sibling=new TagNode(right,null,ptr.sibling);//may need to change this
								}
								
							}
						}else{
							ptr.tag=left;
							if(ptr.sibling==null){
								ptr.sibling=new TagNode(tag,null,null);
								ptr.sibling.firstChild=new TagNode(word,null,null);
								if(!(right.length()==0)){
									ptr.sibling.sibling=new TagNode(right,null,null);
								}
							}else{
								ptr.sibling=new TagNode(tag,null,ptr.sibling);
								ptr.sibling.firstChild=new TagNode(word,null,null);
								if(!(right.length()==0)){
									ptr.sibling.sibling=new TagNode(right,null,ptr.sibling.sibling);
								}
								
							}
						}
					}
					
				}
				addRecurse(word,tag,ptr.sibling.sibling);
			}else{
				addRecurse(word,tag,ptr.firstChild);
				addRecurse(word,tag,ptr.sibling);
			}*/
		} else {
			addRecurse(word,tag,ptr.firstChild);//,count+1);
			addRecurse(word,tag,ptr.sibling);//,count+1);
		}	
	}
	
	private void removeDirectory(String tag){
		if(tag==null){
			return;
		}
		if(tag.equalsIgnoreCase("p")||tag.equalsIgnoreCase("em")||tag.equalsIgnoreCase("b")){
			removeRecurse(root,root.firstChild,tag);
		}else if(tag.equalsIgnoreCase("ol")||tag.equalsIgnoreCase("ul")){
			removeLists(root,root.firstChild,tag,"li");
		}
	}
	
	private static void removeRecurse(TagNode prev,TagNode ptr,String tag){
		if(ptr==null){
			return;
		}
		TagNode sibptr;
		if(prev.firstChild==ptr){
			if(ptr.tag.equalsIgnoreCase(tag)){
				if(ptr.sibling!=null){
					prev.firstChild=ptr.firstChild;
					if(ptr.firstChild.sibling!=null){
						sibptr=ptr.firstChild.sibling;
						while(sibptr.sibling!=null){
							sibptr=sibptr.sibling;
						}
						sibptr.sibling=ptr.sibling;
						removeRecurse(prev,ptr.firstChild,tag);
						//removeRecurse(prev,ptr.sibling,tag);
					}else{
						prev.firstChild.sibling=ptr.sibling;
						removeRecurse(prev,ptr.firstChild,tag);
						//removeRecurse(prev,ptr.sibling,tag);
					}
				}else{
					prev.firstChild=ptr.firstChild;
					removeRecurse(prev,ptr.firstChild,tag);
					//removeRecurse(prev,ptr.sibling,tag);
				}
			}else{
				removeRecurse(ptr,ptr.firstChild,tag);//why you give me stackoverflow?!?!?!?!?
				removeRecurse(ptr,ptr.sibling,tag);
			}
		}else{
			//System.out.println("i was right!!!!!!!!!!!!!");
			if(ptr.tag.equalsIgnoreCase(tag)){
				if(ptr.firstChild!=null){
					prev.sibling=ptr.firstChild;
					if(prev.sibling.sibling!=null){
						sibptr=ptr.firstChild;
						while(sibptr.sibling!=null){
							sibptr=sibptr.sibling;
						}
						sibptr.sibling=ptr.sibling;
						removeRecurse(prev,ptr.firstChild,tag);
					}else{
						prev.sibling.sibling=ptr.sibling;
						removeRecurse(prev,ptr.firstChild,tag);
					}
				}else{
					prev.sibling=ptr.sibling;
					removeRecurse(prev,ptr.sibling,tag);
				}
			}else{
				removeRecurse(prev,prev.firstChild,tag);
				removeRecurse(ptr,ptr.firstChild,tag);
				removeRecurse(ptr,ptr.sibling,tag);
			}
		}
	}
	
	private static void removeLists(TagNode prev,TagNode ptr,String tag,String list){
		if(ptr==null){
			return;
		}
		TagNode sibptr;
		TagNode delptr;
		if(prev.firstChild==ptr){
			if(ptr.tag.equalsIgnoreCase(tag)){
				sibptr=ptr.firstChild;
				while(sibptr!=null){
					if(sibptr.tag.equalsIgnoreCase(list)){
						sibptr.tag="p";
						//sibptr.firstChild=sibptr.firstChild.firstChild;
					}
					sibptr=sibptr.sibling;
				}
				if(ptr.sibling!=null){
					prev.firstChild=ptr.firstChild;
					if(ptr.firstChild.sibling!=null){
						sibptr=ptr.firstChild.sibling;
						while(sibptr.sibling!=null){
							sibptr=sibptr.sibling;
						}
						sibptr.sibling=ptr.sibling;
						removeLists(prev,ptr.firstChild,tag,"li");
						//removeRecurse(prev,ptr.sibling,tag);
					}else{
						prev.firstChild.sibling=ptr.sibling;
						removeLists(prev,ptr.firstChild,tag,"li");
						//removeRecurse(prev,ptr.sibling,tag);
					}
				}else{
					prev.firstChild=ptr.firstChild;
					removeLists(prev,ptr.firstChild,tag,"li");
					//removeRecurse(prev,ptr.sibling,tag);
				}
			}else{
				removeLists(ptr,ptr.firstChild,tag,"li");//why you give me stackoverflow?!?!?!?!?
				removeLists(ptr,ptr.sibling,tag,"li");
			}
		}else{
		//	System.out.println("i was right!!!!!!!!!!!!!");
			if(ptr.tag.equalsIgnoreCase(tag)){
				sibptr=ptr.firstChild;
				while(sibptr!=null){
					if(sibptr.tag.equalsIgnoreCase(list)){
						sibptr.tag="p";//sibptr.firstChild.tag;
						//sibptr.firstChild=sibptr.firstChild.firstChild;
					}
					sibptr=sibptr.sibling;
				}
				if(ptr.firstChild!=null){
					prev.sibling=ptr.firstChild;
					if(prev.sibling.sibling!=null){
						sibptr=ptr.firstChild;
						while(sibptr.sibling!=null){
							sibptr=sibptr.sibling;
						}
						sibptr.sibling=ptr.sibling;
						removeLists(prev,ptr.firstChild,tag,"li");
					}else{
						prev.sibling.sibling=ptr.sibling;
						removeLists(prev,ptr.firstChild,tag,"li");
					}
				}else{
					prev.sibling=ptr.sibling;
					removeLists(prev,ptr.sibling,tag,"li");
				}
			}else{
				removeLists(prev,prev.firstChild,tag,"li");
				removeLists(ptr,ptr.firstChild,tag,"li");
				removeLists(ptr,ptr.sibling,tag,"li");
			}
		}
	}
	
}
