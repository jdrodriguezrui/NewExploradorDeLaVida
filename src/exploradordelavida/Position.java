package exploradordelavida;

import javafx.scene.control.TablePositionBase;

import java.util.ArrayList;

public class Position implements Comparable < Position >
{

    // ----------------------------------------------------------------------------------- ATTRIBUTES

    private int x;
    private int y;

    // ----------------------------------------------------------------------------------- CONSTRUCTOR

    public Position (int newX, int newY)
    {
        this.x = newX;
        this.y = newY;
    }

    // ----------------------------------------------------------------------------------- METHODS

    @Override
    public boolean equals ( Object object ) // This wasn't necessary but it looks fancy :)
    {
        boolean areEqual;
        areEqual = true;

        if ( object == null )
        {
            areEqual = false;
        }
        else if ( this.getClass() != object.getClass() )
        {
            areEqual = false;
        }
        else
        {
            Position theOtherPosition;
            theOtherPosition = ( Position ) ( object );

            boolean conditionA = ( this.getX() == theOtherPosition.getX() );
            boolean conditionB = ( this.getY() == theOtherPosition.getY() );
            if ( !conditionA || !conditionB )
            {
                areEqual = false;
            }
        }

        return areEqual;
    }

    public int compareTo ( Position otherPosition ) // I think this is here because and ONLY because we need to be
                                                    // able to find equal (by state) Position objects on a TreeMap
    {
        int returnValue;

        if ( this.equals ( otherPosition ) )
        {
            returnValue = 0;
        }
        else
        {
            returnValue = 1; // This means nothing!
        }

        return returnValue;
    }

    public ArrayList <Position> adjacentPositions ( )
    {
        ArrayList <Position> adjacentPositions = new ArrayList <Position> ();

        adjacentPositions.add( new Position (this.x - 1 , this.y - 1) );
        adjacentPositions.add( new Position (this.x , this.y - 1) );
        adjacentPositions.add( new Position (this.x + 1 , this.y - 1) );
        adjacentPositions.add( new Position (this.x - 1 , this.y ) );
        adjacentPositions.add( new Position (this.x + 1 , this.y ) );
        adjacentPositions.add( new Position (this.x - 1 , this.y + 1) );
        adjacentPositions.add( new Position (this.x , this.y + 1 ) );
        adjacentPositions.add( new Position (this.x + 1 , this.y + 1) );
        
        return adjacentPositions;
    }

    public int getX ( )
    {
        return this.x;
    }

    public int getY ()
    {
        return this.y;
    }
}
