/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exploradordelavida;

import helper.ComboBoxRenderer;
import helper.FancyButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

    public void pauseSimulation() {
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
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                openMenu();
            }
        });
        menuPanel.add(menuButton);
        //------GUARDAR-----
        FancyButton saveButton = new FancyButton("Guardar");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                saveGame();
            }
        });
        menuPanel.add(saveButton);
        //--------CARGAR-----
        FancyButton loadButton = new FancyButton("Cargar");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                loadGame();
            }
        });
        menuPanel.add(loadButton);
        //-------SELECTOR DE ESPECIE-----
        Integer[] species = {1, 2, 3};
        JComboBox specieSelector = new JComboBox(species);
        ComboBoxRenderer renderer = new ComboBoxRenderer();
        renderer.setPreferredSize(new Dimension(30, 30));
        specieSelector.setRenderer(renderer);
        specieSelector.setSelectedIndex(0);
        specieSelector.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                JComboBox cb = (JComboBox)ae.getSource();
                Integer selectedValue = (Integer)cb.getSelectedItem();
                selectedSpecie = selectedValue;
            }
        });
        menuPanel.add(specieSelector);
        //-----PLAY---- 
        FancyButton playButton = new FancyButton("play", 1);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                doSimulation();
            }
        });
        menuPanel.add(playButton);
        //-----AVANZAR TURNO--
        FancyButton turnButton = new FancyButton("step", 1);
        turnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                gameBoard.doTurn();
            }
        });
        menuPanel.add(turnButton);
        //----PAUSE------
        FancyButton pauseButton = new FancyButton("pause", 1);
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                pauseSimulation();
            }
        });
        menuPanel.add(pauseButton);
        //-----RESTART----
        FancyButton restartButton = new FancyButton("stop", 1);
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                doSimulation();
            }
        });
        menuPanel.add(restartButton);

        this.getContentPane().add(menuPanel, BorderLayout.SOUTH);
    }
}
