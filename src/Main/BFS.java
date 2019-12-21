package Main;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.lang.reflect.Array;
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
    private boolean solution;
    private GridPane gridPane;
    private VBox buttons;

    public BFS(Graph graph, GridPane gridPane, VBox vbox) {
        this.graph = graph;
        this.gridPane = gridPane;
        this.buttons = vbox;
        start = graph.getStart();
        finish = graph.getFinish();
        queue = new LinkedList<MyNode>();
        predecessors = new HashMap<MyNode, MyNode>();
        }


    @Override
    public void run() {

        //Start off by saying we don't have a solution
        solution = false;

        //add the start node into the queue
        queue.add(start);

        //add start node to predecessors map
        predecessors.put(start, start);

        //loop until the finish node is found or queue is empty
        while (!queue.isEmpty()) {

            MyNode currentNode = queue.remove(0);
            if (currentNode == finish) {
                solution = true;
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

        if (!solution) {
            System.out.println("No Path");
        }


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

        ArrayList<String> newPath = new ArrayList<>();
        int cost = 0;
        while (!path.isEmpty()) {
            newPath.add(path.remove(path.size() - 1));
            cost++;
        }



        for (String element : newPath) {
            MyButton b = (MyButton) gridPane.getChildren().get(Integer.parseInt(element) - 1);
            b.setStyle("-fx-background-color: #3342FF; ");
        }

        gridPane.setDisable(true);
        buttons.getChildren().get(0).setDisable(true);
        buttons.getChildren().get(1).setDisable(true);

        System.out.println("Path from Start -> Finish: " + newPath);
        System.out.println("Cost: " + (cost - 1));

    }

}
