package friends;

public class ProfileNode {
	public String name;
	public String school;
	public int index;
	public int minDistance=Integer.MAX_VALUE;
	public NeiNode neighbor;
	
	public ProfileNode(){
		
	}
	public ProfileNode(String name, String school,int index){
		this.name=name.toLowerCase();
		this.school=school.toLowerCase();
		this.index=index;
		this.neighbor=null;
		
	}
}
