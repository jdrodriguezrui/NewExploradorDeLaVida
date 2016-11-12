package exploradordelavida;
import java.util.ArrayList;

public class Position {

    public final int x;
    public final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public ArrayList<Position> getAdjacentPositions()
    {
        ArrayList<Position> positions = new ArrayList<>();
        //Siendo 5 el centro, estas posiciones serán...
        positions.add(new Position(this.x-1,this.y-1));//7
        positions.add(new Position(this.x,this.y-1));//8
        positions.add(new Position(this.x+1,this.y-1));//9
        positions.add(new Position(this.x-1,this.y));//4
        positions.add(new Position(this.x+1,this.y));//6
        positions.add(new Position(this.x-1,this.y+1));//7
        positions.add(new Position(this.x,this.y+1));//8
        positions.add(new Position(this.x+1,this.y+1));//9
        
        return positions;
    }
}
