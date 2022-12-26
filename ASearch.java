import java.util.*;

public class ASearch {

    private PriorityQueue<Node> frontier;
    private HashSet<Node> exploredSet;
    private int searchCost;

    public ASearch(Node initialNode, boolean h1) {
        if (h1)
            frontier = new PriorityQueue<>( (n, n2) -> (n.getCost() + Utils.h1(n)) - (n2.getCost() + Utils.h1(n2)) );
        else
            frontier = new PriorityQueue<>( (n, n2) -> (n.getCost() + Utils.h2(n)) - (n2.getCost() + Utils.h2(n2)) );    
        exploredSet = new HashSet<>();
        frontier.add(initialNode);
        searchCost = 0;
    }

    public int getSearchCost() {
        return searchCost;
    }

    public Node start() {
        while (!frontier.isEmpty()) {
            Node curr = frontier.poll();
            exploredSet.add(curr);
            
            Utils.displayBoard(curr.getState());

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
