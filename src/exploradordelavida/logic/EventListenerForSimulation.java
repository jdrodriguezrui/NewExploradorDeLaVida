package exploradordelavida.logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventListenerForSimulation implements ActionListener
{

    private GameFrame gameFrame;

    public EventListenerForSimulation ( GameFrame newGameFrame ) // "this"...
    {
        this.gameFrame = newGameFrame;
    }

    @Override
    public void actionPerformed (ActionEvent e)
    {
        this.gameFrame.gameBoard.doTurn();

    }
}
