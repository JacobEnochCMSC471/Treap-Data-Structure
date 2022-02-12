import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TreapTest {

	Treap t, t1, t2;
	testObject test1,test2;
	
	@BeforeEach
	void setUp() throws Exception {
		t = new Treap();
	}

	@AfterEach
	void tearDown() throws Exception {
		t = null;
	}

	@Test
	void testCopyContructor() {
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

		//T1 constructed from T using copy constructor...
		String result = t.inorder();
		String result2 = t1.inorder();
		
		assertEquals("((((A:21:0)B:24:1)C:35:2((D:8:0)E:33:1))G:50:5(H:29:4(I:25:3((J:13:1(K:9:0))L:16:2))))", result);
		assertEquals("((((A:21:0)B:24:1)C:35:2((D:8:0)E:33:1))G:50:5(H:29:4(I:25:3((J:13:1(K:9:0))L:16:2))))",result2);
	}

	@Test
	void testEquals() {
		String result, result2, result3;
		
		t.insert("B", 24);
		t.insert("C", 13);
		t.insert("A", 12);
		t.insert("D", 10);
		result = t.inorder();
		assertEquals("((A:12:0)B:24:2(C:13:1(D:10:0)))",result);
		
		t1 = new Treap();
		t1.insert("B", 24);
		t1.insert("C", 13);
		t1.insert("A", 12);
		t1.insert("D", 10);
		result2 = t1.inorder();
		assertEquals("((A:12:0)B:24:2(C:13:1(D:10:0)))",result2);
		
		assertEquals(true,t1.equals(t));
		
		t2 = new Treap();
		t2.insert("C", 24);
		t2.insert("B", 13);
		t2.insert("A", 12);
		t2.insert("D", 10);
		result3 = t2.inorder();
		assertEquals("(((A:12:0)B:13:1)C:24:2(D:10:0))",result3);
		
		assertEquals(false,t1.equals(t2));
		
	}

	@Test
	void testFind() {
		String result;
		
		t.insert("B", 24);
		t.insert("C", 13);
		t.insert("A", 12);
		t.insert("D", 10);
		result = t.inorder();
		assertEquals("((A:12:0)B:24:2(C:13:1(D:10:0)))",result);
		
		assertEquals(true,t.find("A"));
		assertEquals(true,t.find("D"));
		assertEquals(true,t.find("B"));
		assertEquals(false,t.find("E"));
	}

	@Test
	void testInsertBST() {
		t.insert("B", 24);
		String result = t.inorder();
		assertEquals("(B:24:0)",result);
		
		t.insert("C", 13);
		result = t.inorder();
		assertEquals("(B:24:1(C:13:0))",result);
		
		t.insert("A", 12);
		result = t.inorder();
		assertEquals("((A:12:0)B:24:1(C:13:0))",result);
		
		t.insert("D", 10);
		result = t.inorder();
		assertEquals("((A:12:0)B:24:2(C:13:1(D:10:0)))",result);

	}
	
	@Test
	void testInsertrotateRight() {
		t.insert("B", 24);
		String result = t.inorder();
		assertEquals("(B:24:0)",result);
		
		t.insert("A", 32);
		result = t.inorder();
		assertEquals("(A:32:1(B:24:0))",result);
	}
	
	@Test
	void testInsertrotateLeft() {
		t.insert("B", 24);
		String result = t.inorder();
		assertEquals("(B:24:0)",result);
		
		t.insert("C", 32);
		result = t.inorder();
		assertEquals("((B:24:0)C:32:1)",result);
	}

	@Test
	void testRemoveChildWithNoChildren() {
		String result;
		
		t.insert("B", 24);
		t.insert("C", 13);
		t.insert("A", 12);
		t.insert("D", 10);
		result = t.inorder();
		assertEquals("((A:12:0)B:24:2(C:13:1(D:10:0)))",result);
		
		t.remove("A");
		result = t.inorder();
		assertEquals("(B:24:2(C:13:1(D:10:0)))",result);
		
		t.remove("D");
		result = t.inorder();
		assertEquals("(B:24:1(C:13:0))",result);
		
	}
	
	@Test
	void testRemoveChildWithOneChild() {
		String result;
		
		t.insert("C", 13);
		t.insert("B", 12);
		t.insert("A", 11);
		t.insert("D", 10);
		t.insert("E", 5);
		result = t.inorder();
		assertEquals("(((A:11:0)B:12:1)C:13:2(D:10:1(E:5:0)))",result);
		
		t.remove("B");
		result = t.inorder();
		assertEquals("((A:11:0)C:13:2(D:10:1(E:5:0)))",result);
		
		t.remove("D");
		result = t.inorder();
		assertEquals("((A:11:0)C:13:1(E:5:0))",result);		
	}
	
	@Test
	void testRemoveChildWithTwoChildren() {
		String result;
		
		t.insert("C", 13);
		t.insert("B", 12);
		t.insert("A", 10);
		t.insert("F", 11);
		t.insert("E", 5);
		t.insert("G", 3);
		result = t.inorder();
		assertEquals("(((A:10:0)B:12:1)C:13:2((E:5:0)F:11:1(G:3:0)))",result);
		
		t.remove("F");
		result = t.inorder();
		assertEquals("(((A:10:0)B:12:1)C:13:2(E:5:1(G:3:0)))",result);
		
	}
	
	@Test
	void testRemoveRoot() {
		String result;
		
		t.insert("C", 13);
		t.insert("B", 12);
		t.insert("A", 10);
		t.insert("D", 11);
		t.insert("E", 5);
		result = t.inorder();
		assertEquals("(((A:10:0)B:12:1)C:13:2(D:11:1(E:5:0)))",result);
		
		t.remove("C");
		result = t.inorder();
		assertEquals("((A:10:0)B:12:2(D:11:1(E:5:0)))",result);
		
	}

	@Test
	void testInorder() {
		String result,result2;
		t1 = new Treap();
		t1.insert("B", 24);
		t1.insert("C", 13);
		t1.insert("A", 12);
		t1.insert("D", 10);
		result = t1.inorder();
		assertEquals("((A:12:0)B:24:2(C:13:1(D:10:0)))",result);
		
		t2 = new Treap();
		t2.insert("C", 24);
		t2.insert("B", 13);
		t2.insert("A", 12);
		t2.insert("D", 10);
		result2 = t2.inorder();
		assertEquals("(((A:12:0)B:13:1)C:24:2(D:10:0))",result2);
	}

	@Test
	void testLocate() {
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
		testObject test1 = t.locate("LRL",0);
		assertEquals("D", test1.getData());
		
		test1 = t.locate("RRR",0);
		assertEquals("L", test1.getData());
		
		test1 = t.locate("RRRL",0);
		assertEquals("J", test1.getData());
		
		test1 = t.locate("RRRLR",0);
		assertEquals("K", test1.getData());
		
		test1 = t.locate("LRR",0);
		assertEquals(null, test1);
	}
	
	@Test
	void TreapTest1() {
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
		
		String result = t.inorder();
		assertEquals("((((A:21:0)B:24:1)C:35:2((D:8:0)E:33:1))G:50:5(H:29:4(I:25:3((J:13:1(K:9:0))L:16:2))))",result);

	}
	
	@Test
	void TreapTest2() {
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
		

		// Delete "K" and "C"

		t.remove("K");
		t.remove("C");

		String result = t.inorder();
		assertEquals("((((A:21:0)B:24:1(D:8:0))E:33:2)G:50:4(H:29:3(I:25:2((J:13:0)L:16:1))))",result);

	}
	
	@Test
	void TreapTest3() {
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

		//T1 constructed from T using copy constructor...
		String result = t.inorder();
		String result2 = t1.inorder();
		
		assertEquals("((((A:21:0)B:24:1)C:35:2((D:8:0)E:33:1))G:50:5(H:29:4(I:25:3((J:13:1(K:9:0))L:16:2))))", result);
		assertEquals("((((A:21:0)B:24:1)C:35:2((D:8:0)E:33:1))G:50:5(H:29:4(I:25:3((J:13:1(K:9:0))L:16:2))))",result2);

		//Remove all elements except 'G' from T1...

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

		result = t.inorder();;
		result2 = t1.inorder();
		
		assertEquals("((((A:21:0)B:24:1)C:35:2((D:8:0)E:33:1))G:50:5(H:29:4(I:25:3((J:13:1(K:9:0))L:16:2))))", result);
		assertEquals("(G:50:0)",result2);
	}

}
