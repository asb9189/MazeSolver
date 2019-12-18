package Main;

import java.util.ArrayList;

public class Graph {

    private int vertices;
    private ArrayList<Node> nodes;

    public Graph(int vertices) {
        this.vertices = vertices;
        nodes = new ArrayList<>();
        initializeGraph();
    }

    private void initializeGraph() {
    }

    private class Node {

        private String name;
        private ArrayList<Node> neighbors;
        private ArrayList<Edge> edges;
        public Node(String name) {
            this.name = name;
            neighbors = new ArrayList<>();
            edges = new ArrayList<>();
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

    private class Edge {

        private Node start;
        private Node end;
        private int weight;

        public Edge(Node start, Node end, int weight) {

            this.start = start;
            this.end = end;
            this.weight = weight;

        }



    }
}
