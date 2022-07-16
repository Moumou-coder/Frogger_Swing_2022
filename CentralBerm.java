import java.util.ArrayList;

public class CentralBerm extends Track {

    private ArrayList<FixedGameElement> fixedElementList;

    public CentralBerm(int limitAmount, ArrayList bermContent, int bermPositionY) {
        super("none", limitAmount, bermPositionY);
        this.fixedElementList = setFixedElementList(bermContent);
    }

    public ArrayList<FixedGameElement> getFixedElementList() {
        return fixedElementList;
    }

    public ArrayList setFixedElementList(ArrayList<FixedGameElement> fixedElementList) {
        this.fixedElementList = fixedElementList;
        var arraylist = new ArrayList<FixedGameElement>();
        for(FixedGameElement elem : fixedElementList){
            arraylist.add(new Bushes(elem.getPosX(), elem.getPosY()));
        }
        return arraylist;
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public void hitBehaviour() {

    }
}
