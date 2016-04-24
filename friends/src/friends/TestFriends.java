package friends;
import java.io.*;
import java.util.*;
import java.lang.*;
public class TestFriends {

	public static ProfileNode[] profiles;
	
	public static ArrayList<String> conns;//=new ArrayList<String>();//this is so you can call the method multiple times
	//in hindsight i probably dont need to do this but im too lazy to change it now
	
	//public static int[] dfsnum, back;//maybe unnote this, idk how using the arrays in the dfs method called will work without field
	public static int num=1;
	public static int backnum=1;
	public static void main(String[] args) {
		
		boolean filefound=false;
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//RYAN READ THIS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//ok see that field profiles up there? DO NOT CHANGE THAT SHIT!!! That is the adjacency list that has all the students/friends 
		//in it. you can use it in your methods, but dont change it, any of its values, or any of the pointers. ty bb
		
		System.out.println("Please enter the name of the file you wish to read from: ");
		Scanner filescanner=null;
		while(filefound==false){
			Scanner filenamescan= new Scanner(System.in);
			
			
			
			String filename=filenamescan.next();
			File graphtext=new File(filename);
			
			
			try {
				filescanner=new Scanner(graphtext);
				filefound=true;
			} catch (FileNotFoundException e) {
				
				System.out.println("ERROR: File not found, please try again: ");
				continue;
			}
		}
		
		
		profiles=buildGraph(filescanner);
		
		boolean hasQuit=false;
		Scanner userInput;
		while(hasQuit==false){
						
			System.out.println("Select a function: \n\t(P)rint students in a school, s(H)ortest chain, (C)liques at a school, c(O)nnectors, or (Q)uit");
			
			userInput=new Scanner(System.in);
			String command=userInput.next();
			command=command.toLowerCase();
			while(!command.equals("s")||!command.equals("h")||!command.equals("c")||!command.equals("o")||!command.equals("q")||!command.equals("p")){
				if(command.equals("s")||command.equals("h")||command.equals("c")||command.equals("o")||command.equals("q")||command.equals("p")){
					break;
				}
				System.out.println("ERROR: Improper command, please select a function: \n\t(P)rint students in a school, s(H)ortest chain, (C)liques at a school, c(O)nnectors, or (Q)uit");
				userInput=new Scanner(System.in);
				command=userInput.next();
				command=command.toLowerCase();
			}
			//println("i got: "+command);
			if(command.equalsIgnoreCase("q")){
				System.out.println("Bye Bye!");
				System.exit(0);
			}else if(command.equals("p")){
				printGraph(profiles);
			}else if(command.equals("c")){
				Cliques();
			}else if(command.equals("h")){
				shortestChain();
			}else if(command.equals("o")){
				connectors();
			}
		}
		
		
	}

	public static void connectors() {
		conns= new ArrayList<String>();
		
		int[] dfsnum= new int[profiles.length];
		int[] back= new int[profiles.length];
		
		boolean[] visited=new boolean[profiles.length];
		
		for(int i=0;i<visited.length;i++){
			visited[i]=false;
		}
		
		for(int i=0;i<visited.length;i++){
			if(visited[i]==false){
				boolean firstVisit=true;
				dfs(i,visited,dfsnum,back,0,firstVisit);
			}
		}
		
		
		
		
		System.out.println("The connectors are: ");
		boolean isfirst=true;
		for(String s:conns){
			if(isfirst){
				System.out.print(s);
				isfirst=false;
			}else{
				System.out.print(", "+s);
			}
		}
		System.out.println();
	}

	private static void dfs(int index, boolean[] visited, int[] dfsnum, int[] back, int edgeFrom,boolean firstVisit) {
		//implement dfs and do things with it
		//may have to implement 2 more arrays for dfsnum and back like in the project details
		int original;
		boolean wentAlready=false;
		visited[index]=true;
		///////////////test///////////////
		//for testing puposes
		String name=profiles[index].name;
		
		if(firstVisit){
			System.out.println("DFS visited "+profiles[index].name+" first this time");
			//visited[index]=false;//may be onto something here
		}
		//////////////////////////////////
		boolean first=firstVisit;
		//firstVisit=false;
		dfsnum[index]=num;
		back[index]=backnum;
		for(NeiNode ptr=profiles[index].neighbor;ptr!=null;ptr=ptr.next){
			System.out.println("currently at "+num+" : "+backnum+"("+profiles[index].name+")");
			if(visited[ptr.edgeTo]==false){
				
				num++;
				backnum++;
				
				System.out.println("going to "+num+" : "+backnum+"("+profiles[ptr.edgeTo].name+")");
				dfs(ptr.edgeTo,visited,dfsnum,back,index,false);
				wentAlready=false;
			}else{//second bullet point in directions
				wentAlready=true;
				original=back[index];
				back[index]=Math.min(dfsnum[ptr.edgeTo], back[index]);	
				if(original!=back[index]){
					System.out.println("back(v) changed to: "+back[index]+" (bullet 2)");
				}
			}
			
			///////////////////////////test/////////////////////////////
			if(profiles[index].name.equals("aparna")){
				System.out.println("She is currently v");
				System.out.println(firstVisit);
			}
			if(profiles[ptr.edgeTo].equals("aparna"))
				System.out.println("She is now w");

			////////////////////////////////////////////////////////////
			if(!wentAlready){
				if(dfsnum[index]>back[ptr.edgeTo]){//first bullet point in directions
					original=back[index];
					back[index]=Math.min(back[index], back[ptr.edgeTo]);
					if(original!=back[index]){
						System.out.println("back(v) changed to: "+back[index]+" (bullet 1)");
					}
				}else if(dfsnum[index]<=back[ptr.edgeTo]){
					
					if(firstVisit==false){//first case in the box in the directions
						//if(!conns.contains(profiles[index].name))//this gets rid of multiples of a name showing up but its a cover not a fixer
						conns.add(profiles[index].name);
					}else {//second case in the box in direction, maybe change this to an else if when i figure out what condition to put
						
					}
					
					
				}
			}
			/*if(dfsnum[index]<=back[ptr.edgeTo]){
				if(!conns.contains(profiles[index].name)){//second case in the box in direction, maybe change this to an else if when i figure out what condition to put
					conns.add(profiles[index].name);
				}
			}*/
			
			//////maybe this will work
			firstVisit=false;
		}
		
		
		
		
	}

	public static void shortestChain() {
		//edit this method for the shortest chain, make any side methods that this calls private
		//you can edit the method header too, this is just a start. but if you do edit it, make the call of it in the main method match this
		//call so we dont get errors n shit
		
	}

	public static void Cliques() {
		System.out.println("Please enter the name of the school you wish to see the clique's of: ");
		Scanner sc=new Scanner(System.in);
		String school=sc.nextLine();
		
		while(!containsSchool(school,profiles)){
			System.out.println("No student in the graph goes to this school, please enter a new one");
			school=sc.nextLine();
		}
		
		boolean[] visit=new boolean[profiles.length];
		
		for(int i=0;i<visit.length;i++){
			if(profiles[i].school==null){
				visit[i]=true;
			}else if(!(profiles[i].school.equals(school))){
				visit[i]=true;
			}else{
				visit[i]=false;
			}						
		}
		
		
		
		
		/*for(boolean b : visit){
			System.out.print("["+b+"]");
		}
		System.out.println();*/
		/*ProfileNode[] cliqGrapf;//=buildCliqueGraph(cliqGrapf);*/		
		
		dfsDriver(profiles,visit);
		
		
	}

	private static boolean containsSchool(String school, ProfileNode[] profiles) {
		
		
		for(int i=0; i<profiles.length; i++){
			
			if(profiles[i].school.equals("")){
				return false;
			}
			
			if(profiles[i].school.equals(school)){
				return true;
			}
		}
		
		
		return false;
	}

	public static void dfsDriver(ProfileNode[] prof, boolean[] visited){
		int count=1;
		for(int i=0; i<visited.length;i++){
			if(visited[i]==false){
				//call the dfs on here
				//count++;
				
				System.out.println("Clique number "+count+" at "+prof[i].school+" contains these students:");
				count++;
				boolean firstInList=true;
				dfs(i,prof,visited,firstInList);
				System.out.println();
			}
		}		
	}
	
	private static void dfs(int index, ProfileNode[] prof, boolean[] visited,boolean firstInList){
		
		visited[index]=true;
		if(firstInList){
			System.out.print(prof[index].name);
		}else{
			System.out.print(", "+prof[index].name);
		}
		for(NeiNode ptr=prof[index].neighbor;ptr!=null;ptr=ptr.next){
			//System.out.print(prof[index].name+", ");
			if(visited[ptr.edgeTo]==false){
				
				//System.out.print(prof[index].name+", ");
				dfs(ptr.edgeTo,prof,visited,false);
			}
		}
		
		
	}
	
	

	
	
	public static ProfileNode[] buildGraph(Scanner s){
		//maybe change return statement of the method to an array for the adjlist
		//also make a Node class
		//take in the number of "profiles" and make the initial array off of that size
		//fill the array with information of the profiles
		//either make it an array of all nodes or an array of profile nodes and the adj list will contain neighbor nodes
		//finally start building the graph and make the friend connections (undirected graph) then exit the method
		int size=s.nextInt();
		
		String name="";
		String school="";
		String from="";
		String to="";
		int indexFrom=-1;
		int indexTo=-1;		
		
		//boolean inschool=false;
		ProfileNode[] profiles=new ProfileNode[size];
		s.nextLine();
		
		for(int i=0;i<size;i++){
			
			String in=s.nextLine();
			//println(in);
			for(int j=0;j<in.length();j++){//this loop makes the array of profiles
				if(in.charAt(j)=='|'){
					
					name=in.substring(0, j);
					if(in.charAt(j+1)=='y'){
						school=in.substring(j+3,in.length());
						//inschool=true;
						
						ProfileNode p=new ProfileNode();
						p.index=i;
						p.name=name;
						p.school=school;
						p.neighbor=null;//may not need this one, it may cause problems in later methods
						
						profiles[i]=p;
						
						break;
						//make profile node here with college and break
					}else{
						//make profile node here without college and break
						//inschool=false;
						
						ProfileNode p=new ProfileNode();
						p.index=i;
						p.name=name;
						////////////////////////////////////////////////////
						p.school="";//this was previously null, but I changed this so i could stop getting null pointer exceptions lol
						//////////////////////////////////////////////////////
						p.neighbor=null;//may not need this one, it may cause problems in later methods
						
						profiles[i]=p;
						
						break;
					}
				}
				
			}
			/*println("NAME: "+name);
			if(inschool){
				println("SCHOOL: "+school);
			}*/
			
		}
		
		
		
		
		while(s.hasNext()){//this loop builds the edges
			String edge=s.next();
			//println(edge);
			
			for(int i=0;i<edge.length();i++){
				if(edge.charAt(i)=='|'){
					from=edge.substring(0, i);
					to=edge.substring(i+1,edge.length());
					//println(from+" : "+to);
					break;
				}
			}
			
			indexFrom=indexOf(from,profiles);
			indexTo=indexOf(to,profiles);
			
			profiles[indexFrom].neighbor=new NeiNode(indexTo,profiles[indexFrom].neighbor);
			profiles[indexTo].neighbor=new NeiNode(indexFrom,profiles[indexTo].neighbor);
		}
		
		
				
		return profiles;
	}
	
	private static int indexOf(String name, ProfileNode[] profiles){
		for(int i=0; i<profiles.length;i++){
			if(profiles[i].name.equals(name)){
				return i;
			}
		}
		
		
		return (Integer) null;
		
	}
	
	private static String indexToName(int index, ProfileNode[] profiles){
		String name=profiles[index].name;
		
		return name;
	}
	
	private static void printGraph(ProfileNode[] gph){
		
		for(int i=0;i<gph.length;i++){
			System.out.print(i+" : ["+gph[i].name+"]");
			for(NeiNode ptr=gph[i].neighbor;ptr!=null;ptr=ptr.next){
				System.out.print("-->("+ptr.edgeTo+" : "+indexToName(ptr.edgeTo,gph)+")");
			}
			System.out.println("");
		}
		
	}
}
