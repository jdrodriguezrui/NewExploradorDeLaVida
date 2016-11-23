package exploradordelavida.logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class EventListenerForSimulation implements ActionListener,Serializable
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
