import java.util.ArrayList;

public class HighWay extends Track{

    private ArrayList<MovingObject> movingObjectsList;

    public HighWay(String direction, int limitAmount, ArrayList highWayContent, int highWayPositionY) {
        super(direction, limitAmount, highWayPositionY);
        this.movingObjectsList=setMovingObjectsList(highWayContent);
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public void hitBehaviour() {

    }

    public ArrayList setMovingObjectsList(ArrayList<MovingObject> movingObjectsList) {
        var arraylist = new ArrayList<MovingObject>();
        for(MovingObject mvObject:movingObjectsList){
            if(mvObject.getClass() == Car.class){

                Car carObject = (Car) mvObject;
                arraylist.add(new Car(mvObject.getPos_x(),mvObject.getPos_y(),mvObject.getSpeed(), carObject.getColor()));
            }
        }
        return arraylist;
    }

    public ArrayList<MovingObject> getMovingObjectsList() {
        return movingObjectsList;
    }
}
