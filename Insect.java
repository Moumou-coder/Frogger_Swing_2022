
public class Insect extends GameElement {

    private static String panelColor = "Red";

    public Insect() {
        super((int) (Math.random()*500), (int) (Math.random()*500), 0,"./assets/insect" + panelColor + ".png");
        panelColor = (panelColor == "Red") ? "Blue" : "Red";
    }
    
    public static String getPathToImage(){
        return "./assets/insect.png";
    }
    
    public String getType(){
        return "insect";
    }
    
    public void triggerAction(Board board){
        board.incScore(2);
    }
}





