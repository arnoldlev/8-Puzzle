import java.util.*;

public class ASearch {

    private PriorityQueue<Node> frontier;
    private HashSet<Node> exploredSet;
    private int searchCost;

    /**
     * Create the A* Search Object 
     * @param initialNode String - Initial State 
     * @param h1 Boolean - Use Heuristic 1 or Heuristic 2
     */
    public ASearch(Node initialNode, boolean h1) {
        exploredSet = new HashSet<>();
        if (h1)
            frontier = new PriorityQueue<>( (n, n2) -> (n.getCost() + n.h1()) - (n2.getCost() + n2.h1()));
        else
            frontier = new PriorityQueue<>( (n, n2) -> (n.getCost() + n.h2()) - (n2.getCost() + n2.h2()) );    

        frontier.add(initialNode);
        searchCost = 0;
    }

    /**
     * Get Total Search Cost after running 
     * @return Search Cost
     */
    public int getSearchCost() {
        return searchCost;
    }

    /**
     * Start the A* Search
     * @return Node at the Goal State, null if failure
     */
    public Node start(boolean print) {
        while (!frontier.isEmpty()) {
            Node curr = frontier.poll();
            exploredSet.add(curr);
            
            if (print) {
                Utils.displayBoard(curr.getState());
                System.out.println("^ - Current Step Cost: " + curr.getCost() + "  || Search Cost: " + searchCost);
            }

            if (curr.getState().equalsIgnoreCase(Utils.GOAL)) {
                return curr;
            }

            ArrayList<Node> successors = curr.generateSuccessors();
            for (int i = 0; i < successors.size(); i++) {
                if (!exploredSet.contains(successors.get(i))) {
                    searchCost++;
                    frontier.add(successors.get(i));
                }
            }
        }

        return null;
    }
    
}
