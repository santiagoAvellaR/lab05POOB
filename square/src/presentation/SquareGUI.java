package src.presentation;

import src.domain.Square;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import java.awt.*;

/**
 * Write a description of class SquareGUI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SquareGUI extends JFrame{
    private Square square;

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
