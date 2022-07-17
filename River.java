import java.awt.*;
import java.util.ArrayList;

public class River extends Track {

    private ArrayList<TreeTrunk> treeTrunkArrayList;

    public River(String direction, int limitAmount, int riverPositionY) {
        super(direction, limitAmount, riverPositionY);
        setTrackContent(limitAmount);
    }

    @Override
    public void setTrackContent(int limitAmount) {
        this.treeTrunkArrayList = new ArrayList<>();

        for (int i = 0; i < limitAmount; i++) {
            int speed = (int) (Math.random() * (3 - 1) + 1);
            int randPosX = (int) (Math.random() * 500);

            this.treeTrunkArrayList.add(new TreeTrunk(randPosX, super.getTrackPositionY(), speed));
        }
    }

    public ArrayList<TreeTrunk> getTreeTrunkArrayList() {
        return treeTrunkArrayList;
    }

    @Override
    public void hitBehaviour() {

    }
}
