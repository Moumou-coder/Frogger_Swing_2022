import java.awt.*;
import java.util.ArrayList;

public class River extends Track{

    private ArrayList<MovingObject> movingObjectsList;

    public River(String direction, int limitAmount, ArrayList riverContent, int riverPositionY) {
        super(direction, limitAmount, riverPositionY);
        this.movingObjectsList=setMovingObjectsList(riverContent);
        super.setTrackContent(riverContent);
    }

    public ArrayList<MovingObject> getMovingObjectsList() {
        return movingObjectsList;
    }

    public ArrayList setMovingObjectsList(ArrayList<MovingObject> movingObjectsList) {
        var arraylist = new ArrayList<MovingObject>();
        for(MovingObject mvObject:movingObjectsList){
            arraylist.add(new TreeTrunk(mvObject.getPos_x(),mvObject.getPos_y(),mvObject.getSpeed()));
        }
        return arraylist;
    }

    @Override
    public String getType() {
        return "river";
    }

    @Override
    public void hitBehaviour() {

    }
}
