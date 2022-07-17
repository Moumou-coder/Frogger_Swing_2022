import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Track {

    private String direction;
    private int limitAmount;
    protected List<GameElement> trackContent;
    private int trackPositionY;

    public Track(String direction, int limitAmount, int trackPositionY) {
        this.direction = direction;
        this.limitAmount = limitAmount;
        this.trackPositionY = trackPositionY;

    }

    public int getTrackPositionY() {
        return trackPositionY;
    }

    public void setTrackPositionY(int trackPositionY) {
        this.trackPositionY = trackPositionY;
    }

    public List<GameElement> getTrackContent() {
        return trackContent;
    }

    public abstract void setTrackContent(int limitAmount);

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(int limitAmount) {
        this.limitAmount = limitAmount;
    }
}
