package edu.ohio_state.cse.hanoibots.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class is intended to provide various utility methods for use with a
 * {@link edu.ohio_state.cse.hanoibots.models.Node Node}.
 * 
 * @author Ethan Hill
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
	 * This method is intended to calculate a step cost of a "Robot" represented
	 * as a character.
	 * 
	 * @param singleChar
	 *            the char that is used to represent a single robot
	 * @return a step cost of the Robot represented by {@code singleCha}
	 */
	private static int singleCharStepCost(char singleChar) {
		return Character.getNumericValue(singleChar);
	}

	/**
	 * This method is intended to calculate the step cost of "Robots"
	 * represented as characters. The step cost takes the value of the robot
	 * with the highest step cost.
	 * 
	 * @param leftChar
	 *            the robot representing
	 * @param rightChar
	 * @return
	 */
	private static int dualCharStepCost(char leftChar, char rightChar) {
		int stepCost = 0;

		if (Character.valueOf(leftChar) >= Character.valueOf(rightChar)) {
			stepCost = Character.valueOf(leftChar);
		} else {
			stepCost = Character.valueOf(rightChar);
		}

		return stepCost;
	}



	/**
	 * Produces a set of child nodes from a parent node with all possible
	 * states, and the actions to produce those states.
	 * 
	 * @param parent
	 *            the parent Node that will be expanded to produce a set of
	 *            child Nodes
	 * @return a Set of expanded Nodes, the children of a parent node
	 */
	public static Set<Node> expandNodes(Node parent) {

		/*
		 * Retrieve the state representation from the parent (the left and right
		 * side of the chasm)
		 */
		Pair<String, String> parentState = parent.getState();
		String leftSide = parentState.getLeft();
		String rightSide = parentState.getRight();

		Set<Node> childNodes = new HashSet<Node>();
		
		
		
		// if left side has p
			// generate the single robot moves left to right
			//generate the double robot moves
		// else
			// generate the single robot moves right to left
			// generate the double robot moves
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		if (leftSide.contains("P")) {
			// Iterate once to get all single combinations
			String tempLeft = leftSide.replaceAll("P", "");
			
			for ()
			

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
	 * Allows for recursive expansion of the actions taken to create the Node
	 * and returns these actions as a List.
	 * 
	 * @param actionList
	 *            the list of actions taken thus far in the progression of a
	 *            Search {@link edu.ohio_state.cse.hanoibots.agents.Agent Agent}
	 *            in finding a state node which represents a solution
	 * @param node
	 *            the node whose actions and parent actions taken to produce
	 *            {@code node} are added to the returned List
	 * @return a List containing all the actions taken to create {@code node}
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
