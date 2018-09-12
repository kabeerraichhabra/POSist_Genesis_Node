package POSist_Genesis_Node;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.TimeZone;

class node {
	String TimeStamp; 
	String data;  
	int NodeNumber; 
	node NodeId;  
	node RefNodeId; 
	ArrayList<node> ChildRefNodeId; 
	node GenesisRefNodeId;  
	String HashVal; 

	public node(int nodeNumber) {
		this.NodeNumber = nodeNumber;
	}
}

public class Posist {

	public static void main(String[] args) {

	}
	
	public static node createGenesisNode() {

		Scanner s = new Scanner(System.in);

		System.out.println("Please Enter The Root: ");
		int rootData = s.nextInt();
		node genesisNode = new node(rootData);

		Queue<node> pendingNodes = new LinkedList<node>();
		pendingNodes.add(genesisNode);

		int sum = 0;
		while (!pendingNodes.isEmpty()) {
			node currentNode = pendingNodes.poll();

			System.out.println("please enter the children of: " + currentNode.data);
			int children = s.nextInt();

			for (int i = 1; i <= children; i++) {
				System.out.println("please Enter " + i + "th child of " + currentNode.data);
				int childData = s.nextInt();
				sum += childData;
				node child = new node(childData);

				if (sum < rootData) {
					currentNode.ChildRefNodeId.add(child);
					pendingNodes.add(child);
				}
			}
		}

		return genesisNode;

	}

		public static node takeInputOfChildNodesOfParticularNode(node root) {

		Scanner s = new Scanner(System.in);

		Queue<node> pendingNodes = new LinkedList<node>();
		pendingNodes.add(root);

		while (!pendingNodes.isEmpty()) {
			node currentNode = pendingNodes.poll();

			System.out.println("Enter the children of: " + currentNode.data);
			int children = s.nextInt();

			int sum = 0;
			for (int i = 1; i <= children; i++) {
				System.out.println("Enter " + i + "th child of " + currentNode.data);
				int childData = s.nextInt();
				sum += childData;
				node child = new node(childData);

				if (sum < root.NodeNumber) {
					currentNode.ChildRefNodeId.add(child);
					pendingNodes.add(child);
				}
			}
		}
		return root;
	}
	
	public static node createChildNode(node root){
		Scanner s=new Scanner(System.in);
		
		System.out.println("Enter the childNode:");
		int childNode=s.nextInt();
		
		int maxValue=root.NodeNumber;
		
		int sumOfChildren=childNode;
		for(int i=0;i<root.ChildRefNodeId.size();i++){
			sumOfChildren += root.ChildRefNodeId.get(i).NodeNumber;
		}
		
		if(sumOfChildren < maxValue){
			ArrayList<node> get=root.ChildRefNodeId;
			get.add(new node(childNode));
		}
		
		return root;
	}
	
	public static String encrpytByOwnerID(String data,String ownerID){
		int getHashCode=data.hashCode();
		return getHashCode+ownerID;
	}
	
	public static String encrpytByValue(String data,int value){
		int getHashCode=data.hashCode();
		return getHashCode+value+data;
	}
	
	public static String encrpytByOwnerName(String data,String ownerName){
		int getHashCode=data.hashCode();
		return getHashCode+ownerName;
	}
	
		public static int longestChainInGenesisNode(node genesisNode){
		if(genesisNode==null){
			return 0;
		}
		
		int maxLengthTillNow=Integer.MIN_VALUE;
		for(node child:genesisNode.ChildRefNodeId){
			int smallAns=longestChainInGenesisNode(child);
			if(smallAns>maxLengthTillNow){
				maxLengthTillNow=smallAns;
			}
		}
		
		return maxLengthTillNow+1;
	}
	
        public static int longestChainInAnyNode(node particularNode){
		if(particularNode==null){
			return 0;
		} 
		
		int maxLengthTillNow=Integer.MIN_VALUE;
		for(node child:particularNode.ChildRefNodeId){
			int smallAns=longestChainInGenesisNode(child);
			if(smallAns>maxLengthTillNow){
				maxLengthTillNow=smallAns;
			}
		}
		
		return maxLengthTillNow+1;
	}

}
