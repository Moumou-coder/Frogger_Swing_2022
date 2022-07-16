public class Bushes extends FixedGameElement{

    public Bushes(int pos_x, int pos_y) {
        super(pos_x, pos_y);
    }

    public static String getPathToImage(){
        return "./assets/bushes.png";
    }

    public String getType(){
        return "bushes";
    }

    public void triggerAction(Board board){
    }
}
