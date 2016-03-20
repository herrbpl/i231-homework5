
import java.util.*;

public class TreeNode implements Iterator<TreeNode> {

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

	private void setFirstChild(TreeNode firstChild) {
		this.firstChild = firstChild;
	}

	public TreeNode getNextSibling() {
		return nextSibling;
	}

	private void setNextSibling(TreeNode nextSibling) {
		this.nextSibling = nextSibling;
	}

	/**
	 * Adds children to tree. If this does not have children yet we add child as
	 * first children, otherwise we add child as end of children chain Because
	 * each node has only some pointers (next and down and perhaps up) we cannot
	 * add child that has already those pointers filled, i.e. we must never add
	 * any child instance twice to any tree. There are two strategies to deal
	 * with that - set child pointers to null or deny adding such child. Latter
	 * is safer but requires checking whole tree.
	 * 
	 * @param child
	 */
	public void addChild(TreeNode child) {
		// if null, we do not add anything
		if (child == null)
			return;

		// Child must not be parent.
		if (child == this) {
			throw new IllegalArgumentException("Cannot add itself to children!");
		}

		if (findChild(child) != null || child.findChild(this) != null) {
			throw new IllegalArgumentException("Cannot add child because either two tree structures overlap");
		}

		// iterator to find last existing sibling
		Iterator<TreeNode> children = children();

		// if child already has some children, we should make sure none
		// of those point back to any other member of tree to avoid loops
		// nor should child itself be parent of itself

		// Add parent
		child.setParent(this);

		// if this does not have children, add
		if (children == null) {
			setFirstChild(child);
		} else {
			// find last existing sibling
			while (children.hasNext()) {
				children = children.next();
			}
			// add new child to be last sibling
			((TreeNode) children).setNextSibling(child);
		}

	}

	/**
	 * Creates node with name and adds as a child
	 * 
	 * @param name
	 */
	public void addChild(String name) {
		addChild(new TreeNode(name, null, null));
	}

	/**
	 * Recursively checks if child is found in structure
	 * 
	 * @param child
	 *            - node to be looked for. As only one instance of node should
	 *            exist in structure, we only need to find first
	 * @return
	 */
	public TreeNode findChild(TreeNode child) {
		// TODO Auto-generated method stub
		Iterator<TreeNode> children = children();

		// first child is child
		if (((TreeNode) children) == child)
			return ((TreeNode) children);

		TreeNode result = null;

		// no children for node
		if (children == null)
			return null;

		// recursively look for child
		result = ((TreeNode) children).findChild(child);
		if (result != null)
			return result;

		// repeat for each sibling.
		while (children.hasNext()) {
			children = children.next();

			if (((TreeNode) children) == child)
				return ((TreeNode) children);

			// no children for node
			if (children == null)
				return null;

			// recursively look for child
			result = ((TreeNode) children).findChild(child);
			if (result != null)
				return result;
		}

		return null;
	}

	/**
	 * Finds nth child by name
	 * 
	 * @param name
	 *            - child searched for
	 * @param position
	 *            - nth position
	 * @return
	 */
	public TreeNode find(String name, int position) {
		
		TreeNode result = null;		
		int posCounter = position;
		
		if (this.getName() == name ) {
			System.out.println("Find instance with name");
			posCounter--;
		}
		if (posCounter <= 0) return this;
		
		// TODO Auto-generated method stub
		Iterator<TreeNode> children = children();
		
		// no children for node
		if (children == null)
			return null;

		// recursively look for child
		result = ((TreeNode) children).find(name, posCounter);
		if (result != null)
			return result;

		// repeat for each sibling.
		while (children.hasNext()) {
			children = children.next();

			// check for child name
			if (((TreeNode) children).getName() == name ) {
				System.out.println("Find instance with name");
				posCounter--;
			}
			if (posCounter <= 0) return ((TreeNode) children);
			

			// no children for node
			if (children == null)
				return null;

			// recursively look for child
			result = ((TreeNode) children).find(name, posCounter);
			if (result != null)
				return result;
		}

		return null;

	}

	/**
	 * Adds parent to node
	 * 
	 * @param treeNode
	 */
	private void setParent(TreeNode treeNode) {
		// TODO Auto-generated method stub

	}

	/**
	 * get parent node of current node.
	 * 
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
		return (getNextSibling() != null);
	}

	@Override
	public TreeNode next() {
		// TODO Auto-generated method stub
		return getNextSibling();
	}
}
