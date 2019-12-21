package Main;

import java.util.ArrayList;

public class Node {

    private String name;
    private ArrayList<Node> neighbors;

    public Node(String name) {
        this.name = name;
        neighbors = new ArrayList<>();
    }

    public String getNeighborsAsString() {
        String names = "";
        for (int i = 0; i < neighbors.size(); i++) {
            names += neighbors.get(i).getName() + ", ";
        }
        return names;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Node: " + this.name + "\n " + getNeighborsAsString();
    }
}