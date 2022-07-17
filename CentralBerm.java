import java.util.ArrayList;

public class CentralBerm extends Track {

    private ArrayList<Bushes> bushesArrayList;

    public CentralBerm(int limitAmount, int bermPositionY) {
        super("none", limitAmount, bermPositionY);
        setTrackContent(limitAmount);
    }

    public ArrayList<Bushes> getFixedElementList() {
        return bushesArrayList;
    }

    @Override
    public void setTrackContent(int limitAmount) {
        this.bushesArrayList = new ArrayList<>();

        for (int i = 0; i < limitAmount; i++) {
            int randPosX = (int) (Math.random() * 500);
            this.bushesArrayList.add(new Bushes(randPosX, super.getTrackPositionY()));
        }
    }

    public ArrayList<Bushes> getBushesArrayList() {
        return bushesArrayList;
    }

    @Override
    public void hitBehaviour() {

    }
}
