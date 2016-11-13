package exploradordelavida;

import java.awt.Graphics;
import javax.swing.JButton;

public class Cell extends JButton
{

    // ----------------------------------------------------------------------------------- ATTRIBUTES

    private Position position;
    private boolean isAlive;
    private int species;
    private int turnsToBeBornBlack;
    private int turnsToBeBornGreen;
    private int turnsToBeBornRed;

    // ----------------------------------------------------------------------------------- CONSTANTS

    public static final int NO_SPECIES = 0;
    public static final int BLACK_SPECIES = 1;
    public static final int GREEN_SPECIES = 2;
    public static final int RED_SPECIES = 3;

    // ----------------------------------------------------------------------------------- CONSTRUCTOR

    public Cell ( Position newPosition )
    {
        this.position = newPosition;
        this.isAlive = false;
        this.species = this.NO_SPECIES;
        this.turnsToBeBornBlack = 1;
        this.turnsToBeBornGreen = 2;
        this.turnsToBeBornRed = 3;

        // IMPORTANT! We gotta link a Listener to the Cell here, on it's constructor. It would be done like this:
        // this.addActionListener ( new CoolListener ( ) );
    }

    // ----------------------------------------------------------------------------------- METHODS

    public void bringToLife ( int newSpecies )
    {
        this.species = newSpecies;
        this.isAlive = true;
        this.restartCounters ( );
    }

    public void turnToDead ( )
    {
        this.species = this.NO_SPECIES;
        this.isAlive = false;
    }

    public void switchState ( )
    {
        this.isAlive = !this.isAlive;
    }

    private void restartCounters ( )
    {
        this.turnsToBeBornBlack = 1;
        this.turnsToBeBornGreen = 2;
        this.turnsToBeBornRed = 3;
    }

    public Position getPosition ( )
    {
        return this.position;
    }

    public int getSpecies (  )
    {
        return this.species;
    }

    public int getTurnsToBeBornBlack( )
    {
        return turnsToBeBornBlack;
    }

    public void setTurnsToBeBornBlack(int turnsToBeBornBlack)
    {
        this.turnsToBeBornBlack = turnsToBeBornBlack;
    }

    public int getTurnsToBeBornGreen()
    {
        return turnsToBeBornGreen;
    }

    public void setTurnsToBeBornGreen(int turnsToBeBornGreen)
    {
        this.turnsToBeBornGreen = turnsToBeBornGreen;
    }

    public int getTurnsToBeBornRed()
    {
        return turnsToBeBornRed;
    }

    public void setTurnsToBeBornRed(int turnsToBeBornRed)
    {
        this.turnsToBeBornRed = turnsToBeBornRed;
    }

    public boolean isAlive ()
    {
        return this.isAlive;
    }

    public void paint (Graphics graphics, int x, int y) // WHAT ON EARTH ARE X AND Y?
    {
        // Paints a full rectangle with side of length L (not given... yet)
        // Depends on the species and on the cell's state (isAlive)
    }


}
