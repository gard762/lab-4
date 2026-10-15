package exceptions;

public class FigureFileFormatException extends Exception{
    public FigureFileFormatException(String message, Throwable cause){
        super(message, cause);
    }

    public FigureFileFormatException(String message){
        super(message);
    }
}