
class HW2
{
 
	// Node structure containing power and coefficient of variable
	static class Node
	{
	// Your code here
	 double coeff;
	 int power;
	 Node next;
	 
	 public Node(double coeff, int power){
	     this.coeff = coeff;
	     this.power = power;
	     this.next = null;
	 }
	};
	
	//Function To Display The Linked list
	static void printList( Node ptr)
	{
		if (ptr == null) 
		{
			System.out.println();
			return;
		}
		else if (ptr.next != null)
		{
			while (ptr.next != null)
			{
				System.out.print( ptr.coeff + "x^" + ptr.power + " + ");
				ptr = ptr.next;
			}
		}
		System.out.println( ptr.coeff + "x^" + ptr.power);
	}
  
	// Create a node and return
	static Node createNode(double coeff, int power)
	{
	// Your code here
	Node n = new Node(coeff,power);
	return n;
	}
  
  	// Function add a new node
	static Node addnode(Node head, double coeff, int power)
	{
	// Your code here
	 Node n = createNode(coeff,power);
	 if(head == null){
	     head = n;
	 }else{
	     Node curr = head;
	     while(curr.next!=null){
	         curr = curr.next;
	     }
	     curr.next = n;
	 }
	 return head;
	}
  
	static Node multiply(Node poly1, Node poly2)
	{
	// Your code here
	int powerPoly;
	double firstCoeff;
	Node head = null;
	Node temp = poly2;
	while(poly1!=null) {
		while(poly2!=null) {
			firstCoeff = poly1.coeff * poly2.coeff;
			powerPoly = poly1.power + poly2.power;
			head = addnode(head,firstCoeff,powerPoly);
			poly2 = poly2.next;
		}
		poly2 = temp;
		poly1 = poly1.next;
	}
	Node copyHead = head;
	Node copyHead2 = head;
	while(copyHead!=null && copyHead.next!=null) {
		copyHead2 = copyHead;
		while(copyHead2.next!=null) {
			if(copyHead.power==copyHead2.next.power) {
				copyHead.coeff = copyHead.coeff + copyHead2.next.coeff;
				copyHead2.next = copyHead2.next.next;
			}else {
				copyHead2 = copyHead2.next;
			}
		}
		copyHead = copyHead.next;
	}
	Node copyHead3 = head;
	Node count = null;
	while(copyHead3!=null) {
		count = copyHead3.next;
		while(count!=null) {
			if(copyHead3.power<count.power) {
				int tmp = copyHead3.power;
				double tmpCoeff = copyHead3.coeff;
				copyHead3.power = count.power;
				copyHead3.coeff = count.coeff;
				count.coeff = tmpCoeff;
				count.power = tmp;
		}
			count = count.next;
	}
		copyHead3 = copyHead3.next;
	}
	
	return head;
	
	}
  
	static Node add(Node poly1, Node poly2)
	{
	// Your code here
	Node head = null;
	while(poly1!=null) {
		head = addnode(head,poly1.coeff,poly1.power);
		poly1 = poly1.next;
		
	}
	while(poly2!=null) {
		head = addnode(head,poly2.coeff,poly2.power);
		poly2 = poly2.next;
	}
	
	Node copyHead = head;
	Node copyHead2 = head;
	while(copyHead!=null && copyHead.next!=null) {
		copyHead2 = copyHead;
		while(copyHead2.next!=null) {
			if(copyHead.power==copyHead2.next.power) {
				copyHead.coeff = copyHead.coeff + copyHead2.next.coeff;
				copyHead2.next = copyHead2.next.next;
			}else {
				copyHead2 = copyHead2.next;
			}
			
		}
		copyHead = copyHead.next;
	}
	
	Node copyHead3 = head;
	Node count = null;
	while(copyHead3!=null) {
		count = copyHead3.next;
		while(count!=null) {
			if(copyHead3.power<count.power) {
				int tmp = copyHead3.power;
				double tmpCoeff = copyHead3.coeff;
				copyHead3.power = count.power;
				copyHead3.coeff = count.coeff;
				count.coeff = tmpCoeff;
				count.power = tmp;
		}
			count = count.next;
	}
		copyHead3 = copyHead3.next;
	}
	return head;
	}
  
}
