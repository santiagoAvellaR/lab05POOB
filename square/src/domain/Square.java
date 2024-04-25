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

    private void moveUp() throws SquareException {
        for (int i = 1; i < boardSize; i++){
            for (int j = 0; j < boardSize; j++){
                makeMovement(i, j, i-1, j);
            }
        }
    }

    private void moveDown() throws SquareException {
        for (int i = boardSize-1-1; i >= 0; i--){
            for (int j = 0; j < boardSize; j++){
                makeMovement(i, j, i+1, j);
            }
        }
    }

    private void moveRight() throws SquareException {
        for (int j = 0; j < boardSize-1-1; j++){
            for (int i = 0; i < boardSize; i++){
                makeMovement(i, j, i, j+1);
            }
        }
    }

    private void moveLeft() throws SquareException {
        for (int j = boardSize-1-1; j >= 0; j--){
            for (int i = 0; i < boardSize; i++){
                makeMovement(i, j, i, j-1);
            }
        }
    }

    public void move(char direction) throws SquareException {
        if (direction == 'u' || direction == 'U'){
            moveUp();
        }
        else if (direction == 'd' || direction == 'D'){
            moveDown();
        }
        else if (direction == 'r' || direction == 'R'){
            moveRight();
        }
        else if (direction == 'l' || direction == 'L'){
            moveLeft();
        }
    }

    private void makeMovement(int iniRow, int iniCol, int finRow, int finCol) throws SquareException{
        //  ficha - hueco - hueroRelleno - huecoRellenoFicha
        if(squareBoard[iniRow][finCol] == null){return;}
        String[] initialSquareInfo= squareBoard[iniRow][finCol].split(" ");
        if(initialSquareInfo[0].equals("hueco") || initialSquareInfo[0].equals("huecoRelleno")){return;}
        if (initialSquareInfo[0].equals("ficha")){
            if (squareBoard[finRow][finCol] == null){
                squareBoard[finRow][finCol] = squareBoard[iniRow][iniCol];
                squareBoard[iniRow][iniCol] = null;
            }
            else {
                String[] finalSquareInfo = squareBoard[finRow][finCol].split(" ");
                if (finalSquareInfo[0].equals("hueco")) {
                    if(finalSquareInfo[1].equals(initialSquareInfo[1])){
                        squareBoard[finRow][finCol] = "huecoRelleno" + " " + initialSquareInfo[1];
                        squareBoard[iniRow][iniCol] = null;
                    }
                    else{
                        gameEnd = true;
                        throw new SquareException(SquareException.GAME_END);
                    }
                }
                else if (finalSquareInfo[0].equals("huecoRelleno")){
                    squareBoard[finRow][finCol] = "huecoRelleno" + " " + finalSquareInfo[1] + " " + initialSquareInfo[1];
                    squareBoard[iniRow][iniCol] = null;
                }
            }
        }
        else if (initialSquareInfo[0].equals("huecoRellenoFicha")){
            if (squareBoard[finRow][finCol] == null){
                squareBoard[finRow][finCol] = "ficha" + " " + initialSquareInfo[2];
                squareBoard[iniRow][iniCol] = "hueco" + " " + initialSquareInfo[1];
            }
            else {
                String[] finalSquareInfo = squareBoard[finRow][finCol].split(" ");
                if (finalSquareInfo[0].equals("hueco")) {
                    if(finalSquareInfo[1].equals(initialSquareInfo[2])){
                        squareBoard[finRow][finCol] = "huecoRelleno" + " " + initialSquareInfo[2];
                        squareBoard[iniRow][iniCol] = "huecoRelleno" + " " + initialSquareInfo[1];
                    }
                    else{
                        gameEnd = true;
                        throw new SquareException(SquareException.GAME_END);
                    }
                }
                else if (finalSquareInfo[0].equals("huecoRelleno")){
                    squareBoard[finRow][finCol] = "huecoRellenoFicha" + " " + finalSquareInfo[1] + " " + initialSquareInfo[2];
                    squareBoard[iniRow][iniCol] = "huecoRelleno" + " " + initialSquareInfo[1];
                }
            }
        }
    }

}
