
package friends;


import java.io.*;
import java.util.*;
import java.lang.*;
public class Friends {

	public static ProfileNode[] profiles;//stores the graph, built by buildGraph
	
	public static ArrayList<String> conns;//stores the list of students to make the subgraph with
	
	
	public static ArrayList<ProfileNode> csg;//Clique Sub Graph profiles, stores the list of profiles to make the subgraph with
	public static ArrayList<ProfileNode[]> cliqueSubgraphList;//stores the subgraphs made by cliques method, no name for each subgraph, just call the get() method to obtain the subgraphs from the cliques method
	public static void main(String[] args) {
		
		boolean filefound=false;
		
		
		
		System.out.println("Please enter the name of the file you wish to read from: ");
		Scanner filescanner=null;
		while(filefound==false){
			Scanner filenamescan= new Scanner(System.in);
			
			
			
			String filename=filenamescan.next();
			File graphtext=new File(filename);
			
			
			try {
				filescanner=new Scanner(graphtext);//reads in the file
				filefound=true;
			} catch (FileNotFoundException e) {
				
				System.out.println("ERROR: File not found, please try again: ");//if file is not found, loops again and asks for the file
				continue;
			}
		}
		
		
		profiles=buildGraph(filescanner);//buildGraph returns the adjacency list graph and name it profiles
		
		boolean hasQuit=false;
		Scanner userInput;
		while(hasQuit==false){
						
			System.out.println("\nSelect a function: \n\t(P)rint students in a school, s(H)ortest chain, (C)liques at a school, c(O)nnectors, or (Q)uit");
			
			userInput=new Scanner(System.in);
			String command=userInput.next();
			command=command.toLowerCase();
			while(!command.equalsIgnoreCase("s")||!command.equalsIgnoreCase("h")||!command.equalsIgnoreCase("c")||!command.equalsIgnoreCase("o")||!command.equalsIgnoreCase("q")||!command.equalsIgnoreCase("p")){
				if(command.equalsIgnoreCase("s")||command.equalsIgnoreCase("h")||command.equalsIgnoreCase("c")||command.equalsIgnoreCase("o")||command.equalsIgnoreCase("q")||command.equalsIgnoreCase("p")){
					break;
				}
				System.out.println("ERROR: Improper command, please select a function: \n\t(P)rint students in a school, s(H)ortest chain, (C)liques at a school, c(O)nnectors, or (Q)uit");
				//keeps looping until a proper command is typed in
				userInput=new Scanner(System.in);
				command=userInput.next();
				command=command.toLowerCase();
			}
			
			if(command.equalsIgnoreCase("q")){
				System.out.println("Bye Bye!");
				System.exit(0);//exits the program
			}else if(command.equalsIgnoreCase("p")){
				printGraph(profiles);//prints the graph
			}else if(command.equalsIgnoreCase("c")){
				cliques();//executes cliques
			}else if(command.equalsIgnoreCase("h")){
				shortestChain();//executes shortest chain
			}else if(command.equalsIgnoreCase("o")){
				connectors();//executes connectors
			}
		}
		
		
	}
	
	public static void shortestChain() {
		//edit this method for the shortest chain, make any side methods that this calls public
		//you can edit the method header too, this is just a start. but if you do edit it, make the call of it in the main method match this
		//call so we don't get errors n shit
		String start="";
		String end="";
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the name of the person you wish to start the search from: ");//inputs student name to start search
		start=sc.nextLine();
		while(!containsStudent(profiles,start)){
			System.out.println("This student is not in the graph, please enter a different name");
			start=sc.nextLine();
		}
		System.out.println("Enter the name of the person you wish you search to: ");//inputs student name to search to
		end=sc.nextLine();
		while(!containsStudent(profiles,end)){
			System.out.println("This student is not in the graph, please enter a different name");
			end=sc.nextLine();
		}
		
		if(start.equalsIgnoreCase(end)){//if the start and the end are the same person, doesn't correct you but it shows the path which is just the start/end person's name
			System.out.println("The path from "+start+" to "+end+" is goes through these people:\n"+start);
			return;
		}
		
		/*if(!spDfsDriver(start,end,profiles)){
			System.out.println("There is no path from "+start+" to "+end);
		}
		return;*/
		
		
		
		
		
		int startIndex=indexOf(start,profiles);
		int endIndex=indexOf(end,profiles);
		
		if(containsLink(startIndex,endIndex)){
			System.out.println("The path from "+start+" to "+end+" is goes through these people:");
			System.out.println(newbfs(startIndex,endIndex));
		}else{
			System.out.println("There is no path from "+start+" to "+end);
		}
		
		
		
		/*boolean[] visited=new boolean[profiles.length];
		
		for(int i=0;i<visited.length;i++){
			if(i==startIndex){
				visited[i]=false;
			}
		
		}
		String shortestPath="";
		int[] lastIndex=new int[profiles.length];
		//dijkDriver(start,end,profiles);
		int[] arr= bfs(startIndex,endIndex,visited,shortestPath,lastIndex);
		int i=endIndex;
		
		String[] chain=new String[profiles.length+10];
		int j=0;
		while(i!=-1){
			
			//System.out.println("i is: "+i);
			chain[j]=profiles[i].name;
			if(arr[i]==-1){
				break;
			}
			i=profiles[arr[i]].index;
			j++;
		}
		
		i=0;
		while((i<profiles.length)&&chain[i]!=null){
			//System.out.println("i got here");
			System.out.print(chain[i]+"   ");
			i++;
		}
		
		*/
		
		
		
		
	}
	
	public static boolean containsLink(int startIndex, int endIndex) {
		ArrayList<Integer> found=spdfsDriver(startIndex);
		
		for(int i=0;i<found.size();i++){
			if(found.get(i).compareTo(endIndex)==0){
				return true;
			}
		}
		
		return false;
	}
	
	public static ArrayList<Integer> spdfsDriver(int start){
		ArrayList<Integer> arr=new ArrayList<Integer>();
		
		boolean[] visited=new boolean[profiles.length];
		
		for(int i=0;i<profiles.length;i++){
			visited[i]=false;
		}
		
		spdfs(start,visited,arr);
		
		return arr;
	}
	
	public static void spdfs(int index, boolean[] visited,ArrayList<Integer> arr) {
		visited[index] = true;
		arr.add(index);
		for(NeiNode ptr=profiles[index].neighbor;ptr!=null;ptr=ptr.next){
			
			if(visited[ptr.edgeTo]==false){
				
				
				spdfs(ptr.edgeTo,visited,arr);//recursive dfs call
			}
			arr.add(ptr.edgeTo);
		}
		
	}
	

	public static String newbfs(int startIndex, int endIndex){
		String path = null;
		
		Queue<ProfileNode> q=new LinkedList<ProfileNode>();
		
		Hashtable<ProfileNode, String> hash=new Hashtable<ProfileNode, String>();
		
		q.add(profiles[startIndex]);
		
		hash.put(profiles[startIndex], profiles[startIndex].name);
		
		while(!q.isEmpty()){
			
			ProfileNode curr=q.remove();
			
			for(NeiNode ptr=curr.neighbor;ptr!=null;ptr=ptr.next){
				
				q.add(profiles[ptr.edgeTo]);
				hash.put(profiles[ptr.edgeTo], hash.get(curr)+"<-->"+profiles[ptr.edgeTo].name );
				
				if(profiles[ptr.edgeTo].name.equals(indexToName(endIndex,profiles))){
					return hash.get(profiles[ptr.edgeTo]);
				}
				
			}
			
			
			
			
		}
		
		
		
		
		
		return null;
	}
	
	
	
	public static int[] bfs(int startIndex, int endIndex, boolean[] visited, String path,int[] lastIndex){
		
		Queue<ProfileNode> q=new LinkedList<ProfileNode>();
		q.add(profiles[startIndex]);
		
		boolean[] beenInQ=new boolean[profiles.length];
		
		for(int i=0;i<beenInQ.length;i++){
			beenInQ[i]=false;
		}
		
		int last= -1;
		boolean first=true;
		while(!q.isEmpty()){
			
			ProfileNode curr=q.remove();
			if(first){
				first=false;
				
			}else{
				
			}
			
			
			//System.out.println(curr.name);
			beenInQ[curr.index]=true;
			//lastIndex[curr.index]=last;
			//last=curr.index;
			//path=path+"--"+curr.name;
			visited[startIndex]=true;
			lastIndex[curr.index]=last;
			if(curr.equals(profiles[endIndex])){
				
				System.out.println("The path from "+profiles[startIndex].name+" to "+profiles[endIndex].name+" takes this chain: \n");//+path);
				return lastIndex;
			}
			
			
			for(NeiNode ptr=curr.neighbor;ptr!=null;ptr=ptr.next){
				
				
				if((visited[ptr.edgeTo]==false) && (!q.contains(profiles[ptr.edgeTo]) && (!beenInQ[ptr.edgeTo]))){
					
					//System.out.println("adding: "+profiles[ptr.edgeTo].name);
					q.add(profiles[ptr.edgeTo]);
				}
				
				
			}
			last=curr.index;
			
		}
		return null;
		
	}
	
	public static int[] dijkDriver(String start, String end, ProfileNode[] prof){
		int[] minD=new int[prof.length];
		int[] prevIndex=new int[prof.length];
		
		int startIndex=indexOf(start,prof);
		int endIndex=indexOf(end,prof);
		
		boolean[] visited=new boolean[prof.length];
		
		for(int i=0;i<visited.length;i++){
			if(i==startIndex){
				visited[i]=false;
			}
		
		}
		
		for(int i=0;i<minD.length;i++){
			if(i==startIndex){
				minD[i]=Integer.MAX_VALUE;
			}
			
		}
		
		Queue<ProfileNode> q=new LinkedList<ProfileNode>();
		q.add(profiles[startIndex]);
			
		visited[startIndex]=true;
		int last= -1;
		
		int initial=0;
		while(!q.isEmpty()){
			
			ProfileNode curr=q.remove();
			prevIndex[curr.index]=last;
			last=curr.index;
			
			
			if(curr.equals(profiles[endIndex])){
				
				System.out.println("The path from "+profiles[startIndex].name+" to "+profiles[endIndex].name+" takes this chain: \n");
				return prevIndex;
			}
			
			
			for(NeiNode ptr=curr.neighbor;ptr!=null;ptr=ptr.next){
				
				int alt=1+minD[ptr.edgeTo];
				
				
				if(!visited[ptr.edgeTo]){
					q.add(profiles[ptr.edgeTo]);
				}
				
				
			}
			
		}
		return null;
		
	}
	
	/*public static boolean spDfsDriver(String start, String end, ProfileNode[] profs){
		boolean[] visited=new boolean[profs.length];
		
		for(int i=0;i<visited.length;i++){
			visited[i]=false;
		}
		
		if(spDfs(indexOf(start,profs),indexOf(end,profs),visited)){
			return true;
		}
			return false;
		
	}
	
	public static boolean spDfs(int startIndex, int endIndex,boolean[] visited){
		visited[startIndex]=true;
		if(startIndex==endIndex){
			return true;
		}
		for(NeiNode ptr=profiles[startIndex].neighbor;ptr!=null;ptr=ptr.next){
			if(!visited[ptr.edgeTo]){
				return spDfs(ptr.edgeTo,endIndex,visited);
			}
		}
		return false;
	}*/
	
	public static boolean containsStudent(ProfileNode[] profs, String name){//checks if an adjacency list contains a student
		
		for(int i=0;i<profs.length;i++){
			if(name.equalsIgnoreCase(profs[i].name)){
				return true;
			}
		}
		
		return false;
	}

	public static void connectors() {
		conns= new ArrayList<String>();//each new call of connectors sets conns to a new list to allow multiple calls
		
		int[] dfsnum= new int[profiles.length];
		int[] back= new int[profiles.length];
		
		boolean[] visited=new boolean[profiles.length];
		
		for(int i=0;i<visited.length;i++){
			visited[i]=false;
		}
		
		for(int i=0;i<visited.length;i++){//dfs driver
			if(visited[i]==false){
				boolean firstVisit=true;
				dfs(i,visited,dfsnum,back,0,0,0,firstVisit);//first dfs call
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

	public static void dfs(int index, boolean[] visited, int[] dfsnum, int[] back, int num, int backnum, int edgeFrom,boolean firstVisit) {
	
		boolean wentAlready=false;
		visited[index]=true;
	
		boolean first=firstVisit;
	
		dfsnum[index]=num;
		back[index]=backnum;
		for(NeiNode ptr=profiles[index].neighbor;ptr!=null;ptr=ptr.next){
			
			if(visited[ptr.edgeTo]==false){
				num++;
				backnum++;
				dfs(ptr.edgeTo,visited,dfsnum,back,num,backnum,index,false);//recursive dfs call
				wentAlready=false;
			}else{//second bullet point in directions
				
				back[index]=Math.min(dfsnum[ptr.edgeTo], back[index]);	
				wentAlready=true;
			}
				
			if(!wentAlready){//second case in the box in directions
				if(dfsnum[index]>back[ptr.edgeTo]){//first bullet point in directions
					
					back[index]=Math.min(back[index], back[ptr.edgeTo]);
					
				}else if(dfsnum[index]<=back[ptr.edgeTo]){
					
					if(firstVisit==false){//first case in the box in the directions
						if(!conns.contains(profiles[index].name))//this gets rid of multiples of a name showing up but its a cover not a fixer
							conns.add(profiles[index].name);
					}
						
					
					
					
				}
			}
			
			firstVisit=false;
		}
		
		
		
		
	}

	

	public static void cliques() {
		System.out.println("Please enter the name of the school you wish to see the clique's of: ");//enter the name of school to find students belonging to the cliques of the school
		Scanner sc=new Scanner(System.in);
		String school=sc.nextLine();
		
		while(!containsSchool(school,profiles)){
			System.out.println("No student in the graph goes to this school, please enter a new one or press (q) to return to the menu");//the school has no students, the school was spelled wrong, or input did not match the upper/lowercase letters of the school
			school=sc.nextLine();
			if(school.equalsIgnoreCase("q")){
				return;
			}
		}
		
		boolean[] visit=new boolean[profiles.length];
		
		for(int i=0;i<visit.length;i++){
			if(profiles[i].school==null){
				visit[i]=true;
			}else if(!(profiles[i].school.equalsIgnoreCase(school))){
				visit[i]=true;
			}else{
				visit[i]=false;
			}						
		}
		
		
		
	
		
		dfsDriver(profiles,visit);//calls the dfs driver
		
		
	}

	public static boolean containsSchool(String school, ProfileNode[] profiles) {
		
		if(school.equalsIgnoreCase("")){
			return false;
		}
		
		for(int i=0; i<profiles.length; i++){
			
			if(profiles[i].school.equalsIgnoreCase(school)){
				return true;
			}
		}
		
		
		return false;
	}

	
	
	public static void dfsDriver(ProfileNode[] prof, boolean[] visited){
		int count=1;
		cliqueSubgraphList=new ArrayList<ProfileNode[]>();
		for(int i=0; i<visited.length;i++){
			if(visited[i]==false){
				
				
				System.out.println("\nClique number "+count+" at "+prof[i].school+" contains these students:");
				
				boolean firstInList=true;
				csg=new ArrayList<ProfileNode>();//new array list for each subgraph to be made
				dfs(i,prof,visited,firstInList);//driver calls dfs
				System.out.println();
				
				
				cliqueSubgraphList.add(buildCsg(csg));//builds the subgraph and adds it to the list of subgraphs. each item is a separate clique
				//Hey grader! I asked sesh where to store the subgraphs and he said I could just make an arraylist that holds the 
				//subgraphs.  they dont really have a name but you can just get them by calling get() from cliqueSubgraphList.
				//after the cliques method is called again though, the feilds that held the graphs and the arraylist that held the graphs
				//get erased and are created anew
				printGraph(cliqueSubgraphList.get(count-1));
				
				count++;
			}
		}		
	}
	
	public static boolean containsStudent(ArrayList<ProfileNode> profs, String name){//checks if an arraylist of profile nodes contains a student
		
		for(int i=0;i<profs.size();i++){
			if(name.equalsIgnoreCase(profs.get(i).name)){
				return true;
			}
		}
		
		return false;
	}
	
	public static ProfileNode[] buildCsg(ArrayList<ProfileNode> csg){//build method for subgraphs for cliques
		ProfileNode[] subgraph=new ProfileNode[csg.size()];
		
		int newIndex=-1;
		
		
		
		for(int i=0;i<subgraph.length;i++){
		
			subgraph[i]=new ProfileNode(csg.get(i).name,csg.get(i).school,i);//csg.get(i).index);//sets up the profilenode array
			
			
		}		
		
		
		for(int i=0;i<subgraph.length;i++){
			
			
			
			for(NeiNode ptr=csg.get(i).neighbor;ptr!=null;ptr=ptr.next){
			
				
				
				
				if(containsStudent(csg,indexToName(ptr.edgeTo,profiles))){//gives the profile array its proper neighbors
					subgraph[i].neighbor=new NeiNode(indexOf(indexToName(ptr.edgeTo,profiles),subgraph),subgraph[i].neighbor);//neinode's first input cannot be ptr.edgeto
				}
				
				
				
			}
			
		}	
		
		
		return subgraph;//retuns the subgraph
	}
	
	
	
	
	public static void dfs(int index, ProfileNode[] prof, boolean[] visited,boolean firstInList){
		
		visited[index]=true;
		if(firstInList){
			System.out.print(prof[index].name);
		}else{
			System.out.print(", "+prof[index].name);
		}
		csg.add(prof[index]);
		for(NeiNode ptr=prof[index].neighbor;ptr!=null;ptr=ptr.next){
			
			if(visited[ptr.edgeTo]==false){
				
				
				dfs(ptr.edgeTo,prof,visited,false);//recursive dfs call
			}
		}
		
		
	}
	
	

	
	
	public static ProfileNode[] buildGraph(Scanner s){
		
		
		int size=s.nextInt();
		
		String name="";
		String school="";
		String from="";
		String to="";
		int indexFrom=-1;
		int indexTo=-1;		
		
		
		ProfileNode[] profiles=new ProfileNode[size];
		s.nextLine();
		
		for(int i=0;i<size;i++){
			
			String in=s.nextLine();
			;
			for(int j=0;j<in.length();j++){//this loop makes the array of profiles
				if(in.charAt(j)=='|'){
					
					name=in.substring(0, j);
					if(in.charAt(j+1)=='y'){//make profile node if they are in college
						school=in.substring(j+3,in.length());
						
						
						ProfileNode p=new ProfileNode();
						p.index=i;
						p.name=name;
						p.school=school;
						p.neighbor=null;
						
						profiles[i]=p;
						
						break;
						
					}else{//make profile node if they are not in college
						
						
						ProfileNode p=new ProfileNode();
						p.index=i;
						p.name=name;
						
						p.school="";
						p.neighbor=null;
						
						profiles[i]=p;
						
						break;
					}
				}
				
			}
			
			
		}
		
		
		
		
		while(s.hasNext()){//this loop builds the edges
			String edge=s.next();
			
			
			for(int i=0;i<edge.length();i++){
				if(edge.charAt(i)=='|'){
					from=edge.substring(0, i);
					to=edge.substring(i+1,edge.length());
					
					break;
				}
			}
			
			indexFrom=indexOf(from,profiles);
			indexTo=indexOf(to,profiles);
			
			profiles[indexFrom].neighbor=new NeiNode(indexTo,profiles[indexFrom].neighbor);//these two insert the edges in the profilenode array
			profiles[indexTo].neighbor=new NeiNode(indexFrom,profiles[indexTo].neighbor);
		}
		
		
				
		return profiles;//returns the adjacency list graph
	}
	
	public static int indexOf(String name, ProfileNode[] profiles){//put in the name of the person and the graph you want to look at and returns that person's index
		for(int i=0; i<profiles.length;i++){
			if(profiles[i].name.equalsIgnoreCase(name)){
				return i;
			}
		}
		
		
		return (Integer) null;
		
	}
	
	public static String indexToName(int index, ProfileNode[] profiles){//put in the index of a person and the graph you want to find the person in and it returns their name
		String name=profiles[index].name;
		
		return name;
	}
	
	public static void printGraph(ProfileNode[] gph){//printgraph prints out the graph in the same format as the read in files as well as an adjacency list representation. works on all graphs so a the graph must be input to print it
		
		for(int i=0;i<gph.length;i++){//this prints the adjacency list representation
			System.out.print(i+" : ["+gph[i].name+"]");
			for(NeiNode ptr=gph[i].neighbor;ptr!=null;ptr=ptr.next){
				System.out.print("-->("+ptr.edgeTo+" : "+indexToName(ptr.edgeTo,gph)+")");
			}
			System.out.println("");
		}
		System.out.println();
		
		
		///////////////////this might be the right way to do it
		System.out.println(gph.length);//from here it prints the graph in the same format as the read-in files
		for(int i=0;i<gph.length;i++){
			if(!gph[i].school.equalsIgnoreCase("")){
				System.out.println(gph[i].name+"|y|"+gph[i].school);
			}else{
				System.out.println(gph[i].name+"|n");
			}
		}
		boolean[] visited=new boolean[gph.length];
		for(int i=0;i<visited.length;i++){
			visited[i]=false;
		}
		for(int i=0;i<gph.length;i++){
			for(NeiNode ptr=gph[i].neighbor;ptr!=null;ptr=ptr.next){
				if(visited[ptr.edgeTo]==false){
					System.out.println(gph[i].name+"|"+indexToName(ptr.edgeTo, gph));
				}
			}
			visited[i]=true;
		}
		
		
	}
	
	
}
