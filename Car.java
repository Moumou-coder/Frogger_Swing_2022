import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Car extends GameElement implements ActionListener {

    private String color;
    private Timer timer;

    public Car(int pos_x, int pos_y, int speed, String color) {
        super(pos_x, pos_y, speed, "./assets/car" + color + ".png");
        setColor(color);
        timer = new Timer(1000, this);
        timer.start();
    }

    public String getColor() {
        return color;
    }


    public void setColor(String color) {
        this.color = color;
        super.setIconImage("./assets/car" + color + ".png");
        setSpeed(3);
    }

    @Override
    public void triggerAction(Board board) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (color.equals("Orange")) {
            setSpeed((int) (Math.random() * (20 - 1) + 1));
        }
    }
}
