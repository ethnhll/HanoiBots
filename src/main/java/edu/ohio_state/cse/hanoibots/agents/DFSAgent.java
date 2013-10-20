package edu.ohio_state.cse.hanoibots.agents;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import edu.ohio_state.cse.hanoibots.models.Node;
import edu.ohio_state.cse.hanoibots.models.NodeUtility;
import edu.ohio_state.cse.hanoibots.models.Pair;

/**
 * This is the implementation for an Iterative Deepening Search agent.
 * 
 * Based loosely off of the code found in the book, the code repository for
 * iterative deepening, and the code found here...
 * 
 * http://www.codeproject.com/Articles/203828/AI-Simple-Implementation-of-
 * Uninformed-Search-Stra
 * 
 * @author hillet
 * 
 */
public class DFSAgent implements Agent {

    /**
     * A helper method that provides the bulk of the search. Essentially a Depth
     * Limited Search.
     * 
     * @param initialStatePair
     *            the state Pair that represents the initial state space of the
     *            4 robots problem.
     * @param depth
     *            the depth limit of the search
     * @param result
     *            the list to be maintained which will contain a cutoff marker
     *            to alert the algorithm it has finished.
     * @return a list of actions taken to reach the solution path
     */
    private List<String> depthLimitSearch(
            Pair<String, String> initialStatePair, int depth,
            List<String> result) {

        Node parent = new Node(initialStatePair);
        List<Node> frontier = new Stack<Node>();
        frontier.add(0, parent);
        boolean done = false;
        while (!done && (!frontier.isEmpty())) {
            Node node = frontier.remove(0);
            // Report the node and its relevant data
            System.out.println(node.toString());
            // Goal test, left side of state Pair is empty
            if (node.getState().getLeft().length() == 0) {
                done = true;
                result.add("Cutoff");
                result = NodeUtility.retrieveActions(result, node);
                //Report the optimal path info
                System.out.println("Path found...");
                System.out.println(node.toString());
            }
            if (node.getDepth() != depth) {
                Set<Node> children = NodeUtility.expandNodes(node);
                for (Node child : children) {
                    frontier.add(child);
                }
            }
        }
        return result;
    }

    @Override
    public List<String> performSearch(Pair<String, String> initialStatePair) {

        int depth = 0;
        List<String> result = new LinkedList<String>();
        while (!result.contains("Cutoff")) {
            result = depthLimitSearch(initialStatePair, depth, result);
            depth++;
        }
        result.remove("Cutoff");
        return result;

    }

}
