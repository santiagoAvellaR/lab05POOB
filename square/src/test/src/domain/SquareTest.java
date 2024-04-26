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
            String[][] initialBoard = {{"ficha azul", "ficha gris", "ficha rosado", "ficha morado"},
                                       {"hueco azul", "hueco verde", "ficha verde", "hueco cafe"},
                                       {"ficha amarillo", "hueco morado", "hueco gris", "hueco rosado"},
                                       {"ficha cafe", "ficha azul", "ficha verde", "ficha amarillo"}};
            square.setBoard(initialBoard);
            square.move('R');
            String[][] expectedAnswer = {{"ficha azul", "ficha gris", "ficha rosado", "ficha morado"},
                                         {null, "hueco verde", "fichaRelleno verde", "hueco cafe"},
                                         {"ficha amarillo", "huecoRelleno morado", "huecoRelleno gris", "huecoRelleno rosado"},
                                         {"ficha cafe", "ficha azul", "ficha verde", "ficha amarillo"}};
            assertArrayEquals(expectedAnswer, square.getBoard());
            square.move('R');
            String[][] expectedAnswer1 = {{"hueco azul", "fichaRelleno gris", "ficha rosado", "fichaRelleno morado"},
                                          {null, "hueco verde", "fichaRelleno verde", "hueco cafe"},
                                          {"ficha amarillo", null, "huecoRelleno morado", "huecoRelleno gris"},
                                          {"ficha cafe", "ficha azul", "ficha verde", "ficha amarillo"}};
            assertArrayEquals(expectedAnswer1, square.getBoard());
            square.move('R');
            String[][] expectedAnswer2 = {{"hueco azul", "fichaRelleno gris", "ficha rosado", "fichaRelleno morado"},
                                          {null, "hueco verde", "fichaRelleno verde", "hueco cafe"},
                                          {"ficha amarillo", null, null, "huecoRelleno morado"},
                                          {"ficha cafe", "ficha azul", "ficha verde", "ficha amarillo"}};
            assertArrayEquals(expectedAnswer2, square.getBoard());
        }
        catch (SquareException e){
            assertEquals(SquareException.PLAYER_WON, e.getMessage());
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
            String[][] initialBoard = {{"ficha azul", "ficha gris", "ficha rosado", "ficha morado"},
                    {"hueco azul", "hueco verde", "ficha verde", "hueco cafe"},
                    {"ficha amarillo", "hueco morado", "hueco gris", "hueco rosado"},
                    {"ficha cafe", "ficha azul", "ficha verde", "ficha amarillo"}};
            square.setBoard(initialBoard);
            square.move('L');
            String[][] expectedAnswer = {{"fichaRelleno azul", "fichaRelleno gris", "ficha rosado", "fichaRelleno morado"},
                    {null, "hueco azul", "fichaRelleno verde", "hueco cafe"},
                    {"ficha amarillo", "hueco morado", "hueco gris", "hueco rosado"},
                    {"ficha cafe", "ficha azul", "ficha verde", "ficha amarillo"}};
            assertArrayEquals(expectedAnswer, square.getBoard());
            square.move('L');
            String[][] expectedAnswer1 = {{"fichaRelleno azul", "fichaRelleno gris", "ficha rosado", "fichaRelleno morado"},
                    {"hueco azul", "fichaRelleno verde", "hueco cafe", null},
                    {"ficha amarillo", "hueco morado", "hueco gris", "hueco rosado"},
                    {"ficha cafe", "ficha azul", "ficha verde", "ficha amarillo"}};
            assertArrayEquals(expectedAnswer1, square.getBoard());
            square.move('L');
            String[][] expectedAnswer2 = {{"fichaRelleno azul", "fichaRelleno gris", "ficha rosado", "fichaRelleno morado"},
                    {"hueco azul", "fichaRelleno verde", "hueco cafe", null},
                    {"ficha amarillo", "hueco morado", null, "huecoRelleno gris"},
                    {"ficha cafe", "ficha azul", "ficha verde", "ficha amarillo"}};
            assertArrayEquals(expectedAnswer2, square.getBoard());
        }
        catch (SquareException e){
            fail("threw exception");
        }
    }

}