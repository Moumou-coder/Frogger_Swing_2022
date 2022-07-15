import java.util.ArrayList;

public class River extends Track{

    private ArrayList<MovingObject> movingObjectsList;

    public River(String direction, int limitAmount, ArrayList riverContent, int riverPositionY) {
        super(direction, limitAmount, riverPositionY);
        this.movingObjectsList=new ArrayList<MovingObject>(riverContent);
    }

    public ArrayList<MovingObject> getMovingObjectsList() {
        return movingObjectsList;
    }

    public void setMovingObjectsList(ArrayList<MovingObject> movingObjectsList) {
        this.movingObjectsList = movingObjectsList;
    }

    @Override
    public String getType() {
        return "river";
    }

    @Override
    public void hitBehaviour() {

    }
}
