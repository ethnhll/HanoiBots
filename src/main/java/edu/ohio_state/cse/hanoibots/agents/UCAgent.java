package edu.ohio_state.cse.hanoibots.agents;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import edu.ohio_state.cse.hanoibots.models.Node;
import edu.ohio_state.cse.hanoibots.models.NodeUtility;
import edu.ohio_state.cse.hanoibots.models.Pair;

/**
 * The Uniform-Cost search agent implementation. Makes use of a priority queue
 * when expanding nodes.
 * 
 * @author hillet
 * 
 */
public class UCAgent implements Agent {

    @Override
    public List<String> performSearch(Pair<String, String> initialStatePair) {

        Node parent = new Node(initialStatePair);
        Queue<Node> frontier = new PriorityQueue<Node>();
        frontier.add(parent);
        List<String> actionsToSolution = new LinkedList<String>();
        Set<Pair<String, String>> exploredSet = new HashSet<Pair<String, String>>();
        boolean done = false;

        while (!done) {
            // Return a failure string, something was wrong
            if (frontier.isEmpty()) {
                actionsToSolution.add("Failure");
                return actionsToSolution;
            }

            if (parent.getState().getLeft().length() == 0) {
                actionsToSolution.add(parent.getAction());
            } else {
                Node node = frontier.remove();
                // Report the node and its relevant data
                System.out.println(node.toString());

                //Goal test, have we found a solution?
                if (node.getState().getLeft().length() == 0) {
                    done = true;
                    actionsToSolution = NodeUtility.retrieveActions(
                            actionsToSolution, node);
                    //Report the optimal path info
                    System.out.println("Path found...");
                    System.out.println(node.toString());
                } else {
                    exploredSet.add(node.getState());
                    Set<Node> children = NodeUtility.expandNodes(node);
                    for (Node child : children) {
                        //Child state is not explored, child is not on frontier
                        if ((!frontier.contains(child))
                                && (!exploredSet.contains(child.getState()))) {
                            frontier.add(child);
                        }
                        frontier.add(child);
                    }
                }
            }
        }
        return actionsToSolution;
    }

}
