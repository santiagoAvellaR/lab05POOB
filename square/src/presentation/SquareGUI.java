package src.presentation;

import src.domain.Square;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import javax.swing.event.*;
import java.util.*;

/**
 * Write a description of class SquareGUI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SquareGUI extends JFrame{
    private Square square;
    // Menu
    private JMenuBar menuBar;
    private JMenu opciones;
    private JMenuItem nuevo, abrir, guardar, cerrar;



    /**
     * Constructor for objects of class SquareGUI
     */
    private SquareGUI(){
        super("Square");
        square = new Square();
        prepareElements();
        prepareActions();
    }

    private void prepareElements(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width/2, screenSize.height/2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        prepareMenuElements();
    }

    private void prepareMenuElements(){
        // Botones-Opciones Menu
        menuBar = new JMenuBar();
        opciones = new JMenu("Opciones");
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
        prepareMenuActions();
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
    }

    public static void main(String args[]){
        SquareGUI gui = new SquareGUI();
        gui.setVisible(true);
    }
}
