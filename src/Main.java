import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("gui started");
        Scene scene = new Scene(new GridPane());
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /** Called when the GUI is closed */
    public void stop() {
        System.out.println("gui closed");
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
