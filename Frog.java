public class Frog extends MovingObject {

    private int numberOfLife;
    private boolean superFrog;

    public Frog(int pos_x, int pos_y, int speed, int numberOfLife, boolean superFrog) {
        super(pos_x, pos_y, speed, "./assets/head.png");
        this.numberOfLife = 3;
        this.superFrog = false;
    }

    public int getNumberOfLife() {
        return numberOfLife;
    }

    public void setNumberOfLife(int numberOfLife) {
        this.numberOfLife = numberOfLife;
    }

    public boolean isSuperFrog() {
        return superFrog;
    }

    public void setSuperFrog(boolean superFrog) {
        this.superFrog = superFrog;
    }

    @Override
    public void hitBehaviour(Board board) {

    }
}
