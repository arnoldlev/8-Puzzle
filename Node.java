import java.util.*;

public class Node {

    private String state;
    private Node parent;
    private int cost;
    private int depth;

    public Node(String state, Node parent, int cost, int depth) {
        setState(state);
        setParent(parent);
        setCost(cost);
        setDepth(depth);
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setDepth(int d) {
        depth = d;
    }

    public int getDepth() {
        return depth;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    /**
     * Gets the step cost - g(n)
     * @return g(n)
     */
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * Gets the position of empty tile
     * @return Index of empty tile
     */
    public int getEmptyPos() {
        return state.indexOf("0");
    }

    /**
     * Generates all possible successor states from current state
     * @return List of all successors
     */
    public ArrayList<Node> generateSuccessors() {
        ArrayList<Node> nodes = new ArrayList<Node>();
        String curr = getState();
        int emptyPos = getEmptyPos();

        // UP
        if (emptyPos - 3 >= 0) {
            String newState = swap(curr, emptyPos, emptyPos - 3);
            nodes.add(new Node(newState, this, getCost() + 1, getDepth() + 1));
        }

        // DOWN
        if (emptyPos + 3 < 9) {
            String newState = swap(curr, emptyPos, emptyPos + 3);
            nodes.add(new Node(newState, this, getCost() + 1, getDepth() + 1));
        }

        //LEFT
        if (emptyPos != 0 & emptyPos % 3 != 0) {
            String newState = swap(curr, emptyPos, emptyPos - 1);
            nodes.add(new Node(newState, this, getCost() + 1, getDepth() + 1));
        }

        //RIGHT
        if ((emptyPos + 1) % 3 != 0) {
            String newState = swap(curr, emptyPos, emptyPos + 1);
            nodes.add(new Node(newState, this, getCost() + 1, getDepth() + 1));
        }

        return nodes;
    }

    /**
     * Checks if Puzzle is Solvable
     * Odd # is Not Solvable and Even # is Solvable
     * @return Number of Tiles that are inverted 
     */
    public int getInversions() {
        int[] arr = new int[9];
        for (int i = 0; i < state.length(); i++) {
            arr[i] = state.charAt(i) - '0';
        }

        int inv = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (arr[i] > 0 && arr[j] > 0 && arr[i] > arr[j])
                    inv++;
            }
        }

        return inv;
    }

    /**
     * Heuristic #1 Function
     * Calculates all misplaced tiles
     * @return Number of misplaced tiles
     */
    public int h1() {
        String s = getState();
        int c = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) - '0' != i) {
                c++;
            }
        }
        return c;
    }

    /**
     * Heuristic #2 Function
     * Calculates the sum of all tiles distances from proper location (Manhattan distance)
     * @return Manhattan distance sum
     */
    public int h2() {
        String s = getState();
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            int x = s.charAt(i) - '0';
            if (x == i || x == 0) {
                continue;
            }

            int row = x / 3, col = x % 3;
            int goalR = i / 3, goalC = i % 3;
            sum += Math.abs(col - goalC) + Math.abs(row - goalR);
        }
        return sum;
    }


    /**
     * Helper function to swap tiles
     * @param str String to modify
     * @param i Position of tile 1
     * @param j Position of tile 2
     * @return New String with modified move
     */
    private String swap(String str, int i, int j) {
        StringBuilder sb = new StringBuilder(str);
        sb.setCharAt(i, str.charAt(j));
        sb.setCharAt(j, str.charAt(i));
        return sb.toString();
    }


    /**
     * Proper hash of a Node is the hashed String
     */
    @Override
    public int hashCode() {
        return state.hashCode();
    }


    /**
     * Two Nodes are equal if the states are the same
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Node other = (Node) obj;
        return this.getState().equalsIgnoreCase(other.getState());
    }


    
}