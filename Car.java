import javax.swing.*;
import java.awt.*;

public class Car extends MovingObject {

    private String color;


    public Car(int pos_x, int pos_y, int speed, String color) {
        super(pos_x, pos_y, speed, "./assets/car" + color + ".png");
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        super.setIconImage("./assets/car" + color + ".png");
    }

    @Override
    public void hitBehaviour(Board board) {

    }
}
