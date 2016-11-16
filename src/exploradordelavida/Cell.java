package exploradordelavida;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Cell extends JButton {

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
    public Cell(Position newPosition) {
        super("");
        this.position = newPosition;
        this.isAlive = false;
        this.species = Cell.NO_SPECIES;
        this.turnsToBeBornBlack = 1;
        this.turnsToBeBornGreen = 2;
        this.turnsToBeBornRed = 3;

        // IMPORTANT! We gotta link a Listener to the Cell here, on it's constructor. It would be done like this:
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Cell.this.switchState(GameFrame.selectedSpecie);
                Cell.this.repaint();
            }
        });

        //DEFAULT DEAD COLOR!!
        this.setBackground(Color.DARK_GRAY);
        //TEST PURPOSE ONLY, CELLS MUST BE COLORFUL SQUARES ONLY!!
        this.setText(this.position.toString());
        this.setForeground(Color.WHITE);
    }

    // ----------------------------------------------------------------------------------- METHODS
    public void bringToLife(int newSpecies) {
        this.species = newSpecies;
        this.isAlive = true;
        this.restartCounters();
    }

    public void turnToDead() {
        this.species = Cell.NO_SPECIES;
        this.isAlive = false;
    }

    public void switchState(int species) {
        if (!this.isAlive) {
            bringToLife(species);
        } else {
            turnToDead();
        }
    }

    private void restartCounters() {
        this.turnsToBeBornBlack = 1;
        this.turnsToBeBornGreen = 2;
        this.turnsToBeBornRed = 3;
    }

    public Position getPosition() {
        return this.position;
    }

    public int getSpecies() {
        return this.species;
    }

    public int getTurnsToBeBornBlack() {
        return turnsToBeBornBlack;
    }

    public void setTurnsToBeBornBlack(int turnsToBeBornBlack) {
        this.turnsToBeBornBlack = turnsToBeBornBlack;
    }

    public int getTurnsToBeBornGreen() {
        return turnsToBeBornGreen;
    }

    public void setTurnsToBeBornGreen(int turnsToBeBornGreen) {
        this.turnsToBeBornGreen = turnsToBeBornGreen;
    }

    public int getTurnsToBeBornRed() {
        return turnsToBeBornRed;
    }

    public void setTurnsToBeBornRed(int turnsToBeBornRed) {
        this.turnsToBeBornRed = turnsToBeBornRed;
    }

    public boolean isAlive() {
        return this.isAlive;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        // Depends on the species and on the cell's state (isAlive) SWITCH MAGICO
        if (!this.isAlive) {
            this.setBackground(Color.DARK_GRAY);
        } else {
            switch (this.species) {
                case Cell.BLACK_SPECIES:
                    this.setBackground(Color.BLACK);
                    break;
                case Cell.GREEN_SPECIES:
                    this.setBackground(Color.GREEN);
                    break;
                case Cell.RED_SPECIES:
                    this.setBackground(Color.RED);
                    break;
            }
        }

    }
    
    @Override
    public String toString()
    {
        return "Cell, specie "+this.species+" Is alive? "+this.isAlive;
    }
}
