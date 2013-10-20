package edu.ohio_state.cse.hanoibots.drivers;


import java.util.List;

import edu.ohio_state.cse.hanoibots.agents.AStarAgent;
import edu.ohio_state.cse.hanoibots.agents.Agent;
import edu.ohio_state.cse.hanoibots.agents.BFSAgent;
import edu.ohio_state.cse.hanoibots.agents.DFSAgent;
import edu.ohio_state.cse.hanoibots.agents.UCAgent;
import edu.ohio_state.cse.hanoibots.models.Pair;

/**
 * A TestHarness class that distinguishes between the various search agents and
 * calls them to perform their respective searches on an arbitrary state within
 * the state space of the 4 robots problem.
 * 
 * @author hillet
 * 
 */
public class TestHarness {

    /**
     * the search agent that is chosen by the user in the UI
     */
    private Agent searchAgent;

    /**
     * the pair of Strings that makes up the initial state for the problem. The
     * left and right Strings correspond to the left and right side of the chasm
     * (respectively)
     */
    private Pair<String, String> initialStatePair;

    /**
     * Class constructor for the TestHarness that initializes a dynamic search
     * Agent and an initial state Pair from two strings that represent the
     * placement of the robots.
     * 
     * 
     * @param agentSelection
     *            the user-selected search agent to solve the problem
     * @param initialStateLeft
     *            the String that represents the left side of the chasm
     * @param initialStateRight
     *            the String that represents the right side of the chasm
     */
    public TestHarness(String agentSelection, String initialStateLeft,
            String initialStateRight) {

        this.initialStatePair = new Pair<String, String>(initialStateLeft,
                initialStateRight);
        switch (agentSelection) {
            case ("Breadth-First"): {
                this.searchAgent = new BFSAgent();
                break;
            }
            case ("Uniform-Cost"): {
                this.searchAgent = new UCAgent();
                break;
            }
            case ("Depth-First"): {
                this.searchAgent = new DFSAgent();
                break;
            }
            case ("A*"): {
                this.searchAgent = new AStarAgent();
                break;
            }
        }
        // Perform and output the search
        List<String> optimalPath = this.searchAgent
                .performSearch(this.initialStatePair);

        System.out.println("Actions taken to reach solution path...");

        //Actions will display in reverse order otherwise...
        for (int i = optimalPath.size() - 1; i >= 0; i--) {
            String action = optimalPath.get(i);
            System.out.println(action);
        }

    }

}
