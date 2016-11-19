/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exploradordelavida.logic;

import helper.ComboBoxRenderer;
import helper.FancyButton;
import helper.MusicThread;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import menu.Memoria;

/**
 *
 * @author julia
 */
public class GameFrame extends JFrame {

    public Board gameBoard = new Board(50);
    private Thread music;
    public static int selectedSpecie = Cell.BLACK_SPECIES;

    //Persistencia boi
    private Memoria memoria = new Memoria();
    //Simulation boi
    private int gameSpeed;
    private Timer timer;

    public GameFrame() {
        super("Explorador de la vida");
        this.setPreferredSize(new Dimension(800, 800));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        //Simulation stuff
        this.setGameSpeed(1);                                               // NEW!!! Default speed = 1 second
        this.timer = new Timer(this.gameSpeed, new EventListenerForSimulation(this)); // NEW!!!
        //Adding the Game Board!!
        this.getContentPane().add(gameBoard, BorderLayout.CENTER);

        //Adding the game menu
        addGameMenu();

        //Initializing music
        music = new MusicThread();
        music.start();

        this.pack();
        this.setVisible(true);
    }

    //Internal Functions
    public void setGameSpeed(int secondsPerTurn) {
        this.gameSpeed = secondsPerTurn * 250;
    }

    public void doSimulation() {
        this.timer.start();
    }

    public void pauseSimulation() {
        this.timer.stop();
    }

    public void restartGame() {
        gameBoard.clearBoard();
    }

    public void saveGame() {
    }

    public void loadGame() {
        JFileChooser filechooser = new JFileChooser();
        filechooser.setFileFilter(new FileNameExtensionFilter("Partida guardada .EJI", "EJI"));// Muestra solo los archivos de formato .EJI 
        int opciones = filechooser.showOpenDialog(this);
        if (opciones == JFileChooser.APPROVE_OPTION) { // Cuando selecciónan un archivo
            File archivoSeleccionado = filechooser.getSelectedFile();
            this.memoria.setNombre(archivoSeleccionado.getName());
            try {
                this.memoria.abrir();
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println("JFile chooser exception!");
            }

        } else if (opciones == JFileChooser.CANCEL_OPTION) {//En caso de que cancelen la operación  solo cierre esa ventana
            filechooser.hide();
        }
        gameBoard.cells = memoria.memorisa;
    }

    public void openMenu() {
    }

    public void stopMusic() {
        music.stop();
    }

    //Creating Buttons
    private void addGameMenu() {
        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(Color.GRAY);

        //---STOP THE MUSIC----
        FancyButton musicButton = new FancyButton("volume", 1);
        musicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                stopMusic();
            }
        });
        menuPanel.add(musicButton);
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
        specieSelector.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JComboBox cb = (JComboBox) ae.getSource();
                Integer selectedValue = (Integer) cb.getSelectedItem();
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
                restartGame();
            }
        });
        menuPanel.add(restartButton);

        this.getContentPane().add(menuPanel, BorderLayout.SOUTH);
    }
}
