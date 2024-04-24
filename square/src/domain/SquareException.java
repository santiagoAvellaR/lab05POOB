package src.domain;

public class SquareException extends Exception{
    public static final String INVALID_SIZE = "El tamaño es invalido";
    public static final String MAXIMUM_SIZE_EXCEEDED = "El tamaño tiene que ser menor a x y mayor a 1";
    public static final String INVALID_NUMBER_HOLES = "El numero de huecos es invalido";
    public static final String MAXIMUM_NUMBER_HOLES_EXCEEDED = "El numero de huecos tiene que ser menor a x y mayor a 1";
    public SquareException(String message){
        super(message);
    }
}
