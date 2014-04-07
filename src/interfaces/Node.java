package interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Node extends Remote{
	public Node getPredecessor() throws RemoteException;
	public Node getSuccessor() throws RemoteException;
	public void setPredecessor(int n) throws RemoteException;
	public void setSuccessor(int n) throws RemoteException;
	public void update(int key, Serializable value) throws RemoteException;
	public Serializable get(int key) throws RemoteException;
	public int getId() throws RemoteException;
	public Node findSuccessor(int id) throws RemoteException;
	public void display() throws RemoteException;
}
