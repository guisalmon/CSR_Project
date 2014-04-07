package implementation;

import interfaces.Node;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {

	public static void main(String args[]) {
		
		try {
			Registry registry = LocateRegistry.getRegistry();
			Node n1 = (Node) registry.lookup("0");
			Node n2 = (Node) registry.lookup("5");
			n2.display();
			
			n1.setPredecessor(5);
			n1.setSuccessor(5);
			n1.display();
			
			Node n3 = (Node) registry.lookup("10");
			n3.setPredecessor(n1.findSuccessor(10).getPredecessor().getId());
			n3.setSuccessor(n1.findSuccessor(10).getId());
			n1.findSuccessor(10).getPredecessor().setSuccessor(10);
			n1.findSuccessor(10).setPredecessor(10);
			n3.display();
			n2.display();
			n1.display();
			
			n3.update(4, 4);
			n1.update(11, 11);
			n2.update(6, 6);
			n3.display();
			n2.display();
			n1.display();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
