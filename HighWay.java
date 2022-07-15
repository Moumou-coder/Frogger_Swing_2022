import java.util.ArrayList;

public class HighWay extends Track{

    public HighWay(String direction, int limitAmount, ArrayList highWayContent, int highWayPositionY) {
        super(direction, limitAmount, highWayPositionY);
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public void hitBehaviour() {

    }
}
