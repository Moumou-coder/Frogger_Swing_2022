import java.util.ArrayList;

public class CentralBerm extends Track {

    public CentralBerm(int limitAmount, ArrayList berm, int bermPositionY) {
        super("none", limitAmount, bermPositionY);
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public void hitBehaviour() {

    }
}
