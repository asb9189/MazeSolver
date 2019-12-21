package Main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
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
import java.util.List;

public class Main extends Application {

    /** Graphics */
    private BorderPane borderPane;
    private GridPane gridPane;
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

            //convert the current buttons into a graph of nodes
            MyButton[][] arr = new MyButton[(int) Math.sqrt(DIM)][(int) Math.sqrt(DIM)];

            List<Node> nodes = gridPane.getChildren();

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

            for (int i = 0; i < Math.sqrt(DIM); i++) {
                for (int j = 0; j < Math.sqrt(DIM); j++) {

                    //Make the new node based off the current button
                    MyButton currentButton = arr[i][j];
                    MyNode node;
                    MyNode top = null;
                    MyNode left = null;
                    MyNode right = null;
                    MyNode bottom = null;

                    if (currentButton.getType() == MyButton.Type.WALL) {
                        node = new MyNode(currentButton.getText(), MyNode.Type.WALL);
                    } else if (currentButton.getType() == MyButton.Type.PATH) {
                        node = new MyNode(currentButton.getText(), MyNode.Type.PATH);
                    } else if (currentButton.getType() == MyButton.Type.START) {
                        node = new MyNode(currentButton.getText(), MyNode.Type.START);
                    } else {
                        node = new MyNode(currentButton.getText(), MyNode.Type.FINISH);
                    }

                    //above
                    try {

                        MyButton aboveButton = arr[i - 1][j];

                        if (aboveButton.getType() == MyButton.Type.WALL) {
                            top = new MyNode(aboveButton.getText(), MyNode.Type.WALL);
                        } else if (aboveButton.getType() == MyButton.Type.PATH) {
                            top = new MyNode(aboveButton.getText(), MyNode.Type.PATH);
                        } else if (aboveButton.getType() == MyButton.Type.START) {
                            top = new MyNode(aboveButton.getText(), MyNode.Type.START);
                        } else {
                            top = new MyNode(aboveButton.getText(), MyNode.Type.FINISH);
                        }

                    } catch(IndexOutOfBoundsException e) {
                        //ignore the exception
                    }

                    //left
                    try {

                    } catch(IndexOutOfBoundsException e) {
                        //ignore the exception
                    }

                    //right
                    try {

                    } catch(IndexOutOfBoundsException e) {
                        //ignore the exception
                    }

                    //below
                    try {

                    } catch(IndexOutOfBoundsException e) {
                        //ignore the exception
                    }

                }
            }


        });

        Button exitButton = new Button("Exit");
        exitButton.setOnAction((event) -> {
            stop();
        });
        vbox.getChildren().add(solveButton);
        vbox.getChildren().add(new Button("Randomize"));
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

        borderPane.setTop(new Label("Maze Solver"));
        vbox.setPadding(new Insets(10, 10, 10, 10));
        borderPane.setRight(vbox);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();


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
