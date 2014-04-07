package implementation;

import interfaces.Node;

public class Main {

	public static void main(String args[]) {
		Node n1 = new NodeImpl(0, 0, 0);
		Node n2 = new NodeImpl(0, 0, 5);
		n2.display();
		
		n1.setPredecessor(n2);
		n1.setSuccessor(n2);
		n1.display();
		
		Node n3 = new NodeImpl(n1.findSuccessor(10).getPredecessor().getId(), n1.findSuccessor(10).getId(), 10);
		n1.findSuccessor(10).getPredecessor().setSuccessor(n3);
		n1.findSuccessor(10).setPredecessor(n3);
		n3.display();
		n2.display();
		n1.display();
		
		n3.update(4, 4);
		n1.update(11, 11);
		n2.update(6, 6);
		n3.display();
		n2.display();
		n1.display();
	}
	

}
