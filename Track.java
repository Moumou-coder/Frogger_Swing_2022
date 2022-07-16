import java.util.ArrayList;
import java.util.List;

public abstract class Track {

    private String direction;
    private int limitAmount;
    private List<MovingObject> trackContent;

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

    public List<MovingObject> getTrackContent() {
        return trackContent;
    }

    public void setTrackContent(List<MovingObject> trackContent) {
        this.trackContent = trackContent;
    }

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

    public abstract String getType();

    public abstract void hitBehaviour();

}
