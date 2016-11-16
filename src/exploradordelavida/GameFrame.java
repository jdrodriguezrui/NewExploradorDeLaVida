/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exploradordelavida;

import helper.FancyButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 *
 * @author julia
 */
public class GameFrame extends JFrame {

    public Board gameBoard = new Board(4);
    private boolean isRunning;
    public static int selectedSpecie = Cell.BLACK_SPECIES;

    //AQUI VA LO QUE USTEDES HACEN!! LOS BOTONES LLAMAN ESTAS FUNCIONES...
    public void setGameSpeed(int secondsPerTurn) {
    }

    public void doSimulation() {
    }

    public void stopSimulation() {
    }

    public void restartGame() {
    }

    public void saveGame() {
    }

    public void loadGame() {
    }

    public void openMenu() {
    }

    public GameFrame() {
        super("Explorador de la vida");
        this.setPreferredSize(new Dimension(800, 800));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        //Adding the Game Board!!
        this.getContentPane().add(gameBoard, BorderLayout.CENTER);

        //Adding the game menu
        addGameMenu();

        this.pack();
        this.setVisible(true);
    }

    private void addGameMenu() {
        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(Color.GRAY);
        //------MENU---
        FancyButton menuButton = new FancyButton("Menu");
        menuButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                openMenu();
            }
        });
        menuPanel.add(menuButton);
        //------GUARDAR-----
        FancyButton saveButton = new FancyButton("Guardar");
        saveButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                saveGame();
            }
        });
        menuPanel.add(saveButton);
        //--------CARGAR-----
        FancyButton loadButton = new FancyButton("Menu");
        loadButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                loadGame();
            }
        });
        menuPanel.add(loadButton);
        //-------SELECTOR DE SPECIE-----
        
        FancyButton turnButton = new FancyButton("Avanza turno (Functionality under test)");       
        turnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                gameBoard.doTurn();
            }
        });
        menuPanel.add(turnButton);
        //DEBEMOS AGREGARLE BOTONES DE MENU, GUARDAR, CARGAR, SELECTOR DE SPECIE, PLAY, AVANZAR TURNO, PAUSE Y RESTART

        this.getContentPane().add(menuPanel, BorderLayout.SOUTH);
    }
}
