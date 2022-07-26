import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayInputStream;

public class Frog {

    private int pos_x;
    private int pos_y;
    private int speed;
    private Image iconImage;
    private int numberOfLife;
    private boolean superFrog;

    public Frog() {
        this.pos_x = Board.B_WIDTH/2;
        this.pos_y = Board.B_HEIGHT-25;
        this.speed = Board.DOT_SIZE;
        this.numberOfLife = 3;
        this.superFrog = false;
        this.iconImage = new ImageIcon("./assets/head.png").getImage();
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

    public void setIconImage(Image iconImage) {
        this.iconImage = iconImage;
    }

    public int getNumberOfLife() {
        return numberOfLife;
    }

    public void setNumberOfLife(int numberOfLife) {
        this.numberOfLife = numberOfLife;
    }

    public boolean isSuperFrog() {
        return superFrog;
    }

    public void setSuperFrog(boolean superFrog) {
        this.superFrog = superFrog;
    }

}
