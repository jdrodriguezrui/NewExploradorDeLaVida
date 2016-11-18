package helper;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import exploradordelavida.logic.Cell;
import exploradordelavida.logic.GameFrame;

public class ClickListenerForCell implements ActionListener
{

    // ----------------------------------------------------------------------------------- ATTRIBUTES

    private Cell thisCell;

    // ----------------------------------------------------------------------------------- CONSTRUCTOR

    public ClickListenerForCell ( Cell aCell )
    {
        thisCell = aCell;
    }

    // ----------------------------------------------------------------------------------- METHODS

    public void actionPerformed (ActionEvent ae)
    {
        thisCell.switchState ( GameFrame.selectedSpecie );
        thisCell.repaint ( );
    }

}
