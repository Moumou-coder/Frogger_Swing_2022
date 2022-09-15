import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class HighWay extends Track {

    private final String panelColor[] = {"Red", "Violet", "Orange", "Blue"};


    public HighWay(String direction, int limitAmount, int highWayPositionY) {
        super(direction, limitAmount, highWayPositionY);
        setTrackContent(limitAmount);
    }

    @Override
    public void setTrackContent(int limitAmount) {
        super.trackContent = new ArrayList<>();
        int speed = 3;
        for (int i = 0; i < limitAmount; i++) {
//           speed = (int) (Math.random() * (5 - 1) + 1);
            int randPosX = (int) (Math.random() * 500);
            int randElemArray = new Random().nextInt(panelColor.length);
            super.trackContent.add(new Car(randPosX, super.getTrackPositionY(), speed, panelColor[randElemArray]));
        }
    }

    public void changeCarColor(Car car) {
        int iRandomColor = (int) (Math.random() * 4);
        car.setColor(panelColor[iRandomColor]);
    }
}
