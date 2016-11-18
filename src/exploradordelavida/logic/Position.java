package exploradordelavida.logic;

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
    public int compareTo ( Position otherPosition ) // I think this is here because and ONLY because we need to be
                                                    // able to find equal (by state) Position objects on a TreeMap
    {
        int returnValue;

        if ( this.getY() > otherPosition.getY() )
        {
            returnValue = 1;
        }
        else if ( this.getY() < otherPosition.getY() )
        {
            returnValue = -1;
        }
        else
        {
            if ( this.getX() > otherPosition.getX() )
            {
                returnValue = 1;
            }
            else if ( this.getX() < otherPosition.getX() )
            {
                returnValue = -1;
            }
            else
            {
                returnValue = 0;
            }
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

    public int getY ( )
    {
        return this.y;
    }

    @Override
    public String toString ( ) // *DP*
    {
        return ( "(" + this.x + "," + this.y + ")" );
    }
}
