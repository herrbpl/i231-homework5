import static org.junit.Assert.*;
import org.junit.Test;

/** Testklass.
 * @author Jaanus
 */
public class TreeNodeTest {

   @Test (timeout=1000)
   public void testParsePrefix() { 
      String s = "A(B1,C,D)";
      TreeNode t = TreeNode.parsePrefix (s);
      String r = t.rightParentheticRepresentation();
      assertEquals ("Tree: " + s, "(B1,C,D)A", r);
      s = "2";
      t = TreeNode.parsePrefix (s);
      r = t.rightParentheticRepresentation();
      assertEquals ("Tree: " + s, "2", r);
      s = "+(*(-(2,1),4),/(6,3))";
      t = TreeNode.parsePrefix (s);
      r = t.rightParentheticRepresentation();
      assertEquals ("Tree: " + s, "(((2,1)-,4)*,(6,3)/)+", r);
   }

   @Test (timeout=1000)
   public void testParsePrefixAndRightParentheticRepresentation1() {
      String s = "A(B(C(D(E))))"; 
      TreeNode t = TreeNode.parsePrefix (s);
      String r = t.rightParentheticRepresentation();
      assertEquals ("Tree: " + s, "((((E)D)C)B)A", r);
      s = "A(B(C,D(E)),F)";
      t = TreeNode.parsePrefix (s);
      r = t.rightParentheticRepresentation();
      assertEquals ("Tree: " + s, "((C,(E)D)B,F)A", r);
   }

   @Test (timeout=1000)
   public void testParsePrefixAndRightParentheticRepresentation2() {
      String s = "+(*(-(512,1),4),/(-6,3))";
      TreeNode t = TreeNode.parsePrefix (s);
      String r = t.rightParentheticRepresentation();
      assertEquals ("Tree: " + s, "(((512,1)-,4)*,(-6,3)/)+", r);
      s = "A(B,C,D,E,F)";
      t = TreeNode.parsePrefix (s);
      r = t.rightParentheticRepresentation();
      assertEquals ("Tree: " + s, "(B,C,D,E,F)A", r);
      s = "6(5(1,3(2),4))";
      t = TreeNode.parsePrefix (s);
      r = t.rightParentheticRepresentation();
       assertEquals ("Tree: " + s, "((1,(2)3,4)5)6", r);
   }

   @Test (timeout=1000)
   public void testSingleRoot() {
      String s = "ABC";
      TreeNode t = TreeNode.parsePrefix (s);
      String r = t.rightParentheticRepresentation();
      assertEquals ("Tree: " + s, "ABC", r);
      s = ".Y.";
      t = new TreeNode (s, null, null);
      r = t.rightParentheticRepresentation();
      assertEquals ("Single node" + s, s, r);
   } 

   @Test (expected=RuntimeException.class)
   public void testSpaceInTreeNodeName() {
      TreeNode root = TreeNode.parsePrefix ("A B");
   }

   @Test (expected=RuntimeException.class)
   public void testTwoCommas() {
      TreeNode t = TreeNode.parsePrefix ("A(B,,C)");
   }

   @Test (expected=RuntimeException.class)
   public void testEmptySubtree() {
      TreeNode root = TreeNode.parsePrefix ("A()");
   }

   @Test (expected=RuntimeException.class)
   public void testUnbalanced() {
      TreeNode t = TreeNode.parsePrefix ("A,B)");
   }

   @Test (expected=RuntimeException.class)
   public void testInputWithoutBrackets() {
      TreeNode t = TreeNode.parsePrefix ("A,B");
   }

   @Test (expected=RuntimeException.class)
   public void testInputWithDoubleBrackets() {
      TreeNode t = TreeNode.parsePrefix ("A((C,D))");
   }

   @Test (expected=RuntimeException.class)
   public void testComma1() {
      TreeNode root = TreeNode.parsePrefix ("A(,B)");
   }

   @Test (expected=RuntimeException.class)
   public void testComma2() {
      TreeNode root = TreeNode.parsePrefix ("A(B),C(D)");
   }

   @Test (expected=RuntimeException.class)
   public void testComma3() {
      TreeNode root = TreeNode.parsePrefix ("A(B,C),D");
   }

   @Test (expected=RuntimeException.class)
   public void testTab1() {
      TreeNode root = TreeNode.parsePrefix ("\t");
   }

   @Test (expected=RuntimeException.class)
   public void testEmptyString() {
      TreeNode root = TreeNode.parsePrefix ("");
   }

}

