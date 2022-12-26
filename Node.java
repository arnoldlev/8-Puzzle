import java.util.*;

public class Node {

    private String state;
    private Node parent;
    private Action action;
    private int cost;

    public Node(String state, Node parent, Action a, int cost) {
        setState(state);
        setParent(parent);
        setAction(a);
        setCost(cost);
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setAction(Action a) {
        action = a;
    }

    public Action getAction() {
        return action;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getEmptyPos() {
        return state.indexOf("0");
    }

    public ArrayList<Node> generateSuccessors() {
        ArrayList<Node> nodes = new ArrayList<Node>();
        String curr = getState();
        int emptyPos = getEmptyPos();

        // UP
        if (emptyPos - 3 >= 0) {
            String newState = swap(curr, emptyPos, emptyPos - 3);
            nodes.add(new Node(newState, this, Action.UP, getCost() + 1));
        }

        // DOWN
        if (emptyPos + 3 < 9) {
            String newState = swap(curr, emptyPos, emptyPos + 3);
            nodes.add(new Node(newState, this, Action.DOWN, getCost() + 1));
        }

        //LEFT
        if (emptyPos != 0 & emptyPos % 3 != 0) {
            String newState = swap(curr, emptyPos, emptyPos - 1);
            nodes.add(new Node(newState, this, Action.LEFT, getCost() + 1));
        }

        //RIGHT
        if ((emptyPos + 1) % 3 != 0) {
            String newState = swap(curr, emptyPos, emptyPos + 1);
            nodes.add(new Node(newState, this, Action.RIGHT, getCost() + 1));
        }

        return nodes;
    }

    private String swap(String str, int i, int j) {
        StringBuilder sb = new StringBuilder(str);
        sb.setCharAt(i, str.charAt(j));
        sb.setCharAt(j, str.charAt(i));
        return sb.toString();
    }


    
}