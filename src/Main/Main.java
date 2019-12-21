package Main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Main extends Application {

    /** Graphics */
    private BorderPane borderPane;
    private GridPane gridPane;
    private VBox bottom;
    private int DIM;

    public void init() {

        List< String > args = getParameters().getRaw();
        DIM = Integer.parseInt(args.get(0));

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        borderPane = new BorderPane();
        borderPane.setPadding(new Insets(50, 50, 50, 50));

        VBox vbox = new VBox();

        Button solveButton = new Button("Solve");
        solveButton.setOnAction((event) -> {

            //Check to make sure we have a start and finish node
            int numStart = 0;
            int numFinish = 0;

            for (Node node : gridPane.getChildren()) {
                MyButton b = (MyButton) node;
                if (b.getType() == MyButton.Type.START) {
                    numStart++;
                } else if (b.getType() == MyButton.Type.FINISH) {
                    numFinish++;
                }
            }

            if (numStart != 1 || numFinish != 1) {
                Label l = (Label) bottom.getChildren().get(0);
                l.setText("Path: ");
                Label l2 = (Label) bottom.getChildren().get(1);
                l2.setText("Cost: ");
                return;
            }


            //convert the current buttons into a graph of nodes
            MyButton[][] arr = new MyButton[(int) Math.sqrt(DIM)][(int) Math.sqrt(DIM)];

            List<Node> nodes = gridPane.getChildren();
            ArrayList<MyNode> graphNodes = new ArrayList<>();

            int counter = 0;
            for (int i = 0; i < Math.sqrt(DIM); i++) {
                for (int j = 0; j < Math.sqrt(DIM); j++) {
                    arr[i][j] = (MyButton) nodes.get(counter);
                    counter++;
                }
            }

            //now we have a 2D array with the buttons in correct order
            //we can now create a node for each button and
            //give them the appropriate neighbor's.


            HashMap<Integer, MyNode> finalizedNodes = new HashMap<Integer, MyNode>();

            for (int i = 0; i < Math.sqrt(DIM); i++) {
                for (int j = 0; j < Math.sqrt(DIM); j++) {

                    //Make the new node based off the current button
                    MyButton currentButton = arr[i][j];
                    MyNode node;
                    MyNode top = null;
                    MyNode left = null;
                    MyNode right = null;
                    MyNode bottom = null;

                    if (finalizedNodes.containsKey(Integer.parseInt(currentButton.getName()))) {
                        node = finalizedNodes.get(Integer.parseInt(currentButton.getName()));
                    } else {
                        if (currentButton.getType() == MyButton.Type.WALL) {
                            continue;
                        } else if (currentButton.getType() == MyButton.Type.PATH) {
                            node = new MyNode(currentButton.getText(), MyNode.Type.PATH);
                        } else if (currentButton.getType() == MyButton.Type.START) {
                            node = new MyNode(currentButton.getText(), MyNode.Type.START);
                        } else {
                            node = new MyNode(currentButton.getText(), MyNode.Type.FINISH);
                        }
                    }

                    //above
                    try {

                        MyButton aboveButton = arr[i - 1][j];
                        //check if the button above us has already been created
                        if (finalizedNodes.containsKey(Integer.parseInt(aboveButton.getName()))) {
                            top = finalizedNodes.get(Integer.parseInt(aboveButton.getName()));
                        } else {
                            if (aboveButton.getType() == MyButton.Type.WALL) {
                                top = null;
                            } else if (aboveButton.getType() == MyButton.Type.PATH) {
                                top = new MyNode(aboveButton.getText(), MyNode.Type.PATH);
                                finalizedNodes.put(Integer.parseInt(top.getName()), top);
                            } else if (aboveButton.getType() == MyButton.Type.START) {
                                top = new MyNode(aboveButton.getText(), MyNode.Type.START);
                                finalizedNodes.put(Integer.parseInt(top.getName()), top);
                            } else {
                                top = new MyNode(aboveButton.getText(), MyNode.Type.FINISH);
                                finalizedNodes.put(Integer.parseInt(top.getName()), top);
                            }
                        }
                    } catch(IndexOutOfBoundsException e) {
                        //ignore the exception
                    }

                    //left
                    try {

                        MyButton leftButton = arr[i][j - 1];

                        if (finalizedNodes.containsKey(Integer.parseInt(leftButton.getName()))) {
                            left = finalizedNodes.get(Integer.parseInt(leftButton.getName()));
                        } else {
                            if (leftButton.getType() == MyButton.Type.WALL) {
                                left = null;
                            } else if (leftButton.getType() == MyButton.Type.PATH) {
                                left = new MyNode(leftButton.getText(), MyNode.Type.PATH);
                                finalizedNodes.put(Integer.parseInt(left.getName()), left);
                            } else if (leftButton.getType() == MyButton.Type.START) {
                                left = new MyNode(leftButton.getText(), MyNode.Type.START);
                                finalizedNodes.put(Integer.parseInt(left.getName()), left);
                            } else {
                                left = new MyNode(leftButton.getText(), MyNode.Type.FINISH);
                                finalizedNodes.put(Integer.parseInt(left.getName()), left);
                            }
                        }
                    } catch(IndexOutOfBoundsException e) {
                        //ignore the exception
                    }

                    //right
                    try {

                        MyButton rightButton = arr[i][j + 1];

                        if (finalizedNodes.containsKey(Integer.parseInt(rightButton.getName()))) {
                            right = finalizedNodes.get(Integer.parseInt(rightButton.getName()));
                        } else {
                            if (rightButton.getType() == MyButton.Type.WALL) {
                                right = null;
                            } else if (rightButton.getType() == MyButton.Type.PATH) {
                                right = new MyNode(rightButton.getText(), MyNode.Type.PATH);
                                finalizedNodes.put(Integer.parseInt(right.getName()), right);
                            } else if (rightButton.getType() == MyButton.Type.START) {
                                right = new MyNode(rightButton.getText(), MyNode.Type.START);
                                finalizedNodes.put(Integer.parseInt(right.getName()), right);
                            } else {
                                right = new MyNode(rightButton.getText(), MyNode.Type.FINISH);
                                finalizedNodes.put(Integer.parseInt(right.getName()), right);
                            }
                        }
                    } catch(IndexOutOfBoundsException e) {
                        //ignore the exception
                    }

                    //below
                    try {

                        MyButton bottomButton = arr[i + 1][j];

                        if (finalizedNodes.containsKey(Integer.parseInt(bottomButton.getName()))) {
                            bottom = finalizedNodes.get(Integer.parseInt(bottomButton.getName()));
                        } else {
                            if (bottomButton.getType() == MyButton.Type.WALL) {
                                bottom = null;
                            } else if (bottomButton.getType() == MyButton.Type.PATH) {
                                bottom = new MyNode(bottomButton.getText(), MyNode.Type.PATH);
                                finalizedNodes.put(Integer.parseInt(bottom.getName()), bottom);
                            } else if (bottomButton.getType() == MyButton.Type.START) {
                                bottom = new MyNode(bottomButton.getText(), MyNode.Type.START);
                                finalizedNodes.put(Integer.parseInt(bottom.getName()), bottom);
                            } else {
                                bottom = new MyNode(bottomButton.getText(), MyNode.Type.FINISH);
                                finalizedNodes.put(Integer.parseInt(bottom.getName()), bottom);
                            }
                        }
                    } catch(IndexOutOfBoundsException e) {
                        //ignore the exception
                    }

                    //add the none null buttons in order from: above, left, right, and bottom

                    if (top != null) {
                        node.addNeighbor(top);
                    }

                    if (left != null) {
                        node.addNeighbor(left);
                    }

                    if (right != null) {
                        node.addNeighbor(right);
                    }

                    if (bottom != null) {
                        node.addNeighbor(bottom);
                    }

                    finalizedNodes.put(Integer.parseInt(node.getName()), node);
                    graphNodes.add(node);
                }
            }

            MyNode start = null;
            MyNode finish = null;

            for (MyNode node : graphNodes) {
                if (node.getType() == MyNode.Type.START) {
                    start = node;
                } else if (node.getType() == MyNode.Type.FINISH) {
                    finish = node;
                }
            }

            //Here each node has been created with a complete list of its neigbores and is now ready to be passed into
            //the BFS algorithm
            Graph graph = new Graph(start, finish, graphNodes.size(), graphNodes);
            BFS bfs = new BFS(graph, gridPane, vbox, bottom);
            bfs.run();

            //wait for the BFS thread to finish processing
            try {
                bfs.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        Button exitButton = new Button("Exit");
        exitButton.setOnAction((event) -> {
            stop();
        });
        vbox.getChildren().add(solveButton);

        Button randomizeButton = new Button("Randomize");

        randomizeButton.setOnAction((event) -> {
            randomizeGrid();
        });

        vbox.getChildren().add(randomizeButton);

        Button resetButton = new Button("Reset");
        resetButton.setOnAction((event) -> {
            //loop through each button and set its type to path
            for (Node node : gridPane.getChildren()) {
                MyButton b = (MyButton) node;
                b.setType(MyButton.Type.PATH);
            }
            gridPane.setDisable(false);
            vbox.getChildren().get(0).setDisable(false);
            vbox.getChildren().get(1).setDisable(false);
            Label l = (Label) bottom.getChildren().get(0);
            l.setText("Path: ");
            Label l2 = (Label) bottom.getChildren().get(1);
            l2.setText("Cost: ");
        });

        vbox.getChildren().add(resetButton);

        vbox.getChildren().add(exitButton);

        gridPane = new GridPane();
        int counter = 1;
        for (int i = 0; i < Math.sqrt(DIM); i++) {
            for (int j = 0; j < Math.sqrt(DIM); j++) {

                MyButton b = new MyButton(Integer.toString(counter));
                b.setOnAction((event) -> {
                    if (b.getType() == MyButton.Type.PATH) {
                        b.setType(MyButton.Type.WALL);
                    } else if (b.getType() == MyButton.Type.WALL) {
                        b.setType(MyButton.Type.START);
                    } else if (b.getType() == MyButton.Type.START) {
                        b.setType(MyButton.Type.FINISH);
                    } else {
                        b.setType(MyButton.Type.PATH);
                    }
                });
                b.setStyle("-fx-background-radius: 0");
                b.setStyle("-fx-background-color: #FFFFFF; ");
                gridPane.setHgap(5);
                gridPane.setVgap(5);
                gridPane.add(b, j, i);

                counter++;
            }
        }

        borderPane.setCenter(gridPane);

        Label title = new Label("Maze Solver by Aleksei Bingham");
        title.setAlignment(Pos.CENTER);
        title.setPadding(new Insets(10));

        borderPane.setTop(title);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        borderPane.setRight(vbox);

        bottom = new VBox();
        bottom.setPadding(new Insets(15));
        Label path = new Label("Path: ");
        Label cost = new Label("Cost: ");
        bottom.getChildren().add(path);
        bottom.getChildren().add(cost);
        borderPane.setBottom(bottom);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    private void randomizeGrid() {

        Random rand = new Random();

        //Set each node to either a path or wall randomly
        for (int i = 0; i < DIM; i++) {

            MyButton b = (MyButton) gridPane.getChildren().get(i);

            int num = rand.nextInt(2);

            if (num == 1) {
                b.setType(MyButton.Type.PATH);
            } else {
                b.setType(MyButton.Type.WALL);
            }

        }

        //set 2 random nodes to be a start and finish node

        int startLocation = rand.nextInt(DIM);

        MyButton s = (MyButton) gridPane.getChildren().get(startLocation);
        s.setType(MyButton.Type.START);

        int finishLocation = rand.nextInt(DIM);
        while (finishLocation == startLocation) {
            finishLocation = rand.nextInt(DIM);
        }

        MyButton f = (MyButton) gridPane.getChildren().get(finishLocation);
        f.setType(MyButton.Type.FINISH);

    }

    public void displayButtons() {

        List<Node> nodes = gridPane.getChildren();
        for (Node node : nodes) {
            MyButton b = (MyButton) node;
            System.out.println(b.toString());
        }
    }

    /** Called when the GUI is closed */
    public void stop() {
        System.exit(0);
    }

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Usage: java MazeSolver DIM");
            System.exit(0);
        } else {
            try {
                int x = (int) Math.sqrt(Integer.parseInt(args[0]));
                if ( !((x * x) == Integer.parseInt(args[0]) )) {
                    System.out.println("DIM must be a positive square number");
                    System.exit(0);
                }
            } catch (NumberFormatException e) {
                System.out.println("DIM must be a positive square number");
                System.exit(0);
            }

        }

        Application.launch(args);

    }
}
