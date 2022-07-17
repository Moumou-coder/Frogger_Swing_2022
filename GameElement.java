import javax.swing.*;
import java.awt.*;

public abstract class GameElement {

    private int pos_x;
    private int pos_y;
    private int speed;
    private Image iconImage;

    public GameElement(int pos_x, int pos_y, int speed, String iconImage) {
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.speed = speed;
        this.iconImage = new ImageIcon(iconImage).getImage();
    }

    public int getPos_x() {
        return pos_x;
    }

    public void setPos_x(int pos_x) {
        this.pos_x = pos_x;
    }

    public int getPos_y() {
        return pos_y;
    }

    public void setPos_y(int pos_y) {
        this.pos_y = pos_y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Image getIconImage() {
        return iconImage;
    }

    public void setIconImage(String iconImage) {
        this.iconImage = new ImageIcon(iconImage).getImage();
    }

    public abstract void triggerAction(Board board);

}
