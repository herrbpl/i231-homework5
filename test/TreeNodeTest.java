import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Testklass.
 * 
 * @author Jaanus
 */
public class TreeNodeTest {

	@Test(timeout = 1000)
	public void testAsArray() {		
		TreeNode root = new TreeNode("root", null, null);
		TreeNode child1, child2;

		child1 = null;
		child2 = null;
		
		for (int i = 0; i < 10; i++) {
			child1 = new TreeNode(String.format("child%d", i), null, null);
			for (int j = 0; j < 10; j++) {
				child2 = new TreeNode(String.format("child%d:%d", i, j), null, null);
				child1.addChild(child2);				
			}
			root.addChild(child1);
		}
		
		TreeNode[] aTest = root.asArray();
		for (int i = 0; i < aTest.length; i++) {
			System.out.println(aTest[i].getName());
		}
		
	}
	
	@Test(expected = RuntimeException.class)
	public void testInvalidNodeName() {
		TreeNode root = new TreeNode(",");
		root.addChild("  ");
		root.addChild("(");
		root.addChild(")xxx");
		root.addChild("[ss");
	}
	
	@Test(expected = RuntimeException.class)
	public void testAddChildDouble() {
		TreeNode root = new TreeNode("root", null, null);
		TreeNode child1 = new TreeNode("child1", null, null);
		root.addChild(child1);
		
		TreeNode test1, child2;
		test1 = child1;
		child1 = null;
		child2 = null;
		
		for (int i = 0; i < 10; i++) {
			child1 = new TreeNode(String.format("child%d", i), null, null);
			for (int j = 0; j < 10; j++) {
				child2 = new TreeNode(String.format("child%d:%d", i, j), null, null);
				child1.addChild(child2);				
			}
			root.addChild(child1);
		}
		System.out.println("Testing double entry");
		
		root.addChild(test1);
		root.addChild(child1);
	}

	@Test(expected = RuntimeException.class)
	public void testAddChildItself() {
		TreeNode root = new TreeNode("root", null, null);
		root.addChild(root);
	}

	@Test(timeout = 1000)
	public void testfindChild() {
		TreeNode root = new TreeNode("root", null, null);
		TreeNode child1, child2;
		TreeNode test1;
		child1 = null;
		child2 = null;
		test1 = null;
		for (int i = 0; i < 10; i++) {
			child1 = new TreeNode(String.format("child%d", i), null, null);
			for (int j = 0; j < 10; j++) {
				child2 = new TreeNode(String.format("child%d:%d", i, j), null, null);
				child1.addChild(child2);
				if ( j == 5 && i == 5) {
					test1 = child2;
				}
			}
			root.addChild(child1);
		}
		TreeNode find = root.findChild(test1); 
		assertTrue(String.format("child1 should equal to %s", test1.getName()), ( find == test1 ));
		
		find = root.find("root", 1);
		assertNotNull("Expected to find 'root'", find);
		assertEquals(root.getName(), find.getName());
		
		find = root.find("child5:5", 1);
		assertNotNull("Expected to find 'child5:5'", find);
		assertEquals("child5:5", find.getName());
		
		root.addChild("test123");
		root.addChild("test123");
		root.addChild("test123");
		find = root.find("test123", 2);
		assertNotNull("Expected to find 'test123'", find);
		assertEquals("test123", find.getName());
		
		find = root.find("test123", 5);
		assertNull("Expected to find nothing", find);
		
	}

	@Test(timeout = 1000)
	public void testParsePrefix() {
		String s = "A(B1,C,D)";
		TreeNode t = TreeNode.parsePrefix(s);
		String r = t.rightParentheticRepresentation();
		assertEquals("Tree: " + s, "(B1,C,D)A", r);
		s = "2";
		t = TreeNode.parsePrefix(s);
		r = t.rightParentheticRepresentation();
		assertEquals("Tree: " + s, "2", r);
		s = "+(*(-(2,1),4),/(6,3))";
		t = TreeNode.parsePrefix(s);
		r = t.rightParentheticRepresentation();
		assertEquals("Tree: " + s, "(((2,1)-,4)*,(6,3)/)+", r);
	}

	@Test(timeout = 1000)
	public void testParsePrefixAndRightParentheticRepresentation1() {
		String s = "A(B(C(D(E))))";
		TreeNode t = TreeNode.parsePrefix(s);
		String r = t.rightParentheticRepresentation();
		assertEquals("Tree: " + s, "((((E)D)C)B)A", r);
		s = "A(B(C,D(E)),F)";
		t = TreeNode.parsePrefix(s);
		r = t.rightParentheticRepresentation();
		assertEquals("Tree: " + s, "((C,(E)D)B,F)A", r);
	}

	@Test(timeout = 1000)
	public void testParsePrefixAndRightParentheticRepresentation2() {
		String s = "+(*(-(512,1),4),/(-6,3))";
		TreeNode t = TreeNode.parsePrefix(s);
		String r = t.rightParentheticRepresentation();
		assertEquals("Tree: " + s, "(((512,1)-,4)*,(-6,3)/)+", r);
		s = "A(B,C,D,E,F)";
		t = TreeNode.parsePrefix(s);
		r = t.rightParentheticRepresentation();
		assertEquals("Tree: " + s, "(B,C,D,E,F)A", r);
		s = "6(5(1,3(2),4))";
		t = TreeNode.parsePrefix(s);
		r = t.rightParentheticRepresentation();
		assertEquals("Tree: " + s, "((1,(2)3,4)5)6", r);
	}

	@Test(timeout = 1000)
	public void testSingleRoot() {
		String s = "ABC";
		TreeNode t = TreeNode.parsePrefix(s);
		String r = t.rightParentheticRepresentation();
		assertEquals("Tree: " + s, "ABC", r);
		s = ".Y.";
		t = new TreeNode(s, null, null);
		r = t.rightParentheticRepresentation();
		assertEquals("Single node" + s, s, r);
	}

	@Test(expected = RuntimeException.class)
	public void testSpaceInTreeNodeName() {
		TreeNode root = TreeNode.parsePrefix("A B");
	}

	@Test(expected = RuntimeException.class)
	public void testTwoCommas() {
		TreeNode t = TreeNode.parsePrefix("A(B,,C)");
	}

	@Test(expected = RuntimeException.class)
	public void testEmptySubtree() {
		TreeNode root = TreeNode.parsePrefix("A()");
	}

	@Test(expected = RuntimeException.class)
	public void testUnbalanced() {
		TreeNode t = TreeNode.parsePrefix("A,B)");
	}

	@Test
	public void testInputWithoutBrackets() {
		TreeNode t = TreeNode.parsePrefix("A,B");
	}

	@Test(expected = RuntimeException.class)
	public void testInputWithDoubleBrackets() {
		TreeNode t = TreeNode.parsePrefix("A((C,D))");
	}

	@Test(expected = RuntimeException.class)
	public void testComma1() {
		TreeNode root = TreeNode.parsePrefix("A(,B)");
	}

	@Test(expected = RuntimeException.class)
	public void testComma2() {
		TreeNode root = TreeNode.parsePrefix("A(B),C(D)");
	}

	@Test(expected = RuntimeException.class)
	public void testComma3() {
		TreeNode root = TreeNode.parsePrefix("A(B,C),D");
	}

	@Test(expected = RuntimeException.class)
	public void testTab1() {
		TreeNode root = TreeNode.parsePrefix("\t");
	}

	@Test(expected = RuntimeException.class)
	public void testEmptyString() {
		TreeNode root = TreeNode.parsePrefix("");
	}

}
