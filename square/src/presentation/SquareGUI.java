package src.presentation;

import src.domain.Square;
import src.domain.SquareException;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.HashMap;

/**
 * Write a description of class SquareGUI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SquareGUI extends JFrame {
    Dimension screenSize;
    // domain modelo
    private Square square;
    private final SquareGUI gui = this;
    // Menu
    private JMenuItem nuevo, abrir, guardar, cerrar;
    // Principal
    private JPanel mainPanel;
    private JButton newGameButton;
    private JTextField sizeTextField;
    private JTextField holesTextField;
    // Game window
    private JLabel scoreGame;
    private JLabel turns;
    private JPanel gamePanel;
    private JPanel boardPanel;
    private JButton homeButton;
    private JButton upButton;
    private JButton downButton;
    private JButton leftButton;
    private JButton rightButton;
    private JButton changeSizeButton;
    private JButton reStartButton;


    /**
     * Constructor for objects of class SquareGUI
     */
    private SquareGUI(){
        super("Square");
        prepareElements();
        prepareActions();
    }

    private void prepareElements(){
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width/2, screenSize.height/2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        prepareMenuElements();
        prepareMainWindowElements();
        setResizable(false);
    }

    private void prepareActions(){
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
        nuevo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ev){
                if(square != null){
                    int option = JOptionPane.showConfirmDialog(gui, "¿Quieres guardar la partida?", "Guardar Juego", JOptionPane.YES_NO_OPTION);
                    if(option == JOptionPane.YES_OPTION){
                        guardar.doClick();
                    }
                    newGameButton.doClick();
                }
                else {
                    newGameButton.doClick();
                }
            }
        });
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
        changeSizeButton = new JButton("Cambiar de tamaño");
        changeSizeButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        changeSizeButton.setBackground(Color.DARK_GRAY);
        changeSizeButton.setForeground(Color.WHITE);
        JPanel movePlayerPlanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        movePlayerPlanel.setBorder(new CompoundBorder());
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
        gamePanelOptions.add(changeSizeButton);
        gamePanelOptions.add(homeButton);
        scoreGame = new JLabel("Puntuacion: " + (int)(square.getPieces()/square.getHoles()) + "%");
        gamePanelOptions.add(scoreGame);
        Font nuevaFuente = new Font("Tahoma", Font.BOLD, 20);
        scoreGame.setFont(nuevaFuente);
        turns = new JLabel("Turno #: " + (int)square.getTurn());
        gamePanelOptions.add(turns);
        turns.setFont(nuevaFuente);
        reStartButton = new JButton("Reiniciar");
        reStartButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        reStartButton.setBackground(Color.DARK_GRAY);
        reStartButton.setForeground(Color.WHITE);
        gamePanelOptions.add(reStartButton);
        gamePanel.add(gamePanelOptions,BorderLayout.EAST);
        gamePanel.add(boardPanel, BorderLayout.CENTER);
    }

    private void updateScore() {
        scoreGame.setText("Puntuación: " +  (int)(square.getPieces()/square.getHoles()) +"%");
        turns.setText("Turno #: " + (int)square.getTurn());
    }

    private void prepareGameActions(){
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(gui, "¿Quieres abandonar la partida, no se guardara tu progreso?", "Abandonar Partida", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    setContentPane(mainPanel);
                    revalidate();
                    repaint();
                    square = null;
                }
            }
        });

        changeSizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel = new JPanel(new GridLayout(2, 2));
                JTextField newSize = new JTextField();
                JTextField newNumberHoles = new JTextField();
                panel.add(new JTextField("Tamaño: "));
                panel.add(newSize);
                panel.add(new JTextField("Número de huecos: "));
                panel.add(newNumberHoles);
                String[] botones = {"OK", "Cancelar", "Mantener mismas medidas"};
                int option = JOptionPane.showOptionDialog(null, panel, "Cambio de Tamaño", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, botones, botones[0]);
                if (option == 0) {
                    sizeTextField.setText(newSize.getText().trim());
                    holesTextField.setText(newNumberHoles.getText().trim());
                    newGameButton.doClick();
                }
                else if (option == 1) {
                }
                else if (option == 2) {
                    newGameButton.doClick();
                }
                else {
                    JOptionPane.showMessageDialog(gui, "Acción no valida, vuelva a intentarlo: ", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        upButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ev){
                try{
                    square.move('u');
                }
                catch(SquareException e){
                    offerPlayAgain(e.getMessage());
                }
                finally {
                    refresh();
                    updateScore();
                }
            }
        });

        leftButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ev){
                try{
                    square.move('l');
                }
                catch(SquareException e){
                    offerPlayAgain(e.getMessage());
                }
                finally {
                    refresh();
                    updateScore();
                }
            }
        });

        downButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ev){
                try{
                    square.move('d');
                }
                catch(SquareException e){
                    offerPlayAgain(e.getMessage());
                }
                finally {
                    refresh();
                    updateScore();
                }
            }
        });

        rightButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ev){
                try{
                    square.move('r');
                }
                catch(SquareException e){
                    offerPlayAgain(e.getMessage());
                }
                finally {
                    refresh();
                    updateScore();
                }
            }
        });
        reStartButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ev){
                square.reStart();
                refresh();
                updateScore();
            }
        });
    }

    private void offerPlayAgain(String message){
        if (message.equals(SquareException.PLAYER_LOST) || message.equals(SquareException.PLAYER_WON)) {
            String mensaje = (message.equals(SquareException.PLAYER_LOST)) ? "Perdiste. " : "Ganaste. ";
            int option = JOptionPane.showConfirmDialog(gui,   mensaje + "¿Quieres volver a jugar?", "Juego Finalizado", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                changeSizeButton.doClick();
            }
            else{
                homeButton.doClick();
            }
        }
        else {
            JOptionPane.showMessageDialog(gui, "Error al mover: " + message, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void prepareBoardElementsAndFillTheBoard() {
        int screenHeight = screenSize.height/2;
        int boardSize = Integer.parseInt(sizeTextField.getText().trim());
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridBagLayout());
        boardPanel.setPreferredSize(new Dimension(screenHeight, screenHeight));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 0);
        HashMap<String, Integer[]> holesNotAsign = new HashMap<>();
        HashMap<String, Integer[]> piecesNotAsign = new HashMap<>();
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                JPanel squareOnBoard = new JPanel();
                squareOnBoard.setBackground(Color.WHITE);
                int tamanno = boardSize > 4 ? screenHeight/(int)((float)(boardSize*1.2)) : screenHeight/(int)((float)(boardSize*1.4));
                squareOnBoard.setPreferredSize( new Dimension(tamanno, tamanno));
                squareOnBoard.setMinimumSize( new Dimension(tamanno, tamanno));
                squareOnBoard.setMaximumSize( new Dimension(tamanno, tamanno));
                squareOnBoard.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                if (square.getSquareInformation(i, j) != null) {
                    String[] squareInformation = square.getSquareInformation(i, j).split(" ");
                    switch (squareInformation[0]) {
                        case "hueco" -> {
                            if (piecesNotAsign.containsKey(squareInformation[1])) {
                                Integer[] indexPiece = piecesNotAsign.get(squareInformation[1]);
                                boolean hasDiferentBackGround = squareInformation.length >= 3;
                                addAndPaintPiece((JPanel) boardPanel.getComponent(boardSize * indexPiece[0] + indexPiece[1]), indexPiece[0], indexPiece[1], squareInformation[1], i, j, hasDiferentBackGround ? Integer.toString(indexPiece[3]) : "", tamanno, hasDiferentBackGround);
                                piecesNotAsign.remove(squareInformation[1]);
                            } else {
                                holesNotAsign.put(squareInformation[1], new Integer[]{i, j});
                            }
                            addAndPaintHole(squareOnBoard, i, j, squareInformation[1], tamanno);
                        }
                        case "ficha" -> {
                            if (holesNotAsign.containsKey(squareInformation[1])) {
                                Integer[] indexHole = holesNotAsign.get(squareInformation[1]);
                                addAndPaintPiece(squareOnBoard, i, j, squareInformation[1], indexHole[0], indexHole[1], "", tamanno, false);
                                holesNotAsign.remove(squareInformation[1]);
                            } else {
                                piecesNotAsign.put(squareInformation[1], new Integer[]{i, j});
                            }
                        }
                        case "huecoRelleno" ->
                                addAndPaintPiece(squareOnBoard, i, j, squareInformation[1], i, j, squareInformation[1], tamanno, true);
                        case "huecoRellenoFicha" -> {
                            if (holesNotAsign.containsKey(squareInformation[2])) {
                                Integer[] indexHole = holesNotAsign.get(squareInformation[2]);
                                addAndPaintPiece(squareOnBoard, i, j, squareInformation[2], indexHole[0], indexHole[1], squareInformation[1], tamanno, true);
                                holesNotAsign.remove(squareInformation[2]);
                            } else {
                                piecesNotAsign.put(squareInformation[2], new Integer[]{i, j, Integer.parseInt(squareInformation[1])});
                            }
                        }
                    }
                }
                else{
                    squareOnBoard.add(new Label());
                }
                gbc.gridx = j;
                gbc.gridy = i;
                boardPanel.add(squareOnBoard, gbc);
                //System.out.println("fichas: " + (piecesNotAsign.keySet()));
                //System.out.println("huecos: " + (holesNotAsign.keySet()));
                //System.out.println();
            }
        }
    }

    private void addAndPaintPiece(JPanel place, int buttonRow, int buttonCol, String color, int holeRow, int holeCol, String backGroundColor, int tam, boolean backGroundDifferent) {
        ChangeColorButton piece = new ChangeColorButton("", buttonRow, buttonCol, color, holeRow, holeCol);
        place.setLayout(new GridBagLayout());
        piece.setBackground(new Color(Integer.parseInt(color)));
        int buttonSize = (int) (float)(tam * 0.5);
        piece.setPreferredSize(new Dimension(buttonSize, buttonSize));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = buttonCol;
        gbc.gridy = buttonRow;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        place.setBackground(backGroundDifferent ? new Color(Integer.parseInt(backGroundColor)): Color.WHITE);
        place.add(piece, gbc);
    }

    private void addAndPaintHole(JPanel place, int buttonRow, int buttonCol,  String color, int tam){
        JButton hole = new JButton();
        hole.setEnabled(false);
        place.setLayout(new GridBagLayout());
        place.setBackground(new Color(Integer.parseInt(color)));
        int buttonSize = (int)(float)(tam * 0.5);
        hole.setPreferredSize(new Dimension(buttonSize, buttonSize));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = buttonCol;
        gbc.gridy = buttonRow;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        hole.setBackground(Color.WHITE);
        place.add(hole, gbc);
    }

    private void createNewGame() throws SquareException {
        String boardSize = sizeTextField.getText().trim();
        String numberHoles = holesTextField.getText().trim();

        square = new Square(boardSize, numberHoles);
        prepareBoardElementsAndFillTheBoard();
    }

    private Square getSquare(){return square;}

    public void refresh(){
        gamePanel.remove(boardPanel);
        prepareBoardElementsAndFillTheBoard();
        gamePanel.add(boardPanel);
        gamePanel.revalidate();
        gamePanel.repaint();
    }

    public static void main(String args[]){
        SquareGUI gui = new SquareGUI();
        gui.setVisible(true);
    }





    public class ChangeColorButton extends JButton {
        private final Square square1 = getSquare();
        private String color;
        private  int row;
        private  int column;
        private final int holeRow;
        private final int holeCol;

        public ChangeColorButton(String text, int row, int column, String color, int holeRow, int holeCol) {
            super(text);
            this.color = color;
            this.row = row;
            this.column = column;
            this.holeRow = holeRow;
            this.holeCol = holeCol;
            addActionListener(new DefaultActionListener());
        }

        public int getNumberComponentHole(){
                return square1.getSize()* holeRow + holeCol;}

        public void setColor(String newColor){color = newColor;}


        private class DefaultActionListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color newColor = JColorChooser.showDialog(gui, "Seleccionar Color", Color.WHITE);
                if (newColor == null){
                    JOptionPane.showMessageDialog(gui, "Color no valida, vuelva a seleccionar");
                }
                else {
                    String newColorString = Integer.toString(newColor.getRGB());
                    setColor(newColorString);
                    square.changeColor(row, column, newColorString);
                    setBackground(new Color(newColor.getRGB()));
                    square.changeColor(holeRow, holeCol, newColorString);
                    boardPanel.getComponent(getNumberComponentHole()).setBackground(new Color(newColor.getRGB()));
                }
            }
        }
    }
}
