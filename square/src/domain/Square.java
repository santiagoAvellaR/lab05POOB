package src.domain;

import java.awt.*;

/**
 * Write a description of class Square here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Square {
    private String[][] sqaureBoard;
    private int boardSize;
    private int numberHoles;

    /**
     * Constructor for objects of class Square
     */
    public Square(String boardSize, String numberHoles) throws SquareException {
        if(boardSize.matches("[0-9]+") && !boardSize.isEmpty()){
            int intBoardSize = Integer.parseInt(boardSize);
            if (intBoardSize < 1 && intBoardSize > 20){
                throw new SquareException(SquareException.MAXIMUM_SIZE_EXCEEDED);
            }
            this.boardSize = intBoardSize;
            sqaureBoard = new String[intBoardSize][intBoardSize];
        }
        else{
            throw new SquareException(SquareException.INVALID_SIZE);
        }
        if(numberHoles.matches("[0-9]+") && !numberHoles.isEmpty()){
            int intNumberHoles = Integer.parseInt(numberHoles);
            this.numberHoles = Integer.parseInt(numberHoles);
        }
        else{

        }
    }

    public String getSquare(int row, int col){
        return sqaureBoard[row][col];
    }

    public void changeColor(int row, int col, Color color){
        sqaureBoard[row][col] = "#";
    }
}
