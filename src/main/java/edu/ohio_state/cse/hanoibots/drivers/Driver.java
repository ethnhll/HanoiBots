package edu.ohio_state.cse.hanoibots.drivers;


import java.util.Scanner;

/**
 * This class is the main driver class for the whole program. Some nicely
 * formatted output is shown to the user as a UI that details the selections the
 * user can make and then reports back the selections made by the user.
 * 
 * Users select a search agent to solve the four robots crossing a chasm problem
 * and then provide an initial state for the search agent using two Strings. At
 * least one of these Strings must be non-empty for the code to produce a
 * meaningful results.
 * 
 * @author hillet
 * 
 */
public class Driver {

    /**
     * Drives the whole program with a nice UI.
     * 
     * @param args
     *            no command line arguments should be necessary.
     */
    public static void main(String[] args) {

        /*
         * Prompt the user to pick a search agent from a list.
         */

        System.out.println("|-----------------------------------------------|");
        System.out.println("| Please choose a search agent from list below. |");
        System.out.println("|-----------------------------------------------|");
        System.out.println("|                    AGENTS                     |");
        System.out.println("|-----------------------------------------------|");
        System.out.println("| a) Breadth-First Search                       |");
        System.out.println("| b) Depth-First Search (Deepening)             |");
        System.out.println("| c) Uniform-Cost Search                        |");
        System.out.println("| d) A* Search                                  |");
        System.out.println("|-----------------------------------------------|");
        System.out.print("\n   Enter selection (a-d, case insensitive): ");

        Scanner keyboard = new Scanner(System.in);
        String agentSelection = keyboard.nextLine().toLowerCase();

        switch (agentSelection) {
            case ("a"): {
                agentSelection = "Breadth-First";
                System.out
                        .println("You have selected the Breadth-First Search agent.\n");
                break;
            }
            case ("b"): {
                agentSelection = "Depth-First";
                System.out
                        .println("You have selected the Depth-First Search agent.\n");
                break;
            }
            case ("c"): {
                agentSelection = "Uniform-Cost";
                System.out
                        .println("You have selected the Uniform-Cost Search agent.\n");
                break;
            }
            case ("d"): {
                agentSelection = "A*";
                System.out.println("You have selected the A* Search agent.\n");
                break;
            }
            default: {
                System.out
                        .println("The input you entered was not recognized.\n"
                                + "Please re-run the program and make a valid\n"
                                + "selection from the list.");
                keyboard.close();
                System.exit(0);
            }
        }

        /*
         * Prompt the user to enter input strings for the initial states.
         */
        System.out.println("|-----------------------------------------------|");
        System.out.println("|Please enter input string(s) for initial state |");
        System.out.println("|-----------------------------------------------|");
        System.out.print("\n   Left side configuration: ");
        String initialStateLeft = keyboard.nextLine().toUpperCase();
        System.out.print("   Right side configuration: ");
        String initialStateRight = keyboard.nextLine().toUpperCase();
        System.out.println('\n');

        // Output selections made to the user for clarity
        System.out.println("|-----------------------------------------------|");
        System.out
                .println("|               Input Verification              |\n");
        System.out.println("Agent: " + agentSelection + ", Left State: "
                + initialStateLeft + ", Right State: " + initialStateRight);
        System.out.println("|-----------------------------------------------|\n");

        /*
         * Now hand this information to the TestHarness.
         */
        TestHarness harness = new TestHarness(agentSelection, initialStateLeft,
                initialStateRight);
        /*
         * Now initiate the solution itself.
         */

        keyboard.close();

    }

}
