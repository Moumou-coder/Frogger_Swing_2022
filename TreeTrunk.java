public class TreeTrunk extends MovingObject {


    public TreeTrunk(int pos_x, int pos_y, int speed) {
        super(pos_x, pos_y, speed);

    }

    public static String getPathToImage() {
        return "./assets/treeTrunk.png";
    }

    @Override
    public String getType() {
        return "treeTrunk";
    }

    @Override
    public void hitBehaviour() {

    }
}
