package src.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SquareTest {
    private Square square;

    /*TEST MOVE UP*/
    @Test
    void shouldMoveUpAndWin1() {
        try {
            square = new Square("2", "2");
            String[][] initialBoard = {{"hueco azul", "hueco verde"},
                                       {"ficha azul", "ficha verde"}};
            square.setBoard(initialBoard);
            square.move('u');
            String[][] expectedAnswer = {{"huecoRelleno azul", "huecoRelleno verde"}, {null, null}};
            assertArrayEquals(expectedAnswer, square.getBoard());
        }
        catch (SquareException e){
            assertEquals(SquareException.PLAYER_WON, e.getMessage());
        }
    }

    @Test
    void shouldMoveUp2() {
        try {
            square = new Square("4", "7");
            String[][] initialBoard = {{"hueco azul", "hueco gris", "hueco rosado", "hueco morado"},
                                       {"hueco amarillo", "ficha gris", "hueco verde", "ficha morado"},
                                       {null, null, "ficha verde" ,"hueco cafe"},
                                       {"ficha amarillo", "ficha azul", "ficha rosado", "ficha cafe"}};
            square.setBoard(initialBoard);
            square.move('U');
            String[][] expectedAnswer = {{"hueco azul", "huecoRelleno gris", "hueco rosado", "huecoRelleno morado"},
                                         {"hueco amarillo", null, "huecoRelleno verde", null},
                                         {"ficha amarillo", "ficha azul", "ficha rosado", "huecoRelleno cafe"},
                                         {null, null, null, null}};
            assertArrayEquals(expectedAnswer, square.getBoard());
            square.move('U');
            String[][] expectedAnswer1 = {{"hueco azul", "huecoRelleno gris", "hueco rosado", "huecoRelleno morado"},
                                          {"huecoRelleno amarillo", "ficha azul", "huecoRellenoFicha verde rosado", null},
                                          {null, null, null, "huecoRelleno cafe"},
                                          {null, null, null, null}};
            assertArrayEquals(expectedAnswer1, square.getBoard());
            square.move('U');
            String[][] expectedAnswer2 = {{"hueco azul", "huecoRellenoFicha gris azul", "huecoRelleno rosado", "huecoRelleno morado"},
                                          {"huecoRelleno amarillo", null, "huecoRelleno verde", null},
                                          {null, null, null, "huecoRelleno cafe"},
                                          {null, null, null, null}};
            assertArrayEquals(expectedAnswer2, square.getBoard());
        }
        catch (SquareException e){
            fail("threw exception");
        }
    }

    /*TEST MOVE DOWN*/
    @Test
    void shouldMoveUpAndLose1() {
        try {
            square = new Square("2", "2");
            String[][] initialBoard = {{"hueco azul", "hueco verde"},
                                       {"ficha verde", "ficha azul"}};
            square.setBoard(initialBoard);
            square.move('u');
            fail("Did not threw exception");
        }
        catch (SquareException e){
            assertEquals(e.getMessage(), SquareException.PLAYER_LOST);
        }
    }

    @Test
    void shouldMoveUpAndLose2() {
        try {
            square = new Square("4", "3");
            String[][] initialBoard = {{null, "hueco azul", null, "hueco rosado"},
                                       {null, "hueco verde", null, null},
                                       {null, "ficha verde", null,null},
                                       {null, "ficha rosado", "ficha azul", null}};
            square.setBoard(initialBoard);
            square.move('u');
            String[][] expectedAnswer1 = {{null, "hueco azul", null, "hueco rosado"},
                                         {null, "huecoRelleno verde", null, null},
                                         {null, "ficha rosado", "ficha azul",null},
                                         {null, null, null, null}};
            assertArrayEquals(expectedAnswer1, square.getBoard());
            square.move('u');
            String[][] expectedAnswer2 = {{null, "hueco azul", null, "hueco rosado"},
                                          {null, "huecoRellenoFicha verde rosado", "ficha azul", null},
                                          {null, null, null, null},
                                          {null, null, null, null}};
            assertArrayEquals(expectedAnswer2, square.getBoard());
            square.move('u');
            fail("Did not threw exception");
        }
        catch (SquareException e){
            assertEquals(e.getMessage(), SquareException.PLAYER_LOST);
        }
    }

    @Test
    void shouldMoveDownAndWin1() {
        try {
            square = new Square("2", "2");
            String[][] initialBoard = {{"ficha azul", "ficha verde"},
                                       {"hueco azul", "hueco verde"}};
            square.setBoard(initialBoard);
            square.move('d');
            String[][] expectedAnswer = {{null, null}, {"huecoRelleno azul", "huecoRelleno verde"}};
            assertArrayEquals(expectedAnswer, square.getBoard());
        }
        catch (SquareException e){
            assertEquals(SquareException.PLAYER_WON, e.getMessage());
        }
    }

    @Test
    void shouldMoveDown2() {
        try {
            square = new Square("4", "7");
            String[][] initialBoard = {{"ficha amarillo", "ficha azul", "ficha rosado", "ficha cafe"},
                                       {null, null, "ficha verde" ,"hueco cafe"},
                                       {"hueco amarillo", "ficha gris", "hueco verde", "ficha morado"},
                                       {"hueco azul", "hueco gris", "hueco rosado", "hueco morado"}};
            square.setBoard(initialBoard);
            square.move('d');
            String[][] expectedAnswer = {{null, null, null, null},
                                         {"ficha amarillo", "ficha azul", "ficha rosado", "huecoRelleno cafe"},
                                         {"hueco amarillo", null, "huecoRelleno verde", null},
                                         {"hueco azul", "huecoRelleno gris", "hueco rosado", "huecoRelleno morado"},};
            assertArrayEquals(expectedAnswer, square.getBoard());
            square.move('d');
            String[][] expectedAnswer1 = {{null, null, null, null},
                                          {null, null, null, "huecoRelleno cafe"},
                                          {"huecoRelleno amarillo", "ficha azul", "huecoRellenoFicha verde rosado", null},
                                          {"hueco azul", "huecoRelleno gris", "hueco rosado", "huecoRelleno morado"}};
            assertArrayEquals(expectedAnswer1, square.getBoard());
            square.move('d');
            String[][] expectedAnswer2 = {{null, null, null, null},
                                          {null, null, null, "huecoRelleno cafe"},
                                          {"huecoRelleno amarillo", null, "huecoRelleno verde", null},
                                          {"hueco azul", "huecoRellenoFicha gris azul", "huecoRelleno rosado", "huecoRelleno morado"}};
            assertArrayEquals(expectedAnswer2, square.getBoard());
        }
        catch (SquareException e){
            fail("threw exception");
        }
    }

    @Test
    void shouldMoveDownAndLose1() {
        try {
            square = new Square("2", "2");
            String[][] initialBoard = {{"ficha verde", "ficha azul"},
                                       {"hueco azul", "hueco verde"}};
            square.setBoard(initialBoard);
            square.move('d');
            fail("Did not threw exception");
        }
        catch (SquareException e){
            assertEquals(e.getMessage(), SquareException.PLAYER_LOST);
        }
    }

    @Test
    void shouldMoveDownAndLose2() {
        try {
            square = new Square("4", "3");
            String[][] initialBoard = {{null, "ficha rosado", "ficha azul", null},
                                       {null, "ficha verde", null,null},
                                       {null, "hueco verde", null, null},
                                       {null, "hueco azul", null, "hueco rosado"}};
            square.setBoard(initialBoard);
            square.move('D');
            String[][] expectedAnswer1 = {
                    {null, null, null, null},
                    {null, "ficha rosado", "ficha azul",null},
                    {null, "huecoRelleno verde", null, null},
                    {null, "hueco azul", null, "hueco rosado"}};
            assertArrayEquals(expectedAnswer1, square.getBoard());
            square.move('D');
            String[][] expectedAnswer2 = {
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, "huecoRellenoFicha verde rosado", "ficha azul", null},
                    {null, "hueco azul", null, "hueco rosado"}};
            assertArrayEquals(expectedAnswer2, square.getBoard());
            square.move('d');
            fail("Did not threw exception");
        }
        catch (SquareException e){
            assertEquals(e.getMessage(), SquareException.PLAYER_LOST);
        }
    }

    /*TEST MOVE RIGHT*/
    @Test
    void shouldMoveRightAndWin1() {
        try {
            square = new Square("2", "2");
            String[][] initialBoard = {{"ficha azul", "hueco azul"},
                                       {"ficha verde", "hueco verde"}};
            square.setBoard(initialBoard);
            square.move('R');
            String[][] expectedAnswer = {{null, "huecoRelleno azul"},
                                         {null, "huecoRelleno verde"}};
            assertArrayEquals(expectedAnswer, square.getBoard());
        }
        catch (SquareException e){
            assertEquals(SquareException.PLAYER_WON, e.getMessage());
        }
    }

    @Test
    void shouldMoveRight2() {
        try {
            square = new Square("4", "7");
            String[][] initialBoard = {{"ficha azul", "ficha gris", "hueco gris", null},
                                       {"hueco azul", "ficha verde", "hueco verde", null},
                                       {"ficha amarillo", "ficha morado", "hueco morado", "hueco amarillo"},
                                       {"ficha cafe", "hueco cafe", "ficha verde", "hueco verde"}};
            square.setBoard(initialBoard);
            square.move('R');
            String[][] expectedAnswer = {{null, "ficha azul", "huecoRelleno gris", null},
                                         {"hueco azul", null, "huecoRelleno verde", null},
                                         {null, "ficha amarillo", "huecoRelleno morado", "hueco amarillo"},
                                         {null, "huecoRelleno cafe", null, "huecoRelleno verde"}};
            assertArrayEquals(expectedAnswer, square.getBoard());
            square.move('R');
            String[][] expectedAnswer1 = {{null, null, "huecoRellenoFicha gris azul", null},
                                          {"hueco azul", null, "huecoRelleno verde", null},
                                          {null, null, "huecoRellenoFicha morado amarillo", "hueco amarillo"},
                                          {null, "huecoRelleno cafe", null, "huecoRelleno verde"}};
            assertArrayEquals(expectedAnswer1, square.getBoard());
            square.move('R');
            String[][] expectedAnswer2 = {{null, null, "huecoRelleno gris", "ficha azul"},
                                          {"hueco azul", null, "huecoRelleno verde", null},
                                          {null, null, "huecoRelleno morado", "huecoRelleno amarillo"},
                                          {null, "huecoRelleno cafe", null, "huecoRelleno verde"}};
            assertArrayEquals(expectedAnswer2, square.getBoard());
        }
        catch (SquareException e){
            fail("threw exception");
        }
    }


    /*TEST MOVE LEFT*/
    @Test
    void shouldMoveLeftAndWin1() {
        try {
            square = new Square("2", "2");
            String[][] initialBoard = {{"hueco azul", "ficha azul"},
                                       {"hueco verde", "ficha verde"}};
            square.setBoard(initialBoard);
            square.move('L');
            String[][] expectedAnswer = {{"huecoRelleno azul", null},
                                         {"huecoRelleno verde", null}};
            assertArrayEquals(expectedAnswer, square.getBoard());
        }
        catch (SquareException e){
            assertEquals(SquareException.PLAYER_WON, e.getMessage());
        }
    }


    @Test
    void shouldMoveLeft2() {
        try {
            square = new Square("4", "7");
            String[][] initialBoard = {{null, "hueco gris", "ficha gris", "ficha azul"},
                                       {null, "hueco verde", "ficha verde", "hueco azul"},
                                       {"hueco amarillo", "hueco morado", "ficha morado", "ficha amarillo"},
                                       {"hueco verde", "ficha verde", "hueco cafe", "ficha cafe"}};
            square.setBoard(initialBoard);
            square.move('L');
            String[][] expectedAnswer = {{null, "huecoRelleno gris", "ficha azul", null},
                                         {null, "huecoRelleno verde", null, "hueco azul"},
                                         {"hueco amarillo", "huecoRelleno morado", "ficha amarillo", null},
                                         {"huecoRelleno verde", null, "huecoRelleno cafe", null}};
            assertArrayEquals(expectedAnswer, square.getBoard());
            square.move('L');
            String[][] expectedAnswer1 = {{null, "huecoRellenoFicha gris azul", null, null},
                                          {null, "huecoRelleno verde", null, "hueco azul"},
                                          {"hueco amarillo", "huecoRellenoFicha morado amarillo", null, null},
                                          {"huecoRelleno verde", null, "huecoRelleno cafe", null}};
            assertArrayEquals(expectedAnswer1, square.getBoard());
            square.move('L');
            String[][] expectedAnswer2 = {{"ficha azul", "huecoRelleno gris", null, null},
                                          {null, "huecoRelleno verde", null, "hueco azul"},
                                          {"huecoRelleno amarillo", "huecoRelleno morado", null, null},
                                          {"huecoRelleno verde", null, "huecoRelleno cafe", null}};
            assertArrayEquals(expectedAnswer2, square.getBoard());
        }
        catch (SquareException e){
            fail("threw exception");
        }
    }


}