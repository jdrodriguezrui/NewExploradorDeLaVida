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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import menu.Memoria;
import menu.Menu;

public class GameFrame extends JFrame {

    public Board gameBoard;
    private Thread music;
    public static int selectedSpecie = Cell.BLACK_SPECIES;
    private Memoria memoria;
    private int gameSpeed;
    private Timer timer;
    private FileNameExtensionFilter filtro = new FileNameExtensionFilter(".EJI", "EJI");
    private int turnsPerSecond = 1;

    //-----------------STANDARD CONSTRUCTOR-----------------
    public GameFrame() {
        super("Explorador de la vida");
        this.setPreferredSize(new Dimension(800, 800));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.gameBoard = new Board(Board.DEFAULT_SIZE);

        this.memoria = new Memoria();
        //Simulation stuff
        this.setGameSpeed(2);
        this.timer = new Timer(this.gameSpeed, new EventListenerForSimulation(this));
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

    //--------------Constructor de cuando se carga una partida----------------------
    public GameFrame(Board gameBoard) {
        super("Explorador de la vida");
        this.setPreferredSize(new Dimension(800, 800));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.memoria = new Memoria();
        this.gameBoard = gameBoard;

        //Simulation stuff
        this.setGameSpeed(2);                                               // NEW!!! Default speed = 1 second
        //this.timer = new Timer(this.gameSpeed, new EventListenerForSimulation(this)); // NEW!!!
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
        this.gameSpeed = 500/secondsPerTurn;  //milisegundos
        this.timer = new Timer(this.gameSpeed, new EventListenerForSimulation(this));
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

    // -----------------------Guardar y cargar partida------------------------   
    public void saveGame() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(filtro);
        int opcion = fileChooser.showSaveDialog(this);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();
            this.memoria.setNombre(archivoSeleccionado.getAbsolutePath());
            this.memoria.setMemorisa(getGameBoard());
            this.memoria.guardar();
        } else if (opcion == JFileChooser.CANCEL_OPTION) {
            fileChooser.setVisible(false);
        }
    }

    public void loadGame() throws IOException, FileNotFoundException, ClassNotFoundException {
        JFileChooser filechooser = new JFileChooser();
        filechooser.setFileFilter(filtro);
        int opcion = filechooser.showOpenDialog(this);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = filechooser.getSelectedFile();
            this.memoria.setNombre(archivoSeleccionado.getAbsolutePath());
            this.memoria.abrir();
            hide();
            music.stop();
            new GameFrame(this.memoria.getMemorisa());

        } else if (opcion == JFileChooser.CANCEL_OPTION) {
            filechooser.hide();

        }
    }

    //--------------Getter and Setter para gardar y cargar-----------------------
    public Board getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    //Cierra el juego y abre otra vez el menú
    public void openMenu() {
        music.stop();
        hide();
        Menu menu = new Menu();
        menu.setVisible(true);
    }

    //Para la música (Duh)
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
                try {
                    saveGame();
                } catch (IOException ex) {
                    Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        menuPanel.add(saveButton);
        //--------CARGAR-----
        FancyButton loadButton = new FancyButton("Cargar");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    loadGame();
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
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
        //-----SLIDER DE VELOCIDAD----
        JSlider slider = new JSlider(1, 5,2);
        JLabel mensaje = new JLabel("Velocidad");
        mensaje.setForeground(Color.WHITE);
        slider.setPreferredSize(new Dimension(120, 60));
        slider.setBackground(Color.GRAY);
        Hashtable table = new Hashtable();        
        table.put(new Integer(1), new JLabel());
        table.put(new Integer(2), new JLabel());
        table.put(new Integer(3), mensaje);
        table.put(new Integer(4), new JLabel());
        table.put(new Integer(5), new JLabel());
        slider.setLabelTable(table);
        slider.setPaintLabels(true);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                JSlider source = (JSlider) ce.getSource();
                GameFrame.this.timer.stop();
                GameFrame.this.setGameSpeed((int)source.getValue());
            }
        });

        menuPanel.add(slider);
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
                pauseSimulation();
                restartGame();
            }
        });
        menuPanel.add(restartButton);

        this.getContentPane().add(menuPanel, BorderLayout.SOUTH);
    }
}
