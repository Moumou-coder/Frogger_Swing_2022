import javax.swing.*;
import java.awt.*;

public class Car extends MovingObject {

    public String color;

    public Car(int pos_x, int pos_y, int speed, String color) {
        super(pos_x, pos_y, speed);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String getType() {
        return "car";
    }

    @Override
    public void hitBehaviour() {

    }

    public Image getIconImageCar(){
       return new ImageIcon("./assets/car" + color + ".png").getImage();
    }
}
