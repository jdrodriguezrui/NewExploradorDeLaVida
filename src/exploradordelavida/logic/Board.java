package exploradordelavida.logic;

import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.util.TreeMap;
import java.io.Serializable;

public class Board extends JPanel implements Serializable
{

    // ----------------------------------------------------------------------------------- ATTRIBUTES

    private final int sideLength; // Number of visible cells per board side (squared board)
    public TreeMap<Position, Cell> cells; // All the cells on the board with their CURRENT state
    private TreeMap<Position, Cell> oldGeneration; // All the cells on the board with the state they had as a new
                                                   // turn began (that is, without the changes related to the turn's
                                                   // operations).
    private final int drawingMargin = 3; // Number of invisible cells per board side. They help us to have a soft
                                         // transition when a cell reaches the board's sidelines (remember the board is
                                         // "infinite")
    public static int DEFAULT_SIZE = 50;

    // ----------------------------------------------------------------------------------- CONSTRUCTOR
    public Board(int newSideLength)
    {
        super ( );

        // . . . . . . . . . . . . . . . . . . .  LOGIC

        this.sideLength = newSideLength;
        this.cells = new TreeMap <> ();
        this.oldGeneration = new TreeMap <> ();
        this.fillBoard ( );

        // . . . . . . . . . . . . . . . . . . . GRAPHICS

        this.setLayout ( new GridLayout ( this.sideLength-drawingMargin - 2 , this.sideLength-drawingMargin ) );
        Position aPosition;
        for ( int i = drawingMargin ; i <= this.sideLength-drawingMargin ; i = i + 1 )
        {
            for ( int j = drawingMargin ; j <= this.sideLength-drawingMargin ; j = j + 1 )
            {
                aPosition = new Position ( j , i );
                this.add(cells.get(aPosition));
            }
        }

    }

    // ----------------------------------------------------------------------------------- METHODS

    public void fillBoard ( ) // Fills the board (technically, this.cells) with new Cell instances...
    {
        Position aPosition;

        for (int i = 1; i <= this.sideLength; i = i + 1)
        {
            for (int j = 1; j <= this.sideLength; j = j + 1)
            {
                aPosition = new Position(j, i);
                this.cells.put(aPosition, new Cell(aPosition));
            }
        }
    }

    public void saveOldGeneration() // We put new objects here so that their life isn't depending upon their
                                    // "equals" on this.cells
    {
        Cell cellToPut;

        for (Cell cell : this.cells.values()) {
            if (cell.isAlive()) {
                cellToPut = new Cell(cell.getPosition());
                cellToPut.setSpecies(cell.getSpecies());
                this.oldGeneration.put(cell.getPosition(), cellToPut); // Each cell on this.cells has an equivalent
                                                                       // here. It's a different object, but it
                                                                       // shares some relevant characteristics with
                                                                       // the one on this.cells (it's species and
                                                                       // position). Such characteristics happen to be
                                                                       // important to apply the game's rules.
            }
        }
    }

    public void checkNewGeneration ( ) // Apply the rules to know which cells are to be born.
    {
        int blackCellsAround;
        int greenCellsAround;
        int redCellsAround;
        Cell neighbour;

        for (Cell cell : this.cells.values())
        {
            if (!cell.isAlive()) {
                blackCellsAround = 0;
                greenCellsAround = 0;
                redCellsAround = 0;

                for (Position position : cell.getPosition().adjacentPositions())
                {
                    neighbour = this.oldGeneration.get(position);

                    if (neighbour != null)
                    {
                        if (neighbour.getSpecies() == Cell.BLACK_SPECIES)
                        {
                            blackCellsAround = blackCellsAround + 1;
                        } else if (neighbour.getSpecies() == Cell.GREEN_SPECIES)
                        {
                            greenCellsAround = greenCellsAround + 1;
                        } else if (neighbour.getSpecies() == Cell.RED_SPECIES)
                        {
                            redCellsAround = redCellsAround + 1;
                        }
                    }
                }

                if (blackCellsAround == 3)
                {
                    cell.setTurnsToBeBornBlack(cell.getTurnsToBeBornBlack() - 1);
                    if (cell.getTurnsToBeBornBlack() == 0)
                    {
                        cell.bringToLife(Cell.BLACK_SPECIES);
                    }
                } else // This means important things: a) if there are less than 3 neighbours of the species, the count
                // restarts and b) if there are more than 3 neighbours of the species the count restarts as
                // well...
                {
                    cell.setTurnsToBeBornBlack(Cell.BLACK_SPECIES); // This is a happy coincidence! The num value of
                    // this happens to be the default number of
                    // turns for a cell of the species to be born
                    // too!
                }

                if (greenCellsAround == 3)
                {
                    cell.setTurnsToBeBornGreen(cell.getTurnsToBeBornGreen() - 1);
                    if (cell.getTurnsToBeBornGreen() == 0)
                    {
                        cell.bringToLife(Cell.GREEN_SPECIES);
                    }
                }
                else
                {
                    cell.setTurnsToBeBornGreen(Cell.GREEN_SPECIES);
                }

                if (redCellsAround == 3)
                {
                    cell.setTurnsToBeBornRed(cell.getTurnsToBeBornRed() - 1);
                    if (cell.getTurnsToBeBornRed() == 0)
                    {
                        cell.bringToLife(Cell.RED_SPECIES);
                    }
                }
                else
                    {
                    cell.setTurnsToBeBornRed(Cell.RED_SPECIES);
                }
            }
        }
    }

    public void checkOldGeneration() // Check which cells from those alive by the time the current run started should
                                     // die
    {
        int sameSpeciesCellsAround;
        Cell neighbour;

        for (Cell cell : this.oldGeneration.values()) {
            sameSpeciesCellsAround = 0;
            for (Position position : cell.getPosition().adjacentPositions()) {
                neighbour = this.oldGeneration.get(position);

                if (neighbour != null) {
                    if (neighbour.getSpecies() == cell.getSpecies()) {
                        sameSpeciesCellsAround = sameSpeciesCellsAround + 1;
                    }
                }
            }

            if ( ( cell.getPosition().getX() == this.sideLength ) || ( cell.getPosition().getX() == 1) ||
                    ( cell.getPosition().getY() == this.sideLength ) || ( cell.getPosition().getY() == 1 ) )
            {
                this.cells.get(cell.getPosition()).turnToDead();
            }
            else if ((sameSpeciesCellsAround < 2) || (sameSpeciesCellsAround > 3))
            {

                if ( this.cells.get ( cell.getPosition() ).getSpecies() == Cell.BLACK_SPECIES )
                {
                    this.cells.get ( cell.getPosition() ).setTurnsToDieIfBlack ( this.cells.get ( cell.getPosition()
                    ).getTurnsToDieIfBlack() - 1 );

                    if ( this.cells.get ( cell.getPosition() ).getTurnsToDieIfBlack() == 0 )
                    {
                        this.cells.get(cell.getPosition()).turnToDead();
                    }
                }
                else if ( this.cells.get ( cell.getPosition() ).getSpecies() == Cell.GREEN_SPECIES )
                {
                    this.cells.get ( cell.getPosition() ).setTurnsToDieIfGreen ( this.cells.get ( cell.getPosition()
                    ).getTurnsToDieIfGreen() - 1 );

                    if ( this.cells.get ( cell.getPosition() ).getTurnsToDieIfGreen() == 0 )
                    {
                        this.cells.get(cell.getPosition()).turnToDead();
                    }
                }
                else
                {
                    this.cells.get ( cell.getPosition() ).setTurnsToDieIfRed ( this.cells.get ( cell.getPosition()
                    ).getTurnsToDieIfRed() - 1 );

                    if ( this.cells.get ( cell.getPosition() ).getTurnsToDieIfRed() == 0 )
                    {
                        this.cells.get(cell.getPosition()).turnToDead();
                    }
                }
            }
        }
    }

    public void refreshBoard() // Change the board's state based on the application of the game's rules
    {
        this.saveOldGeneration();
        this.checkNewGeneration();
        this.checkOldGeneration();
        this.oldGeneration.clear();
    }

    public void doTurn()
    {
        this.refreshBoard();
        this.repaint();
    }

    public void clearBoard()
    {
        for (Cell cell : this.cells.values() )
        {
            cell.turnToDead();
        }
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);
    }
}
