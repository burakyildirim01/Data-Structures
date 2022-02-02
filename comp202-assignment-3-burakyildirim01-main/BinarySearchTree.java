public class BinarySearchTree
{
    Node root;

    // Node structure containing the subtrees
	static class Node
	{
		// Your code here
		Node left,right;
		int item;
		public Node(int item) {
			this.item = item;
			left = null;
			right = null;
		}
	};

    // Constructor
    public BinarySearchTree()
    {
        // Your code here
        root = null;
    }
	

    // Insert new item into the binary tree
    public void insert(int data)
    {
        // Your code here
        root = addRecursive(root, data);
    }
    
    public Node addRecursive(Node curr, int data) {
		if (curr == null) {
	        	Node n = new Node(data);
	            return n;
	        }
	        if (data > curr.item) {
	            curr.right = addRecursive(curr.right, data);
	        } else if (data < curr.item) {
	            curr.left = addRecursive(curr.left, data);
	        } else {
	            return curr;
	        }

	        return curr;
	    }
    // Check if the tree is balanced or not
    public boolean isBalanced()
    {
        // Your code here
        return isBalancedRec(root);
    }
	public boolean isBalancedRec(Node root)
	    {
	        // Your code here
	    	if(root==null) {
	            return true;
	    	}
	    	int rightHeight = calculateHeight(root.right);
	        int leftHeight = calculateHeight(root.left);
	        return Math.abs(rightHeight-leftHeight)<=1 && isBalancedRec(root.left) && isBalancedRec(root.right) ? true : false;
	    }
	    public int calculateHeight(Node root){
	    	int result = 1;
	        if(root==null) {
	            return 0;
	        }
	        if(calculateHeight(root.left)>calculateHeight(root.right)) {
	        	result += calculateHeight(root.left);
	        }else {
	        	result += calculateHeight(root.right);
	        }
	        return result;
	    }
	
	// Remove an item from the tree
	public void remove(int item)
	{
		// Your code here
		root = deleteRec(root, item);
	}
	public Node deleteRec(Node curr, int item)
	    {
	        if (curr == null) {
	            return curr;
	        }
	        if (item > curr.item) {
	            curr.right = deleteRec(curr.right, item);
	        }else if (item < curr.item) {
	            curr.left = deleteRec(curr.left, item);
	        }else {
	            if (curr.left == null) {
	                return curr.right;
	            }else if (curr.right == null) {
	                return curr.left;
	            }
	            curr.item = calculateMax(curr.left);
	            curr.left = deleteRec(curr.left, curr.item);
	        }
	        return curr;
	    }
	public int calculateMax(Node root)
	    {
	        int result = root.item;
	        while (root.right != null)
	        {
	            result = root.right.item;
	            root = root.right;
	        }
	        return result;
	    }
	// Compare two trees. Return true if both trees are same
	public boolean compareTo(BinarySearchTree tree)
	{
		// Your code here
		return checkIdentical(root,tree.root);
	}
	
	public boolean checkIdentical(Node first, Node second) {
			if(first == null && second == null) {
		        return true;
			}else if(first != null && second != null){
				boolean result = checkIdentical(first.left,second.left) && checkIdentical(first.right,second.right) && first.item == second.item;
		        return result;
		    }else{
		        return false;
		    }
		}
	
	// Given function to print the tree
	public void printInOrder(Node node)
	{
		if (node != null)
		{
			printInOrder(node.left);
			System.out.print(node.item + " ");
			printInOrder(node.right);
		}
    }
}
