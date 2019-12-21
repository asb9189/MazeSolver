package Main;

import javafx.scene.control.Button;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.awt.*;

public class MyButton extends Button {


    private String name;
    private Type type = Type.PATH;

    public enum Type {
        PATH,
        WALL,
        START,
        FINISH
    }

    public MyButton(String name) {
        super(name);
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;

        if (getType() == MyButton.Type.PATH) {
            this.setStyle("-fx-background-color: #FFFFFF; ");
        } else if (getType() == MyButton.Type.WALL) {
            this.setStyle("-fx-background-color: #000000; ");
        } else if (getType() == MyButton.Type.START) {
            this.setStyle("-fx-background-color: #49FF33; ");
        } else {
            this.setStyle("-fx-background-color: #FF0000; ");
        }

    }

    public String toString() {
        return this.name + ", type: " + this.getType();
    }

}
