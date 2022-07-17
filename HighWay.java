import java.util.ArrayList;

public class HighWay extends Track {

    private ArrayList<Car> carArrayList;
    private final String panelColor[] = {"Red", "Violet", "Orange", "Blue"};


    public HighWay(String direction, int limitAmount, int highWayPositionY) {
        super(direction, limitAmount, highWayPositionY);
        setTrackContent(limitAmount);
    }

    @Override
    public void setTrackContent(int limitAmount) {
        this.carArrayList = new ArrayList<>();

        for (int i = 0; i < limitAmount; i++) {
            int speed = (int) (Math.random() * (5 - 1) + 1);
            int randPosX = (int) (Math.random() * 500);
            this.carArrayList.add(new Car(randPosX, super.getTrackPositionY(), speed, panelColor[i % 4]));
        }
    }

    public ArrayList<Car> getCarArrayList() {
        return carArrayList;
    }

    public void changeCarColor(Car car) {
        int iRandomColor = (int) (Math.random() * 4);
        car.setColor(panelColor[iRandomColor]);
    }

    @Override
    public void hitBehaviour() {

    }
}
