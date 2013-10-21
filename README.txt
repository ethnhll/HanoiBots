HanoiBots
=========

This project is a slight variation of an AI programming assignment for CSE 3521 at the Ohio State University. The intent is to expand and improve upon the search agents developed and then convert the test harness/driver class into an interactive Android GUI application.


Original Project Instructions


Programming Assignment 02: Search Algorithms
Reminder: Programming assignments must be turned in via the Carmen dropbox.  See the instructions in Carmen for more details.

In this assignment you will implement a variety of search algorithms as discussed in class.  This is a variant of a classic "toy" search problem in AI.  Consider four poor robotic explorers who are down to their last power pack that they're sharing amongst themselves - all four robots are plugged into a single power pack.  Their home 'base' is a research station on the other side of a large chasm.  Fortunately for them, the chasm can be crossed by a narrow bridge.  Unfortunately, because the bridge is so narrow they can only cross in single file - and the wires on their power pack are only long enough for two of them to cross at a time with the power pack shared between them.

Our robotic explorers need to plan a path that will allow them to cross the chasm.  They can only cross in at most pairs and each pair can only move across if they are connected to the power pack.  Your assignment is to code the search algorithms that will allow them to search through all of the possible sequences of actions that they could take in order for all four exploration robots to get to the other side of the bridge and move on to their base, where they can settle down with free power packs for all.  The purpose for this assignment is for you to gain some more hands on familiarity with the basic breadth-first, depth-first, and uniform cost search algorithms, and to experience how heuristic-based algorithms like A* perform in comparison.

The Problem
As discussed above, our four exploratory robots have found themselves tethered together on a single power pack and now need to cross a narrow bridge that spans an amazingly deep chasm.  In addition to the restriction that our explorers can only cross two at a time and only if they're carrying the power pack, there is the added problem that they all move at different maximum speeds.  Robot A-1 can cross the bridge by himself in only 1 minute.  Robot BR-37 can cross it by himself in 2 minutes.  Robot C3-P7 takes 5 minutes to cross the bridge and Robot DB99 takes a full 10 minutes to cross it by himself.  If two robots cross together, they both can only move at the speed of the slowest robot in the pair.  And, of course, the power pack cannot cross the bridge by itself - at least one robot is needed to carry it across the bridge.

Your job is to implement 4 search algorithms to solve this problem.  You will need to write four different search agents:

    DFSAgent: a depth-first search agent
    BFSAgent: a breadth-first search agent
    UCAgent: a uniform-cost search agent
    AStarAgent: an A* search agent

For A* search, you can use the heuristic that everyone moves at the same speed as the fastestrobot. (Think: why is this admissible?).  Note too that we are asking you to code DFS and BFS - you are not expected to be able to get the best time with these search strategies, so don't try to force these strategies to return the best time.  You should always get the best time with the UC and A* search strategies.

As with Assignment 01, you will need to write agent code AND Test Harness code.  The test harness for this code will call the search agent with two strings containing the letters "ABCDP" for robots A-D assigned by the first letter of their names given above and the power pack (P) which represents the initial conditions of the search; the first string represents the robots/pack on the start side of the bridge, and the second is the set of robots (and possibly power pack) on the end side of the bridge. (This allows us to consider situations where the robots had screwed up the order of operations and need help planning how to finish up optimally.) For example:

    "ABCDP", "" : traditional start of puzzle where all robots and the power pack are on the start side
    "AC", "BDP" : a starting point where A and C are on the start side, but B and D have crossed with the power pack.

For ease of programming you can assume that the input is correctly defined -- all 5 letters occur only once in the two strings and P never appears alone.  As a check on your code, if all robots start on the same side with the power pack, everyone can cross the bridge in 17 minutes.

The agent should take the start state (as two strings) and return a list of actions that correspond to the optimal sequence from that state. The test harness should report the list of actions. The agent should report each node expanded off of the queue (which should include minimally state, parent state, relevant cost(s), and depth) at the end the number of nodes expanded in the search and the total path cost of the optimal path.

You may write your agent from scratch (probably easier) or utilize the objects in the code repository provided with the textbook. Once again, it may be helpful to utilize the pseudocode in the book to help design your agent. If you use the step names in the book such as ExpandNode and QueueingFunction, it will be easy to write the four agents using most of the same code over and over again. Your program will be graded on whether it accepts input in the specified format (you should provide us a way to call each agent with two strings from the command line).
Make sure your depth-first search does not go into an infinite loop. You can prevent this by implementing iterative deepening search, implementing a simpler depth-limited search, or checking for repeated states.




