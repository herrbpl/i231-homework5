
import java.util.*;

public class TreeNode {

	private String name;
	private TreeNode firstChild;
	private TreeNode nextSibling;

	/**
	 * TreeNode object / node
	 * 
	 * @param n
	 *            - Name of node
	 * @param d
	 *            - first child
	 * @param r
	 *            - next sibling
	 */
	TreeNode(String n, TreeNode d, TreeNode r) {
		// TODO!!! Your constructor here
		if (!TreeNode.validNodeName(n)) {
			throw new IllegalArgumentException(String.format("Invalid node name '%s'", n));
		}
		this.setName(n);
		this.setFirstChild(d);
		this.setNextSibling(r);
	}

	/**
	 * Checks for valid node name
	 * 
	 * @param n
	 *            - node name
	 * @return true if name is valid
	 */
	public static boolean validNodeName(String n) {
		if (n.matches("[,\\s()]")) {
			return false;
		}
		return true;
	}

	public static TreeNode parsePrefix(String s) {
		return null; // TODO!!! return the root
	}

	public String rightParentheticRepresentation() {
		StringBuffer b = new StringBuffer();
		// TODO!!! create the result in buffer b
		return b.toString();
	}

	public static void main(String[] param) {
		String s = "A(B1,C,D)";
		TreeNode t = TreeNode.parsePrefix(s);
		String v = t.rightParentheticRepresentation();
		System.out.println(s + " ==> " + v); // A(B1,C,D) ==> (B1,C,D)A
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TreeNode getFirstChild() {
		return firstChild;
	}

	public void setFirstChild(TreeNode firstChild) {
		this.firstChild = firstChild;
	}

	public TreeNode getNextSibling() {
		return nextSibling;
	}

	public void setNextSibling(TreeNode nextSibling) {
		this.nextSibling = nextSibling;
	}
}
