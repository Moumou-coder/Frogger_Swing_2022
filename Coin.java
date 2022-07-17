
public class Coin extends GameElement {
    
    public Coin(int pos_x, int pos_y) {  
        super(pos_x, pos_y, 0, "./assets/coin.png");
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




