
public class Insect extends GameElement {


    public Insect() {
        
        super((int) (Math.random()*500), (int) (Math.random()*500), 0,"./assets/insect.png");
    }
    
    public String getType(){
        return "insect";
    }
    
    public void triggerAction(Board board){
        board.incScore(2);
    }
}





