/*
Jacob Enoch
Prof. Kartchner
CMSC341 - Section 90
October 21, 2020
*/

import java.util.Vector;

class Treap {
	
	  private TreapNode root;
	  //used for the result of the inorder
	  private Vector<String> inorderRes = new Vector<String>();
	  
	  // Constructors, Destructor, Assignment Operator  
	  public Treap()
	  {
		  root = null;
	  }
	  
	  public Treap(Treap other)  //Sets one treap equal to eachother (this = other) (must be deep copy)
	  {
		  Vector<Integer> priorityValues = new Vector<Integer>(); //Vector to hold priority values of the tree being copied
		  priorityValues = preOrderPri(other.root, priorityValues);
		  
		  Vector<String> dataValues = new Vector<String>(); //Vector to hold data values of tree being copied
		  dataValues = preOrderData(other.root, dataValues);
		  
		  for(int i = 0; i < dataValues.size(); i++) //Inserts all values from the vectors into copied tree
		  {
			  this.insert(dataValues.get(i), priorityValues.get(i));
		  }
	  }

	  //const Treap& operator=(const Treap& rhs) ;
	  public boolean equals(Object t) //Uses the inorder transversal to compare the trees; if they are equal, return true; return false otherwise.
	  {
			  Treap rhs = (Treap)t;
			  String rhsInorder = rhs.inorder();
			  String lhsInorder = this.inorder();
			  
			  if(lhsInorder.compareTo(rhsInorder) == 0) //The strings match, so the trees are equal; return true
			  {
				  return true;
			  }
		  
			  else return false; //Strings do not match, return false
	  }
	  
	  //
	  
	  // Other member functions
	  //
	  
	  // Returns true if tree is empty; false otherwise.
	  public boolean empty()
	  { 
		  if(root == null) return true;
			else return false;
	  }  

	  // Returns height of the tree/node.
	  public int getHeight() 
	  {
		  return root != null ? root.height : -1 ;
	  }

	  // Returns priority of the root node of a tree.
	  public int getPriority() 
	  {
		  return root != null ? root.pri : Integer.MIN_VALUE;
	  };

	  // Search for a value; return true if found or false if
	  // value is not found.
	  public boolean find(String data) //Puts the in-order search result into a string; if substring is in string, item exists and true is returned
	  {
		  String inOrderResult = this.inorder();
		 
		  if(inOrderResult.indexOf(data) == -1) //If the data is not found in the inOrderResult, the item is not in treap
		  {
			  return false;
		  }
		  
		  
		  else return true; //Item found, returh true
	  }

	  
	  // Insert the value-priority pair.
	  // Basic BST insertion. Doesn't allow duplicate values
	  //
	  // Complete this function to do Treap insertion
	  //
	  public void insert(String x, int p) 
	  {
		  
		  if (empty()) //Insertion into an empty treap; node becomes new root of entire tree
		  {
			  root = new TreapNode(x, p, 0); //Initalized with values x, p, and height 0 
			  return;
		  }

		  
		  if (root.data.compareTo(x) < 0) //Insert to right (x > root data)
		  { 
			  root.right.insert(x, p);
			  
			  
			  if(root.right.getPriority() > root.pri) //Rotate node right if left.priority > root.priority
			  {
				  root = singleRotateLeft(root);
			  }
			  
			  
		  } 
		  
		  else if (x.compareTo(root.data) < 0 ) //Insert to left (x < root data)
		  {
			  root.left.insert(x, p);
			  
			  
			if(root.left.getPriority() > root.pri) //Rotate node left if right.priority > root.priority
			  {
				root = singleRotateRight(root);
			  }
			

		  }
		  
		  else if (x.compareTo(root.data)==0) //Data already exists, do not insert anything
		  {
			  return; //data is already here - do nothing
		  }

		  
		  //adjust with rotations to make the BST into a Treap
		  
		  // Update height of entire tree - suggest making this a method
		  
		 root.height = updateNodeHeight(root);
	  }
	  
	  
	  //to remove a element, rotate it within the BST until it becomes a leaf node
	  //delete the leaf node
	  public boolean remove(String x) 
	  {
		  
		  if(root == null) //Node is not found, nothing is removed
	      {
	    	  System.out.println("Node not found.");
	    	  return false;
	      }
		  
		  if(x.compareTo(root.data) > 0) //If the item to be removed is > root data... (finding node)
		  {
			  root.right.remove(x);                  //Recursively iterate down tree on right side
			  root.height = updateNodeHeight(root);  //Heights updated before any removal
		  }                        
		  
		  
		  else if(root.data.compareTo(x) > 0) //If the item to be removed is < root data... (finding node)
		  {
			  root.left.remove(x);                  //Recursively iterate down tree on left side
			  root.height = updateNodeHeight(root); //Heights updated before any removal
		  }
		  
		  else //Node is found, removal proceeds towards possible 3 cases (no children, one child, two children)
		  {
			  
			  TreapNode newRoot = new TreapNode(); //Will be used when node being removed has one child
			  
			  if(root.right.root == null && root.left.root == null) //No children to remove, root is simply set to null 
			  {
				  root = null;
				  return true;
			  }
			  
			  
			  else if ((root.right.root == null && root.left.root != null) || (root.right.root != null && root.left.root == null)) //Root only has one child 
			  {
				  
				  if((root.right.root != null && root.left.root == null)) //right child exists, root becomes the right child
				  {
					  newRoot = root.right.root;
					  newRoot.height = updateNodeHeight(newRoot);
				 
				  }
				  
				  else if(root.right.root == null && root.left.root != null) //left child exists, root becomes left child
				  {
					  newRoot = root.left.root;
					  newRoot.height = updateNodeHeight(newRoot);
				  }
				  
				  root = newRoot; //Root becomes the new root
				  return true;
				  
			  }
			  
			  else if(root.right.root != null && root.left.root != null) //Subroot has 2 children, rotate node being removed until it has no children
			  {
				  
				  if(root.left.getPriority() < root.right.getPriority()) //Rotate left to have higher priority child become new root
				  {
					  root = singleRotateLeft(root);
					  root.height = updateNodeHeight(root) - 1;
					  root.left.remove(x);
				  }
				  
				  
				  else //Rotate right to have higher priority child become new root
				  {
					  root = singleRotateRight(root);
					  root.height = updateNodeHeight(root) - 1;
					  root.right.remove(x);
				  }
				  
			  }
		}
		  
		  return false; //Node is not found, return false
	  }

	  //Helper to call the inorder function
	  //passes in an empty Vector to 
	  public String inorder() 
	  {
		  String inorderResult = "";
		  inorderRes.clear();
		  inorder(inorderRes);
		  for(String element:inorderRes) {
			  inorderResult += element;
		  }
		  return inorderResult;
	  }
	  
	  // Perform an inorder traversal and print nodes.
	  public void inorder(Vector<String> inorderRes) 
	  {
		  
		  if (empty()) return;

		  inorderRes.add("(");
			  
		  root.left.inorder(inorderRes);
		  
		  inorderRes.add(root.data+":" + root.pri + ":" + root.height);

		  root.right.inorder(inorderRes) ;
		  
		  inorderRes.add(")");
	  }

	  // Probe the tree.  If a node is at the given position, return the
	  // data (x), priority (p), and height (h).
	  // See project description for details.
	  public testObject locate (String position, int offset)
	  { 
		  if (root == null) return null ;

		  if (offset >= position.length()) {
			  return new testObject(root.data, root.pri, root.height);
		  }

		  if (position.charAt(offset) == 'L') {
		    return root.left.locate(position, offset+1) ;
		  }

		  if (position.charAt(offset) == 'R') {
		    return root.right.locate(position, offset+1) ;
		  }

		  System.out.println( "Bad position character!\n" );

		  return null ; 
		  
	  }

	  // Print data about all nodes 
	  String dump() {
		  if (!empty()) 
			  return root.dump() ;
		  else return null;
	  }


	  //
	  // Private helper functions must be declared here
	  //
	  
	  private static TreapNode singleRotateLeft(TreapNode root) //Rotates a subtree left once, returns new root of subtree (TreapNode)
	  {  
		TreapNode rightChild = root.right.root;
		TreapNode rightLeftChild = root.right.root.left.root;
		
		rightChild.left.root = root;
		root.right.root = rightLeftChild;
		
		root.height = updateNodeHeight(root);
		
		return rightChild;
	  }
	  
	  private static TreapNode singleRotateRight(TreapNode root) //Rotates a subtree right once, returns new root of subtree (TreapNode)
	  {
		  TreapNode leftChild = root.left.root; 
		  TreapNode leftRightChild = root.left.root.right.root;
		  
		  leftChild.right.root = root;
		  root.left.root = leftRightChild;
		  
		  root.height = updateNodeHeight(root);
		  
		  return leftChild;
		  
	  }
	  
	  private static int updateNodeHeight(TreapNode root) //Updates node heights recursively (height = max of 
	  {
		  int leftHeight = root.left.getHeight();
		  int rightHeight = root.right.getHeight();
		  
		  if(leftHeight >= rightHeight)
		  {
			  return leftHeight + 1;
		  }
		  
		  else return rightHeight + 1;
	  }
	  
	  private static Vector<String> preOrderData(TreapNode root, Vector<String> dataValues) //Returns a vector with all data values of a tree (preorder, used for copying)
	  {
		  
		  if(root == null)
		  {
			  return dataValues;
		  }
		  
		  dataValues.add(root.data);
		  preOrderData(root.left.root, dataValues);
		  preOrderData(root.right.root, dataValues);
		  
		  return dataValues;
	  }
	  
	  private static Vector<Integer> preOrderPri(TreapNode root, Vector<Integer> priValues) //Returns a vector with all priority values of a tree (preorder, used for copying)
	  {
		  
		  if(root == null)
		  {
			  return priValues;
		  }
		  
		  priValues.add(root.pri);
		  preOrderPri(root.left.root, priValues);
		  preOrderPri(root.right.root, priValues);
		  
		  return priValues;
	  }
	  
class TreapNode {
		  private String data;          // Data value
		  private int pri;          	// Priority
		  private int height;           // Height
		  private Treap left, right;    // Left and right child trees

	  public TreapNode() {
		  height = 0;
		  pri = 0;
		  left = new Treap();
		  right = new Treap();
	  }
	  
	  public TreapNode(String x, int priority, int ht) {
		  data = x;
		  pri = priority;
		  height = ht;
		  left = new Treap();
		  right = new Treap();
	  }
	  

	  public String dump() {
		  String result = "";
		  result += "===== TreapNode::dump()\n";
		  result += "      this = " + this + "\n"; 
		  result += "      left = " + left +"\n"; 
		  result += "      right= " + right +"\n"; 
		  result += "      data = " + data + "\n";
		  result += "      height =" + height +"\n";
		  result += left.dump() ;
		  result += right.dump() ;
		  return result;
	  }
	
	}
}