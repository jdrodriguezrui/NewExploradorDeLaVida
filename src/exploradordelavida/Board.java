package exploradordelavida;

import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.util.TreeMap;

public class Board extends JPanel
{

    // ----------------------------------------------------------------------------------- ATTRIBUTES

    private int sideLength; // IMPORTANT! Each unit is a cell... And this is a squared board.
    public TreeMap <Position , Cell> cells = new TreeMap<>();
    private TreeMap <Position , Cell> oldGeneration = new TreeMap<>(); // This is for internal functioning only! It's filled in
                                                        // checkNewGeneration and used - cleaned in checkOldGeneration,
                                                         // and, by the way, contains the cells alive before
                                                        // checkNewGeneration

    // ----------------------------------------------------------------------------------- CONSTRUCTOR

    public Board ( int newSideLength )
    {
        super();
        this.sideLength = newSideLength;
        this.fillBoard ( );
        
        //Graphic shit my boy
        this.setLayout(new GridLayout(newSideLength,newSideLength));
        for(Cell cell:this.cells.values())
        {
            this.add(cell);
        }
    }

    // ----------------------------------------------------------------------------------- METHODS

    public void fillBoard ( ) // Fills the board (technically, this.cells) with new Cell instances...
    {
        Position aPosition;

        for ( int i = 1 ; i <= this.sideLength ; i = i + 1 )
        {
            for ( int j = 1 ; j <= this.sideLength ; j = j + 1 )
            {
                aPosition = new Position ( i , j );
                //NULL POINTER EXCEPTION
                this.cells.put( aPosition , new Cell ( aPosition ) );
            }
        }
    }

    public void saveOldGeneration ( )
    {
        for ( Cell cell : this.cells.values () )
        {
            if ( cell.isAlive() )
            {
                this.oldGeneration.put ( cell.getPosition() , cell );
            }
        }
    }

    public void checkNewGeneration ( )
    {
        int blackCellsAround;
        int greenCellsAround;
        int redCellsAround;
        Cell neighbour;

        for ( Cell cell : this.cells.values() )
        {

            if ( cell.isAlive() )  // If the cell is alive, going through this makes no sense!
            {
                continue;
            }
            else
            {
                blackCellsAround = 0;
                greenCellsAround = 0;
                redCellsAround = 0;

                for ( Position position : cell.getPosition().adjacentPositions() )
                {
                    neighbour = this.cells.get ( position );

                    if ( neighbour != null )
                    {
                        if ( neighbour.getSpecies() == Cell.BLACK_SPECIES )
                        {
                            blackCellsAround = blackCellsAround + 1;
                        }
                        else if ( neighbour.getSpecies() == Cell.GREEN_SPECIES )
                        {
                            greenCellsAround = greenCellsAround + 1;
                        }
                        else if ( neighbour.getSpecies() == Cell.RED_SPECIES )
                        {
                            redCellsAround = redCellsAround + 1;
                        }
                    }
                }

                if ( blackCellsAround == 3 )
                {
                    cell.setTurnsToBeBornBlack ( cell.getTurnsToBeBornBlack() - 1 );
                    if ( cell.getTurnsToBeBornBlack() == 0 )
                    {
                        cell.bringToLife ( Cell.BLACK_SPECIES );
                    }
                }
                else // This means important things: a) if there are less than 3 neighbours of the species, the count
                     // restarts and b) if there are more than 3 neighbours of the species the count restarts as
                     // well...
                {
                    cell.setTurnsToBeBornBlack ( Cell.BLACK_SPECIES ); // This is a happy coincidence! The num value of
                                                                       // this happens to be the default number of
                                                                       // turns for a cell of the species to be born
                                                                       // too!
                }

                if ( greenCellsAround == 3 )
                {
                    cell.setTurnsToBeBornGreen ( cell.getTurnsToBeBornGreen() - 1 );
                    if ( cell.getTurnsToBeBornGreen() == 0 )
                    {
                        cell.bringToLife ( Cell.GREEN_SPECIES );
                    }
                }
                else
                {
                    cell.setTurnsToBeBornGreen ( Cell.GREEN_SPECIES );
                }


                if ( redCellsAround == 3 )
                {
                    cell.setTurnsToBeBornRed ( cell.getTurnsToBeBornRed() - 1 );
                    if ( cell.getTurnsToBeBornRed() == 0 )
                    {
                        cell.bringToLife ( Cell.RED_SPECIES );
                    }
                }
                else
                {
                    cell.setTurnsToBeBornRed ( Cell.RED_SPECIES );
                }
            }
        }
    }

    public void checkOldGeneration ( ) // We must not go over the cells we just gave birth to!
    {
        int sameSpeciesCellsAroung;
        Cell neighbour;

        for ( Cell cell : this.oldGeneration.values ( ) )
        {
            sameSpeciesCellsAroung = 0;
            for ( Position position : cell.getPosition().adjacentPositions() )
            {
                neighbour = this.cells.get ( position );

                if ( neighbour != null )
                {
                    if ( neighbour.getSpecies() == cell.getSpecies() )
                    {
                        sameSpeciesCellsAroung = sameSpeciesCellsAroung + 1;
                    }
                }
            }

            if ( ( sameSpeciesCellsAroung < 2 ) || ( sameSpeciesCellsAroung > 3 ) )
            {
                cell.turnToDead ( );
            }
        }
    }

    public void refreshBoard ( ) // The order at which the methods are executed matters!!!
    {
        this.saveOldGeneration();
        this.checkNewGeneration();
        this.checkOldGeneration();
    }

    public void doTurn ( )
    {
        this.refreshBoard ( );
        this.repaint ( );
    }

    @Override
    public void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);       
    }
}
