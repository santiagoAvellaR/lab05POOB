package src.domain;

import java.awt.*;

/**
 * Write a description of class Square here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Square {
    private String[][] squareBoard;
    private int boardSize;
    private int numberHoles;
    private boolean gameEnd;

    /**
     * Constructor for objects of class Square
     */
    public Square(String boardSize, String numberHoles) throws SquareException {
        if(boardSize.matches("[0-9]+") && !boardSize.isEmpty()){
            int intBoardSize = Integer.parseInt(boardSize);
            if (intBoardSize < 1 || intBoardSize > 20){
                throw new SquareException(SquareException.MAXIMUM_SIZE_EXCEEDED);
            }
            this.boardSize = intBoardSize;
            squareBoard = new String[intBoardSize][intBoardSize];
        }
        else{
            throw new SquareException(SquareException.INVALID_SIZE);
        }
        if(numberHoles.matches("[0-9]+") && !numberHoles.isEmpty()){
            int intNumberHoles = Integer.parseInt(numberHoles);
            if (intNumberHoles < 1 || intNumberHoles > Math.pow((float) this.boardSize,2)/2){
                throw new SquareException(SquareException.MAXIMUM_NUMBER_HOLES_EXCEEDED);
            }
            this.numberHoles = intNumberHoles;
        }
        else{
            throw new SquareException(SquareException.INVALID_NUMBER_HOLES);
        }
        gameEnd = false;
    }

    public String getSquare(int row, int col){
        return squareBoard[row][col];
    }

    public void changeColor(int row, int col, String color) throws SquareException {
        if (color == null){
            throw new SquareException(SquareException.INVALID_COLOR);
        }
        if (squareBoard[row][col] != null){
            throw new SquareException(SquareException.EMPTY_SQUARE);
        }
        String[] values = squareBoard[row][col].split(" ");
        squareBoard[row][col] = values[0] + " " +  values[1] + " " + color;
    }

    public void moveUp() throws SquareException {
        for (int i = 1; i < boardSize; i++){
            for (int j = 0; j < boardSize; j++){
                makeMovement(i, j, i, j-1);
            }
        }
    }

    private void makeMovement(int rowIni, int colIni, int rowFin, int colFin) throws SquareException{
        if (squareBoard[rowIni][colIni] != null){
            String[] initialSquareInfo = squareBoard[rowIni][colIni].split(" ");
            if (initialSquareInfo[1].equals("hole:false")){
                if (initialSquareInfo[0].equals("square:true")){
                    if (squareBoard[rowFin][colFin] != null){
                        String[] finalSquareInfo = squareBoard[rowFin][colFin].split(" ");
                        if (finalSquareInfo[1].equals("hole:true")){
                            if (finalSquareInfo[2].equals(initialSquareInfo[2])){
                                squareBoard[rowIni][colIni] = null;
                                setSquareStatus(rowFin, rowIni, true, true);
                            }
                            else{
                                gameEnd = true;
                                throw new SquareException(SquareException.GAME_END);
                            }
                        }
                    }
                    else{
                        squareBoard[rowFin][colFin] = squareBoard[rowIni][colIni];
                        squareBoard[rowIni][colIni] = null;
                    }
                }
            }
        }
    }

    private void setSquareStatus(int row, int col, boolean isSquare, boolean isHole){
        String[] squareInfo = squareBoard[row][col].split(" ");
        squareBoard[row][col] = "square:" + isSquare + " " + "hole:" + isHole + " " + squareInfo[2];
    }

}
