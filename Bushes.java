public class Bushes extends GameElement {

    public Bushes(int pos_x, int pos_y) {
        super(pos_x, pos_y, 0, "./assets/bushes.png");
    }

    public String getType() {
        return "bushes";
    }

    @Override
    public void triggerAction(Board board) {

    }
}
