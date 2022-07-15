public class River extends Track{

    public River(String direction, int limitAmount) {
        super(direction, limitAmount);
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public void hitBehaviour() {

    }
}
