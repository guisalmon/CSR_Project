package implementation;

import interfaces.Node;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
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
			try {
				mSuccessor.update(key, value);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Serializable get(int key) {
		return null;
	}

	@Override
	public void setPredecessor(int n) {
		mPredecessor = findNodeByid(n);
	}

	@Override
	public void setSuccessor(int n) {
		mSuccessor = findNodeByid(n);
	}

	@Override
	public Node findSuccessor(int id) {
		if (isKeyValid(id)) return this;
		else
			try {
				return mSuccessor.findSuccessor(id);
			} catch (RemoteException e) {
				e.printStackTrace();
				return null;
			}
	}

	@Override
	public void display() {
		try {
			System.out.println("Node "+mId+" : ");
			System.out.println("	Successor "+mSuccessor.getId());
			System.out.println("	Predecessor "+mPredecessor.getId());
			System.out.println("	Data :");
			for(Serializable s : mData.values()){
					System.out.println("		"+s);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private boolean isKeyValid(int key) {
		try {
			if (mPredecessor.getId()<mId){
				return (key>mPredecessor.getId() && key<=mId);
			}else{
				return (!(key<=mPredecessor.getId() && key>mId));
			}
		} catch (RemoteException e) {
			e.printStackTrace();
			return false;
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
			if (args.length >= 3){
				Node obj = new NodeImpl(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
			    Node stub = (Node) UnicastRemoteObject.exportObject(obj, 0);
	
			    // Bind the remote object's stub in the registry
			    Registry registry = LocateRegistry.getRegistry();
			    registry.bind(args[2], stub);
	
			    System.err.println("Node "+args[2]+" ready");
			}
		} catch (Exception e) {
		    System.err.println("Node "+args[2]+" exception: " + e.toString());
		    e.printStackTrace();
		}
	}
}
