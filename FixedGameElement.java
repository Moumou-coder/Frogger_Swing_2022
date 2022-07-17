import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class FixedGameElement {

    private int pos_x;
    private int pos_y;

    private Image iconImage;

    public FixedGameElement(int pos_x, int pos_y, String iconImage) {

        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.iconImage = new ImageIcon(iconImage).getImage();
    }

    public int getPosX() {
        return pos_x;
    }

    public int getPosY() {
        return pos_y;
    }

    public void setPosX(int new_pos) {
        pos_x = new_pos;
    }

    public void setPosY(int new_pos) {
        pos_y = new_pos;
    }

    public Image getIconImage() {
        return iconImage;
    }

    public void setIconImage(Image iconImage) {
        this.iconImage = iconImage;
    }

    public abstract String getType();

    public abstract void triggerAction(Board board);

}




