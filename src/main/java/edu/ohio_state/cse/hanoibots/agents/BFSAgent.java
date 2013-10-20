package edu.ohio_state.cse.hanoibots.agents;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import edu.ohio_state.cse.hanoibots.models.Node;
import edu.ohio_state.cse.hanoibots.models.NodeUtility;
import edu.ohio_state.cse.hanoibots.models.Pair;

/**
 * This class is the Breadth-First Search search agent implementation. Expanding
 * nodes are put into an ordinary queue.
 * 
 * @author hillet
 * 
 */
public class BFSAgent implements Agent {

    @Override
    public List<String> performSearch(Pair<String, String> initialStatePair) {
        List<String> actionsToSolution = new LinkedList<String>();
        Node parent = new Node(initialStatePair);

        // Left side of state is empty, solution is reached
        if (parent.getState().getLeft().length() == 0) {
            actionsToSolution.add(parent.getAction());
        } else {
            Queue<Node> frontier = new LinkedList<Node>();
            Set<Pair<String, String>> exploredSet = new HashSet<Pair<String, String>>();
            frontier.add(parent);
            boolean done = false;
            while (!done) {
                // Return a failure string, something was wrong
                if (frontier.isEmpty()) {
                    actionsToSolution.add("Failure");
                    return actionsToSolution;
                }
                Node node = frontier.remove();
                // Report the node and its relevant data
                System.out.println(node.toString());
                exploredSet.add(node.getState());
                Set<Node> children = NodeUtility.expandNodes(node);
                for (Node child : children) {
                    /*
                     * Child state is not explored, child is not on frontier
                     */
                    if ((!frontier.contains(child))
                            && (!exploredSet.contains(child.getState()))) {
                        // Goal test, left side of state Pair is empty
                        if (child.getState().getLeft().length() == 0) {
                            done = true;
                            actionsToSolution = NodeUtility.retrieveActions(
                                    actionsToSolution, child);
                            //Report the optimal path info
                            System.out.println("Path found...");
                            System.out.println(child.toString());
                        } else {
                            frontier.add(child);
                        }
                    }
                }

            }

        }

        return actionsToSolution;
    }
}
