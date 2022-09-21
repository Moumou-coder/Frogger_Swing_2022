
public class Coin extends GameElement {
    
    public Coin() {
        super(50*((int)(Math.random()*10)), 50*((int)(Math.random()*10)), 0, "./assets/coin.png");
    }
    
    public static String getPathToImage(){
        return "./assets/coin.png";
    }
    
    public String getType(){
        return "coin";
    }
    
    public void triggerAction(Board board){
        board.incScore(10);
        board.decreaseCoinAmount();

    }
    
}




