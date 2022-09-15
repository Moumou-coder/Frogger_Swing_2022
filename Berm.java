import java.util.ArrayList;

public class Berm extends Track {

    public Berm(int limitAmount, int bermPositionY) {
        super("none", limitAmount, bermPositionY);
        setTrackContent(limitAmount);
    }

    @Override
    public void setTrackContent(int limitAmount) {
        super.trackContent = new ArrayList<>();

        for (int i = 0; i < limitAmount; i++) {
            int randPosX = (int) (Math.random() * 500);
            super.trackContent.add(new Bushes(randPosX, super.getTrackPositionY()));
        }
    }
}
