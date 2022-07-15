public abstract class Track {

    private String direction;
    private int limitAmount;

    public Track(String direction, int limitAmount) {
        this.direction = direction;
        this.limitAmount = limitAmount;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(int limitAmount) {
        this.limitAmount = limitAmount;
    }

    public abstract String getType();

    public abstract void hitBehaviour();

}
