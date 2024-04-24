package src.domain;

public class SquareException extends Exception{
    public static final String INVALID_SIZE = "El tamaño es invalido";
    public static final String MAXIMUM_SIZE_EXCEEDED = "El tamaño tiene que ser menor a x y mayor a 1";
    public static final String INVALID_NUMBER_HOLES = "El numero de huecos es invalido";
    public static final String MAXIMUM_NUMBER_HOLES_EXCEEDED = "El numero de huecos tiene que ser menor a x y mayor a 1";
    public static final String INVALID_COLOR = "El color es invalido, seleccione nuevamente";
    public static final String EMPTY_SQUARE = "La casilla seleccionada no tiene ficha o hueco";
    public static final String GAME_END = "El juego ha terminado";

    public SquareException(String message){
        super(message);
    }
}
