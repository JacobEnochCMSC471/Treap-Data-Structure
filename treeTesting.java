
public class treeTesting 
{
public static void main(String[] args)
	{
		Treap t = new Treap();

//------------Remove Node w/ 0 children----------------------// Working
		/*t.insert("B", 24);
		t.insert("C", 13);
		t.insert("A", 12);
		t.insert("D", 10);
		System.out.println(t.inorder());
		t.remove("A");
		System.out.println(t.inorder());
		t.remove("D");
		System.out.println(t.inorder());*/
//------------Remove Node w/ 0 children----------------------//
		
		
//-----------------Insert Rotate Right-------------------------// Working
		
		/*t.insert("B", 24);
		t.insert("A", 32);
		System.out.println(t.inorder());*/

//-----------------Insert Rotate Right-------------------------//
		
		
//-----------------Insert Rotate Left-------------------------// Working
		
		/*t.insert("B", 24);
		t.insert("C", 32);
		System.out.println(t.inorder());*/
		
//-----------------Insert Rotate Left-------------------------//


//-----------------------Remove Node w/ 1 child---------------// Working
		/*t.insert("C", 13);
		t.insert("B", 12);
		t.insert("A", 11);
		t.insert("D", 10);
		t.insert("E", 5);
		t.remove("B");
		t.remove("D");
		System.out.println(t.inorder());*/
//-----------------------Remove Node w/ 1 child---------------//
		

		
//-----------------Remove node w/ 2 children-----------------// Working
		/*t.insert("C", 13);
		t.insert("B", 12);
		t.insert("A", 10);
		t.insert("F", 11);
		t.insert("E", 5);
		t.insert("G", 3);
		
		System.out.println(t.inorder());
		t.remove("F");
		System.out.println(t.inorder());*/
//-----------------Remove node w/ 2 children-----------------//
		
		
//---------------------Remove Root---------------------------// Working
		/*t.insert("C", 13);
		t.insert("B", 12);
		t.insert("A", 10);
		t.insert("D", 11);
		t.insert("E", 5);
		System.out.println(t.inorder());
		t.remove("C");
		System.out.println(t.inorder());*/
		
//-------------------Copy Constructor---------------------------//	
		t.insert("B", 24);
		t.insert("C", 35);
		t.insert("H", 29);
		t.insert("E", 33);
		t.insert("I", 25);
		t.insert("K", 9);
		t.insert("A", 21);
		t.insert("L", 16);
		t.insert("D", 8);
		t.insert("J", 13);
		t.insert("G", 50);
		
		Treap t1 = new Treap(t);
		
		t1.remove("B");
		t1.remove("C");
		t1.remove("H");
		t1.remove("E");
		t1.remove("I");
		t1.remove("K");
		t1.remove("A");
		t1.remove("L");
		t1.remove("D");
		t1.remove("J");
		t1.remove("Q");

//-------------------Copy Constructor---------------------------//	

		
//-------------------Equals---------------------------//	
		/*t.insert("B", 24);
		t.insert("C", 13);
		t.insert("A", 12);
		t.insert("D", 10);
		
		Treap t1 = new Treap();
		t1.insert("B", 24);
		t1.insert("C", 13);
		t1.insert("A", 12);
		t1.insert("D", 10);
		
		Treap t2 = new Treap();
		t2.insert("C", 13);
		
		Treap t3 = new Treap(t);
		
		System.out.println(t1.equals(t));
		System.out.println(t2.equals(t));
		System.out.println(t3.equals(t));*/
//-------------------Equals---------------------------//	
	}
	
}
