package Main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
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

public class Main extends Application {

    public void init() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(50, 50, 50, 50));

        VBox vbox = new VBox();
        vbox.getChildren().add(new Button("Solve"));
        vbox.getChildren().add(new Button("Randomize"));
        vbox.getChildren().add(new Button("Exit"));

        GridPane gridPane = new GridPane();
        for (int i = 0; i < Math.sqrt(16); i++) {
            for (int j = 0; j < Math.sqrt(16); j++) {

                MyButton b = new MyButton("     ");
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
                gridPane.add(b, j, i);

            }
        }

        borderPane.setCenter(gridPane);

        borderPane.setTop(new Label("Dijkstra's Algorithm"));
        vbox.setPadding(new Insets(10, 10, 10, 10));
        borderPane.setRight(vbox);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    /** Called when the GUI is closed */
    public void stop() {
        System.out.println("GUI closed");
        System.exit(0);
    }

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Usage: java MazeSolver DIM");
            System.exit(0);
        } else {
            try {
                int x = Integer.parseInt(args[0]);
                if (x <= 0) {
                    System.out.println("DIM must be a positive integer");
                    System.exit(0);
                }
            } catch (NumberFormatException e) {
                System.out.println("DIM must be a positive integer");
                System.exit(0);
            }

        }

        Application.launch(args);

    }
}
