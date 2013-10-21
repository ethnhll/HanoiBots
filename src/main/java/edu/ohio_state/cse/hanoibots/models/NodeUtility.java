package edu.ohio_state.cse.hanoibots.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author hillet
 * 
 */
public class NodeUtility {

	/*
	 * Private constructor to prevent instantiation
	 */
	private NodeUtility() {
		// No code necessary
	}

	/**
	 * 
	 * @param singleChar
	 * @return
	 */
	private static int singleCharStepCost(char singleChar) {
		int stepCost = 0;
		switch (singleChar) {
		case ('A'): {
			stepCost = 1;
			break;
		}
		case ('B'): {
			stepCost = 2;
			break;
		}
		case ('C'): {
			stepCost = 5;
			break;
		}
		case ('D'): {
			stepCost = 10;
			break;
		}
		}
		return stepCost;
	}

	/**
	 * 
	 * @param leftChar
	 * @param rightChar
	 * @return
	 */
	private static int dualCharStepCost(char leftChar, char rightChar) {
		int stepCost = 0;
		if ((leftChar == 'D') || (rightChar == 'D')) {
			stepCost = 10;
		} else if ((leftChar == 'C') || (rightChar == 'C')) {
			stepCost = 5;
		} else {
			// Case when ((leftChar == 'B') || (rightChar == 'B'))
			stepCost = 2;
		}
		return stepCost;
	}

	/**
	 * Produces a set of child nodes from a parent node with all possible
	 * states, actions
	 * 
	 * @param parent
	 *            the parent Node that will be expanded to produce a set of
	 *            child Nodes
	 * @return a Set of expanded Nodes, the children of a parent node
	 */
	public static Set<Node> expandNodes(Node parent) {

		Set<Node> childNodes = new HashSet<Node>();

		Pair<String, String> parentState = parent.getState();

		String leftSide = parentState.getLeft();
		String rightSide = parentState.getRight();

		if (leftSide.contains("P")) {
			// Iterate once to get all single combinations
			String tempLeft = leftSide.replaceAll("P", "");

			// Now iterate to get all dual character combinations
			for (int i = 0; i < tempLeft.length() - 1; i++) {
				char leftChar = tempLeft.charAt(i);
				for (int j = i + 1; j < tempLeft.length(); j++) {
					char rightChar = tempLeft.charAt(j);

					// Begin creating the Node
					int stepCost = dualCharStepCost(leftChar, rightChar);
					String action = "Move " + Character.toString(rightChar)
							+ Character.toString(leftChar) + 'P' + " right";
					String newLeft = tempLeft.replaceAll("[" + leftChar
							+ rightChar + "]", "");
					String newRight = rightSide.concat(Character
							.toString(rightChar)
							+ Character.toString(leftChar)
							+ 'P');
					Pair<String, String> newState = new Pair<String, String>(
							newLeft, newRight);
					// Create the child Node
					Node child = new Node(parent, newState, stepCost, action);
					childNodes.add(child);
				}
			}
		} else if (rightSide.contains("P")) {
			// Iterate once to get all single combinations
			String tempRight = rightSide.replaceAll("P", "");

			for (int i = 0; i < tempRight.length(); i++) {
				char single = tempRight.charAt(i);

				int stepCost = singleCharStepCost(single);
				String action = "Move " + single + 'P' + " left";

				// Create the new state Pair
				String newRight = tempRight.replaceAll(
						Character.toString(single), "");
				String newLeft = leftSide.concat(single + "P");
				Pair<String, String> newState = new Pair<String, String>(
						newLeft, newRight);
				// Create the child Node
				Node child = new Node(parent, newState, stepCost, action);
				childNodes.add(child);
			}
		}
		return childNodes;
	}

	/**
	 * 
	 * @param actionList
	 * @param node
	 * @return
	 */
	public static List<String> retrieveActions(List<String> actionList,
			Node node) {

		if (node.getParent() == null) {
			return actionList;
		} else {
			actionList.add(node.getAction());
			return retrieveActions(actionList, node.getParent());
		}

	}

	/**
	 * Produces a set of child nodes from a parent node with all possible
	 * states, actions. This is a special adaptation for an A* search to include
	 * a heuristic.
	 * 
	 * @param parent
	 *            the parent Node that will be expanded to produce a set of
	 *            child Nodes
	 * @return a Set of expanded Nodes, the children of a parent node
	 */
	public static Set<Node> expandNodesAStar(Node parent) {

		Set<Node> childNodes = new HashSet<Node>();

		Pair<String, String> parentState = parent.getState();

		String leftSide = parentState.getLeft();
		String rightSide = parentState.getRight();

		if (leftSide.contains("P")) {
			// Iterate once to get all single combinations
			String tempLeft = leftSide.replaceAll("P", "");

			// Now iterate to get all dual character combinations
			for (int i = 0; i < tempLeft.length() - 1; i++) {
				char leftChar = tempLeft.charAt(i);
				for (int j = i + 1; j < tempLeft.length(); j++) {
					char rightChar = tempLeft.charAt(j);

					// Begin creating the Node
					int stepCost = dualCharStepCost(leftChar, rightChar);
					String action = "Move " + Character.toString(rightChar)
							+ Character.toString(leftChar) + 'P' + " right";
					String newLeft = tempLeft.replaceAll("[" + leftChar
							+ rightChar + "]", "");
					String newRight = rightSide.concat(Character
							.toString(rightChar)
							+ Character.toString(leftChar)
							+ 'P');
					Pair<String, String> newState = new Pair<String, String>(
							newLeft, newRight);
					// Create the child Node
					Node child = new Node(parent, newState, stepCost, action);
					childNodes.add(child);
				}
			}
		} else if (rightSide.contains("P")) {
			// Iterate once to get all single combinations
			String tempRight = rightSide.replaceAll("P", "");

			for (int i = 0; i < tempRight.length(); i++) {
				char single = tempRight.charAt(i);

				int stepCost = singleCharStepCost(single);
				String action = "Move " + single + 'P' + " left";

				// Create the new state Pair
				String newRight = tempRight.replaceAll(
						Character.toString(single), "");
				String newLeft = leftSide.concat(single + "P");
				Pair<String, String> newState = new Pair<String, String>(
						newLeft, newRight);
				// Create the child Node
				Node child = new Node(parent, newState, stepCost, action);
				childNodes.add(child);
			}
		}
		return childNodes;
	}
}
