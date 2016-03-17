
import java.util.*;

public class TreeNode implements Iterator<TreeNode>{

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
	
	/**
	 * Adds children to tree. If this does not have children yet
	 * we add child as first children, otherwise we add child as end of 
	 * children chain
	 * @param child
	 */
	public void addChild(TreeNode child) {
		// if null, we do not add anything
		if (child == null) return;
		// iterator to find last existing sibling
		Iterator<TreeNode> children = children();
		
		// if child already has some children, we should make sure none
		// of those point back to any other member of tree to avoid loops
		
		
		// if this does not have children, add
		if (children == null) {
			setFirstChild(child);
		} else {
			// find last existing sibling
			while (children.hasNext()) {
				children = children.next();
			}
			// add new child to be last sibling
			((TreeNode)children).setNextSibling(child);
		}
		
	}

	/**
	 * get parent node of current node.
	 * @return parent node or null
	 */
	public TreeNode getParent() {
		throw new UnsupportedOperationException();
	}
	
	public Iterator<TreeNode> children() {
		return getFirstChild();
	}
	
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return (getNextSibling() == null);
	}

	@Override
	public TreeNode next() {
		// TODO Auto-generated method stub
		return getNextSibling();
	}
}
