package interfaces;

import java.io.Serializable;
import java.rmi.Remote;

public interface Node extends Remote{
	public Node getPredecessor();
	public Node getSuccessor();
	public void setPredecessor(Node n);
	public void setSuccessor(Node n);
	public void update(int key, Serializable value);
	public Serializable get(int key);
	public int getId();
	public Node findSuccessor(int id);
	public void display();
}
