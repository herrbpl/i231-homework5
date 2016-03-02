
import java.util.*;

public class TreeNode {

   private String name;
   private TreeNode firstChild;
   private TreeNode nextSibling;

   TreeNode (String n, TreeNode d, TreeNode r) {
      // TODO!!! Your constructor here
   }
   
   public static TreeNode parsePrefix (String s) {
      return null;  // TODO!!! return the root
   }

   public String rightParentheticRepresentation() {
      StringBuffer b = new StringBuffer();
      // TODO!!! create the result in buffer b
      return b.toString();
   }

   public static void main (String[] param) {
      String s = "A(B1,C,D)";
      TreeNode t = TreeNode.parsePrefix (s);
      String v = t.rightParentheticRepresentation();
      System.out.println (s + " ==> " + v); // A(B1,C,D) ==> (B1,C,D)A
   }
}

