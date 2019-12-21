package Main;

import java.util.*;

/**
 * BFS Solves any given maze with a possible solution in a seperate thread.
 * This allows us to slow down the solving process so that we can create a animation
 * showing BFS in action.
 */
public class BFS extends Thread implements Runnable {

    private Graph graph;
    private MyNode start;
    private MyNode finish;
    private List<MyNode> queue;
    private Map<MyNode, MyNode> predecessors;

    public BFS(Graph graph) {
        this.graph = graph;
        start = graph.getStart();
        finish = graph.getFinish();
        queue = new LinkedList<MyNode>();
        predecessors = new HashMap<MyNode, MyNode>();
        }


    @Override
    public void run() {

        //add the start node into the queue
        queue.add(start);

        //add start node to predecessors map
        predecessors.put(start, start);

        //loop until the finish node is found or queue is empty
        while (!queue.isEmpty()) {

            MyNode currentNode = queue.remove(0);
            System.out.println("current node: " + currentNode);
            if (currentNode == finish) {
                constructPath();
                break;
            }

            //iterate through all neighbors of the current node
            ArrayList<MyNode> neighbors = currentNode.getNeighbors();
            for (MyNode node : neighbors) {

                if (!predecessors.containsKey(node)) {
                    predecessors.put(node, currentNode);
                    queue.add(node);
                }

            }
        }
        //if the queue runs empty no path could be found
        System.out.println("No Path");

    }

    private void constructPath() {
        ArrayList<String> path = new ArrayList<>();

        if (predecessors.containsKey(finish)) {
            MyNode currentNode = finish;
            while (currentNode != start) {
                path.add(currentNode.getName());
                currentNode = predecessors.get(currentNode);
            }
            path.add(start.getName());
        }

        for (String str : path) {
            System.out.println(str);
        }

    }

}
