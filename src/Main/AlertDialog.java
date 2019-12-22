package Main;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * The AlertDialog class displays another window on top of the current window when
 * an invalid move is made. This prevents the user from making any invalid moves.
 */
public class AlertDialog {

    private static Stage mainStage;

    /**
     * Displays the Popup window
     * @param title title of window
     * @param message message that will be displayed in window
     */
    public static void display(String title, String message) {

        mainStage = new Stage();

        //Block user interaction with other windows until this window
        //is closed.
        mainStage.initModality(Modality.APPLICATION_MODAL);

        mainStage.setTitle(title);

        mainStage.setMinWidth(200);

        Label label = new Label(message);

        //Setup the button that will close the popup
        Button button = new Button("Ok");
        button.setOnAction( (e) -> {
            mainStage.close();
        });

        VBox vbox = new VBox();
        vbox.getChildren().addAll(label, button);
        vbox.setAlignment(Pos.CENTER);

        //Make the scene
        Scene scene = new Scene(vbox);
        mainStage.setScene(scene);
        mainStage.showAndWait();

    }

}
