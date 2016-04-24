package friends;

public class NeiNode {

	//String name;
	int edgeTo;
	NeiNode next;
	final int weight=1;
	
	public NeiNode(int edgeTo, NeiNode next){
		this.edgeTo=edgeTo;
		this.next=next;
		
	}
	
}
