
public class Coin extends GameElement {
    
    public Coin() {
        super((int) (Math.random()*500), (int) (Math.random()*500), 0, "./assets/coin.png");
    }
    
    public static String getPathToImage(){
        return "./assets/coin.png";
    }
    
    public String getType(){
        return "coin";
    }
    
    public void triggerAction(Board board){
        board.incScore(1);
        board.decreaseCoinAmount();
    }
    
}




