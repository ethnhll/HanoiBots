package edu.ohio_state.cse.hanoibots.models;

/**
 * 
 * This class provides the structure for nodes to be used in search trees
 * produced by the search algorithms implemented within an agent.
 * <p>
 * The structure for a Node is based off of the pseudo-code provided in the
 * repository for Artificial Intelligence: A Modern Approach, specifically based
 * on the code found at <a href=
 * "http://aima-java.googlecode.com/svn/trunk/aima-core/src/main/java/aima/core/
 * search/framework/Node.java"> here.</a>
 * </p>
 * 
 * @author Ethan Hill
 * 
 */
public class Node implements Comparable<Object> {

	/**
	 * this instance's parent node
	 */
	private Node parent;

	/**
	 * the current state that this node represents in the state space
	 */
	private Pair<String, String> state;

	/**
	 * the path cost from the initial state to the current node
	 */
	private int pathCost;

	/**
	 * the action applied to the parent of this node to generate this node
	 */
	private String action;

	/**
	 * the depth within a search tree at which this node resides
	 */
	private int depth;

	/**
	 * Class constructor that creates a root node.
	 * 
	 * @param State
	 *            the pair of Strings that represent the state of this root node
	 */
	public Node(Pair<String, String> state) {
		this.state = state;
		this.pathCost = 0;
		this.depth = 0;
		this.parent = null;
		this.action = "No Action (root)";
	}

	/**
	 * 
	 * @param parent
	 *            the parent Node of this node
	 * @param state
	 *            the Pair of Strings representing the state of this node
	 * @param stepCost
	 *            the path cost of this particular node, to be added to the
	 *            cumulative path cost
	 * @param action
	 *            the String representation of the parent Action taken to create
	 *            the state of this node
	 */
	public Node(Node parent, Pair<String, String> state, int stepCost,
			String action) {
		this.parent = parent;
		this.state = state;
		this.action = action;
		this.pathCost = parent.getPathCost() + stepCost;
		this.depth = parent.getDepth() + 1;
	}

	/**
	 * Returns the parent Node of this node.
	 * 
	 * @return the parent Node of this node
	 */
	public Node getParent() {
		return this.parent;
	}

	/**
	 * Returns the cumulative cost of reaching this node from the initial state
	 * 
	 * @return the cumulative path cost from initial state to this node
	 */
	public int getPathCost() {
		return this.pathCost;
	}

	/**
	 * Returns the depth in the search tree at which this node resides
	 * 
	 * @return the depth at which this node resides in a search tree
	 */
	public int getDepth() {
		return this.depth;
	}

	/**
	 * Returns the Pair of Strings representing the state of the node.
	 * 
	 * @return the Pair of Strings that represent the state of the node
	 */
	public Pair<String, String> getState() {
		return this.state;
	}

	/**
	 * Returns the String representation of the parent Action taken to expand
	 * this node.
	 * 
	 * @return a String that represents the parent Action taken to form this
	 *         node
	 */
	public String getAction() {
		return this.action;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("State: ");
		builder.append(this.state.toString());
		builder.append(", Parent State: ");
		if (this.parent != null) {
			builder.append(this.parent.getState().toString());
		}
		builder.append(", Action: ");
		builder.append(this.action);
		builder.append(", Path Cost: ");
		builder.append(this.pathCost);
		builder.append(", Depth: ");
		builder.append(this.depth);

		return builder.toString();
	}

	@Override
	public boolean equals(Object object) {

		boolean areEqual = false;

		if (object == null) {
			areEqual = false;
		}
		if (object == this) {
			areEqual = true;
		}
		if (!(object instanceof Node)) {
			areEqual = false;
		}

		Node nodeObject = (Node) object;
		areEqual = this.toString().equals(nodeObject.toString());

		return areEqual;
	}

	@Override
	public int compareTo(Object o) {
		int result;
		Node node = (Node) o;
		if (this.pathCost == node.getPathCost()) {
			result = 0;
		} else if (this.pathCost < node.getPathCost()) {
			result = -1;
		} else {
			result = 1;
		}
		return result;
	}
}
