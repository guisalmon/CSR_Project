package implementation;

import interfaces.Node;

import java.io.Serializable;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;

public class NodeImpl implements Node {
	private Node mPredecessor;
	private Node mSuccessor;
	private int mId;
	private Map<Integer, Serializable> mData;
	
	public NodeImpl(int predecessorId, int successorId, int id) {
		mData = new HashMap<Integer, Serializable>();
		if(predecessorId == id){
			mPredecessor = this;
		}else{
			mPredecessor = findNodeByid(predecessorId);
		}
		if(successorId == id){
			mSuccessor = this;
		}else{
			mSuccessor = findNodeByid(successorId);
		}
		mId = id;
	}

	@Override
	public Node getPredecessor() {
		return mPredecessor;
	}

	@Override
	public Node getSuccessor() {
		return mSuccessor;
	}

	@Override
	public int getId() {
		return mId;
	}

	@Override
	public void update(int key, Serializable value) {
		if (isKeyValid(key)){
			System.out.println("Valid : "+key+" in "+mId);
			mData.put(key, value);
		}else{
			System.out.println("Invalid : "+key+" in "+mId);
			mSuccessor.update(key, value);
		}
	}

	@Override
	public Serializable get(int key) {
		return null;
	}

	@Override
	public void setPredecessor(Node n) {
		mPredecessor = n;
	}

	@Override
	public void setSuccessor(Node n) {
		mSuccessor = n;
	}

	@Override
	public Node findSuccessor(int id) {
		if (isKeyValid(id)) return this;
		else return mSuccessor.findSuccessor(id);
	}

	@Override
	public void display() {
		System.out.println("Node "+mId+" : ");
		System.out.println("	Successor "+mSuccessor.getId());
		System.out.println("	Predecessor "+mPredecessor.getId());
		System.out.println("	Data :");
		for(Serializable s : mData.values()){
				System.out.println("		"+s);
		}
		
	}

	private boolean isKeyValid(int key) {
		if (mPredecessor.getId()<mId){
			return (key>mPredecessor.getId() && key<=mId);
		}else{
			return (!(key<=mPredecessor.getId() && key>mId));
		}
	}
	
	private Node findNodeByid(int id){
		try {
			Registry registry = LocateRegistry.getRegistry();
			Node node = (Node)registry.lookup(""+id);
			return node;
		}catch (Exception e){
			return null;
		}
	}
	
	public static void main(String args[]) {
		try {
			
		} catch (Exception e) {
		    System.err.println("Node exception: " + e.toString());
		    e.printStackTrace();
		}
	    }
}
