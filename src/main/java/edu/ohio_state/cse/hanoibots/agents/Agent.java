package edu.ohio_state.cse.hanoibots.agents;
import java.util.List;

import edu.ohio_state.cse.hanoibots.models.Pair;

/**
 * Interface written in order to generalize the behaviour of a search agent.
 * 
 * A search agent must, given an initial state, find a solution and return the
 * actions taken to reach the solution it found.
 * 
 * @author hillet
 * 
 */
public interface Agent {

    /**
     * This method very generally will provide the functionality for the search
     * algorithms the search agents employ to find solutions.
     * 
     * @param initialStatePair
     *            the initial state of the four robots problem
     * @return the list of actions taken to reach the solution/goal state of the
     *         problem
     */
    List<String> performSearch(Pair<String, String> initialStatePair);

}
