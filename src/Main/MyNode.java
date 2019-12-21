package Main;

import java.util.ArrayList;

public class MyNode {

    private String name;
    private ArrayList<MyNode> neighbors;
    private Type type;

    public enum Type {
        PATH,
        WALL,
        START,
        FINISH
    }

    public MyNode(String name, Type type) {
        this.name = name;
        this.type = type;
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
        return "Node: " + this.name + " type: " + type + "\n " + getNeighborsAsString();
    }

    public void addNeighbor(MyNode other) {
        neighbors.add(other);
    }

    public void removeNeighbor(MyNode other) {
        neighbors.remove(other);
    }

    public MyNode pop() {
        return neighbors.remove(0);
    }

    public Type getType() {
        return type;
    }

}