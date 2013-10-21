package edu.ohio_state.cse.hanoibots.agents;

import java.util.List;

import edu.ohio_state.cse.hanoibots.models.Pair;

/**
 * Interface written in order to specify the general behavior of an Agent in the
 * context of the HanoiBots
 * {@link edu.ohio_state.cse.hanoibots.models.ProblemCase problem}.
 * <p>
 * A search agent must, given an initial state, find a solution and return the
 * actions taken to reach the solution it found. A solution is one in which, as
 * an abstract state of the problem, all robots have crossed to the right side
 * of the chasm, leaving no robots on the left side.
 * </p>
 * 
 * @author EthanHill
 * 
 */
public interface Agent {

	/**
	 * This method very generally will provide the functionality for the search
	 * algorithms that search agents employ to find solutions for the HanoiBots
	 * {@link edu.ohio_state.cse.hanoibots.models.ProblemCase problem}.
	 * 
	 * @param initialStatePair
	 *            the initial state of the robots problem, referring to the
	 *            configuration of robots on each side of the chasm
	 * @return the list of actions taken to reach the solution/goal state of the
	 *         problem (no robots remain on the right side of the chasm)
	 */
	List<String> performSearch(Pair<String, String> initialStatePair);

}
