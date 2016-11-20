package helper;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import exploradordelavida.logic.Cell;
import exploradordelavida.logic.GameFrame;
import java.io.Serializable;

public class ClickListenerForCell implements ActionListener,Serializable
{

    // ----------------------------------------------------------------------------------- ATTRIBUTES

    private final Cell thisCell;

    // ----------------------------------------------------------------------------------- CONSTRUCTOR

    public ClickListenerForCell ( Cell aCell )
    {
        thisCell = aCell;
    }

    // ----------------------------------------------------------------------------------- METHODS

    @Override
    public void actionPerformed (ActionEvent ae)
    {
        thisCell.switchState ( GameFrame.selectedSpecie );
        thisCell.repaint ( );
    }

}
