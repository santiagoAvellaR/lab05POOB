package src.presentation;

import src.domain.Square;
import src.domain.SquareException;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;

/**
 * Write a description of class SquareGUI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SquareGUI extends JFrame {
    // domain modelo
    private Square square;
    // Menu
    private JMenuItem nuevo, abrir, guardar, cerrar;
    // Principal
    private JPanel mainPanel;
    private JButton newGameButton;
    private JTextField sizeTextField;
    private JTextField holesTextField;
    // Game window
    private JPanel gamePanel;
    private JPanel boardPanel;
    private JButton homeButton;
    private JButton changeColorButton;
    private JButton upButton;
    private JButton downButton;
    private JButton leftButton;
    private JButton rightButton;
    private JButton refreshButton;


    /**
     * Constructor for objects of class SquareGUI
     */
    private SquareGUI(){
        super("Square");
        prepareElements();
        prepareActions();
    }

    private void prepareElements(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width/2, screenSize.height/2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        prepareMenuElements();
        prepareMainWindowElements();
        setResizable(false);
    }

    private void prepareActions(){
        SquareGUI gui = this;
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent event){
                int option = JOptionPane.showConfirmDialog(gui, "¿Estás seguro de que quieres cerrar?", "Cofirmar cierre", JOptionPane.YES_NO_OPTION);
                if(option == JOptionPane.YES_OPTION){
                    gui.setVisible(false);
                    System.exit(0);
                }
            }
        });
        prepareMenuActions();
        prepareMainWindowActions();
    }

    private void prepareMenuElements(){
        // Botones-Opciones Menu
        JMenuBar menuBar;
        JMenu opciones;
        menuBar = new JMenuBar();
        opciones = new JMenu("Archivo");
        setJMenuBar(menuBar);
        nuevo = new JMenuItem("Nuevo");
        opciones.add(nuevo);
        opciones.addSeparator();
        abrir = new JMenuItem("Abrir");
        opciones.add(abrir);
        opciones.addSeparator();
        guardar = new JMenuItem("Guardar");
        opciones.add(guardar);
        opciones.addSeparator();
        cerrar = new JMenuItem("Cerrar");
        opciones.add(cerrar);
        menuBar.add(opciones);
    }

    private void prepareMenuActions(){
        SquareGUI gui = this;
        cerrar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ev){
                int option = JOptionPane.showConfirmDialog(gui, "¿Estás seguro de que quieres cerrar?", "Cofirmar cierre", JOptionPane.YES_NO_OPTION);
                if(option == JOptionPane.YES_OPTION){
                    gui.setVisible(false);
                    System.exit(0);
                }
            }
        });

        abrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser openFileChooser = new JFileChooser();
                int result = openFileChooser.showOpenDialog(gui);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = openFileChooser.getSelectedFile();
                    JOptionPane.showMessageDialog(gui, "Funcionalidad de abrir en construcción. Archivo seleccionado: " + selectedFile.getName(), "Abrir Juego", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        guardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int option = fileChooser.showSaveDialog(gui);
                if(option == JFileChooser.APPROVE_OPTION){
                    java.io.File selectedFolder = fileChooser.getSelectedFile();
                    JOptionPane.showMessageDialog(gui, "Funcionalidad de guardar en construcción. Carpeta seleccionada: " + selectedFolder.getName(), "Guardar Juego", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    private void prepareMainWindowElements(){
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 1, 0, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel titleLabel = new JLabel("Square Game");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel);
        // Board size
        JPanel boardSizePanel = new JPanel(new BorderLayout());
        boardSizePanel.setBackground(Color.LIGHT_GRAY);
        boardSizePanel.add(Box.createRigidArea(new Dimension(10, 10)), BorderLayout.NORTH);
        boardSizePanel.add(Box.createRigidArea(new Dimension(10, 10)), BorderLayout.WEST);
        boardSizePanel.add(Box.createRigidArea(new Dimension(10, 10)), BorderLayout.EAST);
        boardSizePanel.add(Box.createRigidArea(new Dimension(10, 10)), BorderLayout.SOUTH);
        JLabel boardSizeLabel = new JLabel("Tamaño del tablero:");
        sizeTextField = new JTextField(10);
        JPanel boardSizeInputPanel = new JPanel();
        boardSizeInputPanel.add(boardSizeLabel);
        boardSizeInputPanel.add(sizeTextField);
        boardSizePanel.add(boardSizeInputPanel, BorderLayout.CENTER);
        mainPanel.add(boardSizePanel);
        // Holes on Board
        JPanel holesPanel = new JPanel(new BorderLayout());
        holesPanel.setBackground(Color.LIGHT_GRAY);
        holesPanel.add(Box.createRigidArea(new Dimension(10, 10)), BorderLayout.NORTH);
        holesPanel.add(Box.createRigidArea(new Dimension(10, 10)), BorderLayout.WEST);
        holesPanel.add(Box.createRigidArea(new Dimension(10, 10)), BorderLayout.EAST);
        holesPanel.add(Box.createRigidArea(new Dimension(10, 10)), BorderLayout.SOUTH);
        JLabel holesLabel = new JLabel("Número de huecos:");
        holesTextField = new JTextField(10);
        JPanel holesInputPanel = new JPanel();
        holesInputPanel.add(holesLabel);
        holesInputPanel.add(holesTextField);
        holesPanel.add(holesInputPanel, BorderLayout.CENTER);
        mainPanel.add(holesPanel);
        // new game button
        JPanel newGamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        newGamePanel.setBackground(Color.LIGHT_GRAY);
        newGamePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        newGameButton = new JButton("Juego Nuevo");
        newGameButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        newGameButton.setBackground(Color.DARK_GRAY);
        newGameButton.setForeground(Color.WHITE);
        newGamePanel.add(newGameButton);
        mainPanel.add(newGamePanel);

        add(mainPanel);
    }

    private void prepareMainWindowActions(){
        SquareGUI gui = this;
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    createNewGame();
                    prepareGameElements();
                    prepareGameActions();
                    setContentPane(gamePanel);
                    revalidate();
                    repaint();
                }
                catch (SquareException exception){
                    JOptionPane.showMessageDialog(gui, "Error al crear  tablero: " + exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    setContentPane(mainPanel);
                    revalidate();
                    repaint();
                }
            }
        });
    }

    private void prepareGameElements(){
        gamePanel = new JPanel();
        gamePanel.setLayout(new BorderLayout());
        homeButton = new JButton("Inicio");
        homeButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        homeButton.setBackground(Color.DARK_GRAY);
        homeButton.setForeground(Color.WHITE);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        gamePanel.add(buttonPanel, BorderLayout.SOUTH);
        JPanel gamePanelOptions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        gamePanelOptions.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5), new TitledBorder("Opciones")));
        gamePanelOptions.setLayout(new GridLayout(6,1,3,3));
        changeColorButton = new JButton("Cambiar color");
        changeColorButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        changeColorButton.setBackground(Color.DARK_GRAY);
        changeColorButton.setForeground(Color.WHITE);
        refreshButton = new JButton("Actualizar");
        refreshButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        refreshButton.setBackground(Color.DARK_GRAY);
        refreshButton.setForeground(Color.WHITE);
        JPanel movePlayerPlanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        movePlayerPlanel.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5), new TitledBorder("Mover")));
        movePlayerPlanel.setLayout(new GridLayout(2,1,3,3));
        movePlayerPlanel.add(new JLabel());
        upButton = new JButton("↑");
        upButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        upButton.setBackground(Color.DARK_GRAY);
        upButton.setForeground(Color.WHITE);
        movePlayerPlanel.add(upButton);
        movePlayerPlanel.add(new JLabel());
        leftButton = new JButton("←");
        leftButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        leftButton.setBackground(Color.DARK_GRAY);
        leftButton.setForeground(Color.WHITE);
        movePlayerPlanel.add(leftButton);
        downButton = new JButton("↓");
        downButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        downButton.setBackground(Color.DARK_GRAY);
        downButton.setForeground(Color.WHITE);
        movePlayerPlanel.add(downButton);
        rightButton = new JButton("→");
        rightButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        rightButton.setBackground(Color.DARK_GRAY);
        rightButton.setForeground(Color.WHITE);
        movePlayerPlanel.add(rightButton);
        gamePanelOptions.add(movePlayerPlanel);
        gamePanelOptions.add(changeColorButton);
        gamePanelOptions.add(refreshButton);
        gamePanelOptions.add(homeButton);
        gamePanel.add(gamePanelOptions,BorderLayout.EAST);

        prepareBoardElements();
        gamePanel.add(boardPanel, BorderLayout.CENTER);
    }

    private void prepareGameActions(){
        SquareGUI gui = this;
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(mainPanel);
                revalidate();
                repaint();
            }
        });
        changeColorButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ev){
                Color color = JColorChooser.showDialog(gui, "Seleccionar Color", Color.WHITE);
                changeColor(color);
            }
        });
    }

    private void prepareBoardElements() {
        int boardSize = Integer.parseInt(sizeTextField.getText().trim());
        boardPanel = new JPanel(new GridLayout(boardSize, boardSize));
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                JLabel label = new JLabel();
                label.setOpaque(true);
                label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                boardPanel.add(label);
            }
        }
    }

    private void changeColor(Color newColor){
        try{
            square.changeColor(1, 1, String.valueOf(newColor.getRGB()));
        }
        catch(SquareException e){
            JOptionPane.showMessageDialog(this, "Error al cambiar el color: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createNewGame() throws SquareException {
        String boardSize = sizeTextField.getText().trim();
        String numberHoles = holesTextField.getText().trim();

        square = new Square(boardSize, numberHoles);
        prepareBoardElements();
    }

    public static void main(String args[]){
        SquareGUI gui = new SquareGUI();
        gui.setVisible(true);
    }

}
